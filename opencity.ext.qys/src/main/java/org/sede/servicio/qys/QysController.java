package org.sede.servicio.qys;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.search.SearchParseException;
import org.sede.core.anotaciones.Cache;
import org.sede.core.anotaciones.Description;
import org.sede.core.anotaciones.Fiql;
import org.sede.core.anotaciones.Gcz;
import org.sede.core.anotaciones.NoCache;
import org.sede.core.anotaciones.OpenData;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.PermisosUser;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.dao.JPAIgnoreTraversableResolver;
import org.sede.core.dao.SearchFiql;
import org.sede.core.geo.Geometria;
import org.sede.core.geo.Punto;
import org.sede.core.plantilla.LayoutInterceptor;
import org.sede.core.rest.CheckeoParametros;
import org.sede.core.rest.Formato;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.rest.Peticion;
import org.sede.core.rest.view.TransformadorXml;
import org.sede.core.utils.ConvertDate;
import org.sede.core.utils.Funciones;
import org.sede.core.utils.Propiedades;
import org.sede.core.validator.Recaptcha;
import org.sede.servicio.ModelAttr;
import org.sede.servicio.acceso.dao.CiudadanoGenericDAO;
import org.sede.servicio.acceso.entity.Ciudadano;
import org.sede.servicio.citaprevia.dao.CitaPreviaGenericDAO;
import org.sede.servicio.citaprevia.entity.Cita;
import org.sede.servicio.qys.dao.HBRequestDao;
import org.sede.servicio.qys.dao.HbUserGenericDao;
import org.sede.servicio.qys.dao.QySDao;
import org.sede.servicio.qys.dao.consulta.ServiceDatos;
import org.sede.servicio.qys.entity.Adjunto;
import org.sede.servicio.qys.entity.Attribute;
import org.sede.servicio.qys.entity.Request;
import org.sede.servicio.qys.entity.RespuestaTipo;
import org.sede.servicio.qys.entity.Service;
import org.sede.servicio.qys.entity.ServiceDefinition;
import org.sede.servicio.qys.entity.SolicitudInformacionPublica;
import org.sede.servicio.qys.entity.UtilsQyS;
import org.sede.servicio.qys.entity.Value;
import org.sede.servicio.qys.entity.db.Hbrequestloadfiles;
import org.sede.servicio.qys.entity.db.Hbrequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * Controlador quejas y sugerencias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Gcz(servicio="TICKETING",seccion="REQUESTS")
@Controller
@Transactional(ConfigQys.TM)
@RequestMapping(value = {"/" + QysController.MAPPING,
		"/" + QysController.MAPPING_INFORMACIONPUBLICA}, method = RequestMethod.GET)
@Description("Gobierno Abierto: Solicitudes de Información Pública y Quejas - sugerencias")
public class QysController {

	/**
	 *  Variable message source
	 */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 *  Initial
	 */
	@Autowired
	private QySDao dao;
	
	@Autowired
	private HbUserGenericDao daoUser;
	
	@Autowired
	private HBRequestDao daoRequest;
	
	/**
	 *  Initial ciudadano
	 */
	@Autowired
	private CiudadanoGenericDAO daoCiudadano;
	/**
	 *  Initial cita previa
	 */
	@Autowired
	private CitaPreviaGenericDAO daoCita;
	
	/**
	 *  Variable logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(QysController.class);
	/**
	 *  Variable mapping 
	 */
	public static final String MAPPING = "servicio/quejas-sugerencias";
	/**
	 *  Variable mapping informacion publica 
	 */
	public static final String MAPPING_INFORMACIONPUBLICA = "servicio/resoluciones-informacion-publica";
	/**
	 *  Variable mapping admin 
	 */
	public static final String MAPPING_ADMIN = MAPPING + "/admin";

	
	/**
	 * Metodo listado de quejas
	 * 
	 * @param start
	 * @param rows
	 * @param sort
	 * @param ids
	 * @param title
	 * @param notes
	 * @param serviceCode
	 * @param origin
	 * @param externoCode
	 * @param agencyResponsibleId
	 * @param accountId
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param groupOperator
	 * @param operator
	 * @param answerRequested
	 * @param barrioCode
	 * @param type
	 * @param status
	 * @param validated
	 * @param idCatSip
	 * @return
	 * @throws SQLException
	 */
	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@ResponseClass(value = Request.class, entity = SearchResult.class)
	@RequestMapping(method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.GEOJSON, MimeTypes.XML, MimeTypes.GEORSS, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	@Description("Listado de quejas")
//	@Permisos(Permisos.DET) 
    public @ResponseBody ResponseEntity<?> apiListRequests(
    		@RequestParam(name = CheckeoParametros.PARAMSTART, defaultValue = CheckeoParametros.START) int start, 
    		@RequestParam(name = CheckeoParametros.PARAMROWS, defaultValue = CheckeoParametros.ROWS) int rows, 
    		@RequestParam(name = CheckeoParametros.PARAMSORT, required = false) String sort, 
    		@RequestParam(name = "service_request_id", required = false) @Description("Identificador de la queja") String ids, //se pueden poner varios IDs Separados por coma
    		@RequestParam(name = "title", required = false) @Description("Título de la queja") String title,
    		@RequestParam(name = "notes", required = false) String notes,
    		@RequestParam(name = "service_code", required = false) @Description("Identificador del servicio") String serviceCode,
    		@RequestParam(name = "origin", required = false) @Description("Origen de la queja") String origin,
    		@RequestParam(name = "externo_code", required = false) @Description("Identificador del entidad a la que se deriva") String externoCode,
    		@RequestParam(name = "interno_code", required = false) @Description("Identificador del gestor al que se deriva") String internoCode,
    		@RequestParam(name = "agency_responsible_code", required = false) String agencyResponsibleId,
    		@Permisos(Permisos.NEW) @RequestParam(name = "account_id", required = false) @Description("Identificador del usuario") String accountId,
    		@Permisos(Permisos.NEW) @RequestParam(name = "user_id", required = false) @Description("Identificador del usuario") String userId,
    		@RequestParam(name = "start_date", required = false) @DateTimeFormat(pattern = ConvertDate.ISO8601_FORMAT) @Description("Quejas introducidas despúes de esta fecha") Date startDate,
    		@RequestParam(name = "end_date", required = false) @DateTimeFormat(pattern = ConvertDate.ISO8601_FORMAT) @Description("Quejas introducidas antes de esta fecha") Date endDate,
    		@RequestParam(name = "group_operator", required = false) String groupOperator, //El identificador de usuario de quejas y sugerencias
    		@RequestParam(name = "operator", required = false) String operator,
    		@RequestParam(name = "answer_requested", required = false) String answerRequested,
    		@RequestParam(name = "barrio_code", required = false) String barrioCode,
    		@RequestParam(name = "type", required = false) String type,
    		@RequestParam(name = "status", required = false) String status,
    		@RequestParam(name = "validated", required = false) String validated,
    		@RequestParam(name = "id_cat_sip", required = false) String idCatSip
    		) throws SQLException {
		try {
			
			Peticion peticion = Funciones.getPeticion();
			String usuarioTicketing = UtilsQyS.obtenerUsuarioTicketing(peticion);
			String externoTicketing = UtilsQyS.obtenerExternoTicketing(peticion);
			String barrioTicketing = UtilsQyS.obtenerBarrioTicketing(peticion);
			
			if (barrioTicketing != null) {
				barrioCode = barrioTicketing;
			}
			
			if (peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains(Permisos.NEW)) {
				// Sin permiso no se puede consultar por identificador de usuario
				accountId = null;
			}
			if (UtilsQyS.soloRootCategories(peticion)) {
				serviceCode = UtilsQyS.obtenerRootCategories(peticion);
			}
			if (peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains(Permisos.ADMIN)) {
				// Sin permiso no se puede consultar por origen de la queja ni por operador ni por respuesta solicitada
				origin = null;
				notes = null;
			}
						
			if (peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains(Permisos.ADMINOPERADOR)) {
				// Sin permiso no se puede consultar por usuarios operadores
				groupOperator = null;
			}
			
			if (peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains(Permisos.ANSWERREQUESTED)) {
				// Sin permiso no se puede consultar por respuesta solicitada
				answerRequested = null;
			}
			
			if (peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains(Permisos.OPERADOR) && !peticion.getPermisosEnSeccion().contains(Permisos.ADMINOPERADOR)) {
				// Sin permiso no se puede consultar por operadores
				operator = null;
			}
			
			if (peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains("SENDEXTERNO")) {
				// Sin permiso no se puede consultar por identificador de externo
				externoCode = null;
			}
			if (peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains(Permisos.PUB)) {
				// Sin permiso no se puede consultar por identificador de usuario
				validated = null;
			}
			if (externoTicketing != null) {
				externoCode = externoTicketing;
			}
			
			if (peticion.getPermisosEnSeccion() != null && peticion.getPermisosEnSeccion().contains(Permisos.INSPECTOR)) {
				internoCode = peticion.getClientId();
			} else if (!peticion.getPermisosEnSeccion().contains("SENDINSPECTOR")) {
				// Sin permiso no se puede consultar por identificador de externo
				internoCode = null;
			}
			
			sort = dao.nombresParaBBDD(sort);
			// Rango de 90 días
			if ((peticion.getPermisosEnSeccion() == null || !peticion.getPermisosEnSeccion().contains(Permisos.ADMIN)) && (startDate != null || endDate != null)) {
				startDate = UtilsQyS.ajustarFechaInicio(startDate, endDate);
				endDate = UtilsQyS.ajustarFechaFin(startDate, endDate);
			} else {
				if (startDate != null || endDate != null) {
					if (startDate == null) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(endDate);
						cal.add(Calendar.YEAR, -1);
						startDate = cal.getTime();
					} else if (endDate == null) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(startDate);
						cal.add(Calendar.YEAR, 1);
						endDate = cal.getTime();
					}
				}
			}
			
			if (servicioResolucionInformacionPublica()) {
				type = SolicitudInformacionPublica.TIPOSOLICITUDINFORMACION.toString();
			}
			
//			// Como mucho 1000 resultados
			rows = rows > 1000 ? 1000 : rows;
			SearchResult<Request> results = daoRequest.searchAndCountRequest(rows,
					start, sort, ids, title, notes, 
					StringUtils.isEmpty(serviceCode) ? null : serviceCode,
					StringUtils.isEmpty(externoCode) ? null : Integer.parseInt(externoCode),	
					StringUtils.isEmpty(agencyResponsibleId) ? null : Integer.parseInt(agencyResponsibleId),
					StringUtils.isEmpty(accountId) ? null : Long.parseLong(accountId),
					StringUtils.isEmpty(userId) ? null : Long.parseLong(userId),
					startDate, endDate, type, status, validated, usuarioTicketing, groupOperator, operator, answerRequested, barrioCode, origin, internoCode, Funciones.getPeticion().getClientId(),
					StringUtils.isEmpty(idCatSip) ? null : Integer.parseInt(idCatSip));
//			tratarCoordenadas(results);
			
			return ResponseEntity.ok(results);
			
			
		} catch (Exception e) {
			logger.error(Funciones.getStackTrace(e));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
    }

    /**
     * Metodo detalle de una queja
     * 
     * @param id
     * @param accountId
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
	@OpenData
	@Permisos(Permisos.DET)
    @Description("Detalle de una queja")
    @ResponseClass(Request.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MimeTypes.GEOJSON, MimeTypes.GEORSS,MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
    public @ResponseBody ResponseEntity<?> apiDetalle(@PathVariable BigDecimal id, @RequestParam(name = "account_id", required = false) BigDecimal accountId) throws SQLException {
        Peticion peticion = Funciones.getPeticion();
        String usuarioTicketing = UtilsQyS.obtenerUsuarioTicketing(peticion);
        String externoTicketing = UtilsQyS.obtenerExternoTicketing(peticion);
        if (peticion.getPermisosEnSeccion().contains(Permisos.ADMIN)) {
        	Hbrequests registro = daoRequest.findRqtRequestNumber(id);
        	if (usuarioTicketing == null) {
                usuarioTicketing = "" + accountId;
            }
    		if (registro == null || !UtilsQyS.puedeAccederAQueja(registro, usuarioTicketing, externoTicketing)) {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("generic.notfound", null, LocaleContextHolder.getLocale())));
    		} else {
	        	return ResponseEntity.ok(registro);
    		}
        } else {
            ResponseEntity<?> results = apiListRequests(0, 1, null, "" + id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            if (results.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(((SearchResult<Hbrequests>) results.getBody()).getResult().get(0));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("generic.notfound", null, LocaleContextHolder.getLocale())));
            }
        }
    }

	/**
	 * Metodo crear queja
	 * 
	 * @param account_id
	 * @param registro
	 * @return
	 */
	@Permisos(Permisos.NEW)
	@RequestMapping(method = RequestMethod.POST, consumes = { MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Description("Crear queja")
	@ResponseClass(value = Request.class)
	public @ResponseBody ResponseEntity<?> apiCrear(@RequestParam(name = "account_id", required = false) String account_id, @RequestBody(required = false) Request registro) {
		try {
			Peticion peticion = Funciones.getPeticion();
			Ciudadano usuario = null;
			if (StringUtils.isNotEmpty(account_id)) {
				usuario = daoCiudadano.obtenerUsuario(account_id);
			}
			
			Request req;
			// Si los datos vienen en el body de la petición
			if (StringUtils.isNotEmpty(peticion.getCuerpoPeticion())) {
				Map<String, String> params = Funciones.tratarQueryString(peticion.getCuerpoPeticion());
				req = new Request(params.get("service_code") == null ? null : new BigDecimal(params.get("service_code")),
						usuario == null ? params.get("email") : usuario.getEmail(),
						params.get("address_string"),
						params.get("address_id"),
						params.get("account_id"),
						usuario == null ? params.get("first_name") : usuario.getName(),
						params.get("last_name"),
						params.get("attribute[user_address]") == null ? params.get("user_address") : params.get("attribute[user_address]"),
						usuario == null ? params.get("phone") : usuario.getMobile(),
						params.get("title") == null ? params.get("attribute[title]") : params.get("title"),
						params.get("description"),
						params.get("media_name") == null ? null : Calendar.getInstance().get(Calendar.YEAR) + "/" + UUID.randomUUID().toString() + Funciones.normalizar(params.get("media_name")),
						params.get("media_description") == null && params.get("media_name") != null ? Funciones.normalizar(params.get("media_name")) : params.get("media_description"),
						params.get("media_body"),
						params.get("public") == null ? params.get("attribute[public]") : params.get("public"),
						"",
						params.get("x") == null ? null : new Double(params.get("x")),
						params.get("y") == null ? null : new Double(params.get("y")),
						params.get("lat") == null ? null : new Double(params.get("lat")),
						params.get("long") == null ? (params.get("lon") == null ? null : new Double(params.get("lon"))) : new Double(params.get("long"))
				);
				if (peticion.getPermisosEnSeccion().contains(Permisos.ADMIN)) {
					req.setAgency_responsible_code(params.get("agency_responsible_code") == null ? null : new BigDecimal(params.get("agency_responsible_code")));
					req.setType(params.get("type") == null ? null : new BigDecimal(params.get("type")));
					req.setPriority(params.get("priority") == null ? null : new BigDecimal(params.get("priority")));
					req.setOrigin(params.get("origin") == null ? UtilsQyS.obtenerDefaultOrigin(peticion) : new BigDecimal(params.get("origin")));
					req.setValidated(params.get("validated"));
					req.setAnswer_requested(params.get("answer_requested") == null || "N".equals(params.get("answer_requested")) ? "N" : params.get("answer_requested"));
					req.setExpected_resolution_datetime(params.get("expected_resolution_datetime") == null ? null : ConvertDate.string2Date(params.get("expected_resolution_datetime"), ConvertDate.ISO8601_FORMAT));
				} else {
					req.setOrigin(UtilsQyS.obtenerDefaultOrigin(peticion));
				}
				if (peticion.getPermisosEnSeccion().contains(Permisos.ZONAINSPECCION)) {
					req.setZona_inspeccion(params.get("zona_inspeccion") == null ? null : Integer.parseInt(params.get("zona_inspeccion")));
				} else {
					req.setZona_inspeccion(null);
				}
			//	si los datos vienen en el objeto registro
			} else {
				req = registro;
				
				if (usuario != null) {
					req.setFirst_name(usuario.getName());
					req.setEmail(usuario.getEmail());
					req.setPhone(usuario.getMobile());
				}
				
				req.setMedia_description(req.getMedia_description() == null && req.getMedia_name() != null ? Funciones.normalizar(req.getMedia_name()) : req.getMedia_description());
				req.setMedia_name(req.getMedia_name() == null ? null : Calendar.getInstance().get(Calendar.YEAR) + "/" + UUID.randomUUID().toString() + Funciones.normalizar(req.getMedia_name()));
				req.setOrigin(UtilsQyS.obtenerDefaultOrigin(peticion));	
			}

			if (this.obtenerErroresValidacion(req).length() <= 0) {
				
				dao.almacenarAdjunto(req);
				req = dao.crear(req, UtilsQyS.OPCREAR, UtilsQyS.obtenerUsuarioTicketing(peticion), peticion.getClientId(), (usuario == null ? null : usuario.getId().longValue()));

				return ResponseEntity.ok(tratarCoordenada((Request)req));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), this.obtenerErroresValidacion(req)));
			}
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ex.getMessage().substring(ex.getMessage().indexOf(':') + 2, ex.getMessage().indexOf("\n"))));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}

	
	/**
	 * Metodo modificar queja
	 * 
	 * @param id
	 * @param account_id
	 * @return
	 */
	@Permisos(Permisos.MOD)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Description("Modificar datos de una solicitud")
	@ResponseClass(value = Request.class)
	public @ResponseBody ResponseEntity<?> apiModificar(@PathVariable BigDecimal id, @RequestParam(name = "account_id", required = false) BigDecimal account_id) {
		try {
			Map<String, String> params = Funciones.tratarQueryString(Funciones.getPeticion().getCuerpoPeticion());
			Request req = new Request(params.get("service_code") == null ? null : new BigDecimal(params.get("service_code")),
					params.get("agency_responsible_code") == null ? null : new BigDecimal(params.get("agency_responsible_code")),
					params.get("type"),
					params.get("priority"),
					params.get("origin"),
					params.get("address_string"),
					params.get("address_id"),
					params.get("title"),
					params.get("description"),
					params.get("validated"),
					params.get("visible"),
					params.get("x") == null ? null : new Double(params.get("x")),
					params.get("y") == null ? null : new Double(params.get("y"))
					);
			if (params.get("lat") != null && params.get("long") != null) {
				Double[] p =  Geometria.pasarAUTM30(Double.parseDouble(params.get("long")), Double.parseDouble(params.get("lat")));
				req.setX(p[0]);
				req.setY(p[1]);
			}
			req.setUser_address(params.get("user_address"));
			req.setEmail(params.get("email"));
			req.setFirst_name(params.get("first_name"));
			req.setPhone(params.get("phone"));
			req.setMedia_name(params.get("media_name") == null ? null : "" + Calendar.getInstance().get(Calendar.YEAR) + "/" + UUID.randomUUID().toString() + Funciones.normalizar(params.get("media_name")));
			req.setMedia_description(params.get("media_description") == null && params.get("media_name") != null ? Funciones.normalizar(params.get("media_name")) : params.get("media_description"));
			
			req.setAnswer_requested(params.get("answer_requested"));
			
			req.setId_cat_sip(params.get("id_cat_sip") == null ? null : new BigDecimal(params.get("id_cat_sip")));
			req.setExpected_resolution_datetime(params.get("expected_resolution_datetime") == null ? null : ConvertDate.string2Date(params.get("expected_resolution_datetime"), ConvertDate.ISO8601_FORMAT));
			
			if (Funciones.getPeticion().getPermisosEnSeccion().contains(Permisos.ZONAINSPECCION)) {
				req.setZona_inspeccion(params.get("zona_inspeccion") == null ? null : Integer.parseInt(params.get("zona_inspeccion")));
			} else {
				req.setZona_inspeccion(null);
			}
			
			if (params.get("media_body") != null) {
				String body = params.get("media_body");
				if (body.indexOf("base64,") + 7 > 0) {
					req.setMedia_body(body.substring(body.indexOf("base64,") + 7, body.length()));
				} else {
					req.setMedia_body(params.get("media_body"));
				}
			}
			
			dao.almacenarAdjunto(req);
			req.setService_request_id(id);
			req = dao.guardar(req, UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion()));
	    	return ResponseEntity.ok(tratarCoordenada((Request)req));
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ex.getMessage().substring(ex.getMessage().indexOf(':') + 2, ex.getMessage().indexOf("\n"))));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	/**
	 * Metodo obtener errores validacion
	 * @param entidad
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String obtenerErroresValidacion(Request entidad) {
		ValidatorFactory factory = Validation.byDefaultProvider().configure().traversableResolver(new JPAIgnoreTraversableResolver()).buildValidatorFactory();
		Validator validator = factory.getValidator();
		StringBuilder respuesta = new StringBuilder();
		Set<ConstraintViolation<Request>> constraintViolations = validator.validate(entidad);
		if (!constraintViolations.isEmpty()) {
			for (Iterator iterator = constraintViolations.iterator(); iterator.hasNext();) {
				ConstraintViolation<Object> constraintViolation = (ConstraintViolation<Object>) iterator.next();
				respuesta.append(constraintViolation.getPropertyPath().toString() + ": " + constraintViolation.getMessage() + ". ");
			}
		}
		return respuesta.toString();
	}
	
	/**
	 * Metodo servicion resolucion informacion publica
	 * @return
	 */
	private boolean servicioResolucionInformacionPublica() {
		if (Funciones.getPeticion().getUri().contains(MAPPING_INFORMACIONPUBLICA)){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo home
	 * 
	 * @param start
	 * @param rows
	 * @param sort
	 * @param ids
	 * @param title
	 * @param notes
	 * @param service_code
	 * @param origin
	 * @param externo_code
	 * @param agency_responsible_id
	 * @param account_id
	 * @param user_id
	 * @param start_date
	 * @param end_date
	 * @param group_operator
	 * @param operator
	 * @param answer_requested
	 * @param barrio_code
	 * @param type
	 * @param status
	 * @param validated
	 * @param id_cat_sip
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String home(SearchFiql search, 
			@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "start_date", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date startDate,
			@RequestParam(name = "end_date", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date endDate,
			@RequestParam(name = "barrio_code", required = false) Integer barrioCode,
			@RequestParam(name = "id_cat_sip", required = false) Integer idCatSip,
			@RequestParam(name = "service_code", required = false) BigDecimal serviceCode,
			@RequestParam(name = "service_name", required = false) String serviceName,
			Model model, HttpServletRequest request) {
		String mapping = MAPPING + "/index";
		try {
			 
			if (servicioResolucionInformacionPublica()) {
				request.setAttribute(LayoutInterceptor.PLANTILLA_SALIDA, MAPPING_INFORMACIONPUBLICA);
				model.addAttribute("categorias", dao.obtenerCategoriasConTotal(true, null));
				mapping = mapping + "-resoluciones";
			} else {
				model.addAttribute("categorias", apiServiceListar());
			}
			if (StringUtils.isNotEmpty(status) 
					|| startDate != null
					|| endDate != null
					|| barrioCode != null
					|| idCatSip != null
					|| serviceCode != null
					|| StringUtils.isNotEmpty(serviceName)
					) {
				model.addAttribute(ModelAttr.RESULTADO, apiListar(search, status, startDate, endDate, barrioCode,idCatSip, serviceCode, serviceName));
			}
		} catch (Exception e) {
			logger.error("ERROR", e);
			model.addAttribute("message", e.getMessage());
		}
		return mapping;
	}
	
	/**
	 * Metodo tratar coordenadas resultados
	 * 
	 * @param results
	 */
	private void tratarCoordenadas(SearchResult<Request> results) {
		for (int i = 0; i < results.getResult().size(); i++) {
			Request req = results.getResult().get(i);
			if (req.getX() != null) {
			
				if (CheckeoParametros.SRSWGS84.equals(Funciones.getPeticion().getSrsName())) {
					
					Punto p = Geometria.pasarAWgs84ConPunto(req.getX(), req.getY());
					req.setX(null);
					req.setY(null);
					req.setLat(p.getCoordinates()[0]);
					req.setLon(p.getCoordinates()[1]);
				} else {
					req.setLat(null);
					req.setLon(null);
				}
				results.getResult().remove(i);
				results.getResult().add(i,req);
			}
		}
	}

	/**
	 * Metodo tratar coordenadas peticion 
	 * @param req
	 * @return
	 */
	private Request tratarCoordenada(Request req) {
		if (req.getX() != null) {
		
			if (CheckeoParametros.SRSWGS84.equals(Funciones.getPeticion().getSrsName())) {
				
				Punto p = Geometria.pasarAWgs84ConPunto(req.getX(), req.getY());
				req.setX(null);
				req.setY(null);
				req.setLat(p.getCoordinates()[0]);
				req.setLon(p.getCoordinates()[1]);
			} else {
				req.setLat(null);
				req.setLon(null);
			}
		}
		return req;
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
		model.addAttribute("registro", dao.findByToken(identifier, token));
		return MAPPING + "/token";
	}

	/**
	 * Metodo admin
	 * 
	 * @param model
	 * @return
	 */
	@Permisos(Permisos.MOD)
	@RequestMapping(path = "/admin", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String admin(Model model) {
		return MAPPING_ADMIN + "/index";
	}

	/**
	 * Metodo user
	 * 
	 * @param start
	 * @param rows
	 * @param sort
	 * @param ids
	 * @param title
	 * @param notes
	 * @param serviceCode
	 * @param startDate
	 * @param endDate
	 * @param barrioCode
	 * @param type
	 * @param status
	 * @param id_cat_sip
	 * @param request
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@PermisosUser
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String user(
			@RequestParam(name = CheckeoParametros.PARAMSTART, defaultValue = CheckeoParametros.START) int start, 
    		@RequestParam(name = CheckeoParametros.PARAMROWS, defaultValue = CheckeoParametros.ROWS) int rows, 
    		@RequestParam(name = CheckeoParametros.PARAMSORT, required = false) String sort, 
    		@RequestParam(name = "service_request_id", required = false) @Description("Identificador de la queja") String ids, //se pueden poner varios IDs Separados por coma
    		@RequestParam(name = "title", required = false) @Description("Título de la queja") String title,
    		@RequestParam(name = "notes", required = false) String notes,
    		@RequestParam(name = "service_code", required = false) @Description("Identificador del servicio") String serviceCode,
    		@RequestParam(name = "start_date", required = false) @Description("Quejas introducidas despúes de esta fecha") Date startDate,
    		@RequestParam(name = "end_date", required = false) @Description("Quejas introducidas antes de esta fecha") Date endDate,
    		@RequestParam(name = "barrio_code", required = false) String barrioCode,
    		@RequestParam(name = "type", required = false) String type,
    		@RequestParam(name = "status", required = false) String status,
    		@RequestParam(name = "id_cat_sip", required = false) @Description("Identificador de tipo de Solicitud de Información pública") String id_cat_sip,
    		HttpServletRequest request, Model model) throws SQLException {
		Ciudadano user = Funciones.getUser(request);
		model.addAttribute(ModelAttr.RESULTADO, apiListRequests(start, rows, sort, ids, title, notes, serviceCode, null, null, null, null, null, ("" + user.getId()), startDate, endDate, null, null, null, barrioCode, type, status, null, id_cat_sip));
		return MAPPING + "/user";
	}
	
	/**
	 * Metodo nuevo formulario
	 * 
	 * @param dato
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String newForm(Request dato, BindingResult bindingResult, Model model) throws SQLException {
		model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
		model.addAttribute("service", apiServiceListar());
		model.addAttribute(ModelAttr.DATO, dato);
		model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(new Request()));
		return MAPPING + "/new";
	}
	/**
	 * Metodo crear
	 * 
	 * @param dato
	 * @param matricula
	 * @param file
	 * @param gRecaptchaResponse
	 * @param bindingResult
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = {
	        MediaType.TEXT_HTML_VALUE, "*/*" })
	public String crear(Request dato,
			@RequestParam(name = "matricula", required = false) String matricula,
			@RequestParam("file") MultipartFile file,
			@RequestParam(name = "g-recaptcha-response", defaultValue = "") String gRecaptchaResponse,
			BindingResult bindingResult, Model model, RedirectAttributes attr, HttpServletRequest request) throws SQLException {
		if (Recaptcha.verify(gRecaptchaResponse, request)) {
			if (StringUtils.isNotEmpty(matricula)) {
				dato.setDescription(dato.getDescription() + System.getProperty("line.separator") + "Matricula del punto de luz: " + matricula);
			}
			if (file.isEmpty() == Boolean.FALSE) {
				dato.setMedia_name(file.getName());
				dato.setMedia_description(file.getOriginalFilename());
				
				try {
					String base64 = Base64.encodeBase64String(file.getBytes());
					dato.setMedia_body(base64);	
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				
			}
			Ciudadano user = Funciones.getUser(request);
			ResponseEntity<?> resultado = apiCrear(user == null ? null : user.getAccount_id(), dato);
		    if (resultado.getStatusCode().is2xxSuccessful()) {
		    	attr.addFlashAttribute(ModelAttr.DATO, dato);
		    	attr.addFlashAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.OK.value(), "Registro creado correctamente"));
		    	attr.addFlashAttribute(ModelAttr.REGISTRO, resultado);
		        return "redirect:/" + MAPPING + "/" + ((Request)resultado.getBody()).getService_request_id() + "/" + ((Request)resultado.getBody()).getToken() + "/";
		    } else {
		    	model.addAttribute("recaptcha", Propiedades.getString("recaptcha.html"));
		        model.addAttribute(ModelAttr.MENSAJE, resultado.getBody());
		        model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(dato));
		        model.addAttribute(ModelAttr.DATO, dato);
		        model.addAttribute("service", apiServiceListar());
		        return MAPPING + "/new";
		    }
		} else {
			model.addAttribute(ModelAttr.RECAPTCHA, Propiedades.getString("recaptcha.html"));
			model.addAttribute(ModelAttr.MENSAJE, new Mensaje(HttpStatus.BAD_REQUEST.value(), "Debe marcar la casilla para indicar que no es un robot"));
			model.addAttribute(ModelAttr.REGISTRO, ResponseEntity.ok(dato));
			model.addAttribute("service", apiServiceListar());
			model.addAttribute(ModelAttr.DATO, dato);
			return MAPPING + "/new";
		} 
	    
	}
	
	/**
	 * Metodo listado de servicios con subservicios
	 * @return
	 * @throws SQLException
	 */
	@Cache(Cache.DURACION_30MIN)
	@Description("Listado de servicios con sus subservicios")
	@ResponseClass(value = Service.class, entity = SearchResult.class)
	@RequestMapping(value = "/service-levels", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiServiceListar() throws SQLException {
		return ResponseEntity.ok(dao.searchAndCountService(null));
	}

	
	/**
	 * Metodo definicion del servicio
	 * @param identifier
	 * @return
	 */
	@Cache(Cache.DURACION_30MIN)
	@Description("Definición del servicio")
	@ResponseClass(value = Service.class, entity = SearchResult.class)
	@RequestMapping(value = "/services/{id}", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiServiceListar(@PathVariable BigDecimal identifier) {
		ServiceDefinition service = new ServiceDefinition();
		service.setService_code("" + identifier);
		service.setAttributes(Attribute.getLista());
		return ResponseEntity.ok(service);
	}
	
	
	
	/**
	 * Metodo estadisticas
	 * 
	 * @param service_code
	 * @param year
	 * @return
	 */
	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@Description("Estadísticas")
	@ResponseClass(value = Service.class, entity = SearchResult.class)
	@RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiStatistics(@RequestParam(name = "service_code", required = false) @Description("Identificador del servicio") String service_code,
			@RequestParam(name = "year", required = false) @Description("Año") Integer year) {
		year = year == null ? Calendar.getInstance().get(Calendar.YEAR) : year;
		BigDecimal type = null;
		if (servicioResolucionInformacionPublica()) {
			type = SolicitudInformacionPublica.TIPOSOLICITUDINFORMACION;
		}
		
		return ResponseEntity.ok(dao.statistics(service_code, year, type));
	}
	
	/**
	 * Metodo estadisticas
	 * 
	 * @param model
	 * @param service_code
	 * @param year
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/statistics", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String statistics(Model model, 
			@RequestParam(name = "service_code", required = false) @Description("Identificador del servicio") String service_code,
			@RequestParam(name = "year", required = false) @Description("Año") Integer year, HttpServletRequest request) throws SQLException {
		
		if (servicioResolucionInformacionPublica()) {
			model.addAttribute("title", "Resoluciones procedimiento de información pública");
			request.setAttribute(LayoutInterceptor.PLANTILLA_SALIDA, MAPPING_INFORMACIONPUBLICA);
			model.addAttribute("resoluciones", true);
			model.addAttribute("datosSegunFemp", dao.obtenerCategoriasConTotal(false, "" + year));
		} else {
			model.addAttribute("title", "Quejas y Sugerencias");
		}
		
		ResponseEntity<?> resultado = apiStatistics(service_code, year);
		
		model.addAttribute(ModelAttr.RESULTADO, resultado);
		year = year == null ? Calendar.getInstance().get(Calendar.YEAR) : year;
		model.addAttribute("total", dao.obtenerTotal((SearchResult<ServiceDatos>)resultado.getBody()));
		
		int anyo = Calendar.getInstance().get(Calendar.YEAR);
		List<Integer> years = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			years.add(anyo - i);
		}
		model.addAttribute("years", years);
		
		model.addAttribute("service", apiServiceListar());
		return MAPPING + "/statistics";
	}
	/**
	 * Metodo colaboradores asociados a categorias
	 * @return
	 */
	@Permisos(Permisos.ADMIN)
	@Description("Colaboradores asociados a categorias")
	@ResponseClass(RespuestaTipo.class)
	@RequestMapping(value = "/respuestas-tipo", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody SearchResult<RespuestaTipo> respuestasTipo() {
		return dao.searchAndCountRespuestaTipo(UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion()));
	}

	/**
	 * Metodo estadistica
	 * 
	 * @param title
	 * @param service_code
	 * @param start_date
	 * @param end_date
	 * @return
	 */
	@Permisos(Permisos.ESTADISTICA)
	@ResponseClass(RespuestaTipo.class)
	@RequestMapping(value = "/estadistica", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiEstadistica(@RequestParam(name = "title", required = false) @Description("Título de la queja") String title,
		@RequestParam(name = "service_code", required = false) @Description("Identificador del servicio") String service_code,
		//@Permisos(Permisos.NEW) @QueryParam("account_id") @Description("Identificador del usuario") String account_id,
		@RequestParam(name = "start_date", required = false) @Description("Quejas introducidas despúes de esta fecha") Date start_date,
		@RequestParam(name = "end_date", required = false) @Description("Quejas introducidas antes de esta fecha") Date end_date) {
		try {
			String usuarioTicketing = UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion());
			return ResponseEntity.ok(dao.datosQuejas(start_date, end_date, service_code, title, usuarioTicketing));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}

	@Permisos(Permisos.ESTADISTICA)
	@ResponseClass(RespuestaTipo.class)
	@RequestMapping(value = "/datos", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiDatos(
		@RequestParam(name = "start_date", required = false) @Description("Quejas introducidas despúes de esta fecha") Date start_date,
		@RequestParam(name = "end_date", required = false) @Description("Quejas introducidas antes de esta fecha") Date end_date) {
		try {
			
			int rootCategoria = Integer.parseInt(UtilsQyS.obtenerRootCategory(Funciones.getPeticion()));
			
			String grupo_operador = null;
			if (Funciones.getPeticion().getPermisosEnSeccion().contains(Permisos.ADMINOPERADOR)) {
				grupo_operador = UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion());
			}
			
			return ResponseEntity.ok(dao.datosCategorias(start_date, end_date, rootCategoria, grupo_operador, UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion())));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
	
	/**
	 * Metodo realizar acciones sobre una queja
	 * 
	 * @param id
	 * @param accion
	 * @param account_id
	 * @return
	 */
	@Permisos(Permisos.MOD)
	@RequestMapping(value = "/{id}/acciones/{accion}", method = RequestMethod.PUT, consumes = { MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Description("Realizar acciones sobre una queja")
	@ResponseClass(value = Request.class)
	public @ResponseBody ResponseEntity<?> apiAcciones(@PathVariable BigDecimal id, @PathVariable Integer accion, @RequestParam(name = "account_id", required = false) BigDecimal account_id) {
		Peticion peticion = Funciones.getPeticion();
		if (accion == 2 && !peticion.getPermisosEnSeccion().contains(Permisos.CONTESTAR)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Mensaje(HttpStatus.FORBIDDEN.value(), "No puede ejecutar la petición"));
		}
		
		if (accion == 12 && !peticion.getPermisosEnSeccion().contains(Permisos.CERRARSINCONTESTAR)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Mensaje(HttpStatus.FORBIDDEN.value(), "No puede ejecutar la petición"));
		}
		
		ResponseEntity<?> obj = null;
		Value estadoAnterior = dao.obtenerEstadoDeQueja(id);
		String usuarioTicketing = UtilsQyS.obtenerUsuarioTicketing(Funciones.getPeticion());
		try{
			Map<String, String> params = Funciones.tratarQueryString(Funciones.getPeticion().getCuerpoPeticion());
			String uuid = null;
			Date fecha = null;
			String mailExterno = null;
			BigDecimal idExterno = null;
			String idInterno = null;
			String linkOrdenTrabajo = "";
			String descripcion = params.get("description");
			if (accion == 5) { // Solicitar mas informacion
				// Generamos el uuid que utilizaremos como token para enviar por correo al usuario
				uuid = java.util.UUID.randomUUID().toString();
			} else if (params.get("fecha") != null && accion == 1) {
				fecha = ConvertDate.string2Date(params.get("fecha"), ConvertDate.ISO8601MS_FORMAT);
			}
			
			
			Object original = dao.detalle(id, usuarioTicketing);
			
			if (accion == 2 // Contestar
					|| accion == 9 // Informar 
					|| accion == 6 // Rechazar
					) {
				// Enviar correo al usuario
				
				String externoTicketing = UtilsQyS.obtenerExternoTicketing(peticion);
				if (original instanceof Request) {
					Request req = (Request) original;
					if (externoTicketing == null) {
						Funciones.sendMail("OpenCityExt. Sistema de quejas y sugerencias. Solicitud: " + req.getService_request_id() + ". Asunto: " + req.getTitle(), descripcion + UtilsQyS.TEXTONOCONTESTAR, req.getEmail(), "", "text/plain");
					} else {
						if (req.getExterno_code() == new BigDecimal(externoTicketing)) {
							Funciones.sendMail("OpenCityExt. Sistema de quejas y sugerencias. Solicitud: " + req.getService_request_id() + ". Asunto: " + req.getTitle(), descripcion + UtilsQyS.TEXTONOCONTESTAR, req.getEmail(), "", "text/plain");
						} else {
							return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("generic.notfound", null, LocaleContextHolder.getLocale())));
						}
					}
				} else {
					return ResponseEntity.ok(original);
				}
			}
			if (accion == 8) { // Derivar a externo
				mailExterno = params.get("mailExterno");
				idExterno = new BigDecimal(params.get("idExterno"));
				obj = dao.acciones(id, accion, descripcion, fecha, idExterno, idInterno, usuarioTicketing, uuid, peticion.getClientId(), null);
				if (UtilsQyS.ID_EXTERNO_FCC.equals(idExterno)) {
					
					TransformadorXml transformador = new TransformadorXml();
					StringBuilder respuesta = new StringBuilder();
					Formato tipo = peticion.getFormato();
					peticion.setFormato(MimeTypes.XML_FORMATO);
					transformador.transformarObjeto(respuesta, obj, peticion, true, "");
					peticion.setFormato(tipo);
					Funciones.sendMail("jardines - zaragoza", respuesta.toString(), mailExterno, peticion.getCredenciales().getUsuario().getEmail(), "text/plain");
					descripcion = "Correo a " + mailExterno + ": Descripcion: " + descripcion;
					obj = dao.acciones(id, 10, descripcion, fecha, idExterno, idInterno, usuarioTicketing, uuid, peticion.getClientId(), null);
				} else if (UtilsQyS.ID_EXTERNOLIMPIEZA_FCC.equals(idExterno)) {
					TransformadorXml transformador = new TransformadorXml();
					StringBuilder respuesta = new StringBuilder();
					Formato tipo = peticion.getFormato();
					peticion.setFormato(MimeTypes.XML_FORMATO);
					transformador.transformarObjeto(respuesta, obj, peticion, true, "");
					peticion.setFormato(tipo);
					Funciones.sendMail("limpieza - zaragoza", respuesta.toString(), mailExterno, peticion.getCredenciales().getUsuario().getEmail(), "text/plain");
					descripcion = "Enviado por integracion a " + mailExterno + ": Descripcion: " + descripcion;
					obj = dao.acciones(id, 10, descripcion, fecha, idExterno, idInterno, usuarioTicketing, uuid, peticion.getClientId(), null);
				} else {
					if (params.containsKey("incluir_orden")) {
						// Generamos la orden de trabajo, 
						// la guardamos como adjunto y 
						// la enlazamos desde el correo
						String rootCategory = UtilsQyS.obtenerRootCategory(peticion);
						if (obj.getBody() instanceof Request) {
							Request req = (Request) obj.getBody();
							JasperPrint informe = dao.generarInformeOrdenTrabajo(req, req.getExterno_name(), rootCategory, peticion);
							String mediaName = (Funciones.normalizar(req.getExterno_name())) + ConvertDate.date2String(new Date(), ConvertDate.DATE_FORMAT_FICHERO);
							String mediaNameExt = Calendar.getInstance().get(Calendar.YEAR) + "/" + mediaName + ".pdf";
							dao.almacenarInforme(informe, mediaNameExt);
							
							Request actualizacion = new Request();
							actualizacion.setService_request_id(id);
							actualizacion.setMedia_name(mediaNameExt);
							actualizacion.setMedia_description(mediaName);
							actualizacion = dao.guardar(actualizacion, usuarioTicketing);
							for (Adjunto adj: actualizacion.getFiles()) {
								if (adj.getFile_name().equals(mediaNameExt)) {
									linkOrdenTrabajo = System.getProperty("line.separator") + "Orden de trabajo: " + adj.getMedia_url(); 
									break;
								}
							}
						} else {
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "Error al obtener los datos de la queja"));
						}
					}
					if (!StringUtils.isEmpty(mailExterno)) {
						
						String textoMsg = "";
						if ("".equals(linkOrdenTrabajo)) {
							if (obj.getBody() instanceof Request) {
								Request req = (Request) obj.getBody();
								textoMsg = descripcion + System.getProperty("line.separator")
										+ "Asunto: " + req.getTitle() + System.getProperty("line.separator")
										+ "Descripcion: " + req.getDescription() + System.getProperty("line.separator")
										+ "Usuario: " 
											+ (req.getFirst_name() == null ? "" : " " + req.getFirst_name()) 
											+ (req.getLast_name() == null ? "" : " " + req.getLast_name()) 
											+ (req.getPhone() == null ? "" : " " + req.getPhone())
											+ (req.getEmail() == null || "anonymous@anonymous.es".equals(req.getEmail()) ? "" : " <" + req.getEmail() + ">");
							} else {
								textoMsg = descripcion;	
							}							
						} else {
							textoMsg = descripcion + linkOrdenTrabajo;
						}
						
						Funciones.sendMail(id + ": OpenCityExt. Sistema de quejas y sugerencias.", textoMsg, mailExterno, peticion.getCredenciales().getUsuario().getEmail(), "text/plain");
						descripcion = "Correo a " + mailExterno + ": Descripcion: " + descripcion;
					}
				}
				return ResponseEntity.ok(obj);
			} else if (accion == 22) {
				Integer service_code = params.get("service_code") == null ? null : Integer.parseInt(params.get("service_code"));
				Integer agency_responsible_code = params.get("agency_responsible_code") == null ? null : Integer.parseInt(params.get("agency_responsible_code"));
				String expediente = params.get("expediente") == null ? "" : params.get("expediente");
				String year = params.get("year") == null ? "" : params.get("year");
				String diaCita = params.get("diaCita") == null ? "" : params.get("diaCita");
				String horaCita = params.get("horaCita") == null ? "" : params.get("horaCita");
				dao.asociar(id, service_code, agency_responsible_code, Integer.parseInt(usuarioTicketing));
				
				if (!"".equals(expediente) && !"".equals(year) && !"".equals(diaCita) && !"".equals(horaCita)) {
					Request objeto = (Request)original; 
					descripcion = (descripcion == null ? "" : descripcion) + System.getProperty("line.separator") + "Expediente: " + expediente + " Año: " + year;
					Cita c = daoCita.nuevaCita(1021, 
							objeto.getLast_name(),
							objeto.getFirst_name(),
							objeto.getEmail(),
							objeto.getPhone(),
							dao.obtenerNifDeRequest(objeto),
							objeto.getVisible().equals("S"),
							diaCita, horaCita, expediente + "-" + year);
					descripcion = descripcion + System.getProperty("line.separator") +  "Cita previa: " + c.getFecha() + " " + c.getHora() + " Localizador:" + c.getLocalizador();
					descripcion = descripcion + System.getProperty("line.separator") + dao.enviarCapaz((Request)original, diaCita, horaCita, expediente + "/" + year);
				}
				
				obj = dao.acciones(id, 10, descripcion, fecha, idExterno, idInterno, usuarioTicketing, uuid, peticion.getClientId(), null);
				
				return ResponseEntity.ok(obj);
			} else if (accion == 11) {
				mailExterno = params.get("mailExterno");
				idExterno = new BigDecimal(params.get("idExterno"));
				obj = dao.acciones(id, accion, descripcion, fecha, idExterno, idInterno, usuarioTicketing, uuid, peticion.getClientId(), null);
				if (!StringUtils.isEmpty(mailExterno)) {
					Funciones.sendMail("OpenCityExt. Sistema de quejas y sugerencias.", descripcion, mailExterno, peticion.getCredenciales().getUsuario().getEmail(), "text/plain");
					descripcion = "Correo a " + mailExterno + ": Descripcion: " + descripcion;
				}
				return ResponseEntity.ok(obj);
			} else {
				Integer internalStatus = params.get("internalStatus") == null ? null : Integer.parseInt(params.get("internalStatus"));
				return dao.acciones(id, accion, descripcion, fecha, idExterno, idInterno, usuarioTicketing, uuid, peticion.getClientId(), internalStatus);
			}
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), ex.getMessage().substring(ex.getMessage().indexOf(':') + 2, ex.getMessage().indexOf("\n"))));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (obj != null && obj.getBody() instanceof Request) {
				Request reqObj = (Request) obj.getBody();
				if (!reqObj.getStatus_admin().equals(estadoAnterior.getName())) {
					try {
						dao.devolverEstadoAnteriorQueja(id, estadoAnterior, "ERROR en acción, se devuelve la queja al estado " + estadoAnterior.getName() + ":" + e.getMessage(), usuarioTicketing, peticion.getClientId());
					} catch (Exception ex) {
						logger.error(Funciones.getStackTrace(ex));
					}
				}
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}

	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@Description("Listado de registros")
	@ResponseClass(value = Hbrequests.class, entity = SearchResult.class)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = {MimeTypes.GEOJSON, MimeTypes.GEORSS, MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiListar(@Fiql SearchFiql search, 
			@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "start_date", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date startDate,
			@RequestParam(name = "end_date", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date endDate,
			@RequestParam(name = "barrio_code", required = false) Integer barrioCode,
			@RequestParam(name = "id_cat_sip", required = false) Integer idCatSip,
			@RequestParam(name = "service_code", required = false) BigDecimal serviceCode,
			@RequestParam(name = "service_name", required = false) String serviceName) throws SearchParseException, ParseException {
		search.setExcludeFields("status", "barrio_code", "id_cat_sip", "service_code", "service_name", "start_date", "end_date");
		Search busqueda = search.getConditions(Hbrequests.class);
		busqueda.addFilter(Filter.equal("rqtPublic", "S"));
		busqueda.addFilter(Filter.equal("validated", "S"));
		busqueda.addFilter(Filter.greaterThan("service_request_id", UtilsQyS.RQT_ID_FROM));
		if (barrioCode != null) {
			busqueda.addFilter(Filter.equal("portal.junta.id", barrioCode));	
		}
		if (idCatSip != null) {
			busqueda.addFilter(Filter.equal("catSip.id", idCatSip));	
		}
		
		if (serviceCode != null) {
			busqueda.addFilter(Filter.equal("hbcategories.calHbid", serviceCode));	
		}
		if (serviceName != null) {
			busqueda.addFilter(Filter.ilike("hbcategories.calName", serviceName));	
		}
		if ("open".equals(status)) {
			busqueda.addFilterEmpty("updated_datetime");
		} else if ("closed".equals(status)) {
			busqueda.addFilterNotEmpty("updated_datetime");
		}
		if (servicioResolucionInformacionPublica()) {
			busqueda.addFilter(Filter.equal("type.rtyHbid", SolicitudInformacionPublica.TIPOSOLICITUDINFORMACION));
			busqueda.addFilterNotEmpty("catSip");
		}
		
		if (startDate != null || endDate != null) {
			if (!Funciones.getPeticion().getPermisosEnSeccion().contains(Permisos.ADMIN)) {
				startDate = UtilsQyS.ajustarFechaInicio(startDate, endDate);
				endDate = UtilsQyS.ajustarFechaFin(startDate, endDate);
			} else {
				if (startDate == null) {
					startDate = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(endDate);
					cal.add(Calendar.YEAR, -1);
					startDate = cal.getTime();
				} else if (endDate == null) {
					endDate = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(startDate);
					cal.add(Calendar.YEAR, 1);
					endDate = cal.getTime();
				}
			}
			busqueda.addFilter(Filter.greaterOrEqual("rqtRequestdatetime", startDate));
			busqueda.addFilter(Filter.lessOrEqual("rqtRequestdatetime", endDate));
		}
		
		
		return ResponseEntity.ok(daoRequest.searchAndCount(busqueda));
    }

	@OpenData
	@NoCache
	@ResponseClass(value = Hbrequestloadfiles.class, entity = SearchResult.class)
	@RequestMapping(value="/{id}/file/{idFile}", method = RequestMethod.GET, produces = { MimeTypes.XLS })
    public @ResponseBody ResponseEntity<?> apiGetFile(
    		@PathVariable BigDecimal id,
    		@PathVariable BigDecimal idFile
    		) throws IOException {

		Hbrequestloadfiles f = daoRequest.findFile(id, idFile);
		HttpHeaders headers = new HttpHeaders();
		String fileName = f.getFilename();		
		if (fileName.toLowerCase().indexOf(".gif") > 0) {
	    	headers.setContentType(MediaType.parseMediaType(MimeTypes.GIF));
	    } else if (fileName.toLowerCase().indexOf(".png") > 0) {
	    	headers.setContentType(MediaType.parseMediaType(MimeTypes.PNG));
	    } else if (fileName.toLowerCase().indexOf(".jpg") > 0) {
	    	headers.setContentType(MediaType.parseMediaType("image/jpg"));
	    } else if (fileName.toLowerCase().indexOf(".jpeg") > 0) {
	    	headers.setContentType(MediaType.parseMediaType(MimeTypes.JPEG));
	    } else if (fileName.toLowerCase().indexOf(".pdf") > 0) {
	    	headers.setContentType(MediaType.parseMediaType(MimeTypes.PDF));
	    } else if (fileName.toLowerCase().indexOf(".zip") > 0) {
	    	headers.setContentType(MediaType.parseMediaType("application/zip"));
	    } else if (fileName.toLowerCase().indexOf(".doc") > 0) {
	    	headers.setContentType(MediaType.parseMediaType("application/vnd.ms-word"));
	    } else if (fileName.toLowerCase().indexOf(".odt") > 0) {
	    	headers.setContentType(MediaType.parseMediaType("application/vnd.oasis.opendocument.text"));
	    } else {
	    	headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
	    }
		headers.set("Content-Disposition", "inline; filename=\"" + fileName + "\"");
		
		FileInputStream archivo = new FileInputStream(Propiedades.getPathAplicacionesDisk() + "ticketing/" + fileName);
		int longitud = archivo.available();
	    byte[] datos = new byte[longitud];
	    archivo.read(datos);
	    archivo.close(); 
		return new ResponseEntity<byte[]>(datos, headers, HttpStatus.OK);
    }
	
// TODO BORRAR	@Permisos(Permisos.ADMIN)
//	@Cache(Cache.DURACION_30MIN)
//	@RequestMapping(value = "/change-all-pass", method = RequestMethod.GET, produces = {MimeTypes.GEOJSON, MimeTypes.GEORSS, MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
//	public @ResponseBody ResponseEntity<?> apiChangePass() {
//		List<Hbusers> usr = daoUser.findAll();
//		
//		Query update = daoUser.em().createNativeQuery("Update HBUSERS set PASSWORD=? where USR_HBID = ? ");
//		
//		for (Hbusers u : usr) {
//			update.setParameter(1, Funciones.calculateUserPassword(u.getUsrPassword()));
//			update.setParameter(2, u.getUsrHbid());
//			System.out.println(u.getUsrHbid() + ":" + u.getUsrPassword() + ":" + update.executeUpdate() + ":" + Funciones.calculateUserPassword(u.getUsrPassword()));
//			
//			
//		}
//		System.out.println("OKKK");
//		return ResponseEntity.ok(new Mensaje(200, "Actualizacion realizada"));
//    }
	
}
