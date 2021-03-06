/* Copyright (C) 2020 Oficina T�cnica de Participaci�n, Transparenica y Gobierno Abierto del Ayuntamiento de Zaragoza
 * 
 * Este fichero es parte del "Quejas y Sugerencias - Open City Zaragoza".
 *
 * "Quejas y Sugerencias - Open City Zaragoza" es un software libre; usted puede utilizar esta obra respetando la licencia GNU General Public License, versi�n 3 o posterior, publicada por Free Software Foundation
 *
 * Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�, SIN GARANT�AS NI CONDICIONES DE NING�N TIPO, ni expresas ni impl�citas.
 * V�ase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia. 
 *
 * Para m�s informaci�n, puede contactar con los autores en: gobiernoabierto@zaragoza.es, sedelectronica@zaragoza.es
 */

package org.sede.servicio.qys.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.sede.core.rest.Mensaje;
import org.sede.core.utils.Funciones;
import org.sede.servicio.acceso.entity.Ciudadano;
import org.sede.servicio.qys.ConfigQys;
import org.sede.servicio.qys.entity.db.Hbusers;
import org.springframework.http.HttpStatus;
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

	@Override
	public Mensaje importData(Ciudadano user, String email, String pass) {
		try {
			Query q = em().createNativeQuery("select USR_HBID,status from hbusers where lower(USR_USERNAME)=? and password=?");
			Object[] row = (Object[])q
					.setParameter(1, email.toLowerCase())
					.setParameter(2, Funciones.calculateUserPassword(pass)).getSingleResult();
			BigDecimal idUser = (BigDecimal)row[0]; 
			String status = (String) row[1];
			if (StringUtils.isEmpty(status)) {
				Query update = this.em().createNativeQuery("update hbrequests set user_id=? where hbrequests.usr_hbid_introducer=? and user_id is null")
						.setParameter(1, user.getId())
						.setParameter(2, idUser);
				int registrosAfectados = update.executeUpdate();
				update = this.em().createNativeQuery("update hbusers set status='S' where USR_HBID=?")
						.setParameter(1, idUser);
				update.executeUpdate();
				return new Mensaje(HttpStatus.OK.value(), "Se han importado " + registrosAfectados + " quejas y sugerencias");
				
			} else {
				return new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha podido importar datos ya que los datos de la persona ya han sido importados");
			}
			
		} catch (NoResultException e) {
			return new Mensaje(HttpStatus.BAD_REQUEST.value(), "No se ha encontrado ninguna persona con los datos indicados");
		}
		
	}
}