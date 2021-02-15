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
import org.sede.servicio.qys.ConfigQys;

@XmlRootElement(name = "categoria-sip")
@Entity(name = "CategoriaSIP")
@DynamicUpdate
@Table(name = "CATEGORIA_SIP", schema = ConfigQys.ESQUEMA)
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
