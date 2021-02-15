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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.sede.core.anotaciones.InList;
import org.sede.core.anotaciones.Interno;
import org.sede.core.dao.EntidadBase;
import org.sede.core.validator.NIFNIE;
import org.sede.servicio.citaprevia.entity.Agenda;
import org.sede.servicio.citaprevia.entity.Cita;

/**
 * Clase SolicitudInformacionPublica
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@RestriccionMailDireccion
public class SolicitudInformacionPublica extends EntidadBase {
	/**
	 *  variable TIPOSOLICITUDINFORMACION
	 */
	@Interno
	public static final BigDecimal TIPOSOLICITUDINFORMACION = new BigDecimal(250544128);
	/**
	 *  variable CATEGORIAEXPEDIENTESARCHIVADOS
	 */
	@Interno
	public static final BigDecimal CATEGORIAEXPEDIENTESARCHIVADOS = new BigDecimal(30008);
	/**
	 *  variable id
	 */
	private BigDecimal id;
	/**
	 *  variable nombre
	 */
	@Size(min = 3, max = 200)
	@NotNull(message = "El nombre es obligatorio")
	private String nombre;
	/**
	 *  variable apellidos
	 */
	@Size(min = 3, max = 200)
	@NotNull(message = "Los apellidos son obligatorios")
	private String apellidos;
	/**
	 *  variable dni
	 */
	@NIFNIE
	@NotNull(message = "El dni es obligatorio")
	private String dni;
	/**
	 *  variable esRepresentante
	 */
	private Boolean esRepresentante;
	/**
	 *  variable esNumeroExpediente
	 */
	private Boolean esNumeroExpediente;
	/**
	 *  variable nombreRepresentado
	 */
	private String nombreRepresentado;
	/**
	 *  variable apellidosRepresentado
	 */
	private String apellidosRepresentado;
	/**
	 *  variable dniRepresentado
	 */
	private String dniRepresentado;
	/**
	 *  variable razonSocial
	 */
	private String razonSocial;
	/**
	 *  variable cif
	 */
	private String cif;
	/**
	 *  variable esConsultaExpediente
	 */
	private Boolean esConsultaExpediente;
	/**
	 *  variable address_id
	 */
	private String address_id;
	/**
	 *  variable address_string
	 */
	private String address_string;
	/**
	 *  variable x
	 */
	private Double x;
	/**
	 *  variable y
	 */
	private Double y;
	/**
	 *  variable promotor
	 */
	private String promotor;
	/**
	 *  variable numExpediente
	 */
	private String numExpediente;
	/**
	 *  variable year
	 */
	private String year;
	/**
	 *  variable numExpediente1
	 */
	private String numExpediente1;
	/**
	 *  variable year1
	 */
	private String year1;
	/**
	 *  variable numExpediente2
	 */
	private String numExpediente2;	
	/**
	 *  variable year2
	 */
	private String year2;
	/**
	 *  variable asunto
	 */
	private BigDecimal asunto;
	/**
	 *  variable tipoExpediente
	 */
	private String tipoExpediente;
	/**
	 *  variable otrosDatos
	 */
	private String otrosDatos;
	/**
	 *  variable idAgenda
	 */
	private Integer idAgenda;
	/**
	 *  variable localizador
	 */
	private String localizador;
	/**
	 *  variable idCita
	 */
	private BigDecimal idCita;
	/**
	 *  variable agenda
	 */
	private Agenda agenda;
	/**
	 *  variable timestamp
	 */
	private String timestamp;
	/**
	 *  variable mail
	 */
	@Email
	private String mail;
	/**
	 *  variable direccion
	 */
	private String direccion;
	/**
	 *  variable telefono
	 */
	private String telefono;
	/**
	 *  variable description
	 */
	@Size(min = 20, max = 3000)
	@NotNull
	private String description;
	/**
	 *  variable modalidadPuestaDisposicion
	 */
	@InList({"Formato Electrónico", "Soporte papel"})
//	@NotNull(message = "Debe seleccionar una modalidad de puesta a disposición")
	private String modalidadPuestaDisposicion;
	/**
	 *  variable visible
	 */
	@NotNull
	private Boolean visible;
	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombreRepresentado() {
		return nombreRepresentado;
	}

	public void setNombreRepresentado(String nombreRepresentado) {
		this.nombreRepresentado = nombreRepresentado;
	}

	public String getApellidosRepresentado() {
		return apellidosRepresentado;
	}

	public void setApellidosRepresentado(String apellidosRepresentado) {
		this.apellidosRepresentado = apellidosRepresentado;
	}

	public String getDniRepresentado() {
		return dniRepresentado;
	}

	public void setDniRepresentado(String dniRepresentado) {
		this.dniRepresentado = dniRepresentado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModalidadPuestaDisposicion() {
		return modalidadPuestaDisposicion;
	}

	public void setModalidadPuestaDisposicion(String modalidadPuestaDisposicion) {
		this.modalidadPuestaDisposicion = modalidadPuestaDisposicion;
	}

	public Boolean getEsRepresentante() {
		return esRepresentante;
	}

	public void setEsRepresentante(Boolean esRepresentante) {
		this.esRepresentante = esRepresentante;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Boolean getEsConsultaExpediente() {
		return esConsultaExpediente;
	}

	public void setEsConsultaExpediente(Boolean esConsultaExpediente) {
		this.esConsultaExpediente = esConsultaExpediente;
	}

	public String getPromotor() {
		return promotor;
	}

	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}

	public String getNumExpediente() {
		return numExpediente;
	}

	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}

	public String getYear() {
		return year;
	}

	public String getNumExpediente1() {
		return numExpediente1;
	}

	public void setNumExpediente1(String numExpediente1) {
		this.numExpediente1 = numExpediente1;
	}

	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getNumExpediente2() {
		return numExpediente2;
	}

	public void setNumExpediente2(String numExpediente2) {
		this.numExpediente2 = numExpediente2;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getAsunto() {
		return asunto;
	}

	public void setAsunto(BigDecimal asunto) {
		this.asunto = asunto;
	}

	public String getTipoExpediente() {
		return tipoExpediente;
	}

	public void setTipoExpediente(String tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	public String getOtrosDatos() {
		return otrosDatos;
	}

	public void setOtrosDatos(String otrosDatos) {
		this.otrosDatos = otrosDatos;
	}

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public String getAddress_string() {
		return address_string;
	}

	public void setAddress_string(String address_string) {
		this.address_string = address_string;
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

	public Boolean getEsNumeroExpediente() {
		return esNumeroExpediente;
	}

	public void setEsNumeroExpediente(Boolean esNumeroExpediente) {
		this.esNumeroExpediente = esNumeroExpediente;
	}
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Integer idAgenda) {
		this.idAgenda = idAgenda;
	}

	public String getLocalizador() {
		return localizador;
	}

	public void setLocalizador(String localizador) {
		this.localizador = localizador;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public BigDecimal getIdCita() {
		return idCita;
	}

	public void setIdCita(BigDecimal idCita) {
		this.idCita = idCita;
	}
	public Request convertirSolicitudARequest() {
		Request req = new Request();
		req.setType(TIPOSOLICITUDINFORMACION); // tipo solicitud de informacion publica
		req.setPriority(new BigDecimal(3)); // prioridad alta
		req.setVisible(this.getVisible() ? "S" : "N");
		req.setTitle("Solicitud de informaciÃ³n pÃºblica");
		req.setFirst_name(this.getNombre());
		req.setLast_name(this.getApellidos());
		req.setUser_address(this.getDireccion());
		req.setEmail(this.getMail());
		req.setPhone(this.getTelefono());
		req.setAddress_id(this.getAddress_id());
		req.setAddress_string(this.getAddress_string());
		req.setX(this.getX());
		req.setY(this.getY());
		StringBuilder txt = new StringBuilder();
		txt.append("DNI Solicitante:" + this.getDni() + System.getProperty("line.separator"));
		txt.append("Modalidad de respuesta:" + this.getModalidadPuestaDisposicion() + System.getProperty("line.separator"));
		if (this.getEsRepresentante() == Boolean.TRUE) {
			txt.append("Razon social:" + this.getRazonSocial() + " CIF:" + this.getCif() + System.getProperty("line.separator"));
			txt.append("DNI representado:" + this.getDniRepresentado() 
					+ " Nombre representado:" + this.getNombreRepresentado() + " " + this.getApellidosRepresentado() + System.getProperty("line.separator"));
		}
		if (this.getIdCita() != null) {
			req.setId_cita(this.getIdCita());
			req.setService_code(CATEGORIAEXPEDIENTESARCHIVADOS);
			
		} else {
			req.setService_code(this.getAsunto());
		}
		if (this.getEsConsultaExpediente() == Boolean.TRUE) {
			req.setTitle("Consulta de expedientes archivados");
			req.setAgency_responsible_code(new BigDecimal(10));
			this.setDescription(this.getDescription() + System.getProperty("line.separator")
					+ (StringUtils.isEmpty(this.getPromotor()) ? "" : "Promotor: " + this.getPromotor() + System.getProperty("line.separator"))
					+ (StringUtils.isEmpty(this.getNumExpediente())? "" : "Expediente: " + this.getNumExpediente() + (StringUtils.isEmpty(this.getYear()) ? "" : "-" + this.getYear()) + System.getProperty("line.separator"))
					+ (StringUtils.isEmpty(this.getNumExpediente1())? "" : "Expediente: " + this.getNumExpediente1() + (StringUtils.isEmpty(this.getYear1()) ? "" : "-" + this.getYear1()) + System.getProperty("line.separator"))
					+ (StringUtils.isEmpty(this.getNumExpediente2())? "" : "Expediente: " + this.getNumExpediente2() + (StringUtils.isEmpty(this.getYear2()) ? "" : "-" + this.getYear2()) + System.getProperty("line.separator"))
					+ (StringUtils.isEmpty(this.getTipoExpediente()) ? "" : "Tipo de expediente: " + this.getTipoExpediente() + System.getProperty("line.separator"))
					+ (StringUtils.isEmpty(this.getOtrosDatos())? "" : "Otros datos: " + this.getOtrosDatos() + System.getProperty("line.separator"))); 
			
		}
		req.setNotes(txt.toString());
		req.setDescription(this.getDescription());
		return req;
	}
	public Cita convertirSolicitudACita() {
		Cita cita = new Cita();
		
		cita.setAsunto(this.getDescription());
		cita.setApellidos(this.getApellidos());
		cita.setNombre(this.getNombre());
		cita.setNif(this.getDni());
		cita.setTelefono(this.getTelefono());
		cita.setEmail(this.getMail());
		cita.setExpediente(this.getNumExpediente() + "-" + this.getYear());
		cita.setMotivo(this.getDescription());
		cita.setObservacionesCita("Emplz::" + this.getAddress_string() + System.getProperty("line.separator")
				+ (StringUtils.isEmpty(this.getNumExpediente1())? "" : "##Expte2::" + this.getNumExpediente1() + (StringUtils.isEmpty(this.getYear1()) ? "" : "-" + this.getYear1()) + System.getProperty("line.separator"))
				+ (StringUtils.isEmpty(this.getNumExpediente2())? "" : "##Expte3::" + this.getNumExpediente2() + (StringUtils.isEmpty(this.getYear2()) ? "" : "-" + this.getYear2()) + System.getProperty("line.separator"))
				);
		cita.setLopd(this.getVisible());		
		return cita;
	}
}