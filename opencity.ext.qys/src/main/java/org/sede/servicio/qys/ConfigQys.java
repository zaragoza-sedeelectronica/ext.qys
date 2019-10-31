package org.sede.servicio.qys;

import org.sede.core.PropertyFileInterface;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQys implements PropertyFileInterface {
	public String getSchema() {
		return "ticketing";
	}
	public String getJndi() {
		return "TicketingDS";
	}

	public String getEntity() {
		return "org.sede.servicio.qys.entity";
	}

}
