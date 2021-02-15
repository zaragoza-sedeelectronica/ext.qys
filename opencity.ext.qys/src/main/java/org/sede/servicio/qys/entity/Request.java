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

package org.sede.servicio.qys.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.Context;
import org.sede.core.anotaciones.Description;
import org.sede.core.anotaciones.InList;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.PublicName;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.anotaciones.RdfMultiple;
import org.sede.core.anotaciones.RequiredSinValidacion;
import org.sede.core.anotaciones.ResultsOnly;
import org.sede.core.dao.EntidadBase;
import org.sede.core.geo.Geometria;
import org.sede.core.rest.CheckeoParametros;
import org.sede.core.utils.Funciones;
import org.sede.servicio.qys.InformacionPublicaController;
import org.sede.servicio.qys.QysController;
/**
 * Clase Request
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="request")
@ResultsOnly(xmlroot="service_requests")
@Rdf(contexto=Context.OPEN311, propiedad="ServiceRequest")
@PathId("/" + QysController.MAPPING)
public class Request extends EntidadBase {

	/**
	 *  variable service_request_id
	 */
	@Description("Identificador de la queja/sugerecia")
	@RequiredSinValidacion
	private BigDecimal service_request_id;
	/**
	 *  variable status
	 */
	@InList({"open", "closed"})
	@RequiredSinValidacion
	@Rdf(contexto = Context.OPEN311, propiedad = "Status")
	private String status;
	/**
	 *  variable status_notes
	 */
	@Rdf(contexto = Context.OPEN311, propiedad = "Details")
	private String status_notes; // Explanation of why status was changed to current state or more details on current status than conveyed with status alone.	 May not be returned
	/**
	 *  variable service_code
	 */
	@Description("Identificador de categoría asignada")
	@RequiredSinValidacion
	@Rdf(contexto = Context.OPEN311, propiedad = "has311Type")
	private BigDecimal service_code; // The unique identifier for the service request type	
	/**
	 *  variable service_name
	 */
	@Description("Nombre de la categoría asignada")
	@RequiredSinValidacion
	@RdfMultiple({@Rdf(contexto = Context.SKOS, propiedad = "prefLabel"), @Rdf(contexto = Context.OPEN311, propiedad = "TypeName")})
	private String service_name; // The human readable name of the service request type
	/**
	 *  variable title
	 */
	@NotNull @Size(max=400)
	@RdfMultiple({@Rdf(contexto = Context.DCT, propiedad = "title"), @Rdf(contexto = Context.RDFS, propiedad = "label")})
	private String title;
	/**
	 *  variable notes
	 */
	@Size(max=400)
	@Permisos(Permisos.ADMIN)
	private String notes;
	/**
	 *  variable description
	 */
	@NotNull @Size(max=3000)
	@RdfMultiple({@Rdf(contexto = Context.DCT, propiedad = "description"), @Rdf(contexto = Context.RDFS, propiedad = "comment")})
	private String description; // A full description of the request or report submitted.	 
								 	// This may contain line breaks, but not html or code. 
									// Otherwise, this is free form text limited to 4,000 characters.
	/**
	 *  variable agency_responsible_code
	 */
	@Permisos(Permisos.ADMIN) //¿Anonimizar? Porque ahora No lo podemos publicar porque en algunos casos son Personas
	private BigDecimal agency_responsible_code;
	/**
	 *  variable agency_responsible
	 */
	@Permisos(Permisos.ADMIN)
	private String agency_responsible;
	/**
	 *  variable agency_responsible_email
	 */
	@Permisos(Permisos.ADMIN)
	private String agency_responsible_email;
//	TODO Publicar?? @Permisos(Permisos.DET)	
	/**
	 *  variable service_notice
	 */
	private String service_notice; // Information about the action expected to fulfill the request or otherwise address the information reported.
	/**
	 *  variable requested_datetime
	 */
	@RequiredSinValidacion
	@Rdf(contexto = Context.OPEN311, propiedad = "hasOpenDate")
	private Date requested_datetime; // The date and time when the service request was made
	/**
	 *  variable updated_datetime
	 */
	@Rdf(contexto = Context.OPEN311, propiedad = "hasUpdateDate")
	private Date updated_datetime; // The date and time when the service request was last modified. For requests with status=closed, this will be the date the request was closed.
	/**
	 *  variable expected_datetime
	 */
	@Rdf(contexto = Context.OPEN311, propiedad = "hasDueDate")
	private Date expected_datetime; // The date and time when the service request can be expected to be fulfilled. This may be based on a service-specific service level agreement.
	/**
	 *  variable expected_resolution_datetime
	 */
	@Permisos(Permisos.ADMIN)
	private Date expected_resolution_datetime;
	/**
	 *  variable gestor_datetime
	 */
	@Permisos(Permisos.DET)
	private Date gestor_datetime; //Fecha en la que llega al organo gestor
	/**
	 *  variable externo_datetime
	 */
	@Permisos("SENDEXTERNO")
	private Date externo_datetime; //Fecha en la que se deriva a externo
	/**
	 *  variable externo_code
	 */
	@Permisos("SENDEXTERNO")
	private BigDecimal externo_code; //Fecha en la que se deriva a externo
	/**
	 *  variable externo_name
	 */
	@Permisos("SENDEXTERNO")
	private String externo_name; //Fecha en la que se deriva a externo
	/**
	 *  variable resolved_datetime
	 */
	@Permisos(Permisos.DET)
	private Date resolved_datetime; //Fecha en la que se resuelve
//	TODO publicar?? @Permisos(Permisos.DET)
	/**
	 *  variable closed_datetime
	 */
	@Rdf(contexto = Context.OPEN311, propiedad = "hasClosedDate")
	private Date closed_datetime; //Fecha en la que se cierra
	/**
	 *  variable inspector_datetime
	 */
	@Permisos(Permisos.SENDINSPECTOR)
	private Date inspector_datetime; //Fecha en la que se cierra
	/**
	 *  variable account_id
	 */
	@Permisos(Permisos.NEW)
	private String account_id;
	/**
	 *  variable user_id
	 */
	@Permisos(Permisos.NEW)
	private Long user_id; //Usuario ogob
	/**
	 *  variable device_id
	 */
	@Permisos(Permisos.NEW)
	private String device_id;
	/**
	 *  variable first_name
	 */
	@Permisos(Permisos.NEW)
	private String first_name;
	/**
	 *  variable last_name
	 */
	@Permisos(Permisos.NEW)
	private String last_name;
	/**
	 *  variable phone
	 */
	@Permisos(Permisos.NEW)
	private String phone;
	/**
	 *  variable email
	 */
	@Permisos(Permisos.NEW)
	private String email;
	/**
	 *  variable user_address
	 */
	@Permisos(Permisos.NEW)
	private String user_address;
	/**
	 *  variable visible
	 */
	@Permisos(Permisos.ADMIN) @InList({"S", "N"})
	private String visible;
	/**
	 *  variable validated
	 */
	@Permisos(Permisos.ADMIN)
	private String validated;
	/**
	 *  variable media_body
	 */
	@Permisos(Permisos.NEW)
	private String media_body;
	/**
	 *  variable media_name
	 */
	@Permisos(Permisos.NEW)
	private String media_name;
	/**
	 *  variable media_description
	 */
	@Permisos(Permisos.NEW)
	private String media_description;
	/**
	 *  variable media_url
	 */
	@RdfMultiple({@Rdf(contexto = Context.OPEN311, propiedad = "supportingMaterial"), @Rdf(contexto = Context.SCHEMA, propiedad = "url")})
	private String media_url; // A URL to media associated with the request, eg an image.	 
							//A convention for parsing media from this URL has yet to be established, so currently it will be done on a case by case basis much like Twitter.com does. For example, if a jurisdiction accepts photos submitted via Twitpic.com, then clients can parse the page at the Twitpic URL for the image given the conventions of Twitpic.com. This could also be a URL to a media RSS feed where the clients can parse for media in a more structured way.
	/**
	 *  variable zona_inspeccion
	 */
	@Permisos(Permisos.ZONAINSPECCION)
	private Integer zona_inspeccion;
	/**
	 *  variable inspector
	 */
	@Permisos(Permisos.ZONAINSPECCION)
	private String inspector;
	/**
	 *  variable id_cita
	 */
	@Permisos(Permisos.DET)
	private BigDecimal id_cita; //Identificador de cita
	/**
	 *  variable token
	 */
	@Permisos(Permisos.DET)
	private String token;
	/**
	 *  variable address_string
	 */
	@Rdf(contexto = Context.OPEN311, propiedad = "hasAddress")
	private String address_string; // Human readable address or description of location.	 
								// This should be provided from most specific to most general geographic unit, eg address number or cross streets, 
								// street name, neighborhood/district, city/town/village, county, postal code.
	/**
	 *  variable address_id
	 */
	private String address_id; // The internal address ID used by a jurisdictions master address repository or other addressing system.
	/**
	 *  variable zipcode
	 */
	@RdfMultiple({@Rdf(contexto = Context.OPEN311, propiedad = "hasAddress"), @Rdf(contexto = Context.VCARD, propiedad = "postal-code")})
	private String zipcode; // The postal code for the location of the service request.
	/**
	 *  variable x
	 */
	private Double x;
	/**
	 *  variable y
	 */
	private Double y;
	/**
	 *  variable lon
	 */
	@PublicName("long")
	@RdfMultiple({@Rdf(contexto = Context.XSD, propiedad = "double"), @Rdf(contexto = Context.GEO, propiedad = "long")})
	private Double lon;
	/**
	 *  variable lat
	 */
	@RdfMultiple({@Rdf(contexto = Context.XSD, propiedad = "double"), @Rdf(contexto = Context.GEO, propiedad = "lat")})
	private Double lat;
	
	/**
	 *  variable actions
	 */
	@Permisos(Permisos.ADMIN)
	private ArrayList<Accion> actions;
	/**
	 *  variable files
	 */
	@Permisos(Permisos.ADMIN)
	private ArrayList<Adjunto> files;
	/**
	 *  variable origin
	 */
	@Permisos(Permisos.ADMIN)
	private BigDecimal origin;
	/**
	 *  variable type
	 */
	@Permisos(Permisos.ADMIN)
	private BigDecimal type;
	/**
	 *  variable priority
	 */
	@Permisos(Permisos.ADMIN)
	private BigDecimal priority;
	/**
	 *  variable status_admin
	 */
	@Permisos(Permisos.ADMIN)
	private String status_admin;
	/**
	 *  variable group_operator
	 */
	@Permisos(Permisos.ADMIN)
	private String group_operator;
	/**
	 *  variable operator
	 */
	@Permisos(Permisos.ADMIN)
	private String operator;
	/**
	 *  variable answer_requested
	 */
	@Permisos(Permisos.ADMIN)
	private String answer_requested;
	/**
	 *  variable id_cat_sip
	 */
	private BigDecimal id_cat_sip;
	/**
	 *  variable cat_sip
	 */
	private String cat_sip;
	
	public BigDecimal getService_request_id() {
		return service_request_id;
	}

	public void setService_request_id(BigDecimal service_request_id) {
		this.service_request_id = service_request_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_notes() {
		return status_notes;
	}

	public void setStatus_notes(String status_notes) {
		this.status_notes = status_notes;
	}

	public BigDecimal getService_code() {
		return service_code;
	}

	public void setService_code(BigDecimal service_code) {
		this.service_code = service_code;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAgency_responsible() {
		return agency_responsible;
	}

	public void setAgency_responsible(String agency_responsible) {
		this.agency_responsible = agency_responsible;
	}
	
	public String getAgency_responsible_email() {
		return agency_responsible_email;
	}

	public void setAgency_responsible_email(String agency_responsible_email) {
		this.agency_responsible_email = agency_responsible_email;
	}

	public String getService_notice() {
		return service_notice;
	}

	public void setService_notice(String service_notice) {
		this.service_notice = service_notice;
	}

	public Date getRequested_datetime() {
		return requested_datetime;
	}

	public void setRequested_datetime(Date requested_datetime) {
		this.requested_datetime = requested_datetime;
	}

	public Date getInspector_datetime() {
		return inspector_datetime;
	}

	public void setInspector_datetime(Date inspector_datetime) {
		this.inspector_datetime = inspector_datetime;
	}

	public Date getUpdated_datetime() {
		return updated_datetime;
	}

	public void setUpdated_datetime(Date updated_datetime) {
		this.updated_datetime = updated_datetime;
	}

	public Date getExpected_datetime() {
		return expected_datetime;
	}

	public void setExpected_datetime(Date expected_datetime) {
		this.expected_datetime = expected_datetime;
	}

	

	public String getAddress_string() {
		return address_string;
	}

	public void setAddress_string(String address_string) {
		this.address_string = address_string;
	}

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getLon() {
		return lon;
	}
	
	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public String getMedia_url() {
		return media_url;
	}
	
	public BigDecimal getId_cita() {
		return id_cita;
	}

	public void setId_cita(BigDecimal id_cita) {
		this.id_cita = id_cita;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getZona_inspeccion() {
		return zona_inspeccion;
	}

	public void setZona_inspeccion(Integer zona_inspeccion) {
		this.zona_inspeccion = zona_inspeccion;
	}

	public void setMedia_url(String media_url) {
		if (media_url != null &&  (media_url.toLowerCase().indexOf(".jpg") > 0
				|| media_url.toLowerCase().indexOf(".jpeg") > 0
				|| media_url.toLowerCase().indexOf(".gif") > 0
				|| media_url.toLowerCase().indexOf(".png") > 0)) {
			this.media_url = media_url;
		} else {
			this.media_url = null;
		}
	}

	public ArrayList<Accion> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Accion> actions) {
		this.actions = actions;
	}

	public ArrayList<Adjunto> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<Adjunto> files) {
		this.files = files;
	}

	public BigDecimal getOrigin() {
		return origin;
	}

	public void setOrigin(BigDecimal origin) {
		this.origin = origin;
	}

	public BigDecimal getType() {
		return type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	public BigDecimal getPriority() {
		return priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public String getStatus_admin() {
		return status_admin;
	}

	public void setStatus_admin(String status_admin) {
		this.status_admin = status_admin;
	}


	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public BigDecimal getAgency_responsible_code() {
		return agency_responsible_code;
	}

	public void setAgency_responsible_code(BigDecimal agency_responsible_id) {
		this.agency_responsible_code = agency_responsible_id;
	}

	public String getMedia_body() {
		return media_body;
	}

	public void setMedia_body(String media_body) {
		this.media_body = media_body;
	}

	
	public String getMedia_description() {
		return media_description;
	}

	public void setMedia_description(String media_description) {
		this.media_description = media_description;
	}

	public Date getGestor_datetime() {
		return gestor_datetime;
	}

	public void setGestor_datetime(Date gestor_datetime) {
		this.gestor_datetime = gestor_datetime;
	}
	
	public Date getResolved_datetime() {
		return resolved_datetime;
	}

	public void setResolved_datetime(Date resolved_datetime) {
		this.resolved_datetime = resolved_datetime;
	}

	public Date getClosed_datetime() {
		return closed_datetime;
	}

	public void setClosed_datetime(Date closed_datetime) {
		this.closed_datetime = closed_datetime;
	}

	public BigDecimal getExterno_code() {
		return externo_code;
	}

	public void setExterno_code(BigDecimal externo_code) {
		this.externo_code = externo_code;
	}

	public Date getExterno_datetime() {
		return externo_datetime;
	}

	public void setExterno_datetime(Date externo_datetime) {
		this.externo_datetime = externo_datetime;
	}

	public String getExterno_name() {
		return externo_name;
	}

	public void setExterno_name(String externo_name) {
		this.externo_name = externo_name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	
	
	public String getGroup_operator() {
		return group_operator;
	}

	public void setGroup_operator(String group_operator) {
		this.group_operator = group_operator;
	}

	public String getAnswer_requested() {
		return answer_requested;
	}

	public void setAnswer_requested(String answer_requested) {
		this.answer_requested = answer_requested;
	}

	public Date getExpected_resolution_datetime() {
		return expected_resolution_datetime;
	}

	public void setExpected_resolution_datetime(Date expected_resolution_datetime) {
		this.expected_resolution_datetime = expected_resolution_datetime;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	/**
	 * Constructor
	 */
	public Request() {
		super();
	}


	/**
	 * Constructor 
	 * 
	 * @param service_code
	 * @param email
	 * @param address_string
	 * @param address_id
	 * @param account_id
	 * @param first_name
	 * @param last_name
	 * @param user_address
	 * @param phone
	 * @param title
	 * @param description
	 * @param media_name
	 * @param media_description
	 * @param media_body
	 * @param visible
	 * @param service_notice
	 * @param x
	 * @param y
	 * @param lat
	 * @param lon
	 */
	public Request(BigDecimal service_code, String email, String address_string, String address_id,
			String account_id, String first_name, String last_name, String user_address,
			String phone, String title, String description, String media_name, String media_description, String media_body, String visible,
			String service_notice, Double x, Double y, Double lat, Double lon) {
		super();
		this.service_code = service_code;
		this.email = email;
		this.address_string = address_string;
		this.address_id = address_id;
		this.account_id = account_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_address = user_address;
		this.phone = phone;
		this.title = title == null ? null : title.replaceAll("\\/\\/\\/", " ");;
		this.description = description == null ? null : description.replaceAll("\\/\\/\\/", " ");;
		this.media_name = media_name == null ? null : Funciones.normalizar(media_name);
		this.media_description = media_description == null ? UtilsQyS.quitarExtension(media_name) : media_description;
		this.media_body = media_body;
		this.visible = visible;
		this.lat = lat;
		this.lon = lon;
		this.service_notice = service_notice;
		
		if (lat != null && lon != null) {
			Double[] p =  Geometria.pasarAUTM30(lon, lat);
			this.x = p[0];
			this.y = p[1];
		} else {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param service_code
	 * @param agency_responsible_code
	 * @param type
	 * @param priority
	 * @param origin
	 * @param address_string
	 * @param address_id
	 * @param title
	 * @param description
	 * @param validated
	 * @param visible
	 * @param x
	 * @param y
	 */
	public Request(BigDecimal service_code, BigDecimal agency_responsible_code,
			String type, String priority, String origin,
			String address_string, String address_id, String title, String description,
			String validated, String visible, Double x, Double y) {
		super();
		this.service_code = service_code;
		this.agency_responsible_code = agency_responsible_code;
		this.type = type == null ? null: new BigDecimal(type);
		this.priority = priority == null ? null: new BigDecimal(priority);
		this.origin = origin == null ? null: new BigDecimal(origin);
		this.address_string = address_string;
		this.address_id = address_id;
		this.title = title;
		this.description = description;
		this.validated = validated;
		this.visible = visible;
		this.x = x;
		this.y = y;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public BigDecimal getId_cat_sip() {
		return id_cat_sip;
	}

	public void setId_cat_sip(BigDecimal id_cat_sip) {
		this.id_cat_sip = id_cat_sip;
	}
	@Override
	public String toString() {
		return "Request [service_request_id=" + service_request_id
				+ ", status=" + status + ", status_notes=" + status_notes
				+ ", service_code=" + service_code + ", service_name="
				+ service_name + ", title=" + title + ", description="
				+ description + ", agency_responsible_code="
				+ agency_responsible_code + ", agency_responsible="
				+ agency_responsible + ", agency_responsible_email="
				+ agency_responsible_email + ", service_notice="
				+ service_notice + ", requested_datetime=" + requested_datetime
				+ ", updated_datetime=" + updated_datetime
				+ ", expected_datetime=" + expected_datetime
				+ ", gestor_datetime=" + gestor_datetime
				+ ", externo_datetime=" + externo_datetime + ", externo_code="
				+ externo_code + ", externo_name=" + externo_name
				+ ", resolved_datetime=" + resolved_datetime
				+ ", user_id=" + user_id
				+ ", closed_datetime=" + closed_datetime + ", account_id="
				+ account_id + ", device_id=" + device_id + ", first_name="
				+ first_name + ", last_name=" + last_name + ", phone=" + phone
				+ ", email=" + email + ", user_address=" + user_address
				+ ", visible=" + visible + ", validated=" + validated
				+ ", media_body=" + media_body + ", media_name=" + media_name
				+ ", media_description=" + media_description + ", media_url="
				+ media_url + ", address_string=" + address_string
				+ ", address_id=" + address_id + ", zipcode=" + zipcode
				+ ", x=" + x + ", y=" + y + ", lon=" + lon + ", lat=" + lat
				+ ", actions=" + actions + ", files=" + files + ", origin="
				+ origin + ", type=" + type + ", priority=" + priority
				+ ", status_admin=" + status_admin + ", group_operator="
				+ group_operator + ", operator=" + operator
				+ ", answer_requested=" + answer_requested + ""
				+ ", token=" + token+ "]";
	}

	public String getCat_sip() {
		if (InformacionPublicaController.tipoSip.containsKey(this.getId_cat_sip().intValue())) {
			return InformacionPublicaController.tipoSip.get(this.getId_cat_sip().intValue());
		} else {
			return cat_sip;
		}
	}

	public void setCat_sip(String cat_sip) {
		this.cat_sip = cat_sip;
	}

	public String getUri() {
		return Funciones.obtenerPath(this.getClass()) + getService_request_id();
	}
	public String getRdf() {
		if (getX() != null && getY() != null) {
			double[] coord = (double[])Geometria.transform(getX(), getY(), CheckeoParametros.SRSUTM30N, CheckeoParametros.SRSWGS84);
			this.lon = coord[0];
			this.lat = coord[1];
			
			return "<div property=\"geo\" typeof=\"GeoCoordinates\">"
	            + "<meta property=\"latitude\" content=\"" + this.getLat() + "\"></meta>"
	            + "<meta property=\"longitude\" content=\"" + this.getLon() + "\"></meta>"
	        + "</div>";
			
		} else {
			return null;
		}
	}

}