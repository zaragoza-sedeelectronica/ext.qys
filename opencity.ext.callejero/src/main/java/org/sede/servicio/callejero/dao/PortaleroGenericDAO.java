package org.sede.servicio.callejero.dao;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.sede.servicio.callejero.entity.Portalero;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

public interface PortaleroGenericDAO extends GenericDAO<Portalero, Integer> {
	public Set<ConstraintViolation<Object>> validar(Object registro);
}
