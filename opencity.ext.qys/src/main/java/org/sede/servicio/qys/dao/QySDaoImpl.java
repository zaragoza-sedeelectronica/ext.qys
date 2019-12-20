package org.sede.servicio.qys.dao;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.DatatypeConverter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import oracle.jdbc.OracleTypes;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.context.TypeConstraintMappingContext;
import org.hibernate.validator.cfg.defs.NotEmptyDef;
import org.hibernate.validator.cfg.defs.SizeDef;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.dao.JPAIgnoreTraversableResolver;
import org.sede.core.exception.FormatoNoSoportadoException;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.rest.Peticion;
import org.sede.core.rest.Respuesta;
import org.sede.core.rest.Rest;
import org.sede.core.rest.view.TransformadorXml;
import org.sede.core.utils.ConvertDate;
import org.sede.core.utils.Funciones;
import org.sede.core.utils.Propiedades;
import org.sede.servicio.qys.ConfigQys;
import org.sede.servicio.qys.InformacionPublicaController;
import org.sede.servicio.qys.dao.consulta.BarrioDato;
import org.sede.servicio.qys.dao.consulta.ExternoDato;
import org.sede.servicio.qys.dao.consulta.ServiceConsulta;
import org.sede.servicio.qys.dao.consulta.ServiceDatos;
import org.sede.servicio.qys.entity.Accion;
import org.sede.servicio.qys.entity.Adjunto;
import org.sede.servicio.qys.entity.Category;
import org.sede.servicio.qys.entity.Request;
import org.sede.servicio.qys.entity.RespuestaTipo;
import org.sede.servicio.qys.entity.Service;
import org.sede.servicio.qys.entity.SolicitudInformacionPublica;
import org.sede.servicio.qys.entity.UtilsQyS;
import org.sede.servicio.qys.entity.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

/**
 * Clase que implementa la interfaz Quejas y sugerencias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Repository
@Transactional(ConfigQys.TM)
public class QySDaoImpl extends GenericDAOImpl <Request, BigDecimal> implements QySDao {
	
	/**
	 *  variable messageSource
	 */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 *  variable categorias excluidas 
	 */
	/**
	 * 
	 */
	private static final BigDecimal[] excludedCategories = new BigDecimal[]{
		new BigDecimal(2),//INTERNAL
		new BigDecimal(109740032),//Admin. Electrónica: Indra
		new BigDecimal(109740033),//Admin. Electrónica: IyD
		new BigDecimal(109740034),//Admin. Electrónica: telefónica
		new BigDecimal(4849667),//Infraestructuras:Coordinacion
		new BigDecimal(5144584),//Cultura: Concursos (mal)
		new BigDecimal(106954752),//Calidad de contenidos
		new BigDecimal(4030464),//General: Atención al ciudadano
		new BigDecimal(184647680),//Gestión web
		new BigDecimal(15564801),//Jurídico Servicios Públicos
		new BigDecimal(188088320),//Oficina Técnica Infraestructuras
		new BigDecimal(97779713),//Sección Señalización
		new BigDecimal(4030465),//Agua
		new BigDecimal(4849674),//Prueba
		new BigDecimal(5144580),//Zaragoza Cultural
//		new BigDecimal(30000),//Consultas de expedientes archivados
		new BigDecimal(5144583)//Publicaciones y concursos
	};
	
	
	/**
	 * Metodo setEntityManager
	 * 
	 * @param entityManager
	 */
	@PersistenceContext(unitName=ConfigQys.ESQUEMA)
	public void setEntityManager(EntityManager entityManager) {
		this.setEm(entityManager);
	}
	/**
	 *  variable logger 
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(QySDao.class);


	/**
	 *  variable categorias excluidas 
	 */
	private static final String EXCLUDEDCATEGORYES = "109740032,109740033,109740034,4849667,5144584,30000,30001,30002,30003,30004,30005,30006,30007,30008";

	/**
	 *  variable categorias excluidas no asignadas
	 */
	private static final String EXCLUDEDCATEGORIASUNASSIGNED = "2," + EXCLUDEDCATEGORYES;
	
	/**
	 *  variable validator 
	 */
	private static Validator validator;
	/** 
	 * Metodo validar
	 * 
	 */
	public Set<ConstraintViolation<Object>> validar(Object registro) {
		ValidatorFactory factory = Validation.byDefaultProvider().configure().traversableResolver(new JPAIgnoreTraversableResolver()).buildValidatorFactory();
	    validator = factory.getValidator();
		return validator.validate(registro);
	}
	
	/**
	 * Metodo validar solicitud informacion
	 * 
	 */
	public Set<ConstraintViolation<Object>> validarSolicitudInformacion(Object registro) {

	    SolicitudInformacionPublica solicitud = (SolicitudInformacionPublica) registro;
	    HibernateValidatorConfiguration configuration = Validation
		        .byProvider(HibernateValidator.class)
		        .configure().traversableResolver(new JPAIgnoreTraversableResolver());
	    ConstraintMapping constraintMapping = configuration.createConstraintMapping();
	    TypeConstraintMappingContext<SolicitudInformacionPublica> tipo = constraintMapping.type(SolicitudInformacionPublica.class);
    	if (solicitud.getEsRepresentante() == Boolean.TRUE) {
    		tipo.property("nombreRepresentado", ElementType.FIELD).constraint(new NotEmptyDef())
    		        .property("apellidosRepresentado", ElementType.FIELD).constraint(new NotEmptyDef())
    		        .property("dniRepresentado", ElementType.FIELD).constraint(new NotEmptyDef())
    		        .property("razonSocial", ElementType.FIELD).constraint(new NotEmptyDef())
    		        .property("cif", ElementType.FIELD).constraint(new NotEmptyDef());
    		
    		
    	}
    	if (solicitud.getEsConsultaExpediente() == Boolean.TRUE) {
    		tipo.property("address_string", ElementType.FIELD).constraint(new NotEmptyDef());
    	}
    	if (solicitud.getEsNumeroExpediente() == Boolean.TRUE) {
    		tipo.property("numExpediente", ElementType.FIELD).constraint(new SizeDef().max(7).message("El número de expediente debe tener como mucho 7 dígitos"));
    		tipo.property("year", ElementType.FIELD).constraint(new SizeDef().min(4).max(4).message("El año del expediente debe tener 4 dígitos"));
    		tipo.property("numExpediente1", ElementType.FIELD).constraint(new SizeDef().max(7).message("El número de expediente debe tener como mucho 7 dígitos"));
    		if (StringUtils.isNotEmpty(solicitud.getYear1())) {
    			tipo.property("year1", ElementType.FIELD).constraint(new SizeDef().min(4).max(4).message("El año del expediente debe tener 4 dígitos"));
    		}
    		
    		tipo.property("numExpediente2", ElementType.FIELD).constraint(new SizeDef().max(7).message("El número de expediente debe tener como mucho 7 dígitos"));
    		if (StringUtils.isNotEmpty(solicitud.getYear2())) {
    			tipo.property("year2", ElementType.FIELD).constraint(new SizeDef().min(4).max(4).message("El año del expediente debe tener 4 dígitos"));
    		}
    		
    	}
    	configuration.addMapping(constraintMapping);
    	validator = configuration.addMapping(constraintMapping)
		        .buildValidatorFactory()
		        .getValidator(); 	
	    return validator.validate(registro);
		
	}
	
	/**
	 *  variable mapeos 
	 */
	private static HashMap<String, String> mapeos = new HashMap<String, String>();
	static {
		mapeos.put("service_request_id", "hb_re.rqt_requestnumber");
		mapeos.put("title", "rqt_creationdescription");
		mapeos.put("description", "rqt_creationdescription");
		mapeos.put("requested_datetime", "hb_re.rqt_requestdate");
		mapeos.put("updated_datetime", "acciones.fecha");
		mapeos.put("status", "hb_st.RST_NAME");
		mapeos.put("status_admin", "hb_st.RST_NAME");
	}

	/**
	 * Metodo buscar y contar servicios
	 * 
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Service> searchAndCountService() throws SQLException {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<SearchResult<Service>>() {					
			public SearchResult<Service> execute(Connection connection) throws SQLException {
		
			CallableStatement st = null;
			try {
				st = connection.prepareCall(crearStatement("SERVICES", "GET", 4));
				st.setInt(1, 100);
				st.setInt(2, 0);
				st.registerOutParameter(3, OracleTypes.CLOB);
				st.registerOutParameter(4, OracleTypes.NUMBER);
	            st.execute();
	
	            TransformadorXml trans = new TransformadorXml();
	            @SuppressWarnings("unchecked")
	            SearchResult<Service> results =  (SearchResult<Service>)trans.pasarAObjeto(st.getString(3), true, SearchResult.class, Service.class);
				results.setResult(results.getResult());
				results.setRows(results.getResult().size());
				results.setTotalCount(st.getInt(4));
				return results;	
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return null;
			} catch (FormatoNoSoportadoException e) {
				logger.error(e.getMessage());
				return null;
			} finally {
				if (st != null) {
					st.close();
				}
			}
			}
		});
	}

	/**
	 * Metodo buscar y contar servicios
	 * 
	 * @param all
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Service> searchAndCountService(final String all) throws SQLException {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<SearchResult<Service>>() {
			public SearchResult<Service> execute(Connection connection) throws SQLException {
				Statement st = null;
				ResultSet rs = null;
				try {

					String sql;
					if (all == null) {
						sql = "(SELECT nvl2(c1.cal_hbid,c1.cal_hbid,c.CAL_HBID) as service_code,"
								+"c.CAL_NAME || nvl2(c1.cal_name,' > ' || c1.cal_name ,'') service_name,"
								+"d.cat_templaterequestdescription as description "
								+"FROM HBCATEGORYLEVELS c, hbcategories d, "
								+"HBCATEGORYLEVELS c1, hbcategories d1 "
								+"where "
								+ "c1.CAL_ISCATEGORY(+) > 0 and "
								+"c.cal_hbid not in(2,"
								+ excludedCategoriesToString()
								+ ") "
								+"and c.cal_hbid=d.cat_hbid(+) "
								+"and c.cal_parent=3899392 "
								+"and c1.cal_hbid=d1.cat_hbid(+) "
								+"and c1.cal_parent(+)=c.cal_hbid"
								+") union ("
								+"SELECT c.cal_hbid as service_code, c.cal_name ||' > - Por defecto - ' as service_name,"
								+"d.cat_templaterequestdescription as description "
								+"FROM HBCATEGORYLEVELS c, hbcategories d "
								+"where "
								+"c.cal_parent=3899392 "
								+"and c.cal_hbid=d.cat_hbid(+) "
								+"and c.cal_hbid in (select c1.cal_parent from HBCATEGORYLEVELS c1 where c1.cal_iscategory>0) "
								+") order by 2 asc ";
					} else {
						sql = "SELECT nvl2(c2.cal_hbid,c2.cal_hbid,nvl2(c1.cal_hbid,c1.cal_hbid,c.CAL_HBID)) as service_code, "
								+ "c.CAL_NAME || nvl2(c1.cal_name,' > ' || c1.cal_name || nvl2(c2.cal_name,' > '|| c2.cal_name,''),'') service_name, "
								+ "d.cat_templaterequestdescription as description "
								+ "FROM HBCATEGORYLEVELS c, hbcategories d,"
								+ "HBCATEGORYLEVELS c1, hbcategories d1, "
								+ "HBCATEGORYLEVELS c2, hbcategories d2 "
								+ "where "
								+ "c.cal_hbid=d.cat_hbid(+) "
								+ "and c.cal_parent=3899392 "
								+ "and c1.cal_hbid=d1.cat_hbid(+) "
								+ "and c2.cal_hbid=d2.cat_hbid(+) "
								+ "and c1.cal_parent(+)=c.cal_hbid "
								+ "and c2.cal_parent(+)=c1.cal_hbid ";
					}

					st = connection.prepareStatement(sql);
					rs = st.executeQuery(sql);
					ArrayList<Service> listado = new ArrayList<Service>();
					while (rs.next()) {
						Service s = new Service();
						s.setService_code(rs.getString(1));
						s.setService_name(rs.getString(2));
						listado.add(s);
					}
					SearchResult<Service> resultado = new SearchResult<Service>();
					resultado.setResult(listado);
					resultado.setStart(0);
					resultado.setTotalCount(listado.size());
					resultado.setRows(listado.size());
					return resultado;
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
				}
			}


		});
	}
	/**
	 * Metodo categorias excluidas como string
	 * @return
	 */
	private String excludedCategoriesToString() {
		StringBuilder xhtml = new StringBuilder();
		for (int x = 0; x < excludedCategories.length; x++) {
			if (x > 0) {
				xhtml.append(",");
			}
			xhtml.append(excludedCategories[x]);
		}
		return xhtml.toString();
	}
	/**
	 * Metodo resultados de busqueda
	 * 
	 * @param userId
	 * @return
	 */
	public SearchResult<RespuestaTipo> searchAndCountRespuestaTipo(final String userId) {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<SearchResult<RespuestaTipo>>() {
			public SearchResult<RespuestaTipo> execute(Connection connection) throws SQLException {
				PreparedStatement st = null;
				ResultSet rs = null;
				try {
					String sql = "select tipo, texto from respuestastipo where user_id=?";
					st = connection.prepareStatement(sql);
					st.setBigDecimal(1, new BigDecimal(userId));
					rs = st.executeQuery();
					ArrayList<RespuestaTipo> listado = new ArrayList<RespuestaTipo>();
					while (rs.next()) {
						RespuestaTipo s = new RespuestaTipo();
						s.setId(rs.getInt(1));
						s.setText(rs.getString(2));
						listado.add(s);
					}
					SearchResult<RespuestaTipo> resultado = new SearchResult<RespuestaTipo>();
					resultado.setResult(listado);
					resultado.setStart(0);
					resultado.setTotalCount(listado.size());
					resultado.setRows(listado.size());
					return resultado;
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
				}
			}
		});
	}
	/**
	 * Metodo detalle
	 * 
	 * @param id
	 * @param usuarioTicketing
	 * @return
	 * @throws SQLException
	 */
	public Object detalle(final BigDecimal id, final String usuarioTicketing) throws SQLException {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<Object>() {					
			public Object execute(Connection connection) throws SQLException {
				CallableStatement st = null;
				try {
					st = connection.prepareCall(crearStatement("REQUESTS", "DETALLE", 3));
					if (id == null) {
						st.setNull(1, OracleTypes.NUMBER);
					} else {
						st.setBigDecimal(1, id);	
					}
					if (usuarioTicketing == null) {
						st.setNull(2, OracleTypes.NUMBER);
					} else {
						st.setBigDecimal(2, new BigDecimal(usuarioTicketing));	
					}
					st.registerOutParameter(3, OracleTypes.CLOB);
		            st.executeQuery();
		            TransformadorXml trans = new TransformadorXml();
					return trans.pasarAObjeto(st.getString(3), false, Request.class, Accion.class, Adjunto.class);
				} catch (SQLException e) {
					if (e.getMessage().indexOf("ORA-01403") >= 0) {
						return new Mensaje(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("generic.notfound", null, LocaleContextHolder.getLocale()));
					}
					return new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage());
				} catch (FormatoNoSoportadoException e) {
					return new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
				} finally {
					if (st != null) {
						st.close();
					}
				}
			}
		});
	}
	

	/**
	 * Metodo crear consulta
	 * 
	 * @param modulo
	 * @param proc
	 * @param numParametros
	 * @return
	 * @throws SQLException
	 */
	protected String crearStatement(String modulo,String proc,int numParametros) {
		StringBuilder sb = new StringBuilder("{call ");
		sb.append("PCK_QYS_" + modulo);
		sb.append(".");
		sb.append(proc);
		sb.append("(");
		for (int i = 1; i <= numParametros; i++) {
			if (i > 1)
				sb.append(",");
			sb.append("?");
		}
		logger.debug("PL/SQL:{}", sb.toString());
		return sb.append(")}").toString();
		
	   }
	/**
	 * Metodo nombres para BBDD
	 * 
	 * @param sort
	 * @return
	 */
	public String nombresParaBBDD(String sort) {
		StringBuilder retorno = new StringBuilder();
		if (sort != null && sort.length() > 0) {
			
			String[] palabras = sort.split(","); 
			for (int i = 0; i < palabras.length; i++) {
				if (i > 0){
					retorno.append(",");
				}
				
				String atributo = palabras[i].trim().substring(0, palabras[i].trim().indexOf(' '));
				String valor = palabras[i].trim().substring(palabras[i].trim().indexOf(' ') + 1, palabras[i].trim().length());
				retorno.append(mapeos.get(atributo.trim()));
				if (valor.trim().length() > 1) {
					retorno.append(" " + valor.trim());
				} else {
					retorno.append(" asc");
				}
			}
			return retorno.toString();	
		} else {
			return "hb_re.rqt_requestdate desc";
		}
	}
	
	/**
	 * Metodo crear
	 *  
	 * @param req
	 * @param operacion
	 * @param usuarioAdmin
	 * @param gczUsuario
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws FormatoNoSoportadoException
	 */
	public Request crear(final Request req, final String operacion, final String usuarioAdmin, final String gczUsuario, final Long userId) throws SQLException, FormatoNoSoportadoException {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<Request>() {					
			public Request execute(Connection connection) throws SQLException {
				
			CallableStatement st = null;
			try {
				int i = 0;
								
				if (UtilsQyS.OPGUARDAR.equals(operacion)) {
					st = connection.prepareCall(crearStatement("REQUESTS", operacion, 30));
					st.setBigDecimal(++i, req.getService_request_id());
				} else {
					st = connection.prepareCall(crearStatement("REQUESTS", UtilsQyS.OPCREAR, 30));
				}
				
				if (req.getService_code() == null) {
					st.setNull(++i, OracleTypes.NUMBER);
				} else {
					st.setBigDecimal(++i, req.getService_code());	
				}
				if (req.getEmail() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getEmail());	
				}
				if (req.getAddress_string() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getAddress_string());	
				}
				if (req.getAddress_id() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getAddress_id());	
				}
				if (req.getAccount_id() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getAccount_id());	
				}
				if (req.getFirst_name() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getFirst_name());	
				}
				if (req.getLast_name() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getLast_name());	
				}
				
				if (req.getUser_address() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getUser_address());	
				}
				if (req.getPhone() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getPhone());	
				}
				if (req.getTitle() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getTitle());	
				}
				if (req.getNotes() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getNotes());	
				}
				if (req.getDescription() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getDescription());	
				}
				if (req.getMedia_name() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getMedia_name());	
				}
				if (req.getMedia_description() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getMedia_description());	
				}
				if (req.getVisible() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getVisible());	
				}
				if (req.getX() == null) {
					st.setNull(++i, OracleTypes.DOUBLE);
				} else {
					st.setDouble(++i, req.getX());	
				}
				if (req.getY() == null) {
					st.setNull(++i, OracleTypes.DOUBLE);
				} else {
					st.setDouble(++i, req.getY());	
				}
				
				
				if (req.getAgency_responsible_code() == null) {
					st.setNull(++i, OracleTypes.DOUBLE);
				} else {
					st.setBigDecimal(++i, req.getAgency_responsible_code());	
				}
				if (req.getType() == null) {
					if (UtilsQyS.OPGUARDAR.equals(operacion)) {
						st.setNull(++i, OracleTypes.NUMERIC);
					} else {
						st.setBigDecimal(++i, new BigDecimal(1));
					}
				} else {
					st.setBigDecimal(++i, req.getType());	
				}
				if (req.getPriority() == null) {
					if (UtilsQyS.OPGUARDAR.equals(operacion)) {
						st.setNull(++i, OracleTypes.NUMERIC);
					} else {
						st.setBigDecimal(++i, new BigDecimal(2));
					}
				} else {
					st.setBigDecimal(++i, req.getPriority());	
				}
				
				if (req.getOrigin() == null) {
					if (UtilsQyS.OPGUARDAR.equals(operacion)) {
						st.setNull(++i, OracleTypes.NUMERIC);
					} else {
						st.setBigDecimal(++i, new BigDecimal(3));
					}
				} else {
					st.setBigDecimal(++i, req.getOrigin());	
				}
				if (req.getValidated() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getValidated());	
				}
				if (req.getZona_inspeccion() == null) {
					st.setNull(++i, OracleTypes.NUMERIC);
				} else {
					st.setBigDecimal(++i, new BigDecimal(req.getZona_inspeccion()));	
				}
				if (req.getExpected_resolution_datetime() == null) {
					st.setNull(++i, OracleTypes.DATE);
				} else {
					st.setTimestamp(++i, new java.sql.Timestamp(req.getExpected_resolution_datetime().getTime()));	
				}
				if (usuarioAdmin == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, usuarioAdmin);	
				}
				
				if (UtilsQyS.OPCREAR.equals(operacion)) {
					if (gczUsuario == null) {
						st.setNull(++i, OracleTypes.VARCHAR);
					} else {
						st.setString(++i, gczUsuario);	
					}
				}
				if (req.getAnswer_requested() == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, req.getAnswer_requested());	
				}
				if (req.getId_cita() == null) {
					st.setNull(++i, OracleTypes.NUMBER);
				} else {
					st.setBigDecimal(++i, req.getId_cita());	
				}
				
				if (UtilsQyS.OPGUARDAR.equals(operacion)) {
					if (req.getId_cat_sip() == null) {
						st.setNull(++i, OracleTypes.NUMBER);
					} else {
						st.setBigDecimal(++i, req.getId_cat_sip());	
					}
				}
				if (UtilsQyS.OPCREAR.equals(operacion)) {
					if (userId == null) {
						st.setNull(++i, OracleTypes.NUMBER);
					} else {
						st.setLong(++i, userId);	
					}
				}
				st.registerOutParameter(++i, OracleTypes.CLOB);
	            st.executeQuery();
	            TransformadorXml trans = new TransformadorXml();
				return (Request)trans.pasarAObjeto(st.getString(i), true, SearchResult.class, Request.class);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return null;
			} finally {
				if (st != null) {
					st.close();
				}
			}
			}
		});
	}
	/**
	 * 
	 * Metodo get totales para año actual
	 * 
	 * @return
	 */
	public Integer getTotalYearActual() {
		Query q = em().createNativeQuery("SELECT count(c.cal_hbid) as total FROM HBCATEGORYLEVELS c, hbrequests r, HBREQUESTSTATES hb_st where r.cat_hbid=c.cal_hbid and r.RST_ID=hb_st.RST_HBID and c.cal_hbid not in(" + EXCLUDEDCATEGORIASUNASSIGNED + ") and extract(YEAR from r.rqt_requestdate) = to_char(sysdate, 'YYYY')");
		return ((BigDecimal)q.getSingleResult()).intValue();
	}

	/**
	 * Metodo encontrar por token
	 * 
	 * @param identifier
	 * @param token
	 * @return
	 * @throws SQLException
	 */
	
	public ResponseEntity<?> findByToken(BigDecimal identifier, String token) throws SQLException {
		try {
			Query q = em().createNativeQuery("SELECT RQT_REQUESTNUMBER FROM hbrequests where RQT_REQUESTNUMBER=? and token=?").setParameter(1, identifier).setParameter(2, token);
			BigDecimal id = ((BigDecimal)q.getSingleResult());
			Object ret = detalle(id, UtilsQyS.ID_MAIN_ADMIN.toString());
			if (ret instanceof Request) {
				return ResponseEntity.ok(ret);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);
			}
		} catch (NoResultException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND.value(), "Registro no encontrado."));
		}
	}

	/**
	 * Metodo acciones
	 * 
	 * @param id
	 * @param accion
	 * @param texto
	 * @param fecha
	 * @param idExterno
	 * @param usuarioAdmin
	 * @param uuid
	 * @param clientId
	 * @param internalStatus
	 * @return
	 * @throws SQLException
	 * @throws FormatoNoSoportadoException
	 */
	public ResponseEntity<?> acciones(final BigDecimal id, final Integer accion, final String texto, final Date fecha, final BigDecimal idExterno, final String idInterno, final String usuarioAdmin, final String uuid, final String clientId, final Integer internalStatus) throws SQLException, FormatoNoSoportadoException {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<ResponseEntity<?>>() {					
			public ResponseEntity<?> execute(Connection connection) throws SQLException {
			CallableStatement st = null;
			try {
				int i = 0;
				st = connection.prepareCall(crearStatement("REQUESTS", "ACCIONES", 11));
				
				st.setBigDecimal(++i, id);
				st.setBigDecimal(++i, new BigDecimal(accion));
			
				if (texto == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, texto);	
				}
				
				if (fecha == null) {
					st.setNull(++i, OracleTypes.DATE);
				} else {
					st.setTimestamp(++i, new java.sql.Timestamp(fecha.getTime()));	
				}
				if (idExterno == null) {
					st.setNull(++i, OracleTypes.NUMERIC);
				} else {
					st.setBigDecimal(++i, idExterno);	
				}
				if (idInterno == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, idInterno);	
				}
				if (usuarioAdmin == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, usuarioAdmin);	
				}
				if (clientId == null) {
					st.setNull(++i, OracleTypes.VARCHAR);
				} else {
					st.setString(++i, clientId);	
				}
				if (uuid == null) {
					st.setNull(++i, OracleTypes.VARCHAR);	
				} else {
					st.setString(++i, uuid);
				}
				if (internalStatus == null) {
					st.setNull(++i, OracleTypes.NUMERIC);	
				} else {
					st.setBigDecimal(++i, new BigDecimal(internalStatus));
				}
				st.registerOutParameter(++i, OracleTypes.CLOB);
	            st.executeQuery();
	            
	            TransformadorXml trans = new TransformadorXml();
				Request results =  (Request)trans.pasarAObjeto(st.getString(i), true, SearchResult.class, Request.class);
				
				
//				if (accion == 5 && results.getEmail() != null) {
//					String tituloMail = "Sistema de quejas y sugerencias";
//					if ("250544128".equals(results.getType())) {
//						// texto par enviar en caso de solicitud de informacion publica
//						tituloMail = "Solicitud de Información pública";
//						texto = texto + "\n Puede cumplimentar la información accediendo a https://www.zaragoza.es/ciudad/ticketing/token_Ticketing?token=" + uuid;
//					} else {
//						texto = texto + "\n Para cualquier duda sobre esta consulta puede hacérnosla llegar a través del siguiente enlace:\n https://www.zaragoza.es/ciudad/ticketing/token_Ticketing?token=" + uuid;
//					}
//					Funciones.sendMail("Ayuntamiento de Zaragoza. " + tituloMail + ". Solicitud: " + results.getService_request_id() + ". Asunto: " + results.getTitle(), texto, results.getEmail(), "", "text/plain");				
//					
//				}
				
				return ResponseEntity.ok(results);
			} catch (FormatoNoSoportadoException e) {
				logger.error(e.getMessage());
				return null;
			} finally {
				if (st != null) {
					st.close();
				}
			}
			}
		});
	}
	
	/**
	 * Metodo enviar capaz
	 * 
	 * @param registro
	 * @throws SQLException
	 * @throws FormatoNoSoportadoException
	 * @throws ParseException
	 */
	
	public void enviarCapaz(SolicitudInformacionPublica registro) throws SQLException, FormatoNoSoportadoException, ParseException {
		String json = generarJson(registro);
		
		Respuesta resp;
		if (StringUtils.isEmpty(Propiedades.getCapazUri())) {
			resp = new Respuesta();
			resp.setStatus(HttpStatus.BAD_REQUEST.value());
			resp.setContenido("ERROR Al enviar a CAPAZ. Servicio Rest no definido. Contenido enviado:" + json);			
		} else {
			HashMap<String, String> headers = new HashMap<String, String>();
			headers.put("authorization", Propiedades.getCapazToken());
			headers.put("content-type", MimeTypes.JSON);
			logger.info("uri:{}", Propiedades.getCapazUri());
			logger.info("authorization:{}", Propiedades.getCapazToken());
			logger.info("content-type:{}", MimeTypes.JSON);
			logger.info("body:{}", json);
			resp = Rest.getInstance().postExterno(Propiedades.getCapazUri(), json, headers, null, "solicitud_informacion");
			resp.setContenido("Enviado: " + json + " Respuesta:" + resp.getContenido());
			logger.info("CODIGO RESPUESTA: {}", resp.getStatus());
			if (resp.getStatus() != HttpStatus.OK.value() && resp.getStatus() != HttpStatus.CREATED.value()) {
				try {
					Funciones.sendMail("Error al cargar en CAPAZ la solicitud: " + registro.getId(), "ERROR:" + resp.getContenido(), Propiedades.getCapazErrorMail(), "", "text");
				} catch (AddressException e) {
					logger.error(e.getMessage());
				} catch (SendFailedException e) {
					logger.error(e.getMessage());
				} catch (MessagingException e) {
					logger.error(e.getMessage());
				}
			}
		}
		this.acciones(registro.getId(), 10, resp.getContenido(), null, null, null, "" + 3506176, null, null, null);
	}

	/**
	 * Metodo generar json de un registro
	 * 
	 * @param registro
	 * @return
	 * @throws ParseException
	 */
	private String generarJson(SolicitudInformacionPublica registro) throws ParseException {
		StringBuilder json = new StringBuilder();
		
		Date time = ConvertDate.string2Date(registro.getTimestamp(), ConvertDate.DATETIME_FORMAT);
		final SimpleDateFormat sdf = new SimpleDateFormat(ConvertDate.ISO8601_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String fechaGMT = sdf.format(time);
		
		json.append("{"
				+ (StringUtils.isEmpty(registro.getAddress_string()) ? "" : "\"address\":\"" + Funciones.escapeJson(registro.getAddress_string()) + "\",")
				+ (StringUtils.isEmpty(registro.getDescription()) ? "" : "\"description\":\"" + Funciones.escapeJson(registro.getDescription()) + "\",")
				+ (StringUtils.isEmpty(registro.getMail()) ? "" : "\"email\":\"" + Funciones.escapeJson(registro.getMail()) + "\",")
				+ (StringUtils.isEmpty(registro.getDni()) ? "" : "\"idNumber\":\"" + Funciones.escapeJson(registro.getDni()) + "\",")
				+ (StringUtils.isEmpty(registro.getNombre()) ? "" : "\"name\":\"" + Funciones.escapeJson(registro.getNombre()) + (StringUtils.isEmpty(registro.getApellidos()) ? "" : " " + Funciones.escapeJson(registro.getApellidos())) + "\",")
				+ (registro.getId() == null ? "" : "\"notes\":\"Numero de solicitud: " + registro.getId() + "\",")
				+ (StringUtils.isEmpty(registro.getTelefono()) ? "" : "\"phone\":\"" + Funciones.escapeJson(registro.getTelefono()) + "\",")
				+ "\"referenceCodes\":[\"" + registro.getNumExpediente() + "/" + registro.getYear() + "\""
					+ (StringUtils.isEmpty(registro.getNumExpediente1()) ? "" : ",\"" + registro.getNumExpediente1() + "/" + registro.getYear1() + "\"")
					+ (StringUtils.isEmpty(registro.getNumExpediente2()) ? "" : ",\"" + registro.getNumExpediente2() + "/" + registro.getYear2() + "\"")
				+ "],"
				+ (StringUtils.isEmpty(registro.getTimestamp()) ? "" : "\"requestDate\":\"" + fechaGMT + "\",")
				+ "\"requestType\":\"EXTERNAL_REQUEST\","
				+ "\"department\":\"Solicitud de acceso a Informacion pública\""
				+ "}");
		
		return json.toString();
	}

	/**
	 * Metodo datos quejas
	 * 
	 * @param start_date
	 * @param end_date
	 * @param idsCategoria
	 * @param texto
	 * @param usuarioTicketing
	 * @return
	 * @throws SQLException
	 * @throws FormatoNoSoportadoException
	 */
	public Object datosQuejas(final Date start_date, final Date end_date, final String idsCategoria, final String texto, final String usuarioTicketing) throws SQLException, FormatoNoSoportadoException {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<SearchResult<ServiceConsulta>>() {
			public SearchResult<ServiceConsulta> execute(Connection connection) throws SQLException {
				CallableStatement st = null;
				try {
					int i = 0;
					st = connection.prepareCall(crearStatement("REQUESTS", "INFORMES", 6));
					if (start_date == null) {
						st.setNull(++i, OracleTypes.DATE);
					} else {
						st.setDate(++i, new java.sql.Date(start_date.getTime()));
					}
					if (end_date == null) {
						st.setNull(++i, OracleTypes.DATE);
					} else {
						st.setDate(++i, new java.sql.Date(end_date.getTime()));
					}
					if (idsCategoria == null) {
						st.setNull(++i, OracleTypes.VARCHAR);
					} else {
						st.setString(++i, idsCategoria);
					}
					if (texto == null) {
						st.setNull(++i, OracleTypes.VARCHAR);
					} else {
						st.setString(++i, texto);
					}
					if (usuarioTicketing == null) {
						st.setNull(++i, OracleTypes.NUMBER);
					} else {
						st.setBigDecimal(++i, new BigDecimal(usuarioTicketing));
					}
					st.registerOutParameter(++i, OracleTypes.CLOB);
					st.executeQuery();

					TransformadorXml trans = new TransformadorXml();
					@SuppressWarnings("unchecked")
					SearchResult<ServiceConsulta> results =  (SearchResult<ServiceConsulta>)trans.pasarAObjeto(st.getString(i), true, SearchResult.class, ServiceConsulta.class, BarrioDato.class, ExternoDato.class);
					return results;
				} catch (Exception e) {
					logger.error(e.getMessage());
					return null;

				} finally {
					if (st != null) {
						st.close();
					}
				}
			}
		});
	}

	/**
	 * Metodo statistics
	 * 
	 * @param serviceCode
	 * @param year
	 * @param type
	 * @return
	 */
	
	public SearchResult<ServiceDatos> statistics(String serviceCode, Integer year, BigDecimal type) {
		try {
			StringBuilder sql = new StringBuilder();
			String categoria = serviceCode == null ? "" : serviceCode;
			String cate = (!"".equals(categoria) ? " and cal_HBID=" + categoria
					: "");
			if (type != null) {
				sql.append("SELECT c.cal_hbid,c.cal_name,nvl2(r.rqt_closedate,'closed','open'), count(*) as total, c.cal_parent "
						+ "FROM HBCATEGORYLEVELS c, hbrequests r, HBREQUESTSTATES hb_st "
						+ "where r.cat_hbid=c.cal_hbid and r.RST_ID=hb_st.RST_HBID(+) and r.rst_id<>5 "
						+ "and c.cal_hbid not in(" + EXCLUDEDCATEGORYES + ") "
						+ "and extract(YEAR from r.rqt_requestdate) = "
						+ year
						+ cate
						+ (type == null ? "" : " and r.rty_hbid=" + type + " ")
						+ " group by c.cal_hbid,c.cal_name,nvl2(r.rqt_closedate,'closed','open'),c.cal_parent "
						+ "order by c.cal_name asc");
			} else {
				sql.append("SELECT c.cal_hbid,c.cal_name,nvl2(r.rqt_closedate,'closed','open'), count(*) as total, c.cal_parent "
					+ "FROM HBCATEGORYLEVELS c, hbrequests r, HBREQUESTSTATES hb_st "
					+ "where r.cat_hbid=c.cal_hbid and r.RST_ID=hb_st.RST_HBID(+) "
					+ "and c.cal_hbid not in(" + EXCLUDEDCATEGORIASUNASSIGNED + ") "
					+ "and extract(YEAR from r.rqt_requestdate) = " + year
					+ cate
					+ (type == null ? "" : " and r.rty_hbid=" + type + " ")
					+ " group by c.cal_hbid,c.cal_name,nvl2(r.rqt_closedate,'closed','open'),c.cal_parent "
					+ "order by c.cal_name asc");
			}
			HashMap<BigDecimal, ServiceDatos> listado = new LinkedHashMap<BigDecimal, ServiceDatos>();
			ServiceDatos otros = new ServiceDatos();
			otros.setTitle("Otros");
			otros.setId(new BigDecimal(1111111111));
			otros.setParent(new BigDecimal(3899392));
			otros.setOpen(new BigDecimal(0));
			otros.setClosed(new BigDecimal(0));			
			listado.put(new BigDecimal(1111111111), otros);
			Query query = this.em().createNativeQuery(sql.toString());
			@SuppressWarnings("unchecked")
			List<Object> list = query.getResultList();
			for (Iterator<Object> iterador = list.iterator(); iterador.hasNext();) {
				Object[] row = (Object[])iterador.next();
			
				ServiceDatos s = new ServiceDatos();
				BigDecimal key = (BigDecimal)row[0];
				boolean isExcluded = this.isExcludedCategory(key);
				if (listado.containsKey(key)) {
					s = listado.get(key);
				} else {
					
					if (isExcluded) {
						s = otros;
					} else {
						s.setId(key);
						s.setTitle((String)row[1]);
						s.setParent((BigDecimal)row[4]);
					}
				}
	
				if ("closed".equals((String)row[2])) {
					if (isExcluded) {
						s.setClosed(s.getClosed().add((BigDecimal)row[3]));
					} else {
						s.setClosed((BigDecimal)row[3]);
					}
				} else {
					if (isExcluded) {
						s.setOpen(s.getOpen().add((BigDecimal)row[3]));
					} else {
						s.setOpen((BigDecimal)row[3]);
					}
				}
				listado.put(key, s);
			}
			listado = this.addLevel(listado, new BigDecimal(3899392));
	
			List<ServiceDatos> data = new ArrayList<ServiceDatos>();
			for (Map.Entry<BigDecimal, ServiceDatos> entry : listado.entrySet()) {
			    data.add(entry.getValue());
			}
			SearchResult<ServiceDatos> resultado = new SearchResult<ServiceDatos>();
			resultado.setResult(data);
			resultado.setTotalCount(data.size());
			resultado.setRows(data.size());
			return resultado;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	/**
	 * Metodo ¿es una categoria excluida?
	 * @param key
	 * @return
	 */
	private boolean isExcludedCategory(BigDecimal key) {
		for (BigDecimal d : excludedCategories) {
			if (d.equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo anyadir nivel
	 * 
	 * @param listado
	 * @param categories
	 * @return
	 */
	private HashMap<BigDecimal, ServiceDatos> addLevel(HashMap<BigDecimal, ServiceDatos> listado, BigDecimal... categories) {
		Iterator<BigDecimal> it1 = listado.keySet().iterator();
		HashMap<BigDecimal, ServiceDatos> retorno = new HashMap<BigDecimal, ServiceDatos>();
		
		while (it1.hasNext()) {
			BigDecimal key = it1.next();
			ServiceDatos dato = listado.get(key);
			
			for (BigDecimal rootCategory : categories) {
				if (dato.getParent().equals(rootCategory)) {
					ServiceDatos datosHijos = calcularTotalHijos(dato, listado);
					retorno.put(dato.getId(), datosHijos);
				}
			}
		}
		return retorno;
	}
	
	/**
	 * Metodo calcular hijos de un servicio
	 * 
	 * @param padre
	 * @param listado
	 * @return
	 */
	private static ServiceDatos calcularTotalHijos(ServiceDatos padre, HashMap<BigDecimal, ServiceDatos> listado) {
		Iterator<BigDecimal> it1 = listado.keySet().iterator();
		ServiceDatos dato;
		while (it1.hasNext()) {
			BigDecimal key = it1.next();
			dato = listado.get(key);
			if (padre.getId().equals(dato.getParent())) {
				ServiceDatos datosHijos = calcularTotalHijos(dato, listado);
				
				if (padre.getClosed() == null) {
					padre.setClosed(datosHijos.getClosed());
				} else {
					padre.setClosed(padre.getClosed().add(datosHijos.getClosed() == null ? new BigDecimal(0) : datosHijos.getClosed()));
				}
				if (padre.getOpen() == null) {
					padre.setOpen(datosHijos.getOpen());
				} else {
					padre.setOpen(padre.getOpen().add(datosHijos.getOpen() == null ? new BigDecimal(0) : datosHijos.getOpen()));
				}
			}
		}
		return padre;
	}


	/**
	 * 
	 * Metodo obtener total
	 * 
	 * @param datos
	 * @return
	 */
	
	public ServiceDatos obtenerTotal(SearchResult<ServiceDatos> datos) {
		
		ServiceDatos d = new ServiceDatos();
		d.setOpen(new BigDecimal(0));
		d.setClosed(new BigDecimal(0));
		for (ServiceDatos res : datos.getResult()) {
			d.setClosed(d.getClosed().add(res.getClosed() == null ? new BigDecimal(0) : res.getClosed()));
			d.setOpen(d.getOpen().add(res.getOpen() == null ? new BigDecimal(0) : res.getOpen()));
		}
		return d;
	}

	/**
	 * Metodo obtener categorias con total
	 * 
	 * @param onlyPublic
	 * @param year
	 * @return
	 */
	
	public List<ServiceConsulta> obtenerCategoriasConTotal(boolean onlyPublic, String year) {
		Query query = this.em().createNativeQuery("select count(*), s.id, nvl2(r.rqt_closedate, 'Cerrada', 'Abierta') FROM hbrequests r, categoria_sip s "
				+ "WHERE " 
			    + "  r.id_cat_sip=s.id(+) AND "
				+ "  r.rty_hbid=" + SolicitudInformacionPublica.TIPOSOLICITUDINFORMACION.toString() + " AND " 
				+ "  r.rst_id<>5 and "
				+ "  r.cat_hbid not in(" + EXCLUDEDCATEGORYES + ") "
				+ (year == null ? "" : " and extract(YEAR from r.rqt_requestdate) = " + year + " ")
			    + (onlyPublic ? "  AND r.rqt_public='S' AND r.rqt_validated='S' AND r.cat_hbid<>1 AND r.cat_hbid<>2 " : "")
			+ "group by s.id,nvl2(r.rqt_closedate, 'Cerrada', 'Abierta') order by s.id asc");
		
		HashMap<Integer, ServiceConsulta> resultado = new HashMap<Integer, ServiceConsulta>();
		@SuppressWarnings("unchecked")
		List<Object> list = query.getResultList();
		for (Iterator<Object> iterador = list.iterator(); iterador.hasNext();) {
			
			Object[] row = (Object[])iterador.next();
			ServiceConsulta d;
			Integer id = row[1] == null ? 0 : ((BigDecimal) row[1]).intValue();
			if (resultado.containsKey(id)) {
				d = resultado.get(id);
			} else {
				d = new ServiceConsulta();
				d.setId(id);
				d.setTitle(InformacionPublicaController.tipoSip.get(d.getId()));
			}
			if ("Abierta".equals((String) row[2])) {
				d.setPendientes(((BigDecimal) row[0]).intValue());	
			} else {
				d.setResueltas(((BigDecimal) row[0]).intValue());
			}
			resultado.put(d.getId(), d);
		}
		return new ArrayList<ServiceConsulta>(resultado.values());
	}

	/**
	 * Metodo get categorias
	 * 
	 * @param rootCategory
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Category> getCategories(final String rootCategory) throws SQLException {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<SearchResult<Category>>() {
			public SearchResult<Category> execute(Connection connection) throws SQLException {
				CallableStatement st = null;
				try {
					st = connection.prepareCall(crearStatement("REQUESTS", "CATEGORIA", 2));
					if (rootCategory == null) {
						st.setNull(1, OracleTypes.NUMBER);
					} else {
						st.setBigDecimal(1, new BigDecimal(rootCategory));	
					}
					st.registerOutParameter(2, OracleTypes.CLOB);
		            st.executeQuery();
		            TransformadorXml trans = new TransformadorXml();
					@SuppressWarnings("unchecked")
					SearchResult<Category> results =  (SearchResult<Category>)trans.pasarAObjeto(st.getString(2), false, SearchResult.class, Category.class);
					results.setTotalCount(results.getResult().size());
					return results;
				} catch (FormatoNoSoportadoException e) {
					logger.error(Funciones.getStackTrace(e));
					return null;
				} finally {
					if (st != null) {
						st.close();
					}
				}
			}
		});
	}

	/**
	 * Metodo get categorias adyacentes
	 *  
	 * @param rootCategory
	 * @return
	 */
	
	public SearchResult<Category> getAdjacentCategories(BigDecimal rootCategory) {
		try {
			Query queryPermisosGrupo = this.em().createNativeQuery("select " 
						+ "c1.CAL_HBID as service_code, "
						+ "c1.CAL_NAME as service_name, "
						+ "d1.CAT_TEMPLATEREQUESTDESCRIPTION as service_description, "
						+ "usuario1.usr_hbid as agency_responsible_code, "
						+ "usuario1.per_firstname ||' ' || usuario1.per_lastname as agency_responsible, "
						+ "grupo1.SGP_HBID as group_code, "
						+ "grupo1.sgp_name as group_name, "
						+ "c1.cal_parent as service_parent_code, "
						+ "c1.cal_level as service_level, "
						+ "c1.autoassign as autoassign "
						+ "FROM  "
						+ "HBCATEGORYLEVELS c,HBCATEGORYLEVELS c1,hbcategories d1,hbcategoryscalinggroups cat_grupo1,hbscalinggroups grupo1,hbscalinggroupusers grupo_usuario1,hbusers usuario1 "                  
						+ "where  "
						+ "c.cal_hbid=? "
						+ "and c1.cal_hbid=d1.cat_hbid(+) " 
						+ "and c1.cal_parent=c.cal_parent "
						+ "and c1.cal_hbid=cat_grupo1.cat_hbid(+) "
						+ "and cat_grupo1.sgp_hbid=grupo1.sgp_hbid(+) " 
						+ "and grupo_usuario1.sgp_hbid(+)= grupo1.sgp_hbid " 
						+ "and grupo_usuario1.usr_hbid=usuario1.usr_hbid(+) "
						+ "and (grupo1.sgp_hbid != 1 or grupo1.sgp_hbid is null) "
						+ "order by c1.cal_name");
			@SuppressWarnings("unchecked")
			List<Object> permisos = queryPermisosGrupo.setParameter(0, rootCategory).getResultList();
			ArrayList<Category> categorias = new ArrayList<Category>();
			for (Iterator<Object> iterador = permisos.iterator(); iterador.hasNext();) {
				Object[] row = (Object[])iterador.next();
				Category c = new Category();
				c.setService_code((BigDecimal)row[0]);
				c.setService_name(row[1].toString());
				c.setService_description(row[2].toString());
				c.setAgency_responsible_code((BigDecimal)row[3]);
				c.setAgency_responsible(row[4].toString());
				c.setGroup_code((BigDecimal)row[5]);
				c.setGroup_name(row[6].toString());
				c.setService_parent_code((BigDecimal)row[7]);
				c.setService_level((BigDecimal)row[8]);
				c.setAutoassign(row[9].toString());
				categorias.add(c);
			}
			SearchResult<Category> resultado = new SearchResult<Category>();
			resultado.setResult(categorias);
			resultado.setStart(0);
			resultado.setRows(categorias.size());
			resultado.setTotalCount(categorias.size());
			return resultado;
		} catch (Exception e) {
			logger.error(Funciones.getStackTrace(e));
			return null;
		}
	}

	/**
	 * Metodo crear categoria
	 * 
	 * @param registro
	 * @return
	 */
	
	public ResponseEntity<?> crearCategory(Category registro) {
		registro.setService_code(obtenerIdCategoria());
		
		Query update = this.em().createNativeQuery("insert into hbcategorylevels (CAL_HBID,CAL_HBVERSION,CAL_PARENT,CAL_LEVEL,CAL_NAME,CAL_ISCATEGORY,AUTOASSIGN) "
				+ "values (?,0,?,?,?,1,?)");
		int i = 0;
		update.setParameter(++i, registro.getService_code());
		update.setParameter(++i, registro.getService_parent_code());
		update.setParameter(++i, registro.getService_level());
		update.setParameter(++i, registro.getService_name());
		update.setParameter(++i, registro.getAutoassign());
		if (update.executeUpdate() > 0) {
			
			Query descripcion = this.em().createNativeQuery("insert into hbcategories (CAT_HBID,CAT_TEMPLATEREQUESTDESCRIPTION) values (?, ?)");
			i = 0;
			descripcion.setParameter(++i, registro.getService_code());
			descripcion.setParameter(++i, registro.getService_description() == null ? " " : registro.getService_description());
			if (descripcion.executeUpdate() > 0) {
				
				Query grupo = this.em().createNativeQuery("insert into HBCATEGORYSCALINGGROUPS(CAT_HBID,SGP_HBID,CSG_PRIORITY) values(?,?,0)");
				i = -1;
				grupo.setParameter(++i, registro.getService_code());
				grupo.setParameter(++i, registro.getGroup_code());
				if (grupo.executeUpdate() > 0) {
					return ResponseEntity.ok(registro);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha asociado el grupo"));	
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha insertado la descripcion asociada"));	
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha modificado ningún registro"));	
		}
	}
	
	/**
	 * Metodo obtener id categoria 
	 * @return
	 */
	private BigDecimal obtenerIdCategoria(){
		try {
			Query query = this.em().createNativeQuery("select max(CAL_HBID)+1 from HBCATEGORYLEVELS where CAL_HBID<3000000");
			return (BigDecimal) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BigDecimal(0);
		}
	}

	/**
	 * Metodo guardar categoria
	 * 
	 * @param registro
	 * @return
	 */
	
	public ResponseEntity<?> saveCategory(Category registro) {
		try {
			
			Query update = this.em().createNativeQuery("update hbcategorylevels SET CAL_HBVERSION=CAL_HBVERSION+1, CAL_PARENT = ?,CAL_LEVEL = ?, CAL_NAME = ? ,AUTOASSIGN = ? "
					+ "where CAL_HBID=?");
			int i = 0;
			
			update.setParameter(++i, registro.getService_parent_code());
			update.setParameter(++i, registro.getService_level());
			update.setParameter(++i, registro.getService_name());
			update.setParameter(++i, registro.getAutoassign());
			
			update.setParameter(++i, registro.getService_code());
			if (update.executeUpdate() > 0) {
				Query descripcion = this.em().createNativeQuery("update hbcategories set CAT_TEMPLATEREQUESTDESCRIPTION = ? where CAT_HBID = ?");
				i = -1;
				descripcion.setParameter(++i, registro.getService_description() == null ? " " : registro.getService_description());
				descripcion.setParameter(++i, registro.getService_code());
				if (descripcion.executeUpdate() > 0) {
					Query grupo = this.em().createNativeQuery("update HBCATEGORYSCALINGGROUPS set SGP_HBID = ? where CAT_HBID = ?");
					i = -1;
					grupo.setParameter(++i, registro.getService_code());
					grupo.setParameter(++i, registro.getGroup_code());
					if (grupo.executeUpdate() > 0) {
						return ResponseEntity.ok(registro);
					} else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha asociado el grupo"));	
					}
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha modificado la descripcion"));
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha modificado ningún registro"));	
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		} 
	}

	/**
	 * Metodo almacenar adjunto
	 * 
	 * @param req
	 * @throws IOException
	 */
	
	public void almacenarAdjunto(Request req) throws IOException {
		if (req.getMedia_body() != null && req.getMedia_name() != null) {
			
			Integer year = Calendar.getInstance().get(Calendar.YEAR);
			
			File f = new File(Propiedades.getPathAplicacionesDisk() + "ticketing/" + year);
			if (!f.exists() || !f.isDirectory()) {
				f.mkdir();
			}
			
			Funciones.saveFile(Propiedades.getPathAplicacionesDisk() + "/ticketing/" + req.getMedia_name(), DatatypeConverter.parseBase64Binary(req.getMedia_body()));
		}
	}

	/**
	 * Metodo guardar
	 * 
	 * @param req
	 * @param usuarioAdmin
	 * @return
	 * @throws SQLException
	 * @throws FormatoNoSoportadoException
	 */
	
	public Request guardar(Request req, String usuarioAdmin) throws SQLException, FormatoNoSoportadoException {
		return crear(req, UtilsQyS.OPGUARDAR, usuarioAdmin, "GUARDAR", null);
	}

	/** 
	 * Metodo asociar
	 *
	 * @param id
	 * @param service_code
	 * @param agency_responsible_code
	 * @param parseInt
	 * @return
	 */
	
	public ResponseEntity<?> asociar(final BigDecimal id, final Integer service_code,
			final Integer agency_responsible_code, final int user_id) {
		
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<ResponseEntity<?>>() {					
			public ResponseEntity<?> execute(Connection connection) throws SQLException {
				CallableStatement st = null;
				try {
					int i = 0;
					st = connection.prepareCall(crearStatement("REQUESTS", "ASOCIAR", 4));
					st.setBigDecimal(++i, id);
					st.setBigDecimal(++i, new BigDecimal(service_code));
					st.setBigDecimal(++i, new BigDecimal(agency_responsible_code));
					st.setBigDecimal(++i, new BigDecimal(user_id));
		            st.executeQuery();
		            return ResponseEntity.ok(new Mensaje(HttpStatus.OK.value(), "Asociado correctamente"));
				} finally {
					if (st != null) {
						st.close();
					}
				}
			}
		});
	}

	/**
	 * Metod obtener estado de queja
	 * 
	 * @param id
	 * @return
	 */
	
	public Value obtenerEstadoDeQueja(BigDecimal id) {
		
		String sql = "SELECT r.rst_id,s.RST_NAME from hbrequests r,HBREQUESTSTATES s "
					+ "where r.RQT_REQUESTNUMBER=? and r.RST_ID=s.RST_HBID";
		Query q = em().createNativeQuery(sql).setParameter(1, id);
		
		@SuppressWarnings("unchecked")
		List<Object> list = q.getResultList();
		for (Iterator<Object> iterador = list.iterator(); iterador.hasNext();) {
			Object[] row = (Object[])iterador.next();
			return new Value(((BigDecimal)row[0]).toString(), (String) row[1]);
		}
		
		return null;
	}

	
	/**
	 * Metodo devolver estado anterior queja
	 * 
	 * @param id
	 * @param estadoAnterior
	 * @param mensaje
	 * @param usuarioAdmin
	 * @param clientId
	 * @return
	 */
	
	public ResponseEntity<?> devolverEstadoAnteriorQueja(final BigDecimal id,
			final Value estadoAnterior, final String mensaje, final String usuarioAdmin,
			final String clientId) {
		
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<ResponseEntity<?>>() {					
			public ResponseEntity<?> execute(Connection connection) throws SQLException {
				CallableStatement st = null;
				try {
					int i = 0;
					st = connection.prepareCall(crearStatement("REQUESTS", "ESTADOANTERIORQUEJA", 5));
					
					st.setBigDecimal(++i, id);
					st.setBigDecimal(++i, new BigDecimal(estadoAnterior.getKey()));
				
					if (mensaje == null) {
						st.setNull(++i, OracleTypes.VARCHAR);
					} else {
						st.setString(++i, mensaje);	
					}
					
					if (usuarioAdmin == null) {
						st.setNull(++i, OracleTypes.VARCHAR);
					} else {
						st.setString(++i, usuarioAdmin);	
					}
					if (clientId == null) {
						st.setNull(++i, OracleTypes.VARCHAR);
					} else {
						st.setString(++i, clientId);	
					}
					
		            st.executeQuery();
		            return ResponseEntity.ok(new Mensaje(HttpStatus.OK.value(), "Solicitud devuelta a estado anterior"));
				} finally {
					if (st != null) {
						st.close();
					}
				}
			}
		});
	}
	/**
	 * Metodo almacenar informe
	 * 
	 * @param informe
	 * @param fileName
	 * @throws IOException
	 * @throws JRException
	 */
	
	public void almacenarInforme(JasperPrint informe, String fileName) throws IOException, JRException {
		JasperExportManager.exportReportToPdfFile(informe, Propiedades.getString("datos") + "/aplicaciones/ticketing/" + fileName);
	}

	/**
	 * Metodo generar informe orden trabajo
	 * 
	 * @param req
	 * @param externo
	 * @param rootCategory
	 * @param peticion
	 * @return
	 * @throws JRException
	 */
	
	public JasperPrint generarInformeOrdenTrabajo(Request req, String externo, String rootCategory, Peticion peticion) throws JRException {
		
		@SuppressWarnings("deprecation")
		JasperReport reporte = (JasperReport) JRLoader.loadObject(peticion.getPathInformes("ot-" + rootCategory + ".jasper"));
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("LOGO", peticion.getPathInformes("logo.gif"));
		parametros.put("EXTERNO", externo);
		if (req.getX() != null) {
			int tamanyo = 350;
			int escala = 4000;
			double tamMapa = tamanyo * 0.00028 * escala;
			String bbox = "" + ((req.getX() - (tamMapa / 2)) + "," + (req.getY() - (tamMapa / 2) + "," + (req.getX() + (tamMapa / 2)) + "," + (req.getY() + (tamMapa / 2))));
			parametros.put("IMAGE_SRC", "http://idezar.zaragoza.es/wms/IDEZar_base/IDEZar_base?TRANSPARENT=true&FORMAT=image%2Fpng&VERSION=1.3.0&SERVICE=WMS&REQUEST=GetMap&STYLES=default&EXCEPTIONS=XML&LAYERS=base&BGCOLOR=0xFFFFFF&CRS=EPSG%3A23030&BBOX=" + bbox + "&WIDTH=" + tamanyo + "&HEIGHT=" + tamanyo);
		}
		
		if (req.getFiles() != null) {
			for (Adjunto a: req.getFiles()) {
				if (a.getFile_name().toLowerCase().indexOf(".gif") >= 0 
						|| a.getFile_name().toLowerCase().indexOf(".jpg") >= 0
						|| a.getFile_name().toLowerCase().indexOf(".jpeg") >= 0
						|| a.getFile_name().toLowerCase().indexOf(".png") >= 0) {
					parametros.put("MEDIA_URL", a.getMedia_url());
					break;
				}
			}
		}
		parametros.put("SUBREPORT_DIR", peticion.getPathInformes("queja_acciones.jasper"));
		Funciones.setProxy();
		return JasperFillManager.fillReport(reporte, parametros,new JRBeanArrayDataSource(new Request[]{req}));
	}
	
	/**
	 * @param registro
	 * @param diaCita
	 * @param horaCita
	 * @param expediente
	 * @return
	 */
	public String enviarCapaz(Request registro, String diaCita, String horaCita, String expediente) {
		String json = generarJsonCapaz(registro, diaCita, horaCita, expediente);
		
		if ("".equals(Propiedades.getCapazUri())) {
			return "ERROR Al enviar a CAPAZ. Servicio Rest no definido. Contenido enviado:" + json;			
		} else {
			HashMap<String, String> headers = new HashMap<String, String>();
			headers.put("authorization", Propiedades.getCapazToken());
			return postExterno(Propiedades.getCapazUri(), json, headers, null, "solicitud_informacion");
		}
	}
	/**
	 * Metodo generar json de registro capaz
	 * @param registro
	 * @param diaCita
	 * @param horaCita
	 * @param expediente
	 * @return
	 */
	private String generarJsonCapaz(Request registro, String diaCita, String horaCita, String expediente) {
		
		try {
			Date time = ConvertDate.string2Date(diaCita + " " + horaCita, ConvertDate.DATETIMEEN_FORMAT);
			final SimpleDateFormat sdf = new SimpleDateFormat(ConvertDate.ISO8601_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			String fechaGMT = sdf.format(time);
		
			StringBuilder json = new StringBuilder();
			json.append("{"
					+ (StringUtils.isEmpty(registro.getAddress_string()) ? "" : "\"address\":\"" + Funciones.escapeJson(registro.getAddress_string()) + "\",")
					+ (StringUtils.isEmpty(registro.getDescription()) ? "" : "\"description\":\"" + Funciones.escapeJson(registro.getDescription()) + "\",")
					+ (StringUtils.isEmpty(registro.getEmail()) ? "" : "\"email\":\"" + Funciones.escapeJson(registro.getEmail()) + "\",")
					+ "\"idNumber\":\"" + this.obtenerNifDeRequest(registro) + "\","
					+ (StringUtils.isEmpty(registro.getFirst_name()) ? "" : "\"name\":\"" + Funciones.escapeJson(registro.getFirst_name()) + (StringUtils.isEmpty(registro.getLast_name()) ? "" : " " + Funciones.escapeJson(registro.getLast_name())) + "\",")
					+ (registro.getService_request_id() != null ? "" : "\"notes\":\"Numero de solicitud: " + registro.getService_request_id() + "\",")
					+ (StringUtils.isEmpty(registro.getPhone()) ? "" : "\"phone\":\"" + Funciones.escapeJson(registro.getPhone()) + "\",")
	//				+ (Funciones.isEmpty(registro.getAddress_string()) ? "" : "\"reason\":\"" + registro.getAddress_string() + "\",")
					+ "\"referenceCodes\":[\"" + expediente + "\"],"
					+ "\"requestDate\":\"" + fechaGMT + "\","
					+ "\"requestType\":\"EXTERNAL_REQUEST\","
					+ "\"department\":\"Solicitud de acceso a Informacion Publica\""
					+ "}");
			return new String(json.toString().getBytes(), CharEncoding.UTF_8);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return null;
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Metodo peticion POST servicio externo
	 * @param uri
	 * @param json
	 * @param headers
	 * @param referer
	 * @param userAgent
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String postExterno(String uri, String json, HashMap<String, String> headers, String referer, String userAgent) {
		HttpClient client = new HttpClient();

		PostMethod method = new PostMethod(uri);

		method.addRequestHeader("User-Agent", userAgent);
		method.addRequestHeader("Accept", "application/json");
		method.addRequestHeader("content-type", "application/json");
		if (referer != null) {
			method.addRequestHeader("Referer", referer);
		}
		logger.info("URI:{}", uri);
		if (headers != null) {
			Iterator it = headers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				method.addRequestHeader(e.getKey().toString(), e.getValue()
						.toString());
				logger.info("{}:{}", e.getKey(), e.getValue());
				
			}
		}
		logger.info("body:{}", json);
		method.setRequestEntity(new StringRequestEntity(json));
		String proxy = Propiedades.getString("proxy.host");
		if (proxy.length() > 0) {
			client.getHostConfiguration().setProxy(Propiedades.getString("proxy.host"), Integer.parseInt(Propiedades.getString("proxy.port")));				
		}
		
		try {
			client.executeMethod(method);
			return new String(method.getResponseBody(), CharEncoding.UTF_8);
		} catch (HttpException e) {
			return e.getMessage();
		} catch (IOException e) {
			return e.getMessage();
		} finally {
			method.releaseConnection();
		}
	}
	
	public String obtenerNifDeRequest(Request registro) {
		String[] lines = registro.getNotes().split(System.getProperty("line.separator"));
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].indexOf("DNI Solicitante:") >= 0) {
				return lines[i].replace("DNI Solicitante:", "").trim();
			}
		}
		return null;
	}
}