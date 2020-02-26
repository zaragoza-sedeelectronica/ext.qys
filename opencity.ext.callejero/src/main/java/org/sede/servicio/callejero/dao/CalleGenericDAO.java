package org.sede.servicio.callejero.dao;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.sede.servicio.callejero.entity.Calle;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

public interface CalleGenericDAO extends GenericDAO<Calle, Integer> {
	public Set<ConstraintViolation<Object>> validar(Object registro);
}
