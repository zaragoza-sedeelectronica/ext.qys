package org.sede.servicio.qys;

import org.sede.core.PropertyFileInterface;
import org.sede.core.anotaciones.Esquema;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQys implements PropertyFileInterface {
	public static final String ESQUEMA = Esquema.TICKETING;
	public static final String TM = Esquema.TMTICKETING;
	
	public String getSchema() {
		return ESQUEMA.toLowerCase();
	}
	public String getJndi() {
		return "TicketingDS";
	}

	public String getEntity() {
		return "org.sede.servicio.qys.entity";
	}

}
