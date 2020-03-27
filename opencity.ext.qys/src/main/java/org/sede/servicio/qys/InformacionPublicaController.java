package org.sede.servicio.qys;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.anotaciones.NoCache;
import org.sede.core.anotaciones.OpenData;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.PlantillaHTML;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.utils.ConvertDate;
import org.sede.core.utils.Funciones;
import org.sede.core.utils.Propiedades;
import org.sede.core.validator.Recaptcha;
import org.sede.servicio.ModelAttr;
import org.sede.servicio.citaprevia.CitaPreviaController;
import org.sede.servicio.citaprevia.entity.Agenda;
import org.sede.servicio.citaprevia.entity.Cita;
import org.sede.servicio.qys.dao.QySDao;
import org.sede.servicio.qys.entity.Request;
import org.sede.servicio.qys.entity.SolicitudInformacionPublica;
import org.sede.servicio.qys.entity.UtilsQyS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;

/**
 * Controlador informacion publica
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Controller
@Transactional(ConfigQys.TM)
@RequestMapping(value = InformacionPublicaController.MAPPING, method = RequestMethod.GET)
@PlantillaHTML("portal/transparencia")
public class InformacionPublicaController {
	
	/**
	 *  Variable tipoSip
	 */
	public static final Map<Integer, String> tipoSip = new HashMap<Integer, String>();
    static {
    	tipoSip.put(0, "Sin asignar");
		tipoSip.put(1, "Información institucional y organizativa");
		tipoSip.put(2, "Planes y programas operativos");
		tipoSip.put(3, "Normativa");
		tipoSip.put(4, "Información económica");
		tipoSip.put(5, "Ayudas y subvenciones");
		tipoSip.put(6, "Urbanismo");
		tipoSip.put(7, "Contratación administrativa");
		tipoSip.put(8, "Medio Ambiente");
		tipoSip.put(9, "Recursos humanos");
		tipoSip.put(10, "Relaciones con la ciudadanía y participación");
		tipoSip.put(11, "Otros");

	}
	
    /**
	 *  Initial
	 */
	@Autowired
	private QySDao dao;
	
	/**
	 *  Initial
	 */
	@Autowired
	private CitaPreviaController cita;
	
	/**
	 *  logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(InformacionPublicaController.class);
	/**
	 *  Variable mapping del servicio
	 */
	public static final String MAPPING = "servicio/informacion-publica";
	/**
	 *  Variable mapping del formulario
	 */
	public static final String MAPPING_FORM = MAPPING + "/formulario";
	/**
	 *  Variable mapping del detalle
	 */
	public static final String MAPPING_DETALLE = MAPPING + "/detalle";
	
	/**
	 * Metodo crear
	 * 
	 * @param registro
	 * @return
	 * @throws ParseException
	 */
	@ResponseClass(value = SolicitudInformacionPublica.class)
	@RequestMapping(method = RequestMethod.POST, consumes = { MimeTypes.JSON,
            MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Permisos(Permisos.NEW)
	public @ResponseBody ResponseEntity<?> apiCrear(
	        @RequestBody SolicitudInformacionPublica registro) throws ParseException {
		try {
		    Set<ConstraintViolation<Object>> errores = dao.validarSolicitudInformacion(registro);
		    if (!errores.isEmpty()) {
		        return Funciones.generarMensajeError(errores);
		    }
		    boolean enviarCapaz = false;
		    if (registro.getEsConsultaExpediente() != null && registro.getEsConsultaExpediente() && StringUtils.isNotEmpty(registro.getTimestamp())) {
		    	Date fecha = ConvertDate.string2Date(registro.getTimestamp(), ConvertDate.DATETIME_FORMAT);
				Calendar c = Calendar.getInstance();
				c.setTime(fecha);
				Cita datosCita = registro.convertirSolicitudACita();
				datosCita.setHora(ConvertDate.date2String(fecha, ConvertDate.HOUR_FORMAT));
		    	ResponseEntity<?> respuestaCita = cita.apiCrear(registro.getIdAgenda(), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR), "", "", "N", datosCita);
		    	if (!respuestaCita.getStatusCode().is2xxSuccessful()) {
		    		return respuestaCita;
		    	} else {
		    		registro.setIdCita(new BigDecimal(((Cita)respuestaCita.getBody()).getId()));
		    		registro.setLocalizador(((Cita)respuestaCita.getBody()).getLocalizador());
		    		registro.setAgenda((Agenda)cita.apiDetalle(registro.getIdAgenda()).getBody());
		    		enviarCapaz = true;
		    	}
		    }
		    Request request = registro.convertirSolicitudARequest();
		    try {
		    	request = dao.crear(request, UtilsQyS.OPCREAR, /*usuarioAdmin*/null, /*gczUsuario*/null, /*userId*/null);
		    	registro.setId(request.getService_request_id());
		    	if (enviarCapaz) {
		    		dao.enviarCapaz(registro);
		    	} else {
		    		if (StringUtils.isNotEmpty(request.getEmail())) {
						try {
							Funciones.sendMail("Solicitud:" + request.getService_request_id() , "Hemos recibido correctamente su petición. Puede hacer un seguimiento del estado de la gestión a través del siguiente enlace: "
									+ System.getProperty("line.separator")
									+ "https://www.zaragoza.es/sede/servicio/informacion-publica/" + request.getService_request_id() + "/" + request.getToken() + "/ "
									+ UtilsQyS.TEXTONOCONTESTAR, request.getEmail(), 
									"", "text/plain");
						} catch (Exception e) {
							logger.error("ERROR al enviar correo solicitud:{}", e.getMessage());
						}
					}
		    	}
		    	
				return ResponseEntity.ok(registro);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	    
	}
	
	
	/**
	 * Metodo nuevo formulario
	 * 
	 * @param dato
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, produces = {
	        MediaType.TEXT_HTML_VALUE, "*/*" })
	public String newForm(SolicitudInformacionPublica dato, BindingResult bindingResult,
	        Model model) {
		model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
	    model.addAttribute(ModelAttr.DATO, dato);
	    model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(new SolicitudInformacionPublica()));
	    return MAPPING_FORM;
	}
	
	/**
	 * Metodo crear
	 * 
	 * @param dato
	 * @param gRecaptchaResponse
	 * @param bindingResult
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = {
	        MediaType.TEXT_HTML_VALUE, "*/*" })
	public String crear(SolicitudInformacionPublica dato,
			@RequestParam(name = "g-recaptcha-response", defaultValue = "") String gRecaptchaResponse,
			BindingResult bindingResult, Model model, RedirectAttributes attr, HttpServletRequest request) throws ParseException {
		if (Recaptcha.verify(gRecaptchaResponse, request)) {
		    ResponseEntity<?> resultado = apiCrear(dato);
		    if (resultado.getStatusCode().is2xxSuccessful()) {
		    	attr.addFlashAttribute(ModelAttr.DATO, dato);
		    	attr.addFlashAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.OK.value(), "Registro creado correctamente"));
		    	attr.addFlashAttribute(ModelAttr.REGISTRO, resultado);
		        return "redirect:/" + MAPPING_DETALLE;
		    } else {
		    	model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
		        model.addAttribute(ModelAttr.MENSAJE, resultado.getBody());
		        model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(dato));
		        model.addAttribute(ModelAttr.DATO, dato);
		        return MAPPING_FORM;
		    }
		} else {
			model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
			model.addAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.BAD_REQUEST.value(), "Debe marcar la casilla para indicar que no es un robot"));
			model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(dato));
			model.addAttribute(ModelAttr.DATO, dato);
			return MAPPING_FORM;
		} 
	    
	}
	
	/**
	 * Metodo detalle por token
	 * 
	 * @param identifier
	 * @param token
	 * @param model
	 * @param attr
	 * @return
	 * @throws SQLException
	 */
	@OpenData
	@RequestMapping(value = "{identifier}/{token}/", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String detalleByToken(@PathVariable BigDecimal identifier,
			@PathVariable String token, Model model,
			RedirectAttributes attr) throws SQLException {
		model.addAttribute(ModelAttr.REGISTRO, dao.findByToken(identifier, token));
		return QysController.MAPPING + "/token";
	}
	
	/**
	 * Metodo detalle
	 * 
	 * @return
	 */
	@NoCache
	@RequestMapping(value = "/detalle", method = RequestMethod.POST, produces = {
	        MediaType.TEXT_HTML_VALUE, "*/*" })
	public String detalle() {
		return MAPPING_DETALLE;
		
	}
}