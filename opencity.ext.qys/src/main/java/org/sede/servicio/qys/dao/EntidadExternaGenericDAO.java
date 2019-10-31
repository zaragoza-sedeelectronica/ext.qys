package org.sede.servicio.qys.dao;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.sede.servicio.qys.entity.EntidadExterna;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

/**
 * Interfaz EntidadExternaGenericDAO
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public interface EntidadExternaGenericDAO extends GenericDAO<EntidadExterna, BigDecimal>  {
	/**
	 * Metodo interfaz validar
	 * 
	 * @param registro
	 * @return
	 */
	public Set<ConstraintViolation<Object>> validar(Object registro);
}
