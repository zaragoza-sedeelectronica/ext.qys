package org.sede.servicio.citaprevia.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.dao.JPAIgnoreTraversableResolver;
import org.sede.core.exception.FormatoNoSoportadoException;
import org.sede.core.rest.view.TransformadorXml;
import org.sede.core.utils.ConvertDate;
import org.sede.core.utils.Funciones;
import org.sede.servicio.citaprevia.ConfigCitaprevia;
import org.sede.servicio.citaprevia.entity.Agenda;
import org.sede.servicio.citaprevia.entity.Cita;
import org.sede.servicio.citaprevia.entity.CitaPrevia;
import org.sede.servicio.citaprevia.entity.CitaPreviaException;
import org.sede.servicio.citaprevia.entity.Dias;
import org.sede.servicio.citaprevia.entity.Horario;
import org.sede.servicio.citaprevia.entity.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.SearchResult;

import oracle.jdbc.OracleTypes;
@Repository
@Transactional(ConfigCitaprevia.TM)
public class CitaPreviaGenericDAOImpl extends GenericDAOImpl <CitaPrevia, Integer> implements CitaPreviaGenericDAO {
	private static final Logger logger = LoggerFactory.getLogger(CitaPreviaGenericDAO.class);
	
	@PersistenceContext(unitName=ConfigCitaprevia.ESQUEMA)
	public void setEntityManager(EntityManager entityManager) {
		this.setEm(entityManager);
	}
	
	public Set<ConstraintViolation<Object>> validar(Object registro) {
		ValidatorFactory factory = Validation.byDefaultProvider().configure().traversableResolver(new JPAIgnoreTraversableResolver()).buildValidatorFactory();
		Validator validator = factory.getValidator();
		return validator.validate(registro);
	}
	public List<CitaPrevia> obtenerCentros() {
		
		try {
			List<CitaPrevia> listado = new ArrayList<CitaPrevia>();
			CitaPrevia centroActual = new CitaPrevia(0,"","");
	    	Agenda agendaActual = new Agenda(0,"","",0,0,0,"","");
			Query query = em().createNativeQuery("select cs.id_cen, cs.nombre, ag.id_agenda, ag.nombre as ag_nombre," +
					" ag.tam_hueco, asun.descripcion as asun_descripcion, ag.maxcitas "
					+ "from centro_servicios cs, cpu_agendas ag, asunto asun "
					+ "where cs.id_cen=ag.id_cen and ag.activada='S' "
					+ "and ag.idproc=asun.idproc "
					+ "and ag.id_cen=asun.id_cen "
					+ "and ag.id_agenda in (select id_agenda from cpu_agendahorario horario where horario.fchfin>sysdate) "
					+ "order by id_cen");
			@SuppressWarnings("unchecked")
			List<Object> centros = query.getResultList();
			
			for (Iterator<Object> iterador = centros.iterator(); iterador.hasNext();) {
	    		Object[] row = (Object[])iterador.next();
	    		if (agendaActual.getId_estructura()!=Integer.parseInt(row[0].toString())) {
	    			if (!"".equals(agendaActual.getTitle())) {
	    				centroActual.getAgendas().add(agendaActual);
	    			}
	    			agendaActual = new Agenda(Integer.parseInt(row[2].toString()), 
	    					row[3].toString(), 
	    					(row[5] == null ? null : row[5].toString()), 
	    					(row[4] == null ? null : Integer.parseInt(row[4].toString())),
	    					(row[6] == null ? null : Integer.parseInt(row[6].toString())),
	    					Integer.parseInt(row[0].toString()),
	    					(row[1] == null ? null : row[1].toString()),null);
	    		}else{
	    			centroActual.getAgendas().add(agendaActual);
	    			agendaActual = new Agenda(Integer.parseInt(row[2].toString()), 
	    					row[3].toString(), 
	    					(row[5] == null ? null : row[5].toString()), 
	    					(row[4] == null ? null : Integer.parseInt(row[4].toString())),
	    					(row[6] == null ? null : Integer.parseInt(row[6].toString())),
	    					Integer.parseInt(row[0].toString()),
	    					(row[1] == null ? null : row[1].toString()),null);
	    		}	    		
	    		if (centroActual.getId()!=Integer.parseInt(row[0].toString())) {
	    			if (centroActual.getId()!=0) {
	    				listado.add(centroActual);
	    			}
	    			centroActual = new CitaPrevia(Integer.parseInt(row[0].toString()), row[1].toString(), null);
	    		}
    			if (!iterador.hasNext()){
    				centroActual.getAgendas().add(agendaActual);
    				listado.add(centroActual);
    			}
			}
			
			return listado;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public List<Agenda> obtenerAgenda(int id) {
		try {
			List<Agenda> listado = new ArrayList<Agenda>();
			Agenda agendaActual = new Agenda(0,"","",0,0,0,"","");
	    	Horario horarioActual;
			Query query = em().createNativeQuery("select ag.id_agenda, ag.nombre as ag_nombre,"
					+ "asun.descripcion as asun_descripcion, ag.tam_hueco, hor.idhorario, hor.nombre, hor.fchinicio, hor.fchfin, ag.maxcitas, ag.id_cen, "
					+ "c.nombre as nombre_centro, e.nombre || ' - ' || e.calle || ' ' || e.codpos || nvl2(u.planta,' Planta: ' || u.planta,'') || nvl2(u.acceso,' Acceso por: ' || u.acceso,'')"
					+" from cpu_agendas ag, asunto asun, cpu_agendahorario hor,centro_servicios c, edificio e, ubicacion u "
					+" where ag.id_agenda=? and ag.idproc=asun.idproc and ag.id_cen=asun.id_cen and ag.id_agenda=hor.id_agenda "
					+ "and ag.id_cen=c.id_cen(+) "
					+ "and c.id_cen=u.id_cen(+) "
					+ "and u.id_edi=e.id_edi(+) "
					
					+ "and hor.fchfin >= sysdate order by hor.fchinicio");
			@SuppressWarnings("unchecked")
			List<Object> agenda = query.setParameter(1, id).getResultList();
			Iterator<Object> iterador = agenda.iterator();
			if(iterador.hasNext()){
				Object[] row = (Object[])iterador.next();
				agendaActual = new Agenda(Integer.parseInt(row[0].toString()), 
						row[1].toString(), 
						(row[2] == null ? null : row[2].toString()), 
						(row[3] == null ? null : Integer.parseInt(row[3].toString())),
						(row[8] == null ? null : Integer.parseInt(row[8].toString())),
						Integer.parseInt(row[9].toString()),
						(row[10] == null ? null : row[10].toString()),
						(row[11] == null ? null : row[11].toString()));
				horarioActual = new Horario(Integer.parseInt(row[4].toString()),
						(row[5] == null ? null : row[5].toString()), 
						row[6].toString(),
						row[7].toString());
				agendaActual.getHorarios().add(horarioActual);
			}		
			while (iterador.hasNext()) {
	    		Object[] row = (Object[])iterador.next();
	    		horarioActual=new Horario(Integer.parseInt(row[4].toString()),(row[5] == null ? null : row[5].toString()), row[6].toString(),row[7].toString());
				agendaActual.getHorarios().add(horarioActual);
			}
			if(agendaActual.getId()!=0){
				listado.add(agendaActual);
			}else {
				listado=null;
			}
			
			return listado;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public List<Turno> obtenerTurnos(int id, Date fecha) throws CitaPreviaException, ParseException {

		List<Turno> listado = new ArrayList<Turno>();
		List<Turno> ocupados = new ArrayList<Turno>();
		Turno turno;
		int idH = 0;
		int tamHueco = 0;
		String horaInicio = null; 
		String horaFin = null;
		String maxCitas = null;

		GregorianCalendar cal = new GregorianCalendar();
		
		cal.setTime(fecha);

		Date hoy = new Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(hoy);
		try {
			hoy = df.parse(fechaInicioString);
		} catch (ParseException ex) {
		}

		String fechaFinalString = df.format(fecha);
		try {
			fecha = df.parse(fechaFinalString);
		} catch (ParseException ex) {
		}
		long diferencia = fecha.getTime() - hoy.getTime();
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		if ((dias >= 1 && (id == 701 || id == 741 || id == 742 || id == 761
				|| id == 781 || id == 721 || id == 801 || id == 821
				|| id == 881 || id == 861 || id == 862 || id == 923
				|| id == 922 || id == 921 || id == 920 || id == 919
				|| id == 1143 || id == 1141 || id == 1161 || id == 1142 || id == 1144
				|| id == 918 || id == 917 || id == 916 || id == 915
				|| id == 914 || id == 913 || id == 912 || id == 911
				|| id == 910 || id == 909 || id == 908 || id == 907
				|| id == 906 || id == 905 || id == 904 || id == 903
				|| id == 902 || id == 901 || id == 961 || id == 1201))
				|| (dias >= 2 && (id != 701 && id != 741 && id != 742
						&& id != 761 && id != 781 && id != 721 && id != 801
						&& id != 821 && id != 881 && id != 861 && id != 862
						&& id != 923 && id != 922 && id != 921 && id != 920
						&& id != 919 && id != 918 && id != 917 && id != 916
						&& id != 1143 && id != 1141 && id != 1161 && id != 1142 && id != 1144
						&& id != 915 && id != 914 && id != 913 && id != 912
						&& id != 911 && id != 910 && id != 909 && id != 908
						&& id != 907 && id != 906 && id != 905 && id != 904
						&& id != 903 && id != 902 && id != 901 && id != 961 && id != 1201))) {

			Query query = em().createNativeQuery("select h.idhorario,a.tam_hueco, a.maxcitas, a.horainicio, a.horafin from cpu_agendahorario h, cpu_agendas a where h.id_agenda="
							+ id
							+ " and h.id_agenda=a.id_agenda and to_date('"
							+ ConvertDate.date2String(fecha, ConvertDate.DATE_FORMAT) + "','dd-mm-yyyy') between fchinicio AND fchfin");
			@SuppressWarnings("unchecked")
			List<Object> resultado = query.getResultList();
			Iterator<Object> iterador = resultado.iterator();
			if (iterador.hasNext()) {
				Object[] row = (Object[]) iterador.next();
				idH = Integer.parseInt(row[0].toString());
				tamHueco = Integer.parseInt(row[1].toString());
				maxCitas = row[2].toString();
				horaInicio = row[3].toString();
				horaFin = row[4].toString();
			}
			int dia = cal.get(Calendar.DAY_OF_WEEK);
			dia = dia - 1;

			Query query2 = em().createNativeQuery("select hora,id_cita from cpu_cita where fecha=to_date('"
							+ ConvertDate.date2String(fecha, ConvertDate.DATE_FORMAT) 
							+ "','dd-mm-yyyy') and id_agenda="
							+ id
							+ " order by hora");
			@SuppressWarnings("unchecked")
			List<Object> resultado2 = query2.getResultList();
			iterador = resultado2.iterator();
			while (iterador.hasNext()) {
				Object[] row = (Object[]) iterador.next();
				turno = new Turno(row[0].toString());
				ocupados.add(turno);
			}

			Query query3 = em().createNativeQuery("select horainicio,horafin,maxcitas from cpu_agendaturno where idhorario="
							+ idH
							+ " and dia="
							+ dia
							+ " order by horainicio");
			@SuppressWarnings("unchecked")
			List<Object> resultado3 = query3.getResultList();
			Iterator<Object> iterador3 = resultado3.iterator();
			Query query4 = em().createNativeQuery("select idfestivo from cpu_agendafestivo where id_agenda="
							+ id + " and fecha=to_date('" + ConvertDate.date2String(fecha, ConvertDate.DATE_FORMAT) + "','dd-mm-yyyy')");
			Object resultado4 = query4.getSingleResult();
			if (iterador3.hasNext() && (resultado4 == null)) {
				while (iterador3.hasNext()) {
					Object[] row = (Object[]) iterador3.next();
					horaInicio = row[0].toString();
					horaFin = row[1].toString();
					int horaFinInt = Integer.parseInt(horaFin.replace(":", ""));
					maxCitas = row[2].toString();
					String horaActual = horaInicio;
					
					int horaActualInt = Integer.parseInt(horaActual.replace(":", ""));
					while (!horaActual.equals(horaFin) && horaActualInt < horaFinInt) {
						turno = new Turno(horaActual);
						turno.setLibres(Integer.parseInt(maxCitas));
						turno.setMax_citas(Integer.parseInt(maxCitas));
						for (int i = 0; i < ocupados.size(); i++) {
							if (ocupados.get(i).getHora()
									.equals(turno.getHora())) {
								turno.setLibres(turno.getLibres() - 1);
							}
						}
						int sigMin = (Integer.parseInt(horaActual
								.split(":")[1]) + tamHueco) % 60;
						int sigHora = Integer.parseInt(horaActual
								.split(":")[0]);
						if ((Integer.parseInt(horaActual.split(":")[1]) + tamHueco) / 60 == 1) {
							sigHora++;
						}
						String hora = null;
						String min = null;
						if (sigHora < 10) {
							hora = "0" + Integer.toString(sigHora);
						} else {
							hora = Integer.toString(sigHora);
						}
						if (sigMin < 10) {
							min = "0" + Integer.toString(sigMin);
						} else {
							min = Integer.toString(sigMin);
						}
						horaActual = hora + ":" + min;
						horaActualInt = Integer.parseInt(horaActual.replace(":", ""));
						if (anyadirTurno(turno, listado)) {
							listado.add(turno);
						}
					}
				}
				return listado;
			} else {
				throw new CitaPreviaException(CitaPreviaException.ERR_20005, "La fecha y la hora seleccionadas para la cita no pertenecen a ning\u00FAn turno establecido para la agenda");
			}
		} else {
			throw new CitaPreviaException(CitaPreviaException.ERR_20008, "No se puede solicitar cita previa para la fecha seleccionada");
		}
	}
	
	private boolean anyadirTurno(Turno turno, List<Turno> listado) {
		for (int i = 0; i < listado.size(); i++) {
			Turno actual = listado.get(i);
			if (actual.getHora().equals(turno.getHora())) {
				return false;
			}
		}
		return true;
	}

	public int obtenerid(){
		try {
			int id=0;
			Query query = em().createNativeQuery("select max(id_cita)+1 from cpu_cita");
			id = (Integer) query.getSingleResult();
			
			return id;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
	}	

	public Cita nuevaCita(final Cita cita, final boolean validarNif) throws SQLException {
		try {
			return em().unwrap(Session.class).doReturningWork(new ReturningWork<Cita>() {					
				public Cita execute(Connection connection) throws SQLException {		
					ResultSet rs = null;
					try {
						CallableStatement update = connection.prepareCall(crearStatement("CITA", "INSERT", 17));
			        	setInteger(1, cita.getId_agenda(), update);                        
			            setInteger(2, null, update);
			            setDate(3, cita.getFecha(), update);
			            setString(4, cita.getHora(), update);
			            setString(5, cita.getApellidos()+ "," + cita.getNombre(), update);
			            setString(6, cita.getNif(), update);             
			            setString(7, cita.getTelefono(), update);                     
			            setString(8, cita.getEmail(), update);                                             
			            setString(9, cita.getExpediente(), update);
			            setString(10, cita.getMotivo(), update);
			            setString(11, cita.getObservacionesCita(), update);
			            setInteger(12, 1, update);         // Estado 1 = citado                 
			//            setBoolean(13, true, update);
			            setBoolean(13, validarNif, update); // Modificada validacion de que el NIF tenga otras citas
			            setBoolean(14, cita.isLopd(), update);
			            setString(15, cita.getMovil(), update);          // Movil
			            setString(16, cita.getTelefono2(), update);      // Telefono2
			            update.registerOutParameter(17, OracleTypes.CURSOR);
			            update.executeUpdate();
			            rs = (ResultSet) update.getObject(17);
			            if (rs.next()) {
			                cita.setId(rs.getInt(1));
			                cita.setLocalizador(rs.getString("IDQWIN"));
			            }
			            return cita;
	
			        } finally {
			        	if (rs != null) {
							rs.close();
						}
			        }
				}
			});
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new SQLException(e.getCause()); 
		}
	}
	
 public Cita obtenerDatosCita(Cita cita){
		try {
			Query query = em().createNativeQuery("select org_servicio.nombre as servicio, procedimiento.nombre as tramite, " +
					"centro_servicios.nombre as asunto, edificio.nombre as centro, edificio.calle  || nvl2(ubicacion.planta,' Planta: ' || ubicacion.planta,'') || nvl2(ubicacion.acceso,' Acceso por: ' || ubicacion.acceso,'') " + 
					"from centro_servicios, " +
					"org_servicio, cpu_agendas, procedimiento, edificio, ubicacion " +
					"where org_servicio.id_jse=centro_servicios.id_org and " +
					"centro_servicios.id_cen=cpu_agendas.id_cen and cpu_agendas.id_agenda="+cita.getId_agenda()+" and " +
					"cpu_agendas.idproc=procedimiento.idproc and ubicacion.id_cen=cpu_agendas.id_cen " +
					"and edificio.id_edi=ubicacion.id_edi");
			@SuppressWarnings("unchecked")
			List<Object> resultado = query.getResultList();
			Iterator<Object> iterador = resultado.iterator();
			while (iterador.hasNext()) {
				Object[] row = (Object[])iterador.next();
				cita.setServicio(row[0].toString());
				cita.setTramite(row[1].toString());
				cita.setCentro(row[3].toString());
				cita.setCalle(row[4].toString());
			}
			return cita;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}	
	
	
	
	private StringBuilder mostrarResumen(Cita cita){

		final StringBuilder xhtm = new StringBuilder(960);
		
		xhtm.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML Basic 1.0//EN\" \"" 
				+ "/contenidos/dtd/xhtml-basic10.dtd\">" 
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"es\">" 
				+ "<head><title>Ayuntamiento de Zaragoza. " + "Cita previa" + "</title>" 
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"
				+ "<meta name=\"DC.date\" content=\"2005-05-01\"/>"
				+ "<meta name=\"DC.organization\" content=\"Ayuntamiento de Zaragoza\"/>"
				+ "<meta name=\"DC.author\" content=\"Ayuntamiento de Zaragoza. Sección Información Web. "
				+ "sedeelectronica@zaragoza.es\"/>"
				+ "<meta name=\"DC.lang\" content=\"es\"/>"
				+ "<meta name=\"DC.description\" content=\"Cita previa\"/>"
				+ "<meta name=\"DC.subject\" content=\"ayuntamiento, council, zaragoza,saragossa, aragón, aragon, spain,tourist\"/>"
				+ "</head>"
				+ "<body>");
		xhtm.append("<div class=\"citaPrevia\">"+
				"<h1>Confirmación de cita</h1>");
		xhtm.append("</div><div class=\"fieldset\">"+
		"<form id=\"cita_previa\">");
		xhtm.append("<fieldset>"
				+ "<legend>"
				+ "<span>Datos de su cita</span>"
				+ "</legend>"
				+ "<fieldset>"
				+ "<label><strong>Servicio: </strong>"+cita.getServicio()+ " </label>"+"<br/>"
				+ "<label><strong>Trámite: </strong>"+cita.getTramite()+ " </label>"+"<br/>"
				+ "<label><strong>Asunto: </strong>"+cita.getAsunto()+ " </label>"+"<br/>"
				+ "<label><strong>Lugar: </strong>"+cita.getCentro()+" c/ "+ cita.getCalle()+ " </label>"+"<br/>"
				
				+ (StringUtils.isNotEmpty(cita.getExpediente()) ? "<label><strong>Expediente: </strong>"+cita.getExpediente() + "</label> " : "") 
				+ (StringUtils.isNotEmpty(cita.getObservacionesCita()) ? cita.getObservacionesCita() : "")
				
				+ "<br/>"
				+ "<label><strong>Fecha: </strong>"+cita.getFecha()+ " </label>"+"<br/>"
				+ "<label><strong>Hora: </strong>"+cita.getHora()+ " </label>"+"<br/>"
				+ "<label><strong>Localizador: </strong>"+cita.getLocalizador()+ " </label>"+"<br/></fieldset></fieldset>");
		if (cita.getId_agenda().intValue() == 1021) {
			xhtm.append("<div><strong>AVISO:</strong> Si no puede asistir le agracederemos que  lo comunique a los teléfonos: 976724273 -  976800144, para  poder dejar libre la cita y poder atender otra consulta.</div>");
		}
		xhtm.append("</form></div></body></html>");
		return xhtm;

	}
	
	public void enviaEmail(Cita cita) throws MessagingException {
        // Enviar Email de Notificacion al usuario
		Funciones.sendMail("Confirmación de cita", mostrarResumen(cita).toString(), 
				cita.getEmail(),"25232871@zaragoza.es", "HTML");
    }
	
	public List<Cita> listarCitas(final Cita cita, final boolean verCitasVencidas) throws CitaPreviaException, ParseException, SQLException {
		final List<Cita> resultado = em().unwrap(Session.class).doReturningWork(new ReturningWork<List<Cita>>() {					
			public List<Cita> execute(Connection connection) throws SQLException {
		
			List<Cita> listado = new ArrayList<Cita>();
			Cita modifcita =null;
			int i=0;
	
			ResultSet rs = null;
			
		    try {
		        
		        CallableStatement update = connection.prepareCall(crearStatement("CITA", "LISTA", 12));
				setInteger(1, null, update);                             // IdCita
	            setDate(2, cita.getFecha(), update);              // Fecha
	            setString(3, cita.getHora(), update);             // Hora
	            setString(4, cita.getApellidos() + "," + cita.getNombre(), update);           // Nombre
	            setString(5, cita.getNif(), update);              // NIF
	            setString(6, cita.getTelefono(), update);         // Telefono            
	            setInteger(7, cita.getEstado(), update);
	            setInteger(8, cita.getId_agenda(), update);        // IdAgenda            
	            setString(9, cita.getEmail(), update);            // Email
	            setString(10, cita.getExpediente(), update);
	            setBoolean(11, verCitasVencidas, update);                            // VerCitasVencidas
	            
	            update.registerOutParameter(12, OracleTypes.CURSOR);
	            update.execute();
	            rs = (ResultSet) update.getObject(12);
	
	            java.sql.Date fecha=null;
	            while (rs.next()) { 
					modifcita = new Cita();				
					i = 0;
					
					modifcita.setId(rs.getInt(++i));
					modifcita.setId_agenda(rs.getInt(++i));
					
					
					fecha = new java.sql.Date(ConvertDate.string2Date(rs.getString(++i), ConvertDate.DATE_FORMAT_BARRA).getTime());
					modifcita.setFecha(fecha);
					modifcita.setHora(rs.getString(++i));
					modifcita.setNombre(rs.getString(++i));
					modifcita.setNif(rs.getString(++i));
					modifcita.setTelefono(rs.getString(++i));
					modifcita.setEmail(rs.getString(++i));
					modifcita.setExpediente(rs.getString(++i));
					modifcita.setMotivo(rs.getString(++i));
					modifcita.setEstado(rs.getInt(++i));
					modifcita.setLocalizador(rs.getString(++i));
					modifcita.setId_estructura(rs.getInt(++i));
					String obs = rs.getString(++i);
					modifcita.setObservacionesCita(obs == null ? null : obs.replaceAll("##", "").replaceAll("::", ":"));
					listado.add(modifcita);
	            }
	            return listado;
	        } catch (ParseException e) {
				throw new SQLException(e);
			} finally {
	        	if (rs != null) {
					rs.close();
				}
	        }
			}
		});
		List<Cita> retorno = new ArrayList<Cita>();
		for (Cita c : resultado) {
			List<Agenda> g = this.obtenerAgenda(c.getId_agenda());
			Agenda a = g.get(0);
			c.setServicio(Funciones.removeHTMLEntity(a.getTitle_estructura()));
			c.setTramite(Funciones.removeHTMLEntity(a.getTitle()));
			c.setLugar(Funciones.removeHTMLEntity(a.getAddress_estructura()));
			c.setAsunto(Funciones.removeHTMLEntity(a.getDescription()));
			
			retorno.add(c);
		}
		
		
		return retorno;
	}
	
	public void eliminarCita(final int idCita) {
		
		em().unwrap(Session.class).doReturningWork(new ReturningWork<Boolean>() {					
			public Boolean execute(Connection connection) throws SQLException {
		        CallableStatement update = connection.prepareCall(crearStatement("CITA", "DELETE", 1));
		        setInteger(1, idCita,update);
	           return update.execute();
			}
		});
 
	}
	
	private static final String PROC_CALL_PREFIX          = "{call ";
	private static final String PROC_CALL_PREFIX_BEGIN    = "(";
	private static final String PROC_CALL_PREFIX_END      = ")}";
	private static final String PROC_CALL_DOT             = ".";
	protected String crearStatement(String modulo,String proc,int numParametros) {
		StringBuilder sb = new StringBuilder(PROC_CALL_PREFIX);
		sb.append("PCK_CPU_" + modulo);
		sb.append(PROC_CALL_DOT);
		sb.append(modulo + "_" + proc);
		sb.append(PROC_CALL_PREFIX_BEGIN);
		for (int i = 1; i <= numParametros; i++) {
			if (i > 1)
				sb.append(",");
			sb.append("?");
		}
		return sb.append(PROC_CALL_PREFIX_END).toString();
		
	   }
	 
	protected void setInteger(int param, Integer value, CallableStatement st) throws SQLException {
	        if (value != null)
	            st.setInt(param,value.intValue());
	        else
	            st.setNull(param,OracleTypes.INTEGER);
	    }
	protected void setString(int param, String value, CallableStatement st) throws SQLException {
	        if (value != null)
	            st.setString(param,value);
	        else
	            st.setNull(param,OracleTypes.VARCHAR);
	    }
	protected void setDate(int param, java.util.Date value, CallableStatement st) throws SQLException {
	        setDate(param,value == null ? null : new java.sql.Date(value.getTime()), st);
	    }
	
    protected void setDate(int param, java.util.Date value) throws SQLException {
        setDate(param,value == null ? null : new java.sql.Date(value.getTime()));
    }
	    
	protected void setDate(int param, java.sql.Date value, CallableStatement st) throws SQLException {
	        if (value != null)
	            st.setDate(param,value);
	        else
	            st.setNull(param,OracleTypes.DATE);
	    }    
	protected void setBoolean(int param, java.lang.Boolean value, CallableStatement st) throws SQLException{
	        if (value != null)
	            st.setBoolean(param,value);
	        else
	            st.setBoolean(param,false);
	    }
	public SearchResult<Dias> obtenerHuecosAgenda(final Integer id) {
		return em().unwrap(Session.class).doReturningWork(new ReturningWork<SearchResult<Dias>>() {					
			public SearchResult<Dias> execute(Connection connection) throws SQLException {
		
			CallableStatement st = null;
			try {
				st = connection.prepareCall(crearStatement("SEDE", "DIAS_LIBRES", 2));
				st.setInt(1, id);
				st.registerOutParameter(2, OracleTypes.CLOB);
	            st.execute();
	
	            TransformadorXml trans = new TransformadorXml();
	            
	            @SuppressWarnings("unchecked")
				SearchResult<Dias> results =  (SearchResult<Dias>)trans.pasarAObjeto(st.getString(2), true, SearchResult.class, Dias.class);
	            results.setTotalCount(results.getResult().size());
	            results.setRows(results.getResult().size());	            
				results.setResult(results.getResult());
				return results;	
			} catch (FormatoNoSoportadoException e) {
				throw new SQLException(e);
			} finally {
				if (st != null) {
					st.close();
				}
			}
			}
		});
	}
	
	public void borrarCitasAsociadas(String nif, int id) {
		Query update = em().createNativeQuery(
				"delete from cpu_cita "
				+ "where fecha > sysdate and "
				+ "id_agenda=? and nif=?");
		
		int x = 0;
		update.setParameter(++x, id);
		update.setParameter(++x, nif);
		update.executeUpdate();
	}
	public boolean tieneHuecosDisponibles(ResponseEntity<?> dias) {
		if (dias.getBody() instanceof SearchResult) {
			@SuppressWarnings("unchecked")
			SearchResult<Dias> arrDias = (SearchResult<Dias>) dias.getBody();
			for (Dias d : arrDias.getResult()){
				 if ((d.getHoras() != null && !d.getHoras().isEmpty())) {
					 return true;
				 }
			}
		}
		return false;
	}
	
	
	public Cita nuevaCita(int idAgenda, String lastName, String firstName, String email, String phone, String nif, boolean lopd, String diaCita,
			String horaCita, String expediente) throws Exception {
		try {
			Cita c = new Cita();
			this.getSession().beginTransaction();
			c.setId_agenda(idAgenda);
			// formato yyyy-mm-dd
			c.setFecha(java.sql.Date.valueOf(diaCita));
			c.setHora(horaCita);
			c.setApellidos(lastName);
			c.setNombre(firstName);
			c.setNif(nif);
			c.setEmail(email);
			c.setExpediente(expediente);
			c.setTelefono(phone);
			c.setLopd(lopd);
			c.setId(this.nuevaCita(c, false).getId());
			this.enviaEmail(c);
			return c;
		} catch (Exception e) {
			if (this.getSession().getTransaction().isActive()) {
				this.getSession().getTransaction().rollback();
            }
			throw e;
		} finally {
			this.getSession().getTransaction().commit();
		}
	}
}