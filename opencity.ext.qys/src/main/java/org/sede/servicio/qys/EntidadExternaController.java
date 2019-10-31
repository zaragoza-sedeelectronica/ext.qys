package org.sede.servicio.qys;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.cxf.jaxrs.ext.search.SearchParseException;
import org.sede.core.anotaciones.Cache;
import org.sede.core.anotaciones.Description;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.anotaciones.Fiql;
import org.sede.core.anotaciones.Gcz;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.dao.SearchFiql;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.utils.Funciones;
import org.sede.servicio.qys.dao.EntidadExternaGenericDAO;
import org.sede.servicio.qys.entity.EntidadExterna;
import org.sede.servicio.qys.entity.UtilsQyS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;

/**
 * Controlador entidades externas
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Gcz(servicio="TICKETING",seccion="REQUESTS")
@Controller
@Transactional(Esquema.TMTICKETING)
@RequestMapping(value = "/" + EntidadExternaController.MAPPING, method = RequestMethod.GET)
public class EntidadExternaController {

	/**
	 *  Variable servicio
	 */
    public static final String SERVICIO = "entidad-externa";
    /**
	 *  Variable mapping del servicio
	 */
    public static final String MAPPING = "servicio/quejas-sugerencias/" + SERVICIO;

    /**
	 * Variable fuente del mensaje
	 */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Intial
	 */
    @Autowired
    private EntidadExternaGenericDAO dao;


    /**
     * Metodo listar
     * 
     * @param search
     * @return
     * @throws SearchParseException
     */
    @Permisos(Permisos.DET)
    @Cache(Cache.DURACION_30MIN)
    @Description("Listado de entidades organizadoras")
    @ResponseClass(value = EntidadExterna.class, entity = SearchResult.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
    public @ResponseBody ResponseEntity<?> apiListar(@Fiql SearchFiql search) throws SearchParseException {
    	
    	String usuario = UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion());
		search.addCondition("user_id==" + usuario);
    	return ResponseEntity.ok(dao.searchAndCount(search.getConditions(EntidadExterna.class)));
    }

    /**
     * Metodo detalle entidad
     * 
     * @param id
     * @return
     */
    @Permisos(Permisos.DET)
    @Cache(Cache.DURACION_30MIN)
    @Description("Detalle de una entidad organizadora")
    @ResponseClass(EntidadExterna.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
    public @ResponseBody ResponseEntity<?> apiDetalleEntidad(@PathVariable BigDecimal id) {
        EntidadExterna registro = dao.find(id);
        BigDecimal usuario = new BigDecimal(UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion()));
        if (registro == null || !registro.getUser_id().equals(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("generic.notfound", null, LocaleContextHolder.getLocale())));
        } else {
            return ResponseEntity.ok(registro);
        }
    }

    /**
     * Metodo crear entidad
     * 
     * @param registro
     * @return
     */
    @Permisos(Permisos.NEW)
    @RequestMapping(method = RequestMethod.POST, consumes = { MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
    @Description("Crear entidad")
    @ResponseClass(value = EntidadExterna.class)
    public @ResponseBody ResponseEntity<?> apiCrear(@RequestBody EntidadExterna registro) {
        Set<ConstraintViolation<Object>> errores = dao.validar(registro);
        if (!errores.isEmpty()) {
            return Funciones.generarMensajeError(errores);
        } else {
        	registro.setUser_id(new BigDecimal(UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion())));
            dao.save(registro);
        }
        return ResponseEntity.ok(registro);
    }

    /**
     * Metodo modificar entidad externa
     * 
     * @param id
     * @param registro
     * @return
     */
    @Permisos(Permisos.MOD)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
    @Description("Modificar entidad")
    @ResponseClass(value = EntidadExterna.class)
    public @ResponseBody ResponseEntity<?> apiModificar(@PathVariable BigDecimal id, @RequestBody EntidadExterna registro) {
    	final BigDecimal usuario = new BigDecimal(UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion()));
        Set<ConstraintViolation<Object>> errores = dao.validar(registro);
        if (!errores.isEmpty()) {
            return Funciones.generarMensajeError(errores);
        } else if (usuario.equals(registro.getUser_id())) {
            dao.save(registro);
        }
        return ResponseEntity.ok(registro);
    }

}
