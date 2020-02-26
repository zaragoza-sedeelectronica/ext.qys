package org.sede.servicio.callejero.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.sede.core.dao.JPAIgnoreTraversableResolver;
import org.sede.servicio.callejero.ConfigCallejero;
import org.sede.servicio.callejero.entity.CalleTerritorio;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
@Repository
@Transactional(ConfigCallejero.TM)
public class CalleTerritorioGenericDAOImpl extends GenericDAOImpl <CalleTerritorio, Integer> implements CalleTerritorioGenericDAO {
	
	@PersistenceContext(unitName=ConfigCallejero.ESQUEMA)
	public void setEntityManager(EntityManager entityManager) {
		this.setEm(entityManager);
	}
	
	public Set<ConstraintViolation<Object>> validar(Object registro) {
		ValidatorFactory factory = Validation.byDefaultProvider().configure().traversableResolver(new JPAIgnoreTraversableResolver()).buildValidatorFactory();
		Validator validator = factory.getValidator();
		return validator.validate(registro);
	}
}