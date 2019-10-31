# 1. Descripción

A través del Servicio de Quejas y Sugerencias la ciudadanía puede comunicar al Ayuntamiento todas sus inquietudes, intereses y reclamaciones, con la confianza de recibir en toda ocasión una respuesta personalizada.

El compromiso del ayuntamiento está recogido en la Carta de Servicios y dicho compromiso consiste en contestar las quejas y sugerencias recibidas a través de la web municipal en un plazo no superior a 48 horas.

El sistema de gestión del servicio de quejas y sugerencias posibilita:

1. La recepción de las quejas y/o sugerencias que la ciudadanía envía a la sede electrónica a través de la web de la Sede Electrónica del Ayuntamiento de Zaragoza y del servicio telefónico 010.
2. La gestión descentralizada, derivando a los servicios municipales/subcontratas responsables las quejas/sugerencias para la contestación o resolución de las mismas permitiendo el seguimiento y trazabilidad de las acciones llevadas a cabo sobre ellas.
3. Publicación de las quejas y sugerencias como un conjunto de datos en formatos abiertos descritos semánticamente.

Para el desarrollo de este módulo se utiliza [Open311](http://www.open311.org/). Open311 es un modelo colaborativo y estándar abierto para el seguimiento de quejas y sugerencias en el entorno de la servicios sociales a nivel de ciudades. El Ayuntamiento de Zaragoza es uno de los más de 30 ayuntamientos que soportan la especificación GeoReport v2 de Open311, se puede consultar la lista completa en el siguiente [enlace](http://wiki.open311.org/GeoReport_v2/Servers/). Esta especificación permite a los ciudadanos la creación de quejas y sugerencias georeferenciadas de manera transparente para ellos. En el [siguiente enlace](https://www.zaragoza.es/sede/portal/datos-abiertos/open311) se pueden consultar las operaciones y métodos disponibles de quejas y sugerencias desarrollados acordes a Open311.

Dado que las quejas pueden ser georeferenciadas, se ha utilizado la [Directiva INSPIRE (Infraestructure for Spatial Information Europe)](http://www.idee.es/europeo-inspire). La Directiva INSPIRE, según se puede leer en el [reglamento de datos de INSPIRE en España](http://www.idee.es/web/guest/datos), la directiva se aplicará si el conjunto de datos espaciales:

- se refieren a una zona sobre la que un Estado miembro **tenga y/o ejerza jurisdicción**;
- estén en **formato electrónico**;
- obren **en poder de** alguna de las partes que figuran a continuación, o de una entidad que actúe en su nombre:
	* una autoridad pública, después de ser producidos o recibidos por una autoridad pública, o sean gestionados o actualizados por dicha autoridad y estén comprendidos en el ámbito de sus actividades públicas,
	* un tercero al que se hubiera facilitado el acceso a la red con arreglo a lo dispuesto en el artículo 12 de la Directiva;
- traten de uno o más de los temas recogidos en los anexos I, II o III de la Directiva

La guía de implementación de la directiva que ha seguido el Ayuntamiento se puede consultar en el [siguiente enlace](http://www.idee.es/web/guest/guias-para-implementar).

Como resumen, el módulo de quejas y sugerencias utiliza estándares abiertos, Open311 y la Directiva INSPIRE, permitiendo de esta manera una mejor la interoperabilidad e interacción con otros módulos del propio Ayuntamiento y sistemas externos.

# 2. Componentes básicos

A continuación se detallan los componentes básicos para una herramienta que tiene que permitir la creación de quejas y sugerencias que permitan una mejor respuesta/interacción/gestión de la administración pública con los ciudadanos.
 
Es importante que existan mapas interactivos que permitan combinar un gran volumen de datos y visualizaciones a la carta de la ciudadanía en múltiples pantallas y dispositivos.

## 2.1 Cartografía base

La ciudadanía puede acceder a la Infraestructura de datos espaciales de Zaragoza desde cualquier dispositivo. Una dificultad es comprender y aprovechar flujos de interacción y captación de datos de manera que sean útiles para la acción política y la gobernanza de las ciudades. Una de las oportunidades de una IDE (Directiva INSPIRE) es poder crear capas de información que mejoren la actuación y la intervención en lo público. Desde un punto de vista tecnológico, los servicios son la parte más importante de una IDE y proporcionan una base para la búsqueda, evaluación y explotación de la información espacial para usuarios y proveedores de todos los niveles siendo la base que permite construir aplicaciones para explotar los datos geográficos. Los servicios fundamentales, siempre conforme a la especificación de [OGC](http://www.opengeospatial.org/) son: 

* Servicios de visualización: 
	* Servicios Web de Mapas [WMS](http://www.opengeospatial.org/standards/wms). Estos servicios proporcionan una representación (imagen) de un mapa para un área requerida. Las capas se muestran y pueden solicitarse individualmente o mediante un mapa compuesto. 
	* Servicios Web de Teselas de Mapa [WMTS](http://www.opengeospatial.org/standards/wmts), de modo que son estándar e interoperables que proporcionan un acceso más rápido y eficiente y permiten ofrecer una mejor experiencia de uso.
* Servicios de Descarga
	* Servicios Web de Features WFS. Estos servicios facilitan datos que poseen un conjunto de características espaciales. Habitualmente los datos proporcionados están codificados mediante GML, pero cualquier otro tipo de dato y cualquier formato es válido. GML codifica vectores de datos (por ejemplo un servicio de features usual devuelve las coordenadas para datos geométricos, sin embargo, un servidor web de mapas devuelve una imagen). Además, los datos geométricos pueden ir acompañados de información no espacial.
 
En cuanto a los formatos de almacenamiento de datos geográficos se recomienda utilizar un sistema gestor de base de datos con módulo espacial como PostgreSQL (software libre) con POSTGIS o como Oracle Spatial para almacenar las geometrías como objetos espaciales nativos de dichos sistemas. Si no se puede utilizar ningún sistema de este tipo las geometrías se almacenarán en columnas de tipo BLOB o CLOB en formato WKB o WKT.

## 2.2 Capas de información

Son los conjunto de datos que se pueden incorporar a una queja o sugerencia de los servicios prestados por el Ayuntamiento de Zaragoza. Se pretende facilitar la incorporación de recursos a la queja o sugerencia favoreciendo que las personas puedan facilitar la mayor información posible.

Además de la información geoespacial indicada en el punto 2.1, el módulo de quejas y sugerencias permite que los ciudadanos aporten archivos adjuntos a la queja/sugerencia que genere en el portal.

## 2.3 Tipologías o niveles de participación y co-creación
 
Las posibles tipologías o niveles de participación y co-creación que la ciudadanía tiene a su disposición a través del módulo de quejas y sugerencias son:
 
* **Sugerencias**. Serían sugerencias objetivas y justificadas. Sobre las bases de información existente: la ciudadanía selecciona una de las categorías ya existentes y sobre la misma hace una sugerencia (estado en el que se encuentra una infraestructura, equipamiento o servicio. El estado de un equipamiento como un banco estropeado, baldosas o firme en mal estado, una mejora del servicio,…).
 
* **Quejas**. Sobre un servicio prestado por el Ayuntamiento de Zaragoza, la ciudadanía puede indicar problemas que esté sufriendo a nivel particular o que puedan afectar al conjunto entero de la ciudadanía. Estos problemas podrían deberse tanto a la falta de acceso a un servicio en concreto para ese usuario, como la caída de un servicio online, retrasos en algún medio de transporte… 

## 2.4 Usuarios/usuarias del servicio

Existen tres tipos de usuarios/as del servicio del módulo de mapas colaborativos. Los tipos de usuarios que se pueden definir son:

* **Usuarios/as anónimos/as**: cualquier usuario que acceda a la URL del sistema sin autenticar en la plataforma de Gobierno Abierto puede acceder al listado de  los quejas y sugerencias, puede crear quejas y sugerencias sin necesidad de registrarse y consultar el histórico de quejas y sugerencias por año.
* **Usuarios/as autenticados**: los usuarios/as dados de alta en la plataforma de Gobierno Abierto del Ayuntamiento, además de las opciones listadas para los usuarios anónimos, pueden consultar las quejas y sugerencias que haya creado en el sistema para comprobar el estado en el que se encuentran.
* **Administrador**: los usuarios administradores se encargan del tratamiento de las quejas y sugerencias. Las acciones que pueden realizar son:
	* responder directamente a las quejas/sugerencias directamente sin son competencia de la Oficina Técnica
	* remitir la queja/sugerencia al servicio competente, informando al ciudadano/a de ello
	* desestimar la queja/sugerencia, informando al ciudadano/a por qué se ha desestimado


# 3. Protocolo de Actuación

La Gestión del Servicio de Quejas/Sugerencias de la Sede Electrónica del Ayuntamiento de Zaragoza sigue un Protocolo de Actuación compuesto por los siguiente pasos:

1. Recepción de la queja/sugerencia
2. Análisis de la queja/sugerencia
3. Resolución de la queja/sugerencia
4. Respuesta a la ciudadanía
5. Archivo de la queja/sugerencia

A continuación se explicará en detalle cada uno de los pasos.

## 3.1 Recepción

La ciudadanía puede realizar quejas y sugerencias al ayuntamiento a través de:

* **Digital**, desde la primera página de la sede electrónica del ayuntamiento de Zaragoza: https://www.zaragoza.es/sede/servicio/quejas-sugerencias/

Al acceder al servicio se presenta el formulario para el envío de quejas y sugerencias. Los datos que son obligatorios de cumplimentar son el asunto y la descripción mientras que el resto (email, nombre, dirección, teléfono, localización en mapa) están destinados a facilitar la comunicación entre el Ayuntamiento y la ciudadanía de cara a solicitar más información acerca de su queja/sugerencia o para comunicarle el estado de tramitación de la misma.

Además, el servicio ofrece la posibilidad de registrarse, lo que aporta como ventajas la posibilidad de hacer un seguimiento exhaustivo del estado de tramitación y/o ejecución en el que se encuentra su queja/sugerencia.

* **Servicio Telefónico 010**: el equipo del servicio 010 graban las quejas y sugerencias realizadas por la ciudadanía en el sistema.

Con la finalidad de cumplir con la normativa vigente de protección de datos de carácter personal, Ley Orgánica 15/1999, el ciudadano/a tiene que autorizar expresamente la publicación del asunto, la descripción y la fotografía adjunta a su queja/sugerencia en el Portal de Datos Abiertos, y que se haga visible su ubicación en el mapa de quejas y sugerencias. En ningún caso se publicarán datos de carácter personal. (http://www.zaragoza.es/ciudadania/gobierno-abierto/transparencia/ver_Mapa?id=35)

## 3.2 Análisis

Una vez que la queja/sugerencia es dada de alta en el sistema de Gestión de Quejas y Sugerencias se comprueba por parte del equipo de la Oficina Técnica de Transparencia, Participación y Gobierno Abierto (Oficina Técnica), si el ciudadano/a está registrado/a en el sistema y, en función de ello, se tramita la queja de una u otra forma.

Tareas a realizar:

* Comprobación si el ciudadano/a ha localizado la queja en el mapa y si ha dado su autorización para publicar los datos (asunto y descripción) de su queja.
* Comprobación que la descripción no contiene datos de carácter personal. Si contiene datos personales, se anonimiza y se publica. Si no contiene datos personales, se publica directamente.

Atendiendo a la Carta de Servicios de la Web Municipal, estamos sujetos a un compromiso de contestar desde la Sede Electrónica, en un plazo máximo de 48 horas laborables.

Se eliminan las quejas y sugerencias:

* Repetidas del mismo día y misma persona.
* Si no tienen correo y requieren respuestas.
* Si hay motivos para su archivo, ya sea por orden del Servicio, porque ha pasado demasiado tiempo, etc.

### 3.2.1. Ciudadano/a SI registrado/a

Las acciones de este punto son:

* Si la queja o sugerencia versa sobre un tema competencia de la Web Municipal: se le contesta directamente al Ciudadano. Seleccionamos la opción de “Contestar”
* Si es un tema competencia de otro servicio: primero se comprueba si el ciudadano/a lo ha categorizado y en caso afirmativo, si la categorización es correcta, se confirma y se deriva al Servicio competente. Si no es correcta, se modifica desde la Oficina Técnica y también se deriva al Servicio competente. Para categorizar, se hace a través de un menú desplegable, donde aparecen todas las categorías ordenadas alfabéticamente, que coinciden con las materias de cada Servicio Colaborador, Patronato o Sociedad Municipal.

### 3.2.2. Ciudadano/a NO registrado/a

Las acciones de este punto son:

* Si la queja, sugerencia versa sobre un tema competencia de la Oficina Técnica: se le contesta directamente a la ciudadanía. Realizar click en el Botón “Contestar.”
* Si es un tema competencia de otro servicio, se le “Informa” al ciudadano/a, que se ha remitido su queja al Servicio competente para su resolución. Se comprueba la categorización y se deriva al Servicio competente.

## 3.3. Resolver la Queja o Sugerencia

Las acciones de este punto son:

* El servicio o empresa que recibe la queja/sugerencia de la Oficina Técnica realiza un primer análisis para comprobar si la naturaleza de la queja/sugerencia es de su competencia. Si el servicio entiende que no es competente para su resolución, deberá comunicar esta circunstancia a la Oficina Técnica.
* Si después de analizada la queja/sugerencia el servicio entiende que es de su competencia puede ocurrir dos cosas: 
	* El servicio no la acepta por qué no la considera apropiada, tendrá que enviar una respuesta a la ciudadanía explicando el caso.
	* El servicio acepta la queja o sugerencia y responderá a la ciudadanía indicando las acciones que va a realizar, incluso en algunos casos se informa de los plazos.

## 3.4 Respuesta a la ciudadanía

Como se ha descrito en el apartado anterior la respuesta a la ciudadanía la puede hacer el servicio/empresa directamente, funcionalidad “Resolver” del Sistema de Gestión.

El servicio envía la respuesta a la Oficina Técnica, en este caso la Oficina comprueba que la respuesta es correcta y la envía al ciudadano/a a través de la opción del Sistema de Gestión (“Contestar”).
Si el Servicio nos indica que no es competencia de ellos, se deriva al Servicio que sí sea competente. En caso que no sea competencia municipal, también se le informa desde la Oficina Técnica al ciudadano/a, en ese sentido.

Quejas y Sugerencias que entran por el Servicio 010:

* El/la ciudadano/a informa de su correo electrónico y la Oficina Técnica le enviará la respuesta elaborada por el servicio / empresa.
* La queja o sugerencia es anónima, se resuelve sin poder contestar al ciudadano/a

## 3.5. Archivo de la queja o sugerencia

A parte del procedimiento de tramitación general de una queja/sugerencia puede darse el caso de que se reciban quejas/sugerencias repetidas o de que quién las envía no proporcione un medio para comunicarle el estado de tramitación de la misma. En cuanto al procedimiento de conservación de las quejas/sugerencias con carácter general se guardarán para seguir siendo accesibles desde la plataforma de gestión de quejas y sugerencias.

# 4. Funcionalidades del sistema

El sistema de quejas y sugerencias ofrece una serie de funcionalidades en función del usuario.

Para la ciudadanía:

* listado de quejas y sugerencias
* creación de quejas y sugerencias
* consulta de “Mis quejas y sugerencias” para usuarios autenticados
* consulta histórico de quejas y sugerencias
* Para la administradores, además de las opciones anteriores:
* búsqueda y listado de quejas y sugerencias avanzado
* gestión de las quejas y sugerencias
* estadísticas de quejas y sugerencias
* consulta de categorías
* consulta de servicios
* geolocalización de quejas y sugerencias

## 4.1 Funcionalidades para la ciudadanía

A continuación se explicarán las funcionalidades disponibles para la ciudadanía, ya sean usuarios autenticados o no.

### 4.1.1 Listado de quejas y sugerencias

Esta funcionalidad permite consultar las quejas y sugerencias que se han dado de alta en el sistema. Se muestra un listado de quejas y sugerencias, indicando el estado de la misma y la categoría a la que pertenece. Por cada queja y sugerencia se muestra:

* la categoría, estado y fecha
* parte del texto, pudiendo mostrar el texto completo de la queja o sugerencia elemento si se desea
* si el estado es “cerrada”, se mostrará la respuesta a la queja o sugerencia

El listado se puede filtrar por quejas y sugerencias “Pendientes” o “Cerradas”.

#### Posibles mejoras

Entre las posibles mejoras estarían:

* buscador de quejas y sugerencias por fechas, nombres, categorías. Ahora mismo se puede buscar por categorías pero para ello, hay que pinchar en la categoría asociada a una queja o sugerencia para que se ordenen por dicha categoría. Estaría bien poder hacerlo desde un cuadro de búsqueda.
* reducir el número de elementos que se muestran por pantalla. Ahora mismo, el scroll de elementos es demasiado elevado. Podría limitarse a 10-20 elementos y paginar si se quieren ver más registros.

### 4.1.2 Creación de quejas y sugerencias

Esta funcionalidad permite crear quejas y sugerencias tanto a usuarios autenticados como usuarios anónimos. Cuando se genera una queja o sugerencia, se deben introducir los siguientes datos:

* datos del ciudadano: si el usuario está autenticado, se toman de su perfil. Si no, se solicitan al usuario:
	* Nombre
	* Apellidos
	* Dirección
	* Teléfono
	* Correo electrónico
* descripción de la queja o sugerencia
	* Categoría
	* Asunto
	* Descripción
	* Ficheros adjuntos, si es necesario
	* Geolocalización, si se desea añadir
* Autorización para publicar la queja o sugerencia

Los datos del ciudadano no son obligatorios para la generación de la queja o sugerencia. Los datos de la queja o sugerencia serían suficientes para dar de alta un elemento en el sistema

#### Posibles mejoras

Entre las posibles mejoras estaría:

* por utilizar un lenguaje inclusivo, sustituir “Datos del ciudadano” por “Datos del ciudadano/a”. 

### 4.1.3 Consulta de “Mis quejas y sugerencias” para usuarios autenticados

Esta funcionalidad permite consultar las quejas y sugerencias que se han dado de alta en el sistema para un usuario autenticado. Se muestra un listado de quejas y sugerencias, indicando el estado de la misma y la categoría a la que pertenece. Por cada queja y sugerencia se muestra:

* la categoría, estado y fecha
* parte del texto, pudiendo mostrar el texto completo de la queja o sugerencia elemento si se desea
* si el estado es “cerrada”, se mostrará la respuesta a la queja o sugerencia

El listado se puede filtrar por quejas y sugerencias “Pendientes” o “Cerradas”.

#### Posibles mejoras

Entre las posibles mejoras estarían:

* buscador de quejas y sugerencias por fechas, nombres, categorías. Ahora mismo se puede buscar por categorías pero para ello, hay que pinchar en la categoría asociada a una queja o sugerencia para que se ordenen por dicha categoría. Estaría bien poder hacerlo desde un cuadro de búsqueda.
* reducir el número de elementos que se muestran por pantalla. Ahora mismo, el scroll de elementos es demasiado elevado. Podría limitarse a 10-20 elementos y paginar si se quieren ver más registros.

### 4.1.4 Consulta histórico de quejas y sugerencias

Esta funcionalidad permite consultar el número de quejas y sugerencias que se han dado de alta en el sistema por cada año desde para 2014. Se muestra un listado donde, por cada categoría disponible en el sistema, se muestra la siguiente información:

* nombre de la categoría
* número de quejas o sugerencias pendientes
* número de quejas o sugerencias cerradas
* número de quejas o sugerencias totales

El listado se puede filtrar por año.

#### Posibles mejoras

Entre las posibles mejoras estarían:

* poder consultar el listado de las quejas o sugerencias para cada categoría o estado de un año en concreto.
* poder exportar el resumen en formato CSV, EXCEL, etc directamente desde la página. Ahora se puede realizar si, por ejemplo, se utilizar añade, por ejemplo, .csv a la url de estadísticas: https://www.zaragoza.es/sede/servicio/quejas-sugerencias/statistics.csv Estaría bien poder hacerlo desde la página directamente con un botón.

## 4.2 Funcionalidades para los administradores

A continuación se explicarán las funcionalidades disponibles para los administradores del módulo.

### 4.2.1 Búsqueda  y listado de quejas y sugerencias avanzado

Esta funcionalidad permite consultar las quejas y sugerencias que se han dado de alta en el sistema. Esta funcionalidad está compuesta de dos partes, un buscador de quejas y sugerencias y un listado.

El buscador de quejas y sugerencias está compuesto por los siguientes campos:

* Id de la queja o sugerencia
* Asunto
* Estado de la queja o sugerencia
* Prioridad

El listado de quejas y sugerencias muestra, por cada queja y sugerencia, los siguientes campos:

* Id
* Fecha de registro
* Asunto
* Estado
* Categoría
* Dirección
* Visible
* Validado
* Operaciones

Desde esta pantalla se realizar diferentes consultas para obtener diferentes listados de quejas y sugerencias, así como ir al detalle de una queja o sugerencia para gestionarla.

#### Posibles mejoras

Entre las posibles mejoras estarían:

* buscador de quejas y sugerencias por fechas, por origen del elemento (web o teléfono).
* posibilidad de ordenar los resultados por las cabeceras de las columnas.

### 4.2.2 Gestión de las quejas y sugerencias

Esta funcionalidad permite gestionar las quejas y sugerencias que se han dado de alta en el sistema. Esta funcionalidad está compuesta de dos partes, una lista de acciones que se pueden realizar sobre la queja o sugerencia y el detalle de la queja o sugerencia.

La posibles acciones que se pueden realizar sobre la queja o sugerencia son:

* Guardar los cambios realizados sobre la queja o sugerencia
* Estado, modificar el estado de la queja o sugerencia
* Copiar la queja o sugerencia
* Resolver, dar por resuelta la queja o sugerencia indicando las acciones que se van a realizar al respecto de la queja o sugerencia. Esto devuelve la queja o sugerencia a la Oficina Técnica
* Contestar, dar una contestación a la queja o sugerencia cuando la Oficina * * Técnica ha comprobado que la respuesta es correcta
* Derivar a web, cuando un servicio/empresa piensa que la queja o sugerencia no pertenece a su ámbito de acción, le devuelve la queja a la Oficina Técnica indicando las razones de la devolución.
* Informar, cuando un usuario ha categorizado la queja o sugerencia pero la Oficina Técnica piensa que no está bien categorizada, se utilizar esta opción para indicar que la categoría no es correcta y se informa al usuario de que se ha remitido la queja o sugerencia al servicio/empresa competente.
* Solicitar, solicitar más información sobre la queja o sugerencia
* Rechazar la queja o sugerencia, indicando por qué se ha rechazado
* Archivar la queja o sugerencia, si así se considera
* Borrar la queja o sugerencia

El detalle de una queja o sugerencia está compuesto por varias pestañas, cada una con diferente información:

* Datos de la categoría y colaborador responsable de dicha categoría
* Datos del registro, contiene la información general de la queja o sugerencia:
	* Origen de la queja o sugerencia
	* Tipo: queja o sugerencia
	* Prioridad de la queja o sugerencia
	* Estado
	* Asunto
	* Id
	* Descripción
	* Fecha prevista de cierre de la queja o sugerencia
* Usuario del usuario, indicando si está registrado o no
	* Nombre
	* Apellidos
	* Dirección
	* Email
	* Teléfono
* Acciones, listado de las acciones que se han realizado sobre la queja o sugerencia
* Adjunto, permite consultar los datos adjuntos de la queja o sugerencia, si se proporcionaron
* Localización, permite observar la localización de la queja o sugerencia si se indicó en el registro

Desde esta pantalla se realizar las diferentes acciones que se indicaron en el proceso de actuación descrito en el punto 3.

#### Posibles mejoras

No se indican posibles mejoras a este punto.

### 4.2.3 Estadísticas de quejas y sugerencias

Esta funcionalidad permite ver de un vistazo, estadísticas referentes a la queja y sugerencia. Las estadísticas de las sugerencias se muestran en varios listados en pantalla. Se muestran cuatro tablas las dos primeras se refieren a las quejas y sugerencias resueltas y abiertas de las Juntas y las dos últimas a las quejas y sugerencias resueltas y abiertas de los Servicios. 

#### Posibles mejoras

Entre las posibles mejoras estarían:

* Posibilidad de ordenar los resultados de las estadísticas por las cabeceras de las columnas.
* Posibilidad de exportar los datos en diferentes formatos (csv, excel, etc.)

### 4.2.4 Consulta de categorías

Esta funcionalidad permite la administración de las categorías que se utilizan en la generación de quejas y sugerencias. Esta funcionalidad está formada por una listado de las categorías ya existentes y un botón para añadir nuevas categorías.

En el listado de las categorías, por cada registro se muestran los siguientes campos:

* nombre
* nombre del grupo al que pertenece
* usuario, usuario gestor asociado a la categoría
* ID padre, si está asociada a otra categoría o no
* nivel de la categoría, si es una categoría principal o secundaria
* autoasignar, si se autoasigna la categoría o no
* botón de edición, para editar la categoría

Para añadir una nueva categoría, hay que pulsar en el botón “+Añadir Categoría”. Aparecerá un formulario donde habrá que informar de los campos que se han comentado en el listado de categorías.

#### Posibles mejoras

Entre las posibles mejoras estarían:

* Posibilidad de ordenar los resultados de las estadísticas por las cabeceras de las columnas.

### 4.2.5 Consulta de servicios
Esta funcionalidad permite la administración de los distintos servicios encargados de la gestión de las quejas y sugerencias. Esta funcionalidad está formada por una listado de los servicios ya existentes y un botón para añadir nuevos servicios.

En el listado de los servicicos, por cada registro se muestran los siguientes campos:

* ID
* nombre
* correo eléctronico
* botón de edición, para editar el servicio

Para añadir una nueva categoría, hay que pulsar en el botón “+Añadir Categoría”. Aparecerá un formulario donde habrá que informar de los campos que se han comentado en el listado de categorías.

#### Posibles mejoras

Entre las posibles mejoras estarían:

* Posibilidad de ordenar los resultados de las estadísticas por las cabeceras de las columnas.

### 4.2.6 Geolocalización de quejas y sugerencias

Esta funcionalidad permite la consulta en un mapa de las quejas y sugerencias que se han generado en el sistema. Esta funcionalidad está formado por un visualizador donde se puede ir navegando para ver la geolocalización de las quejas y sugerencias, en el que caso de que se disponga de dicha información.

#### Posibles mejoras

No se indican posibles mejoras a este punto.

