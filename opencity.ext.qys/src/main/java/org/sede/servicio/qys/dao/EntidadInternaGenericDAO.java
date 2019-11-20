package org.sede.servicio.qys.dao;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.sede.servicio.qys.entity.EntidadInterna;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

/**
 * Interfaz EntidadExternaGenericDAO
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public interface EntidadInternaGenericDAO extends GenericDAO<EntidadInterna, String>  {
	/**
	 * Metodo interfaz validar
	 * 
	 * @param registro
	 * @return
	 */
	public Set<ConstraintViolation<Object>> validar(Object registro);
}
