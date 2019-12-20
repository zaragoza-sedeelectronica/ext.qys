package org.sede.servicio.qys.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sede.core.anotaciones.Esquema;
import org.sede.servicio.qys.ConfigQys;
import org.sede.servicio.qys.entity.db.Hbusers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;

/**
 * Clase que implementa la interfaz Quejas y sugerencias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Repository
@Transactional(ConfigQys.TM)
public class HbUserGenericDaoImpl extends GenericDAOImpl <Hbusers, BigDecimal> implements HbUserGenericDao {
	
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
}