package org.sede.servicio.qys.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.sede.core.dao.JPAIgnoreTraversableResolver;
import org.sede.servicio.qys.ConfigQys;
import org.sede.servicio.qys.entity.EntidadInterna;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;

/**
 * Clase que implemente la interfaz EntidadExternaGenericDAO
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Repository("entidadInternaTicketing")
@Transactional(ConfigQys.TM)
public class EntidadInternaGenericDAOImpl extends GenericDAOImpl <EntidadInterna, String> implements EntidadInternaGenericDAO {
	
		
	/**
	 * Metodo setEntityManager
	 * 
	 * @param entityManager
	 */
	@PersistenceContext(unitName=ConfigQys.ESQUEMA)
	public void setEntityManager(EntityManager entityManager) {
		this.setEm(entityManager);
	}
	/**
	 * Metodo validar
	 * 
	 * @param registro
	 */
	public Set<ConstraintViolation<Object>> validar(Object registro) {
		ValidatorFactory factory = Validation.byDefaultProvider().configure().traversableResolver(new JPAIgnoreTraversableResolver()).buildValidatorFactory();
		Validator validator = factory.getValidator();
		return validator.validate(registro);
	}
}