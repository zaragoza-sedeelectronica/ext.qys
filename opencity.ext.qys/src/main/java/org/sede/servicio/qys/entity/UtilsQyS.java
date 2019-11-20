package org.sede.servicio.qys.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.sede.core.rest.Peticion;
import org.sede.servicio.qys.entity.db.Hbrequestactions;
import org.sede.servicio.qys.entity.db.Hbrequests;

/**
 * Clase UtilsQyS
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public class UtilsQyS {
	private UtilsQyS() {
		super();
	}
	
	/**
	 *  usuarios especiales
	 */
	//FIXME extraer a fichero de propiedades
	public static final BigDecimal ID_MAIN_ADMIN = new BigDecimal(2);
	//FIXME extraer a fichero de propiedades
	public static final BigDecimal ID_010 = new BigDecimal(235798528);
	// FIXME extraer a fichero de propiedades
	public static final int RQT_ID_FROM = 0;//375997;
	/**
	 *  variable PROPGCZUSR
	 */
	public static final String PROPGCZUSR = "usr_ticketing";
	/**
	 *  variable PROPGCZEXTERNO
	 */
	public static final String PROPGCZEXTERNO = "externo_ticketing";
	/**
	 *  variable PROPGCZROOTCATEGORY
	 */
	public static final String PROPGCZROOTCATEGORY = "rootcat_ticketing";
	/**
	 *  variable PROPGCZROOTCATEGORY_1
	 */
	public static final String PROPGCZROOTCATEGORY_1 = "rootcat_ticketing_1";
	/**
	 *  variable FILTROSOLOROOTCAT
	 */
	public static final String FILTROSOLOROOTCAT = "filtrosolorootcat";
	/**
	 *  variable PROPGCZDEFAULTORIGIN
	 */
	public static final String PROPGCZDEFAULTORIGIN = "origin_ticketing";
	
	public static final String PROPBARRIO = "junta_ticketing";
	/**
	 *  variable ID_EXTERNO_FCC
	 */
	public static final BigDecimal ID_EXTERNO_FCC = new BigDecimal(3);
	/**
	 *  variable ID_EXTERNOLIMPIEZA_FCC
	 */
	public static final BigDecimal ID_EXTERNOLIMPIEZA_FCC = new BigDecimal(141);
	/**
	 *  variable OPCREAR
	 */
	public static final String OPCREAR = "CREAR";
	/**
	 *  variable OPGUARDAR
	 */
	public static final String OPGUARDAR = "GUARDAR";
	/**
	 *  variable TEXTONOCONTESTAR
	 */
	public static final String TEXTONOCONTESTAR = System.getProperty("line.separator")
			+ "Así mismo indicarle que esta dirección de correo no está habilitada para la recepción de mensajes. Si desea ponerse  en contacto con nosotros o enviar una nueva Queja, Sugerencia o Solicitud de Información Pública, utilice el formulario de Quejas/Sugerencias o de Solicitud de Información Pública, respectivamente.";
	
	
	/**
	 * Metodo ajustarFechaInicio
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Date ajustarFechaInicio(Date startDate, Date endDate) {
		
		if (startDate == null) {
			
			if (endDate == null) {
				startDate = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, -90);
				startDate = cal.getTime();
			} else {
				Calendar cal = Calendar.getInstance();
				cal.setTime(endDate);
				cal.add(Calendar.DATE, -90);
				startDate = cal.getTime();
			}
		}
		
		return startDate;
	}

	/**
	 * Metodo ajustarFechaFin
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Date ajustarFechaFin(Date startDate, Date endDate) {
		if (endDate == null) {
			if (startDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, 90);
				endDate = cal.getTime();
			} else {
				endDate = new Date();
			}
		} else if (calcularDias(startDate, endDate) > 90 || calcularDias(startDate, endDate) < 0) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DATE, 90);
			endDate = cal.getTime();
		}
		return endDate;
	}
	
	/**
	 * Metodo calcularDias
	 * 
	 * @param dateEarly
	 * @param dateLater
	 * @return
	 */
	public static long calcularDias(Date dateEarly, Date dateLater) {  
		   return (dateLater.getTime() - dateEarly.getTime()) / (24 * 60 * 60 * 1000);  
	}

	/**
	 * Metodo quitarExtension
	 * 
	 * @param mediaName
	 * @return
	 */
	public static String quitarExtension(String mediaName) {
		if (mediaName != null) {
			if (mediaName.indexOf('.') >= 0) {
				return mediaName.substring(0, mediaName.lastIndexOf('.'));
			} else {
				return mediaName;
			}
		} else {
			return null;
		}
	}

	
	/**
	 * Metodo soloRootCategories
	 * @param peticion
	 * @return
	 */
	public static boolean soloRootCategories(Peticion peticion) {
		if (peticion.getCredenciales() != null && peticion.getCredenciales().getUsuario().getPropiedades() != null) {
			return peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.FILTROSOLOROOTCAT) != null;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo obtenerRootCategories
	 * 
	 * @param peticion
	 * @return
	 */
	public static String obtenerRootCategories(Peticion peticion) {
		if (peticion.getCredenciales() != null && peticion.getCredenciales().getUsuario().getPropiedades() != null) {
			return peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZROOTCATEGORY)
					+ (peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZROOTCATEGORY_1) == null ? "" : "," + peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZROOTCATEGORY_1));
		} else {
			return "";
		}
	}
	
	/**
	 * Metodo obtenerUsuarioTicketing
	 * 
	 * @param peticion
	 * @return
	 */
	public static String obtenerUsuarioTicketing(Peticion peticion) {
		if (peticion.getCredenciales() != null && peticion.getCredenciales().getUsuario().getPropiedades() != null) {
			return peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZUSR);
		} else {
			return null;
		}
				
	}
	
	/**
	 * Metodo obtenerExternoTicketing
	 * 
	 * @param peticion
	 * @return
	 */
	public static String obtenerExternoTicketing(Peticion peticion) {
		if (peticion.getCredenciales() != null && peticion.getCredenciales().getUsuario().getPropiedades() != null) {
			return peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZEXTERNO);
		} else {
			return null;
		}
				
	} 
	/**
	 * Metodo obtenerRootCategory
	 * 
	 * @param peticion
	 * @return
	 */
	public static String obtenerRootCategory(Peticion peticion) {
		if (peticion.getCredenciales() != null && peticion.getCredenciales().getUsuario().getPropiedades() != null) {
			return peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZROOTCATEGORY);
		} else {
			return null;
		}	
	} 
	/**
	 * Metodo obtenerDefaultOrigin
	 * 
	 * @param peticion
	 * @return
	 */
	public static BigDecimal obtenerDefaultOrigin(Peticion peticion) {
		if (peticion.getCredenciales() != null 
				&& peticion.getCredenciales().getUsuario().getPropiedades() != null 
				&& peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZDEFAULTORIGIN) != null) {
			return new BigDecimal(peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPGCZDEFAULTORIGIN));
		} else {
			return null;
		}	
	}

	public static String obtenerBarrioTicketing(Peticion peticion) {
		if (peticion.getCredenciales() != null && peticion.getCredenciales().getUsuario().getPropiedades() != null) {
			return peticion.getCredenciales().getUsuario().getPropiedades().get(UtilsQyS.PROPBARRIO);
		} else {
			return null;
		}
	}

	public static boolean puedeAccederAQueja(Hbrequests registro, String usuarioTicketing, String externoTicketing) {
		BigDecimal userTicketing = new BigDecimal(usuarioTicketing);
		
		if (externoTicketing == null) {
			if (userTicketing.equals(registro.getUsrHbidRequester())
					|| userTicketing.equals(registro.getUsrHbidIntroducer())
					|| userTicketing.equals(registro.getUsrHbidManager())
					|| userTicketing.equals(ID_MAIN_ADMIN)
					|| (registro.getUsrHbidManager() == null
							&& (userTicketing.equals(ID_MAIN_ADMIN)
									|| userTicketing.equals(ID_010)))) {
				return true;
			} else {
				for (Hbrequestactions accion : registro.getActions()) {
					if (accion.getUsrHbidAgent().equals(userTicketing)) {
						return true;
					}
				}
				return false;
			}
		} else if (registro.getHbEntidadesexternas().getId().equals(new BigDecimal(externoTicketing))) {
			return true;
		} else {
			return false;
		}
	}

	public static BigDecimal[] asBigDecimalArray(String valor) {
		String[] datos = valor.split(",");
		BigDecimal[] bd = new BigDecimal[datos.length];
		for (int i = 0; i < datos.length; i++) {
			bd[i] = new BigDecimal(datos[i]);
		}
		return bd;
	}
	
}
