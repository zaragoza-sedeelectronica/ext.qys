package org.sede.servicio.citaprevia;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;
import org.sede.core.anotaciones.Cache;
import org.sede.core.anotaciones.Description;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.anotaciones.Gcz;
import org.sede.core.anotaciones.NoCache;
import org.sede.core.anotaciones.OpenData;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.PlantillaHTML;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.anotaciones.TestValue;
import org.sede.core.rest.CheckeoParametros;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.utils.ConvertDate;
import org.sede.core.utils.Funciones;
import org.sede.core.utils.Propiedades;
import org.sede.core.validator.Recaptcha;
import org.sede.servicio.ModelAttr;
import org.sede.servicio.citaprevia.dao.CitaPreviaGenericDAO;
import org.sede.servicio.citaprevia.entity.Agenda;
import org.sede.servicio.citaprevia.entity.Cita;
import org.sede.servicio.citaprevia.entity.CitaPrevia;
import org.sede.servicio.citaprevia.entity.CitaPreviaException;
import org.sede.servicio.citaprevia.entity.Dias;
import org.sede.servicio.citaprevia.entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.googlecode.genericdao.search.SearchResult;
import com.ibm.icu.util.Calendar;

@Controller
@Gcz(servicio="CITAPREVIA",seccion="CITAPREVIA")
@Description("Ayuntamiento: Cita Previa")
@RequestMapping(value = "/" + CitaPreviaController.MAPPING)
@Transactional(Esquema.TMINTRA)
@PlantillaHTML("portal/tramites-servicios")
public class CitaPreviaController {
	public static final String SERVICIO = "cita-previa";
	public static final String MAPPING = "servicio/" + SERVICIO;
	@Autowired
	private CitaPreviaGenericDAO dao;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String redirect() {
		return "redirect:" + SERVICIO + "/";
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String home(Model model,
			@ModelAttribute CitaPrevia dato,
			@RequestParam(name = CheckeoParametros.PARAMSTART, defaultValue = CheckeoParametros.START) int start, 
    		@RequestParam(name = CheckeoParametros.PARAMROWS, defaultValue = CheckeoParametros.ROWS) int rows,
    		@RequestParam(name = CheckeoParametros.PARAMSORT, defaultValue = "") String sort,
    		@RequestParam(name = CheckeoParametros.PARAMQUERYSOLR, defaultValue = "") String searchExpression,
    		@RequestParam(name = CheckeoParametros.PARAMFQ, defaultValue = "") String fq) {
		model.addAttribute(ModelAttr.RESULTADO, apiListar(dato, start, rows, sort, searchExpression));
		return MAPPING + "/index";
	}
	
	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@Description("Listado de servicios que disponen de agendas")
	@ResponseClass(value = CitaPrevia.class, entity = SearchResult.class)
	@RequestMapping(method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiListar(
			@ModelAttribute CitaPrevia dato,
			@RequestParam(name = CheckeoParametros.PARAMSTART, defaultValue = CheckeoParametros.START) int start, 
    		@RequestParam(name = CheckeoParametros.PARAMROWS, defaultValue = CheckeoParametros.ROWS) int rows, 
    		@RequestParam(name = CheckeoParametros.PARAMSORT, defaultValue = "") String sort, 
    		@RequestParam(name = CheckeoParametros.PARAMQUERY, defaultValue = "") String searchExpression
    		) {
		try {
			List<CitaPrevia> citas = dao.obtenerCentros();
			
			SearchResult<CitaPrevia> resultado = new SearchResult<CitaPrevia>();
			resultado.setResult(citas);
			resultado.setRows(citas.size());
			resultado.setTotalCount(citas.size());
			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
    }
	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@ResponseClass(CitaPrevia.class)
	@Description("Detalle de una agenda")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV})
	public @ResponseBody ResponseEntity<?> apiDetalle(@PathVariable @TestValue("741") Integer id) {
		try {
			List<Agenda> agenda = dao.obtenerAgenda(id);
			if (agenda != null && !agenda.isEmpty()) {
				return ResponseEntity.ok(agenda.get(0));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se encontró ninguna agenda activa con el identificador indicado")); 
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@ResponseClass(CitaPrevia.class)
	@Description("Listado de turnos")
	@RequestMapping(value = "/{id}/{day}-{month}-{year}", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV})
	public @ResponseBody ResponseEntity<?> apiDetalleTurno(@PathVariable Integer id,
			@PathVariable Integer day, 
			@PathVariable Integer month,
			@PathVariable Integer year) {
		try {
			List<Turno> horario = dao.obtenerTurnos(id,ConvertDate.string2Date(day + "-" + month + "-" + year, ConvertDate.DATE_FORMAT));
			
			SearchResult<Turno> resultado = new SearchResult<Turno>();
			resultado.setRows(horario.size());
			resultado.setTotalCount(horario.size());
			resultado.setResult(horario);
			return ResponseEntity.ok(resultado);
		} catch (CitaPreviaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getNivel(), e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	
	@OpenData
	@NoCache
	@ResponseClass(value = Dias.class, entity = SearchResult.class)
	@Description("Próximos días que tienen huecos disponibles")
	@RequestMapping(value = "/{id}/next-days", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV})
	public @ResponseBody ResponseEntity<?> apiNextDays(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(dao.obtenerHuecosAgenda(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	
	@ResponseClass(value = Cita.class)
	@RequestMapping(value="/{id}/{day}-{month}-{year}", method = RequestMethod.POST, consumes = { MimeTypes.JSON,
			MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Permisos(Permisos.NEW)
	public @ResponseBody ResponseEntity<?> apiCrear(@PathVariable Integer id,
			@PathVariable Integer day, 
			@PathVariable Integer month,
			@PathVariable Integer year,
			@RequestParam(name = "borrar", defaultValue = "") String borrar,
			@RequestParam(name = "crear", defaultValue = "") String crear,
			@RequestParam(name = "v", defaultValue = "") String v,
			@RequestBody Cita cita) throws ParseException {
		cita.setId_agenda(id);
		cita.setFecha(new java.sql.Date(ConvertDate.string2Date(day + "-" + month + "-" + year, ConvertDate.DATE_FORMAT).getTime()));
		Set<ConstraintViolation<Object>> errores = dao.validar(cita);
		if (!errores.isEmpty()) {
			return Funciones.generarMensajeError(errores);
		}
		try {
			boolean validarNif = true;
			if (!"".equals(v)) {
				validarNif = false;
				if (!"".equals(borrar)) {
					dao.borrarCitasAsociadas(cita.getNif(), id);
				}
			}
			Cita retorno = dao.nuevaCita(cita, validarNif);
			dao.enviaEmail(retorno); 
//				if(id == 701){
//		        	dao.enviaPost(cita);
//		        }
	    	return ResponseEntity.ok(retorno);
		} catch (SQLException e) {
            switch (((SQLException)e.getCause()).getErrorCode()) {
                case CitaPreviaException.ERR_20001:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "citaprevia.citas.error.alta.ambito"));
                case CitaPreviaException.ERR_20002:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "No es posible a\u00F1adir nuevas citas para la fecha y hora seleccionadas puesto que se supera el n\u00FAmero m\u00E1ximo de citas de la agenda."));
                case CitaPreviaException.ERR_20003:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "La fecha seleccionada para la cita se corresponde con un d\u00EDa festivo"));
                case CitaPreviaException.ERR_20004:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "La hora introducida est\u00E1 fuera del horario"));
                case CitaPreviaException.ERR_20005:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "La fecha y la hora seleccionadas para la cita no pertenecen a ning\u00FAn turno establecido para la agenda"));
                case CitaPreviaException.ERR_20006:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "La hora seleccionada para la cita no se corresponde con el per\u00EDodo o duraci\u00F3n de las citas establecido para la agenda"));
                case CitaPreviaException.ERR_20007:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "Ya existe una cita dada de alta para ese DNI"));
                case CitaPreviaException.ERR_20008:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ((SQLException)e.getCause()).getErrorCode(), "Con el fin de atender al mayor n\u00FAmero de ciudadanos y aminorar la lista de espera, nos vemos obligados a limitar a 4 el n\u00FAmero de reservas por d\u00EDa. Gracias por su atención"));
                default:
                	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
            }
		} catch (AddressException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		} catch (SendFailedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		} catch (MessagingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	
	@NoCache
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String detalle(@PathVariable Integer id, Cita dato, Model model) {
		model.addAttribute(ModelAttr.DATO, dato);
		model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(new Cita()));	
		model.addAttribute("agenda", apiDetalle(id));
		ResponseEntity<?> dias = apiNextDays(id);
		model.addAttribute("huecos", dao.tieneHuecosDisponibles(dias));
		model.addAttribute(ModelAttr.RESULTADO, dias);
		return MAPPING + "/formulario";
	}
	
	@NoCache
	@RequestMapping(value = "/{identificador}/save", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String crear(@PathVariable Integer identificador,
			@RequestParam(name = "v", defaultValue = "") String v,
			@RequestParam(name = "borrar", defaultValue = "") String borrar,
			@RequestParam(name = "crear", defaultValue = "") String crear,
			Cita dato, BindingResult bindingResult, Model model, RedirectAttributes attr) throws ParseException {
		Date fecha = ConvertDate.string2Date(dato.getTimestamp(), ConvertDate.DATETIME_FORMAT);
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		dato.setHora(ConvertDate.date2String(fecha, ConvertDate.HOUR_FORMAT));
		ResponseEntity<?> resultado = apiCrear(identificador, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR), borrar, crear, v, dato);
		if (resultado.getStatusCode().is2xxSuccessful()) {
			attr.addFlashAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.OK.value(), "Registro creado correctamente"));
			attr.addFlashAttribute(ModelAttr.REGISTRO, resultado);
			attr.addFlashAttribute("agenda", apiDetalle(identificador));
			attr.addFlashAttribute(ModelAttr.DATO, resultado.getBody());
			return "redirect:/" + MAPPING + "/confirmacion";
		} else {
			Mensaje error = (Mensaje) resultado.getBody();
			if (error.getCode() != null && error.getCode() == CitaPreviaException.ERR_20007) {
				model.addAttribute("error_nif", true);
			}
			model.addAttribute(ModelAttr.MENSAJE, resultado.getBody());
			model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(dato));
			model.addAttribute(ModelAttr.DATO, dato);
			model.addAttribute("agenda", apiDetalle(identificador));
			ResponseEntity<?> dias = apiNextDays(identificador);
			model.addAttribute("huecos", dao.tieneHuecosDisponibles(dias));
			model.addAttribute(ModelAttr.RESULTADO, dias);
			return MAPPING + "/formulario";
		}
	}
	@NoCache
	@RequestMapping(value = "/confirmacion", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String confirmacion() {
		return MAPPING + "/confirmacion";
	}
	
	
	@NoCache
	@RequestMapping(value = "/consulta", method = {RequestMethod.GET}, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String consulta(Model model, Cita dato) {
		model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
		model.addAttribute(ModelAttr.DATO, dato);
		return MAPPING + "/consulta";
	}
	
	@NoCache
	@RequestMapping(value = "/consulta-results", method = {RequestMethod.POST}, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String consultaResults(@RequestParam(name = "g-recaptcha-response", defaultValue = "") String gRecaptchaResponse,
			HttpServletRequest request, Model model, Cita dato) throws CitaPreviaException, ParseException, SQLException {
		if (Recaptcha.verify(gRecaptchaResponse, request)) {
			model.addAttribute(ModelAttr.DATO, dato);
			if (StringUtils.isNotEmpty(dato.getNombre())
					&& StringUtils.isNotEmpty(dato.getApellidos())
					&&  (StringUtils.isNotEmpty(dato.getNif()) || StringUtils.isNotEmpty(dato.getExpediente()))) { 
				model.addAttribute(ModelAttr.RESULTADO, dao.listarCitas(dato, false));
				return MAPPING + "/consulta-resultado";
			} else {
				model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Mensaje(HttpStatus.FORBIDDEN.value(), "Debe introducir nombre, apellidos y DNI o Expediente")));
				model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
				model.addAttribute(ModelAttr.DATO, dato);
				return MAPPING + "/consulta";
			}
		} else {
			model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Mensaje(HttpStatus.FORBIDDEN.value(), "Debe marcar la casilla para indicar que no es un robot")));
			model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
			model.addAttribute(ModelAttr.DATO, dato);
			return MAPPING + "/consulta";
		}		
	}
	
	
	@NoCache
	@RequestMapping(value = "/detalle", method = {RequestMethod.GET}, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String detalleCita(Model model, Cita dato) throws CitaPreviaException, ParseException, SQLException {
		
			List<Cita> lista = dao.listarCitas(dato, false);
			Cita c = lista.get(0);
			model.addAttribute("agenda", apiDetalle(c.getId_agenda()));
			model.addAttribute(ModelAttr.DATO, c);
			model.addAttribute("detalleCita", true);
			return MAPPING + "/confirmacion";
	}
	
	@NoCache
	@RequestMapping(value = "/anular", method = {RequestMethod.GET}, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String anularCita(Model model, Cita dato) throws CitaPreviaException, ParseException, SQLException {
	
		List<Cita> lista = dao.listarCitas(dato, false);
		Cita c = lista.get(0);
		dao.eliminarCita(c.getId());
		if (c.getId_agenda().intValue() == 1021) {
			try {
				Funciones.sendMail("Cita consulta sótano anulada. " + c.getHora() + " " + ConvertDate.date2String(c.getFecha(), ConvertDate.DATE_FORMAT), "" + c.getNombre() + " " + c.getNif() + " ha anulado la cita que tenía.", Propiedades.getCapazErrorMail(), "", "text");
			} catch (Exception e) {
				
			}
		}
		
		dato.setHora(null);
		dato.setFecha(null);
		
		model.addAttribute(ModelAttr.DATO, dato);
		model.addAttribute(ModelAttr.RESULTADO, dao.listarCitas(dato, false));
		model.addAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.OK.value(), "Cita anulada correctamente"));
		return MAPPING + "/consulta-resultado";
	}
}
