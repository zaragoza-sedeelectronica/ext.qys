package org.sede.servicio.qys.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.SafeHtml;
import org.sede.core.anotaciones.Context;
import org.sede.core.anotaciones.Rdf;

@XmlRootElement(name = "categoria-sip")
@Entity(name = "CategoriaSIP")
@DynamicUpdate
@Table(name = "CATEGORIA_SIP", schema = "TICKETING")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriaSIP {
	@Id
    @Column(name = "ID", unique = true, nullable = false)
    @Digits(integer = 6, fraction = 0)
    @Rdf(contexto = Context.DCT, propiedad = "identifier")
    private Integer id;

    @Column(name = "TITLE")
    @Size(max = 100)
    @SafeHtml
    @Rdf(contexto = Context.SKOS, propiedad = "prefLabel")
    private String title;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", title=" + title + "]";
	}
    
}
