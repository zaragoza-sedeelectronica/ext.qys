package org.sede.servicio.qys.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.sede.servicio.qys.ConfigQys;
import org.sede.servicio.qys.entity.Request;
import org.sede.servicio.qys.entity.UtilsQyS;
import org.sede.servicio.qys.entity.db.Hbrequestloadfiles;
import org.sede.servicio.qys.entity.db.Hbrequests;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;


@Repository
@Transactional(ConfigQys.TM)
public class HBRequestDaoImpl extends GenericDAOImpl <Hbrequests, BigDecimal> implements HBRequestDao {
	
	@PersistenceContext(unitName=ConfigQys.ESQUEMA)
	public void setEntityManager(EntityManager entityManager) {
		this.setEm(entityManager);
	}
	
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
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @param status
	 * @param validated
	 * @param usuarioTicketing
	 * @param group_operator
	 * @param operator
	 * @param answer_requested
	 * @param barrio_code
	 * @param origin
	 * @param interno
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
			final String notes,
			final String service_code,
			final Integer externo_code,
			final Integer agency_responsible,
			final Long account_id,
			final Long user_id,
			final Date startDate, 
			final Date endDate,
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
			final Integer id_cat_sip) throws SQLException {
		
		Search busqueda = new Search();
				
		busqueda.setMaxResults(rows);
		busqueda.setFirstResult(start);
		
		BigDecimal usuario = StringUtils.isNotEmpty(usuarioTicketing) ? new BigDecimal(usuarioTicketing) : (account_id == null ? null : BigDecimal.valueOf(account_id));		
		if (usuario == null && user_id == null) {
			busqueda.addFilter(Filter.equal("rqtPublic", "S"));
			busqueda.addFilter(Filter.equal("validated", "S"));
			busqueda.addFilter(Filter.greaterThan("service_request_id", UtilsQyS.RQT_ID_FROM));
			busqueda.addFilter(Filter.notEqual("hbrequeststates.rstHbid", 5));

		} else if (user_id == null && !usuario.equals(new BigDecimal(-1)) && !usuario.equals(UtilsQyS.ID_MAIN_ADMIN)) {
			if (usuario.equals(UtilsQyS.ID_MAIN_ADMIN) || usuario.equals(UtilsQyS.ID_010)) {
				busqueda.addFilterOr(Filter.equal("usrHbidRequester", usuario), 
            		Filter.equal("usrHbidIntroducer", usuario),
            		Filter.equal("usrHbidManager", usuario),
            		Filter.ilike("operador", ":" + operadorGet),
            		Filter.isNull("usrHbidManager"));
			} else {
				busqueda.addFilterOr(Filter.equal("usrHbidRequester", usuario), 
	            	Filter.equal("usrHbidIntroducer", usuario),
	            	Filter.equal("usrHbidManager", usuario),
	            	Filter.ilike("operador", ":" + operadorGet));
			}
            if (status == null && ids == null && usuarioTicketing == null) {
            	busqueda.addFilter(Filter.greaterThan("service_request_id", UtilsQyS.RQT_ID_FROM));
            }
		}
		
		if (StringUtils.isNotEmpty(interno)) {
			busqueda.addFilterOr(Filter.equal("interno_code", interno), Filter.ilike("operador", ":" + interno));
		}
		
		if (StringUtils.isNotEmpty(service_code)) {
			busqueda.addFilterIn("hbcategories.calHbid", UtilsQyS.asBigDecimalArray(service_code));
			
		}
		if (externo_code != null && externo_code > 0) {
			busqueda.addFilter(Filter.equal("hbEntidadesexternas.id", externo_code));
		}
    
		if (StringUtils.isNotEmpty(group_operator)) {
			busqueda.addFilter(Filter.ilike("operador", group_operator + ":"));
		}
    
	    if (StringUtils.isNoneEmpty(answer_requested)) {
	    	busqueda.addFilter(Filter.equal("answerRequested", answer_requested));
	    }
    
	    if (id_cat_sip != null && id_cat_sip > 0) {
	    	busqueda.addFilter(Filter.equal("id_cat_sip", id_cat_sip));
	    
	    }
	    if (StringUtils.isNotEmpty(operator)) {
			busqueda.addFilter(Filter.ilike("operador", ":" + operator));
		}
        
	    if (StringUtils.isNotEmpty(origin)) {
	    	busqueda.addFilter(Filter.equal("origin", Integer.parseInt(origin)));
	    }
    
//    TODO búsqueda por barrio if p_barrio is not null then
//      consulta := consulta || 'hb_re.address_id in (select p.id_por from intra.portalero p where p.id_jun='||p_barrio||') AND ';      
//    end if;

    if (StringUtils.isNotEmpty(validated)) {
    	if ("N".equals(validated)) {
    		busqueda.addFilter(Filter.equal("rqtPublic", "S"));
    		busqueda.addFilter(Filter.equal("validated", validated));
    	} else {
    		busqueda.addFilter(Filter.equal("validated", validated));
    	}
    }
    if (StringUtils.isNotEmpty(ids)) {
    	busqueda.addFilter(Filter.in("service_request_id", UtilsQyS.asBigDecimalArray(ids)));
    }
    if (StringUtils.isNotEmpty(title)) {
    	busqueda.addFilter(Filter.ilike("title", title));
    }
    if (StringUtils.isNotEmpty(notes)) {
    	busqueda.addFilter(Filter.ilike("notes", title));
    }
    
    if (StringUtils.isNotEmpty(status)) {
    	if ("open".equalsIgnoreCase(status)) {
    		busqueda.addFilterEmpty("updated_datetime");
		} else if ("closed".equalsIgnoreCase(status)) {
    		busqueda.addFilterNotEmpty("updated_datetime");
    	} else {
    		busqueda.addFilterIn("hbrequeststates.rstHbid", UtilsQyS.asBigDecimalArray(status));
    	}
    }
    
    if (agency_responsible != null && agency_responsible > 0) {
    	busqueda.addFilter(Filter.equal("usrHbidManager", agency_responsible));
    }
    
    if (account_id != null && account_id > 0) {
    	busqueda.addFilter(Filter.equal("usrHbidRequester", account_id));
    }
    
    if (user_id != null && user_id > 0) {
    	busqueda.addFilter(Filter.equal("userId", user_id));
    }
    
    if (startDate != null) {
    	busqueda.addFilter(Filter.greaterOrEqual("requested_datetime", startDate));
    	busqueda.addFilter(Filter.lessOrEqual("requested_datetime", endDate));
    }
    
    if (StringUtils.isNotEmpty(type)) {
    	busqueda.addFilterIn("type.rtyHbid", UtilsQyS.asBigDecimalArray(type));
    }
    
    return this.searchAndCount(busqueda);
		
	}


	public Hbrequests findRqtRequestNumber(BigDecimal id) {
		Query q = em().createQuery("from Hbrequests where service_request_id=:id", Hbrequests.class)
				.setParameter("id", id);
		return (Hbrequests)q.getSingleResult();
	}
		
	public Hbrequestloadfiles findFile(BigDecimal id, BigDecimal idFile) {
		Query q = em().createQuery("from Hbrequestloadfiles where hbrequests.rqtHbid=:id and rlfHbid=:idFile", Hbrequestloadfiles.class)
			.setParameter("id", id)
			.setParameter("idFile", idFile);
		return (Hbrequestloadfiles)q.getSingleResult();
	}
	
}