package org.sede.servicio.callejero;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.search.SearchParseException;
import org.sede.core.anotaciones.Cache;
import org.sede.core.anotaciones.Description;
import org.sede.core.anotaciones.Fiql;
import org.sede.core.anotaciones.OpenData;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.dao.SearchFiql;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.servicio.ModelAttr;
import org.sede.servicio.callejero.dao.CalleTerritorioGenericDAO;
import org.sede.servicio.callejero.entity.CalleTerritorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;

@Transactional(ConfigCallejero.TM)
@Controller
@RequestMapping(value = "/" + CallejeroFiscalController.MAPPING, method = RequestMethod.GET)
@Description("Ayuntamiento: Callejero fiscal")
public class CallejeroFiscalController {

	private static final String SERVICIO = "callejero-fiscal";
	public static final String MAPPING = "servicio/" + SERVICIO;

	@Autowired
	public CalleTerritorioGenericDAO dao;

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String redirect() {
		return "redirect:" + SERVICIO + "/";
	}

	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@ResponseClass(value = CalleTerritorio.class, entity = SearchResult.class)
	@Description("Listado de calles")
	@RequestMapping(
			method = RequestMethod.GET,
			produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiListar(@Fiql SearchFiql search, @RequestParam(name = "letra", required = false) String letra) throws SearchParseException {
		search.setExcludeFields("letra");
		Search s = search.getConditions(CalleTerritorio.class);
		if (StringUtils.isEmpty(search.getSort())) {
			List<Sort> sorts = new ArrayList<Sort>();
			sorts.add(new Sort("title_ext"));
			s.setSorts(sorts);
		}
		if (StringUtils.isNotEmpty(letra)) {
			s.addFilter(Filter.like("title_ext", letra + "%"));
		}
		return ResponseEntity.ok(dao.searchAndCount(s));
	}

	@OpenData
	@Cache(Cache.DURACION_30MIN)
	@ResponseClass(CalleTerritorio.class)
	@Description("Datos de una calle")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML, MimeTypes.CSV, MimeTypes.JSONLD, MimeTypes.RDF, MimeTypes.TURTLE, MimeTypes.RDF_N3})
	public @ResponseBody ResponseEntity<?> apiDetalle(@PathVariable Integer id) {
		CalleTerritorio registro = dao.find(id);
		if (registro == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("generic.notfound", null, LocaleContextHolder.getLocale())));
		} else {
			return ResponseEntity.ok(registro);
		}
	}

	@RequestMapping(path = "/", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String home(Model model, @Fiql SearchFiql search, @RequestParam(name = "letra", required = false) String letra) throws SearchParseException {
		search.setExcludeFields("letra");
		if (StringUtils.isEmpty(search.getSearchExpression()) && StringUtils.isEmpty(letra)) {
			return MAPPING + "/index";
		} else {
			model.addAttribute(ModelAttr.RESULTADO, apiListar(search, letra));
			return MAPPING + "/list";
		}
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
			MediaType.TEXT_HTML_VALUE, "*/*" })
	public String detalle(@PathVariable Integer id, Model model,
						  HttpServletRequest request) {
		model.addAttribute(ModelAttr.REGISTRO, apiDetalle(id));
		return MAPPING + "/detalle";
	}
}