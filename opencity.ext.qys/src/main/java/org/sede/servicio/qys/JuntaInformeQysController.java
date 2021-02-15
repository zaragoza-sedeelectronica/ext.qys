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

package org.sede.servicio.qys;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.sede.core.anotaciones.Cache;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.anotaciones.Gcz;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.ResponseClass;
import org.sede.core.rest.Mensaje;
import org.sede.core.rest.MimeTypes;
import org.sede.core.utils.ConvertDate;
import org.sede.core.utils.Funciones;
import org.sede.core.utils.Propiedades;
import org.sede.core.utils.WordUtils;
import org.sede.servicio.qys.dao.QySDao;
import org.sede.servicio.qys.entity.Request;
import org.sede.servicio.qys.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;

@Gcz(servicio="TICKETING",seccion="REQUESTS")
@Controller
@Transactional(Esquema.TMTICKETING)
@RequestMapping(value = "/" + JuntaInformeQysController.MAPPING, method = RequestMethod.GET)
public class JuntaInformeQysController {

	private static final String SERVICIO = "informe-quejas-sugerencias-junta";
    public static final String MAPPING = "servicio/" + SERVICIO;
    
    
    public static final Map<String, String> juntas = new HashMap<String, String>();
    static {
	    juntas.put("Junta Municipal La Almozara", "junta-almozara@zaragoza.es");
	    juntas.put("Junta Vecinal Movera", "junta-movera@zaragoza.es");
	    juntas.put("Junta Vecinal Monzalbarba", "junta-monzalbarba@zaragoza.es");
	    juntas.put("Junta Municipal Delicias", "junta-delicias@zaragoza.es");
	    juntas.put("Junta Municipal El Rabal", "junta-rabal@zaragoza.es");
	    juntas.put("Junta Vecinal Montañana", "junta-montanana@zaragoza.es");
	    juntas.put("Junta Vecinal San Juan Mozarrifar", "junta-sanjuan@zaragoza.es");
	    juntas.put("Junta Vecinal Garrapinillos", "junta-garrapinillos@zaragoza.es");
	    juntas.put("Junta Vecinal Casetas", "junta-casetas@zaragoza.es");
	    juntas.put("Junta Vecinal Alfocea", "junta-alfocea@zaragoza.es");
	    juntas.put("Junta Vecinal San Gregorio", "junta-sangregorio@zaragoza.es");
	    juntas.put("Junta Municipal Miralbueno", "junta-miralbueno@zaragoza.es");
	    juntas.put("Junta Municipal Actur-Rey Fernando", "junta-actur@zaragoza.es");
	    juntas.put("Junta Municipal Casablanca", "junta-casablanca@zaragoza.es");
	    juntas.put("Junta Municipal Casco Histórico", "junta-historico@zaragoza.es");
	    juntas.put("Junta Municipal Oliver-Valdefierro", "junta-oliver@zaragoza.es");
	    juntas.put("Junta Municipal San José", "junta-sanjose@zaragoza.es");
	    juntas.put("Junta Municipal Santa Isabel", "junta-staisabel@zaragoza.es");
	    juntas.put("Junta Municipal Torrero", "junta-torrero@zaragoza.es");
	    juntas.put("Junta Vecinal Juslibol-El Zorongo", "junta-juslibol@zaragoza.es");
	    juntas.put("Junta Vecinal La Cartuja Baja", "junta-cartuja@zaragoza.es");
	    juntas.put("Junta Municipal Sur", "junta-sur@zaragoza.es");
	    juntas.put("Junta Vecinal Peñaflor", "junta-penaflor@zaragoza.es");
	    juntas.put("Junta Vecinal Venta del Olivar", "junta-volivar@zaragoza.es");
	    juntas.put("Junta Vecinal Torrecilla de Valmadrid", "junta-torrecilla@zaragoza.es");
	    juntas.put("Junta Municipal Universidad", "junta-universidad@zaragoza.es");
	    juntas.put("Junta Municipal Las Fuentes", "junta-lasfuentes@zaragoza.es");
	    juntas.put("Junta Municipal Centro", "junta-centro@zaragoza.es");
    }
    
    
    @Autowired
	private QySDao dao;
    
    @SuppressWarnings("unchecked")
	@Permisos(Permisos.ADMIN)
    @Cache(Cache.DURACION_30MIN)
    @ResponseClass(value = Mensaje.class, entity = SearchResult.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MimeTypes.JSON, MimeTypes.XML})
    public @ResponseBody ResponseEntity<?> apiEnvioInforme(@RequestParam String startDate, @RequestParam String endDate) throws IOException {
    	StringBuilder mensaje = new StringBuilder();
    	try {
    		
    		SearchResult<Service> lista = dao.searchAndCountService("S");
    		HashMap<String, String> servicios = new HashMap<String, String>(); 
    		for (Service s : lista.getResult()) {
    			servicios.put(s.getService_code(), s.getService_name());
    		}
	    	Query query = dao.em().createNativeQuery("select J.Nombre, hb_re.rqt_requestnumber as id, hb_re.rqt_introductiondatetime as fecha,hb_re.title as asunto,hb_re.ADDRESS_STRING as localizacion,nvl2(hb_re.rqt_closedate,'Cerrada','Pendiente') as estado,hb_le.CAL_HBID as service_id,hb_le.CAL_NAME as service_name,hb_re.rqt_closedate as fecha_cierre "
	    			+ "from "
	    			+ "hbrequests hb_re, intra.portalero p, intra.junta j, hbcategorylevels hb_le "
	    			+ "where "
	    			+ "hb_re.address_id=p.id_por and P.Id_Jun=J.Id_Junta and "
	    			+ "hb_re.cat_hbid=hb_le.cal_hbid "
	    			+ "and hb_re.rqt_introductiondatetime >= to_date(?,'dd-mm-yyyy') "
	    			+ "and hb_re.rqt_introductiondatetime <= to_date(?,'dd-mm-yyyy') "
	    			// no mostramos las solicitudes de informaciÃ³n publica"
	    			+ "and Hb_Re.Rty_Hbid <> 250544128 "
	    			// no mostramos 5	Borrada, 9	Archivada"
	    			+ "and Hb_Re.Rst_Id not in (5,9) "
	    			// mostramos las anÃ³nimas o las que tenemos consentimiento"
	    			+ "and (hb_re.email='anonymous@anonymous.es' or hb_re.rqt_public='S') "
	    			+ "order by j.nombre asc,hb_le.cal_name asc")
	    			.setParameter(1, startDate)
	    			.setParameter(2, endDate);
			
			HashMap<String, List<Request>> resultado = new HashMap<String, List<Request>>();
	
			List<Object> list = query.getResultList();
			for (Iterator<Object> iterador = list.iterator(); iterador.hasNext();) {
				Object[] row = (Object[])iterador.next();
				List<Request> d;
				String junta = ((String) row[0]);
				if (resultado.containsKey(junta)) {
					d = resultado.get(junta);
				} else {
					d = new ArrayList<Request>();
				}
				
				Request r = new Request();
				r.setService_request_id(((BigDecimal) row[1]));
				r.setRequested_datetime((Date) row[2]);
				r.setTitle((String) row[3]);
				r.setAddress_string((String) row[4]);
				r.setStatus((String) row[5]);
				String name = servicios.get(((BigDecimal) row[6]).toString());
				if (name == null) {
					name = (String) row[7];
				}
				r.setClosed_datetime((Date) row[8]);
				r.setService_name(name);
				
				d.add(r);	
				
				resultado.put(junta, d);
			}
			 
			for (Entry<String, List<Request>> junta : resultado.entrySet()){
				String clave = junta.getKey();
				List<Request> valor = junta.getValue();
				
				ByteArrayOutputStream baos = this.generarDoc(clave, valor, startDate, endDate);
				if (juntas.containsKey(clave)) {
//					FileUtils.writeByteArrayToFile(new File(Propiedades.getPathContDisk() + clave + ".doc"), baos.toByteArray());
//					Funciones.sendMailAdjunto("Informe de quejas y sugerencias " + clave + ": " + startDate + " " + endDate, juntas.get(clave) + System.getProperty("line.separator") + "Adjuntamos informe de quejas y sugerencias recibidas en el sistema de quejas y sugerencias vinculadas a la junta.", "analistaweb@zaragoza.es", "", "HTML", baos.toByteArray(), "informe.doc",MimeTypes.DOC);
					Funciones.sendMailAdjunto("Informe de quejas y sugerencias " + clave + ": " + startDate + " " + endDate, juntas.get(clave) + System.getProperty("line.separator") + "Adjuntamos informe de quejas y sugerencias recibidas en el sistema de quejas y sugerencias vinculadas a la junta.", "contenidosweb@zaragoza.es", "", "HTML", baos.toByteArray(), "informe.doc",MimeTypes.DOC);
//					Funciones.sendMailAdjunto("Informe de quejas y sugerencias: " + startDate + " " + endDate, "Adjuntamos informe de quejas y sugerencias recibidas en el sistema de quejas y sugerencias vinculadas a la junta.", juntas.get(clave), "", "HTML", baos.toByteArray(), "informe.doc",MimeTypes.DOC);
					mensaje.append(System.getProperty("line.separator") + "Enviado a " + juntas.get(clave));
				} else {
					mensaje.append(System.getProperty("line.separator") + "No se ha enviado a " + clave);
				}
				
			}
			return ResponseEntity.ok(new Mensaje(HttpStatus.OK.value(), mensaje.toString()));
    	} catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Mensaje(HttpStatus.BAD_REQUEST.value(),
							e.getMessage() + System.getProperty("line.separator") + mensaje.toString()));
    	}
    }

	private ByteArrayOutputStream generarDoc(String clave, List<Request> valor, String startDate, String endDate) throws IOException, InvalidFormatException {
		
		WordUtils doc = new WordUtils();
		try {
			
			double scale = 0.7;
			File image = new File(Propiedades.getPathContDisk() + "Cabecera-Informes.jpg");
			FileInputStream inputStream = new FileInputStream(image);
		    final BufferedImage bufferedImage = ImageIO.read(inputStream);
		    final int widthI = (int) Math.ceil(Units.toEMU(bufferedImage.getWidth() * scale));
		    final int heightI = (int) Math.ceil(Units.toEMU(bufferedImage.getHeight() * scale));
		    inputStream.close();
		    XWPFParagraph paragraph = doc.getDocument().createParagraph();
			XWPFRun run = paragraph.createRun();
		    run.setText("", 0);
		    inputStream = new FileInputStream(image);
		    run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, image.getName(), widthI, heightI);
		    inputStream.close();
			
		    doc.h1("Informe " + clave);
			doc.h4(startDate + " al " + endDate + System.getProperty("line.separator"));
			
			XWPFTable table = doc.getDocument().createTable(1, 4);
			
			
			table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(1300));
		    table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(1400));
		    table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3700));
		    table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(3600));
			
			
			CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
			width.setType(STTblWidth.DXA);
			width.setW(BigInteger.valueOf(9072));
			
			int cols = -1;
			table.getRow(0).getCell(++cols).setText("ID");
			table.getRow(0).getCell(++cols).setText("Fecha");
			table.getRow(0).getCell(++cols).setText("Asunto");
			table.getRow(0).getCell(++cols).setText("CategorÃ­a");

			for (Request r : valor) {
				cols = -1;
				
				String cierre = ConvertDate.date2String(r.getClosed_datetime(), ConvertDate.DATE_FORMAT);
				if (!StringUtils.isEmpty(cierre)) {
					cierre = System.getProperty("line.separator") + "Cierre: " + System.getProperty("line.separator") + cierre;
				}
				XWPFTableRow newrow = table.createRow();
				newrow.getCell(++cols).setText(r.getService_request_id().toString() + System.getProperty("line.separator") + r.getStatus());
				newrow.getCell(++cols).setText(ConvertDate.date2String(r.getRequested_datetime(), ConvertDate.DATE_FORMAT) + cierre);
				newrow.getCell(++cols).setText(r.getTitle() + System.getProperty("line.separator") + r.getAddress_string());
				newrow.getCell(++cols).setText(r.getService_name());

			}
			
			XWPFParagraph p1 = doc.getDocument().createParagraph();
			XWPFRun r1 = p1.createRun();
			r1.setText(System.getProperty("line.separator") + "Contacto: SecciÃ³n de Quejas y Sugerencias. contenidosweb@zaragoza.es.");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.getDocument().write(baos);
			return baos;
		} finally {
			doc.getDocument().close();
		}
	}
	
}
