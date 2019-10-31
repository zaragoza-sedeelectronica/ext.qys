package org.sede.servicio.qys.dao;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.sede.core.anotaciones.Esquema;
import org.sede.core.dao.JPAIgnoreTraversableResolver;
import org.sede.servicio.qys.entity.EntidadExterna;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;

/**
 * Clase que implemente la interfaz EntidadExternaGenericDAO
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Repository("entidadExternaTicketing")
@Transactional(Esquema.TMTICKETING)
public class EntidadExternaGenericDAOImpl extends GenericDAOImpl <EntidadExterna, BigDecimal> implements EntidadExternaGenericDAO {
	
		
	/**
	 * Metodo setEntityManager
	 * 
	 * @param entityManager
	 */
	@PersistenceContext(unitName=Esquema.TICKETING)
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