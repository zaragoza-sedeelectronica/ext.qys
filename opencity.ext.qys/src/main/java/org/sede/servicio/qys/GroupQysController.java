package org.sede.servicio.qys;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.sede.core.anotaciones.Cache;
import org.sede.core.anotaciones.Description;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.anotaciones.Fiql;
import org.sede.core.anotaciones.Gcz;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.dao.SearchFiql;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.utils.Funciones;
import org.sede.servicio.qys.dao.QySDao;
import org.sede.servicio.qys.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;


/**
 * Controlador categorias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Gcz(servicio="TICKETING",seccion="REQUESTS")
@Controller
@Transactional(ConfigQys.TM)
@RequestMapping(value = "/" + GroupQysController.MAPPING, method = RequestMethod.GET)
public class GroupQysController {

	/**
	 *  Variable servicio
	 */
    public static final String SERVICIO = "group";
    /**
	 *  Variable mapping del servicio
	 */
    public static final String MAPPING = "servicio/quejas-sugerencias/" + SERVICIO;

    /**
	 * Intial
	 */
    @Autowired
    private QySDao dao;


    /**
     * Metodo apiListar
     * 
     * @param search
     * @return
     * @throws SQLException
     */
    @Permisos(Permisos.ADMIN)
    @Cache(Cache.DURACION_30MIN)
    @Description("Listado de grupo")
    @ResponseClass(value = Group.class, entity = SearchResult.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
    public @ResponseBody ResponseEntity<?> apiListar(@Fiql SearchFiql search) throws SQLException {
    	return ResponseEntity.ok(dao.getGroups());
    }

    /**
     * Metodo api crear categoria
     * 
     * @param registro
     * @return
     */
    @Permisos(Permisos.CATEGORY)
	@RequestMapping(method = RequestMethod.POST, consumes = { MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Description("Crear una grupo")
	@ResponseClass(value = Group.class)
	public @ResponseBody ResponseEntity<?> apiCrear(@RequestBody Group registro) {
    	registro.setGroup_code(null);
		if (registro.getGroup_name() != null && !"".equals(registro.getGroup_name().trim())
			) {
			return dao.crearGroup(registro);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "Falta campos obligatorios"));
		}
    	
	}
    
    /**
     * Metodo modificar
     * 
     * @param id
     * @param registro
     * @return
     */
    @Permisos(Permisos.CATEGORY)
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = { MimeTypes.JSON, MimeTypes.XML }, produces = { MimeTypes.JSON, MimeTypes.XML })
	@Description("Modificar un grupo")
	@ResponseClass(value = Group.class)
	public @ResponseBody ResponseEntity<?> apiModificar(@PathVariable BigDecimal id, @RequestBody Group registro) {
    	registro.setGroup_code(id);
		if (registro.getGroup_name() != null && !"".equals(registro.getGroup_name().trim())
			) {
	    	dao.saveGroup(registro);
	    	return ResponseEntity.ok(registro);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(HttpStatus.BAD_REQUEST.value(), "Falta campos obligatorios para modificar"));
		}	
	}
}
