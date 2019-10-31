package org.sede.servicio.citaprevia.entity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.Interno;
import org.sede.core.dao.EntidadBase;
import org.sede.core.utils.ConvertDate;

@XmlRootElement(name="dias")
@Entity
public class Dias extends EntidadBase {
	
	private Date fecha;
	@Interno
	private String fechaint;
	List<Turno> horas;
	public String getFechaint() {
		return fechaint;
	}
	public void setFechaint(String fecha) {
		try {
			this.fecha = ConvertDate.string2Date(fecha, ConvertDate.DATE_FORMAT);
		} catch (ParseException e) {
			;
		}
	}
	public List<Turno> getHoras() {
		return horas;
	}
	public void setHoras(List<Turno> horas) {
		this.horas = horas;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Dias [fechaint=" + fechaint + ", horas=" + horas + "]";
	}
	
}
