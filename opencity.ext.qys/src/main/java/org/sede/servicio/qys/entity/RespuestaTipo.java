package org.sede.servicio.qys.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.ResultsOnly;
/**
 * Clase RespuestaTipo
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="respuesta-tipo")
@ResultsOnly(xmlroot="respuestas-tipo")

public class RespuestaTipo {
	/**
	 *  variable id
	 */
    private Integer id;
    /**
	 *  variable tezt
	 */
    private String text;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "RespuestaTipo [id=" + id + ", text=" + text + "]";
    }

}

