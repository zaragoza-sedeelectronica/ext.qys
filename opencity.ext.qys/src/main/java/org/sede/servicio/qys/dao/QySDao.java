package org.sede.servicio.qys.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.sede.core.exception.FormatoNoSoportadoException;
import org.sede.core.rest.Peticion;
import org.sede.servicio.qys.dao.consulta.ServiceConsulta;
import org.sede.servicio.qys.dao.consulta.ServiceDatos;
import org.sede.servicio.qys.entity.Category;
import org.sede.servicio.qys.entity.Request;
import org.sede.servicio.qys.entity.RespuestaTipo;
import org.sede.servicio.qys.entity.Service;
import org.sede.servicio.qys.entity.SolicitudInformacionPublica;
import org.sede.servicio.qys.entity.Value;
import org.springframework.http.ResponseEntity;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

/**
 * Interfaz Quejas y sugerencias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public interface QySDao extends GenericDAO<Request, BigDecimal> {
	/**
	 * Metodo validar
	 * 
	 * @param registro
	 * @return
	 */
	public Set<ConstraintViolation<Object>> validar(Object registro);
	/** 
	 * Metodo validar information de solicitud
	 * 
	 * @param registro
	 * @return
	 */
	public Set<ConstraintViolation<Object>> validarSolicitudInformacion(Object registro);
	/**
	 * Metodo buscar y contar servicios
	 * 
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Service> searchAndCountService() throws SQLException;
	/**
	 * Metodo buscar y contar servicios
	 * 
	 * @param all
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Service> searchAndCountService(String all) throws SQLException;
	/**
	 * Metodo resultados de busqueda
	 * 
	 * @param userId
	 * @return
	 */
	public SearchResult<RespuestaTipo> searchAndCountRespuestaTipo(String userId);
	/**
	 * Metodo detalle
	 * 
	 * @param id
	 * @param usuarioTicketing
	 * @return
	 * @throws SQLException
	 */
	public Object detalle(final BigDecimal id, final String usuarioTicketing) throws SQLException;
	/**
	 * 
	 * Metodo buscar y contar peticiones
	 * 
	 * @param rows
	 * @param start
	 * @param sort
	 * @param ids
	 * @param title
	 * @param notes
	 * @param service_code
	 * @param externo_code
	 * @param agency_responsible
	 * @param account_id
	 * @param user_id
	 * @param start_date
	 * @param end_date
	 * @param type
	 * @param status
	 * @param validated
	 * @param usuarioTicketing
	 * @param group_operator
	 * @param operator
	 * @param answer_requested
	 * @param barrio_code
	 * @param origin
	 * @param inspector
	 * @param operadorGet
	 * @param id_cat_sip
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Request> searchAndCountRequest(final int rows, 
			final int start, 
			final String sort, 
			final String ids, 
			final String title,
			final String notes,
			final String service_code,
			final Integer externo_code,
			final Integer agency_responsible,
			final Long account_id,
			final Long user_id,
			final Date start_date, 
			final Date end_date,
			final String type,
			final String status,
			final String validated,
			final String usuarioTicketing,
			final String group_operator,
			final String operator,
			final String answer_requested,
			final String barrio_code,
			final String origin,
			final String inspector,
			final String operadorGet,
			final Integer id_cat_sip) throws SQLException;
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
	public Request crear(final Request req, final String operacion, final String usuarioAdmin, final String gczUsuario, final Long userId) throws SQLException, FormatoNoSoportadoException;
	/**
	 * Metodo guardar
	 * 
	 * @param req
	 * @param usuarioAdmin
	 * @return
	 * @throws SQLException
	 * @throws FormatoNoSoportadoException
	 */
	public Request guardar(Request req, String usuarioAdmin) throws SQLException, FormatoNoSoportadoException;
	/**
	 * 
	 * Metodo get totales para año actual
	 * 
	 * @return
	 */
	public Integer getTotalYearActual();
	/**
	 * Metodo nombres para BBDD
	 * 
	 * @param sort
	 * @return
	 */
	public String nombresParaBBDD(String sort);
	/**
	 * Metodo encontrar por token
	 * 
	 * @param identifier
	 * @param token
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<?> findByToken(BigDecimal identifier, String token) throws SQLException;
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
	public ResponseEntity<?> acciones(final BigDecimal id, final Integer accion, final String texto, final Date fecha, final BigDecimal idExterno, final String usuarioAdmin, final String uuid, final String clientId, final Integer internalStatus) throws SQLException, FormatoNoSoportadoException;
	/**
	 * Metodo enviar capaz
	 * 
	 * @param registro
	 * @throws SQLException
	 * @throws FormatoNoSoportadoException
	 * @throws ParseException
	 */
	public void enviarCapaz(SolicitudInformacionPublica registro) throws SQLException, FormatoNoSoportadoException, ParseException;
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
	public Object datosQuejas(Date start_date, Date end_date, String idsCategoria, String texto, String usuarioTicketing) throws SQLException, FormatoNoSoportadoException;
	/**
	 * Metodo statistics
	 * 
	 * @param service_code
	 * @param year
	 * @param type
	 * @return
	 */
	public SearchResult<ServiceDatos> statistics(String service_code, Integer year, BigDecimal type);
	/**
	 * 
	 * Metodo obtener total
	 * 
	 * @param datos
	 * @return
	 */
	public ServiceDatos obtenerTotal(SearchResult<ServiceDatos> datos);
	/**
	 * Metodo obtener categorias con total
	 * 
	 * @param onlyPublic
	 * @param year
	 * @return
	 */
	public List<ServiceConsulta> obtenerCategoriasConTotal(boolean onlyPublic, String year);
	/**
	 * Metodo get categorias
	 * 
	 * @param rootCategory
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Category> getCategories(final String rootCategory) throws SQLException;
	/**
	 * Metodo get categorias adyacentes
	 *  
	 * @param rootCategory
	 * @return
	 */
	public SearchResult<Category> getAdjacentCategories(BigDecimal rootCategory);
	/**
	 * Metodo crear categoria
	 * 
	 * @param registro
	 * @return
	 */
	public ResponseEntity<?> crearCategory(Category registro);
	/**
	 * Metodo guardar categoria
	 * 
	 * @param registro
	 * @return
	 */
	public ResponseEntity<?> saveCategory(Category registro);
	/**
	 * Metodo almacenar adjunto
	 * 
	 * @param req
	 * @throws IOException
	 */
	public void almacenarAdjunto(Request req) throws IOException;
	/** 
	 * Metodo asociar
	 *
	 * @param id
	 * @param service_code
	 * @param agency_responsible_code
	 * @param parseInt
	 * @return
	 */
	public ResponseEntity<?> asociar(BigDecimal id, Integer service_code, Integer agency_responsible_code, int parseInt);
	/**
	 * Metod obtener estado de queja
	 * 
	 * @param id
	 * @return
	 */
	public Value obtenerEstadoDeQueja(BigDecimal id);
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
			final String clientId);
	/**
	 * Metodo almacenar informe
	 * 
	 * @param informe
	 * @param fileName
	 * @throws IOException
	 * @throws JRException
	 */
	public void almacenarInforme(JasperPrint informe, String fileName) throws IOException, JRException;
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
	public JasperPrint generarInformeOrdenTrabajo(Request req, String externo, String rootCategory, Peticion peticion) throws JRException;
	/**
	 * Metodo enviar capaz
	 * 
	 * @param registro
	 * @param diaCita
	 * @param horaCita
	 * @param expediente
	 * @return
	 */
	public String enviarCapaz(Request registro, String diaCita, String horaCita, String expediente);
	
	public String obtenerNifDeRequest(Request registro);
	
}