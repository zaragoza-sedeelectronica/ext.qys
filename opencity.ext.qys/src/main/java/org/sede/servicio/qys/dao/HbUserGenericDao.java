package org.sede.servicio.qys.dao;

import java.math.BigDecimal;

import org.sede.core.rest.Mensaje;
import org.sede.servicio.acceso.entity.Ciudadano;
import org.sede.servicio.qys.entity.db.Hbusers;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

/**
 * Interfaz Quejas y sugerencias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public interface HbUserGenericDao extends GenericDAO<Hbusers, BigDecimal> {

	public Mensaje importData(Ciudadano user, String email, String pass);
	
	
}