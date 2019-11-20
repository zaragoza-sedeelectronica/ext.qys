package org.sede.servicio.citaprevia;

import org.sede.core.PropertyFileInterface;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCitaprevia implements PropertyFileInterface {
	public String getSchema() {
		return "intra";
	}
	public String getJndi() {
		return "WebIntraDS";
	}

	public String getEntity() {
		return "org.sede.servicio.citaprevia.entity";
	}

}
