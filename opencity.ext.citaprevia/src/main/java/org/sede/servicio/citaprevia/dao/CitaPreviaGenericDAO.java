package org.sede.servicio.citaprevia.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;

import org.sede.servicio.citaprevia.entity.Agenda;
import org.sede.servicio.citaprevia.entity.Cita;
import org.sede.servicio.citaprevia.entity.CitaPrevia;
import org.sede.servicio.citaprevia.entity.CitaPreviaException;
import org.sede.servicio.citaprevia.entity.Dias;
import org.sede.servicio.citaprevia.entity.Turno;
import org.springframework.http.ResponseEntity;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface CitaPreviaGenericDAO extends GenericDAO<CitaPrevia, Integer> {
	
	public Set<ConstraintViolation<Object>> validar(Object registro);
	public List<CitaPrevia> obtenerCentros();
	public List<Agenda> obtenerAgenda(int id);
	public List<Turno> obtenerTurnos(int id, Date fecha) throws CitaPreviaException, ParseException;
	public int obtenerid();
	public Cita nuevaCita(final Cita cita, final boolean validarNif) throws SQLException;
	public Cita obtenerDatosCita(Cita cita);
	public void enviaEmail(Cita cita) throws MessagingException;
	public List<Cita> listarCitas(final Cita cita, final boolean verCitasVencidas) throws CitaPreviaException, ParseException, SQLException;
	public void eliminarCita(final int idCita) throws SQLException;
	public SearchResult<Dias> obtenerHuecosAgenda(final Integer id) throws SQLException;
	public void borrarCitasAsociadas(String nif, int id);
	public boolean tieneHuecosDisponibles(ResponseEntity<?>ias);
	public Cita nuevaCita(int i, String lastName, String firstName, String email, String phone, String nif, boolean lopd, String diaCita, String horaCita, String string) throws Exception;
}