package org.sede.servicio.callejero.dao;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.sede.servicio.callejero.entity.CalleTerritorio;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

public interface CalleTerritorioGenericDAO extends GenericDAO<CalleTerritorio, Integer> {
	public Set<ConstraintViolation<Object>> validar(Object registro);
}
