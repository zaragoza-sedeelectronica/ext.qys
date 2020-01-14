package org.zaragoza.opencity.ext.qys;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.sede.core.TestPeticion;
import org.sede.core.db.DataSourceRule;
import org.sede.core.utils.Funciones;
import org.sede.servicio.qys.QysController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import antlr.Utils;


/**
 * Test de pruebas unitarias para Quejas y Sugerencias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
/**
 * @author magarcia
 *
 */
@RunWith(DataSourceRule.class)
@ContextConfiguration(classes = org.sede.core.WebConfig.class)
@WebAppConfiguration
public class QysTest {
	
	/**
	 *  Web application context
	 */
	@Autowired
	private WebApplicationContext context;
	
	/**
	 *  controlador de Quejas y Sugerencias
	 */
	@InjectMocks
	private QysController controller;
	
	/**
	 *  ruta de Quejas y Sugerencias
	 */
	private static String MAPPING = QysController.MAPPING;
	/**
	 *  peticion de pruebas de Quejas y Sugerencias
	 */
	private TestPeticion peticion = new TestPeticion();
	/**
	 *  mock de modelo vista controlador
	 */
	private MockMvc mockMvc;

	/**
	 * Metodo setup ejecutado antes de las pruebas
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		peticion.setMock(mockMvc);
	}
	
	/**
	 * Método home
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHome() throws Exception {
		
		System.out.println(peticion.get("/" + MAPPING, MediaType.APPLICATION_XHTML_XML)
				.andExpect(status().isOk())
				.andExpect(view().name("servicio/quejas-sugerencias/index"))
				.andReturn().getResponse().getContentAsString());
	}
	
	/**
	 * Metodo detalle
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDetailHtml() throws Exception {
		
		System.out.println(peticion.get("/" + MAPPING + "/119752", MediaType.APPLICATION_XHTML_XML)
				.andExpect(status().isOk())
				.andExpect(view().name("servicio/quejas-sugerencias/detalle"))
				.andReturn().getResponse().getContentAsString());
	}
	
	/**
	 * Metodo detalle
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDetail() throws Exception {

		peticion.get("/" + MAPPING + "/20", MediaType.APPLICATION_XHTML_XML)
				.andExpect(status().isOk())
				.andExpect(view().name(MAPPING + "/detalle"))
				;		
	}
	/**
	 * Metodo listar 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testList() throws Exception {

		MvcResult result = peticion.get("/" + MAPPING)
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
		
	}
	
	/**
	 * Metodo  detalle
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDetailJson() throws Exception {
		peticion.setCredenciales();
		MvcResult result = peticion.get("/" + MAPPING + "/581836")
				.andExpect(status().isOk())
				.andReturn()
				;
		System.out.println(Funciones.formatear(result.getResponse().getContentAsString()));
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}

	/**
	 * Metodo listado servicios 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testServiceJson() throws Exception {
		MvcResult result = peticion.get("/" + MAPPING + "/service-levels")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}
	/**
	 * Metodo estadisticas
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStatistics() throws Exception {
		MvcResult result = peticion.get("/" + MAPPING + "/statistics?year=2016")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}
	/**
	 * Metodo respuestas-tipo
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRespuestasTipo() throws Exception {
		peticion.setCredenciales();
		MvcResult result = peticion.get("/" + MAPPING + "/respuestas-tipo")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}
	/**
	 * Metodo entidades externas
	 *  
	 * @throws Exception
	 */
	@Test
	public void testEntidadesExternas() throws Exception {
		peticion.setCredenciales();
		MvcResult result = peticion.get("/" + MAPPING + "/entidad-externa")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}
	/**
	 * Metodo lista categorias
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListCategory() throws Exception {
		peticion.setCredenciales();
		MvcResult result = peticion.get("/" + MAPPING + "/category")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}	

	/**
	 * Metodo crear queja
	 * 
	 * @throws Exception
	 */
//TODO Revisar para Jenkins	@Test
//	public void testCrear() throws Exception {
//		peticion.setCredenciales();
//		String params = "title=titulo&service_code=4849671&description=descripcion";
//		MvcResult result = peticion.post("/" + MAPPING, params)
//				.andExpect(status().isOk())
//				.andReturn()
//				;
//		Assert.assertTrue(result.getResponse().getStatus()==200);
//	}
	
	/**
	 * Metodo modificar queja
	 * 
	 * @throws Exception
	 * 
	 */
//TODO Revisar para Jenkins	@Test
//	public void testModificar() throws Exception {
//		peticion.setCredenciales();
//		String params = "title=title&service_code=4849671&description=description";
//		MvcResult result = peticion.put("/" + MAPPING+"/2147438622", params)
//				.andExpect(status().isOk())
//				.andReturn()
//				;
//		Assert.assertTrue(result.getResponse().getStatus()==200);
//	}
	
	/**
	 * Metodo accion queja 
	 * 
	 * @throws Exception
	 * 
	 */
//TODO Revisar para Jenkins	@Test
//	public void testAccion() throws Exception {
//		peticion.setCredenciales();
//		MvcResult result = peticion.put("/" + MAPPING +"/2147438622/acciones/5", "")
//				.andExpect(status().isOk())
//				.andReturn()
//				;
//		Assert.assertTrue(result.getResponse().getStatus()==200);
//	}

	/**
	 * Metodo estadisticas
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEstadisticas() throws Exception {
		peticion.setCredenciales();
		MvcResult result = peticion.get("/" + MAPPING + "/estadistica")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}
	@Test
	public void testListStatus() throws Exception {
		peticion.setCredenciales();
		MvcResult result = peticion.get("/" + MAPPING + "?status=3,4")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}
	/**
	 * Metodo Service listar
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListar() throws Exception {
		MvcResult result = peticion.get("/" + MAPPING + "/service/4849672")
				.andExpect(status().isOk())
				.andReturn()
				;
		Assert.assertTrue(result.getResponse().getStatus()==200);
	}
}