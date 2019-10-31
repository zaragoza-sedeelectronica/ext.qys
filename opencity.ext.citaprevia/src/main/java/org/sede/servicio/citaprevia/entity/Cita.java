package org.sede.servicio.citaprevia.entity;

import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.HtmlContent;
import org.sede.core.anotaciones.Interno;
import org.sede.core.anotaciones.ValidHTML;
import org.sede.core.dao.EntidadBase;

@XmlRootElement(name = "cita")
@RestriccionMailTelefono
public class Cita extends EntidadBase {
	@Interno
	public static final int ESTADO_CITADO = 1;
	@Interno
	public static final int ESTADO_EN_ESPERA = 2;
	@Interno
	public static final int ESTADO_ATENDIENDO = 3;
	@Interno
	public static final int ESTADO_FINALIZADO = 5;
	
	@Digits(integer = 22, fraction = 0)
	private Integer id;
	
	private Integer estado;
	
	private Integer id_agenda;
	
	private Integer id_estructura;
	
	// Utilizado para obtener del fomulario
	private String timestamp;
	
	private Date fecha;
	@NotNull
	private String hora;
	@NotNull @Size(max = 30)
	private String nombre;
	@NotNull @Size(max = 70)
	private String apellidos;
	@NotNull @Size(max = 15)
	private String nif;
	@Size(max = 16)
	@NotNull
	private String telefono;
	@Size(max = 255)
	private String email;
	@Size(max = 12)
	private String expediente;
	@NotNull
	private String motivo;
	@Size(max = 1000)
	@ValidHTML @HtmlContent
	private String observacionesCita;
	
	private Boolean lopd;
	@Size(max = 16)
	private String movil;
	@Size(max = 16)
	private String telefono2;
	
	private Integer idUsuario;
	
	private Integer idUsuarioAtiende;
	
	private Date horaLlegada;
	
	private Date horaAtendido;
	
	private Date horaSalida;
	
	private String tipoOtros;
	
	private String tipo;
	
	private String emplazamiento;
	
	private String nexpediente;
	
	private String promotor;
	
	private String fechaprox;
	
	private String otrainfo;
	
	private String servicio;
	
	private String tramite;
	
	private String asunto;
	
	private String lugar;
	
	private String centro;
	
	private String calle;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getExpediente() {
		return expediente;
	}



	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}



	public String getMotivo() {
		return motivo;
	}



	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}



	public String getObservacionesCita() {
		return observacionesCita;
	}



	public void setObservacionesCita(String observacionesCita) {
		this.observacionesCita = observacionesCita;
	}

	public Boolean isLopd() {
		return lopd;
	}
	
	public Boolean getLopd() {
		return lopd;
	}
	

	public void setLopd(Boolean lopd) {
		this.lopd = lopd;
	}

	public String getMovil() {
		return movil;
	}



	public void setMovil(String movil) {
		this.movil = movil;
	}



	public String getTelefono2() {
		return telefono2;
	}



	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer v) {
		id = v;
	}
	
	private String localizador;

	public String getLocalizador() {
		return localizador;
	}

	public void setLocalizador(String localizador) {
		this.localizador = localizador;
	}
	
	public Integer getEstado() {
		return estado;
	}



	public void setEstado(Integer estado) {
		this.estado = estado;
	}



	public Integer getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}



	public Integer getIdUsuarioAtiende() {
		return idUsuarioAtiende;
	}



	public void setIdUsuarioAtiende(Integer idUsuarioAtiende) {
		this.idUsuarioAtiende = idUsuarioAtiende;
	}



	public Date getHoraLlegada() {
		return horaLlegada;
	}



	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}



	public Date getHoraAtendido() {
		return horaAtendido;
	}



	public void setHoraAtendido(Date horaAtendido) {
		this.horaAtendido = horaAtendido;
	}



	public Date getHoraSalida() {
		return horaSalida;
	}



	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}



	public String getTipoOtros() {
		return tipoOtros;
	}



	public void setTipoOtros(String tipoOtros) {
		this.tipoOtros = tipoOtros;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getEmplazamiento() {
		return emplazamiento;
	}



	public void setEmplazamiento(String emplazamiento) {
		this.emplazamiento = emplazamiento;
	}



	public String getNexpediente() {
		return nexpediente;
	}



	public void setNexpediente(String nexpediente) {
		this.nexpediente = nexpediente;
	}



	public String getPromotor() {
		return promotor;
	}



	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}



	public String getFechaprox() {
		return fechaprox;
	}



	public void setFechaprox(String fechaprox) {
		this.fechaprox = fechaprox;
	}



	public String getOtrainfo() {
		return otrainfo;
	}



	public void setOtrainfo(String otrainfo) {
		this.otrainfo = otrainfo;
	}



	public static int getEstadoCitado() {
		return ESTADO_CITADO;
	}



	public static int getEstadoEnEspera() {
		return ESTADO_EN_ESPERA;
	}



	public static int getEstadoAtendiendo() {
		return ESTADO_ATENDIENDO;
	}



	public static int getEstadoFinalizado() {
		return ESTADO_FINALIZADO;
	}



	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	}



	public Integer getId_agenda() {
		return id_agenda;
	}
	
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
	}

	public Cita(int id_agenda, Date fecha, String hora, String nombre,String apellidos,
			String nif, String telefono, String email, String expediente,
			String motivo, String observacionesCita, Boolean lopd, String movil,
			String telefono2) {
		
		this.id_agenda = id_agenda;
		this.fecha = fecha;
		this.hora = hora;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.telefono = telefono;
		this.email = email;
		this.expediente = expediente;
		this.motivo = motivo;
		this.observacionesCita = observacionesCita;
		this.lopd=lopd;
		this.movil = movil;
		this.telefono2 = telefono2;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Cita() {
		super();
	}
	
	public String toString() {
		return "Cita [id_agenda=" + id_agenda + ", fecha=" + fecha + ", hora=" + hora
				+ ", nombre=" + nombre + ", nif=" + nif + ", telefono=" + telefono
				+ ", email=" + email + ", expediente=" + expediente
				+ ", motivo=" + motivo + ", observacionesCita=" + observacionesCita
				+ ", lopd=" + lopd
				+ ", localizador=" + localizador
				+ ", timestamp=" + timestamp
				+ ", movil="
				+ movil + ", telefono2=" + telefono2 + "]";
	}


}

