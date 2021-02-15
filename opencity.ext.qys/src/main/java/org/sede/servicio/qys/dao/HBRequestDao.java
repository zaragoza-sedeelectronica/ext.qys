/* Copyright (C) 2020 Oficina Técnica de Participación, Transparenica y Gobierno Abierto del Ayuntamiento de Zaragoza
 * 
 * Este fichero es parte del "Quejas y Sugerencias - Open City Zaragoza".
 *
 * "Quejas y Sugerencias - Open City Zaragoza" es un software libre; usted puede utilizar esta obra respetando la licencia GNU General Public License, versión 3 o posterior, publicada por Free Software Foundation
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL», SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia. 
 *
 * Para más información, puede contactar con los autores en: gobiernoabierto@zaragoza.es, sedelectronica@zaragoza.es
 */

package org.sede.servicio.qys.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.sede.servicio.qys.entity.Request;
import org.sede.servicio.qys.entity.db.Hbrequestloadfiles;
import org.sede.servicio.qys.entity.db.Hbrequests;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;


public interface HBRequestDao extends GenericDAO<Hbrequests, BigDecimal> {
	
	/**
	 * 
	 * Metodo buscar y contar peticiones
	 * 
	 * @param rows
	 * @param start
	 * @param sort
	 * @param ids
	 * @param title
	 * @param notes
	 * @param service_code
	 * @param externo_code
	 * @param agency_responsible
	 * @param account_id
	 * @param user_id
	 * @param start_date
	 * @param end_date
	 * @param type
	 * @param status
	 * @param validated
	 * @param usuarioTicketing
	 * @param group_operator
	 * @param operator
	 * @param answer_requested
	 * @param barrio_code
	 * @param origin
	 * @param inspector
	 * @param operadorGet
	 * @param id_cat_sip
	 * @return
	 * @throws SQLException
	 */
	public SearchResult<Request> searchAndCountRequest(final int rows, 
			final int start, 
			final String sort, 
			final String ids, 
			final String title,
			final String address,
			final String notes,
			final String service_code,
			final Integer externo_code,
			final Integer agency_responsible,
			final Long account_id,
			final Long user_id,
			final Date start_date, 
			final Date end_date,
			final String type,
			final String status,
			final String validated,
			final String usuarioTicketing,
			final String group_operator,
			final String operator,
			final String answer_requested,
			final String barrio_code,
			final String origin,
			final String interno,
			final String operadorGet,
			final Integer id_cat_sip) throws SQLException;

	public Hbrequests findRqtRequestNumber(BigDecimal id);
	public Hbrequestloadfiles findFile(BigDecimal id, BigDecimal idFile);
}