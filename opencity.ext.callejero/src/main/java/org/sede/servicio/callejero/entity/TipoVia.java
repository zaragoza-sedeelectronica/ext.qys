package org.sede.servicio.callejero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.anotaciones.Context;
import org.sede.core.dao.EntidadBase;
import org.sede.servicio.callejero.ConfigCallejero;


@XmlRootElement(name = "tipo-via")
@Entity
@DynamicUpdate
@Table(name = "TIPO_VIA", schema = ConfigCallejero.ESQUEMA)
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/kos/urbanismo-infraestructuras/callejero/tipovia")
@Rdf(contexto = Context.SKOS, propiedad = "Concept", prefijo = "tipo_via")
public class TipoVia extends EntidadBase {

	@Id
	@Column(name = "CODIGO", unique = true, nullable = false)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private String code;
	
	@Column(name = "NOMBRE_COMPLETO", nullable = false)
	@Size(max = 255)
	private String name;
	
	@Column(name = "NOMBRE_CORTO", nullable = false)
	@Size(max = 255)
	private String short_name;
	
	@Column(name = "ABREVIATURA", nullable = false)
	@Size(max = 20)
	private String abbr;
	
	@Rdf(contexto = Context.SKOS, propiedad = "inScheme")
	@Transient
	private String scheme;
	
	@Rdf(contexto = Context.SKOS, propiedad = "exactMatch")
	@Transient
	private String match;
	
	private enum Code {AN,AV,AVP,BR,CJ,CL,CLP,CLTP,CN,CNTP,CR,CT,DS,EB,GL,JR,LG,PA,PG,PJ,PL,PQ,PS,PT,RC,RD,RT,TR,UR,VI;}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}	
	
	public String getMatch() {		
		Code value = Code.valueOf(code);
		switch (value) {
			case AN: 
					return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/ANDAD";
			case AV: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/AVDA";
			case AVP: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/AVDA";
			case BR: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/BARRO";
			case CJ: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CLLON";
			case CL: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CALLE";
			case CLP: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CALLE";
			case CLTP: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CALLE";
			case CN: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CMNO";
			case CNTP: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CMNO";
			case CR: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CRA";
			case CT: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CTRA";
			case GL: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/GTA";
			case JR: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/JDIN";
			case LG: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/LAGO";
			case PG: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/POLIG";
			case PJ: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/PSAJE";
			case PL: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/PLAZA";
			case PQ: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/PQUE";
			case PS: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/PASEO";
			case PT: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/PNTE";
			case RC: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/RCON";
			case RD: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/RONDA";
			case RT: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/RTDA";
			case TR: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/TRVA";
			case UR: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/URB";
			case VI: 
				return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/VIA";
			default: 
					return "http://vocab.linkeddata.es/datosabiertos/kos/urbanismo-infraestructuras/tipo-via/CALLE";
		}
	}

	public void setMatch(String match) {		
		this.match = getAbbr();
	}

	public String getScheme() {
		return "http://www.zaragoza.es/api/kos/urbanismo-infraestructuras/callejero/tipovia";
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	@Override
	public String toString() {
		return "TipoVia [code=" + code + ", name=" + name + ", short_name="
				+ short_name + ", abbr=" + abbr + "]";
	}
	
	
}
