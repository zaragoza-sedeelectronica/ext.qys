package org.sede.servicio.callejero.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sede.servicio.callejero.ConfigCallejero;
import org.sede.servicio.callejero.entity.MapeoSemanticoVia;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
@Repository
@Transactional(ConfigCallejero.TM)
public class MapeoSemanticoGenericDAOImpl extends GenericDAOImpl <MapeoSemanticoVia, Integer> implements MapeoSemanticoGenericDAO {
	
	@PersistenceContext(unitName=ConfigCallejero.ESQUEMA)
	public void setEntityManager(EntityManager entityManager) {
		this.setEm(entityManager);
	}

}