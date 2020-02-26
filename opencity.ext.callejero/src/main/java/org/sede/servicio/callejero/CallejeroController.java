package org.sede.servicio.callejero;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.cxf.jaxrs.ext.search.SearchParseException;
import org.sede.core.anotaciones.Cache;
import org.sede.core.anotaciones.Description;
import org.sede.core.anotaciones.Fiql;
import org.sede.core.anotaciones.Gcz;
import org.sede.core.anotaciones.Grafo;
import org.sede.core.anotaciones.NoCache;
import org.sede.core.anotaciones.OpenData;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.anotaciones.TestValue;
import org.sede.core.dao.EntidadBase;
import org.sede.core.dao.SearchFiql;
import org.sede.core.dao.VirtuosoDataManagement;
import org.sede.core.rest.Formato;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.rest.Peticion;
import org.sede.core.rest.view.TransformadorRdf;
import org.sede.core.utils.Funciones;
import org.sede.servicio.ModelAttr;
import org.sede.servicio.callejero.dao.CalleGenericDAO;
import org.sede.servicio.callejero.dao.MapeoSemanticoGenericDAO;
import org.sede.servicio.callejero.dao.PortaleroGenericDAO;
import org.sede.servicio.callejero.entity.Calle;
import org.sede.servicio.callejero.entity.MapeoSemanticoVia;
import org.sede.servicio.callejero.entity.Portalero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;

@Gcz(servicio="ORGA",seccion="CALLEJERO")
@Transactional(ConfigCallejero.TM)
@Controller
@RequestMapping(value = "/" + CallejeroController.MAPPING, method = RequestMethod.GET)
@Description("Callejero")
public class CallejeroController {
	private static final String SERVICIO = "callejero";
	public static final String MAPPING = "servicio/" + SERVICIO;
	private static final String MAPPING_FORM = MAPPING + "/formulario";
	private static final Logger logger = LoggerFactory.getLogger(CallejeroController.class);
	
	public static final String MAPPING_MAPEO_SEMANTICO = "servicio/mapeo-calle";

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	public CalleGenericDAO dao;
	@Autowired
	public PortaleroGenericDAO daoPortalero;
	@Autowired
	public MapeoSemanticoGenericDAO daoMapeoSemantico;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String redirect() {
		return "redirect:" + SERVICIO + "/";
	}
	
	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@Description("Listado de viales de Zaragoza")
	@ResponseClass(value = Calle.class, entity = SearchResult.class)
	@RequestMapping(method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiListar(
			@Fiql SearchFiql search
    		) throws SearchParseException  {
		
		return ResponseEntity.ok(dao.searchAndCount(search.getConditions(Calle.class)));
    }
	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@Description("Detalle de una vial de Zaragoza")
	@ResponseClass(Calle.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiDetalle(@PathVariable @TestValue("23220") Integer id) {
		Calle registro = dao.find(id);
		if (registro == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("generic.notfound", null, LocaleContextHolder.getLocale())));
		} else {
			return ResponseEntity.ok(registro);
		}
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String home(Model model, @Fiql SearchFiql search) throws SearchParseException  {
		model.addAttribute(ModelAttr.RESULTADO, apiListar(search));
		return MAPPING + "/index";
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String detalle(@PathVariable Integer id, Model model,
			HttpServletRequest request) {
		model.addAttribute(ModelAttr.REGISTRO, apiDetalle(id));
		return MAPPING + "/detalle";
	}
	
	
	@ResponseClass(value = Calle.class)
	@RequestMapping(method = RequestMethod.POST, consumes = { MimeTypes.JSON,
			MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Permisos(Permisos.NEW)
	public @ResponseBody ResponseEntity<?> apiCrear(
			@RequestBody Calle registro) {
		Set<ConstraintViolation<Object>> errores = dao.validar(registro);
		if (!errores.isEmpty()) {
			return Funciones.generarMensajeError(errores);
		}
		dao.save(registro);
		return ResponseEntity.ok(registro);
	}
	
	@ResponseClass(value = Calle.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {
			MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON,
			MimeTypes.XML })
	@Permisos(Permisos.MOD)
	public @ResponseBody ResponseEntity<?> apiModificar(@PathVariable Integer id,
			@RequestBody Calle registro) {
		ResponseEntity<?> resp = apiDetalle(id);
		if (resp.getStatusCode().is2xxSuccessful()) {
			Calle reg = (Calle)resp.getBody();
			EntidadBase.combinar(reg, registro);
			reg.setId(id);
			
			Set<ConstraintViolation<Object>> errores = dao.validar(registro);
			if (!errores.isEmpty()) {
				return Funciones.generarMensajeError(errores);
			}
			return ResponseEntity.ok(reg);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Mensaje) resp.getBody());
		}
	}

	@ResponseClass(value = Mensaje.class)
	@RequestMapping(value = "/{id}/remove", method = RequestMethod.DELETE, produces = { MimeTypes.JSON,
			MimeTypes.XML })
	@Permisos(Permisos.DEL)
	public @ResponseBody ResponseEntity<?> apiDelete(@PathVariable Integer id) {
		ResponseEntity<?> resp = apiDetalle(id);
		if (resp.getStatusCode().is2xxSuccessful()) {
			Calle registro = (Calle)resp.getBody();
	    	dao.remove(registro);
	    	return ResponseEntity.ok(new Mensaje(HttpStatus.OK.value(),
	    				"Registro eliminado"));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "Error al obtener el registro"));
		}
    	
	}
	
	
	
	@Permisos(Permisos.NEW)
	@RequestMapping(value = "/new", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String newForm(Calle dato, BindingResult bindingResult,
			Model model) {
		model.addAttribute(ModelAttr.DATO, dato);
		model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(new Calle()));
		return MAPPING_FORM;
	}

	@Permisos(Permisos.MOD)
	@NoCache
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String edit(@PathVariable Integer id, Calle dato,
			BindingResult bindingResult, Model model) {
		ResponseEntity<?> registro = apiDetalle(id);
		model.addAttribute(ModelAttr.DATO, registro.getBody());
		model.addAttribute(ModelAttr.REGISTRO, registro);
		return MAPPING_FORM;
	}

	@Permisos(Permisos.NEW)
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String crear(Calle dato, BindingResult bindingResult, Model model) {
		ResponseEntity<?> resultado = apiCrear(dato);
		if (resultado.getStatusCode().is2xxSuccessful()) {
			model.addAttribute(ModelAttr.DATO, dato);
			model.addAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.OK.value(), "Registro creado correctamente"));
			model.addAttribute(ModelAttr.REGISTRO, resultado);
		} else {
			model.addAttribute(ModelAttr.MENSAJE, resultado.getBody());
			model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(dato));
			model.addAttribute(ModelAttr.DATO, dato);
		}
		return MAPPING_FORM;
	}
	
	@Permisos(Permisos.MOD)
	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String modificar(@PathVariable Integer id, Calle dato,
			BindingResult bindingResult, Model model) {
		ResponseEntity<?> resultado = apiModificar(id, dato);
		if (resultado.getStatusCode().is2xxSuccessful()) {
			model.addAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.OK.value(), "Registro modificado correctamente"));
			model.addAttribute(ModelAttr.REGISTRO, resultado);
			model.addAttribute(ModelAttr.DATO, dato);
		} else {
			model.addAttribute(ModelAttr.MENSAJE, resultado.getBody());
			model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(dato));
			model.addAttribute(ModelAttr.DATO, dato);
		}
		
		return MAPPING_FORM;
	}
	@Permisos(Permisos.DEL)
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String eliminar(@PathVariable Integer id, Model model) throws SearchParseException  {
		ResponseEntity<?> resultado = apiDelete(id);
		if (resultado.getStatusCode().is2xxSuccessful()) {
			model.addAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.OK.value(), "Registro eliminado correctamente"));
		} else {
			model.addAttribute(ModelAttr.MENSAJE, resultado.getBody());
		}		
		return home(model, new SearchFiql());
	}
	
	@RequestMapping(value = "/virtuoso", method = RequestMethod.PUT, produces = {MimeTypes.JSON})
    @Permisos(Permisos.PUB)
    public @ResponseBody ResponseEntity<?> actualizarVirtuoso() {
		Peticion peticion = Funciones.getPeticion();
		Formato original = peticion.getFormato();
		try {
    		peticion.setCargandoEnVirtuoso(true);
    		peticion.setFormato(MimeTypes.TURTLE_FORMATO);
    		VirtuosoDataManagement.deleteGrafo(Calle.class.getAnnotation(Grafo.class).value());

    		cargarCalles(peticion);
    		
    		cargarPortales(peticion);
    		
    		cargarMapeos(peticion);
    		
			return ResponseEntity.ok(new Mensaje(HttpStatus.OK.value(), "Informacion actualizada correctamente"));
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(),
					e.getMessage()));
    	} finally {
    		peticion.setCargandoEnVirtuoso(false);
    		peticion.setFormato(original);
    	}
    }

	private void cargarMapeos(Peticion peticion) throws Exception {
		int rows = VirtuosoDataManagement.ROWS_CARGA;
		int maxrowscarga = VirtuosoDataManagement.TOTAL_CARGA;
		int i = rows;
		int start = 0;			
		while (i < maxrowscarga) {
			SearchFiql search = new SearchFiql();
			search.setStart(start);
			search.setRows(rows);
			List<MapeoSemanticoVia> lista = daoMapeoSemantico.search(search.getConditions(MapeoSemanticoVia.class));
			SearchResult<MapeoSemanticoVia> listado = new SearchResult<MapeoSemanticoVia>();
			listado.setResult(lista);
			i = i + rows;
			start = start + rows;
			if (!listado.getResult().isEmpty()) {
				TransformadorRdf transformador = new TransformadorRdf();
				StringBuilder respuesta = new StringBuilder();
				transformador.transformarObjeto(respuesta, listado, peticion, true, "");
				VirtuosoDataManagement.addElements(MapeoSemanticoVia.class.getAnnotation(Grafo.class).value(), respuesta.toString());
			} else {
				i = maxrowscarga;
			}
		}
	}

	private void cargarPortales(Peticion peticion) throws Exception {
		int rows = VirtuosoDataManagement.ROWS_CARGA;
		int maxrowscarga = VirtuosoDataManagement.TOTAL_CARGA;
		int i = rows;
		int start = 0;			
		while (i < maxrowscarga) {
			SearchFiql search = new SearchFiql();
			search.setStart(start);
			search.setRows(rows);
			List<Portalero> lista = daoPortalero.search(search.getConditions(Portalero.class));
			SearchResult<Portalero> listado = new SearchResult<Portalero>();
			listado.setResult(lista);
			i = i + rows;
			start = start + rows;
			if (!listado.getResult().isEmpty()) {
				TransformadorRdf transformador = new TransformadorRdf();
				StringBuilder respuesta = new StringBuilder();
				transformador.transformarObjeto(respuesta, listado, peticion, true, "");
				VirtuosoDataManagement.addElements(Portalero.class.getAnnotation(Grafo.class).value(), respuesta.toString());
			} else {
				i = maxrowscarga;
			}
		}
	}

	private void cargarCalles(Peticion peticion) throws Exception {
		int rows = VirtuosoDataManagement.ROWS_CARGA;
		int maxrowscarga = VirtuosoDataManagement.TOTAL_CARGA;
		int i = rows;
		int start = 0;			
		while (i < maxrowscarga) {
			SearchFiql search = new SearchFiql();
			search.setStart(start);
			search.setRows(rows);
			List<Calle> lista = dao.search(search.getConditions(Calle.class));
			SearchResult<Calle> listado = new SearchResult<Calle>();
			listado.setResult(lista);
			i = i + rows;
			start = start + rows;
			if (!listado.getResult().isEmpty()) {
				TransformadorRdf transformador = new TransformadorRdf();
				StringBuilder respuesta = new StringBuilder();
				transformador.transformarObjeto(respuesta, listado, peticion, true, "");
				VirtuosoDataManagement.addElements(Calle.class.getAnnotation(Grafo.class).value(), respuesta.toString());
			} else {
				i = maxrowscarga;
			}
		}
		
	}
	
}