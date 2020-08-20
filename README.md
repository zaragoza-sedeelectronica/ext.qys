### OpenCity-Ext - Quejas y Sugerencias ###

# Introducción

A través del Servicio de Quejas y Sugerencias la ciudadanía puede comunicar al Ayuntamiento todas sus inquietudes, intereses y reclamaciones, con la confianza de recibir en toda ocasión una respuesta personalizada.

Toda la información sobre este módulo está en el [siguiente enlace](documentacion/). Aquí puede consultar:

* [el documento funcional](documentacion/funcional/README.md) 
* [el manual de uso](documentacion/manual/README.md).

A continuación se indican las instrucciones necesarias para la descarga y configuración del módulo de "Quejas y Sugerencias" del proyecto OpenCity-Ext desarrollado por el Ayuntamiento de Zaragoza.

## Manual de instalación ##

Primero, hay que realizar los siguientes pasos del manual general:

* instalar y comprobar versiones software
* configurar la base de datos
* clonar repositorios
* instalación de librerías en repositorio local
* configuración del contenido estático en Apache
* configuración del módulo
* prueba del módulo
* otras configuraciones

# Instalar y comprobar versiones software

Las versiones mínimas del software son:

* Java 1.8
* Maven >= 3.0.5
* Oracle 11
* Apache Server (2.2 versión usada en las pruebas)
* Eclipse 2019-03 (recomendada)

Una vez instalado Eclipse, hay que instalar  maven integration for eclipse desde la siguiente url http://download.eclipse.org/releases/indigo/ y seleccionando la opción "Generarl Purpose -> m2e". Este paso solo es necesario si se va a trabajar con el código. El módulo puede ser ejecutado sin necesidad de Eclipse.

# Configurar la base de datos

El sistema de quejas y sugerencias utiliza un usuario general para las bases de datos.

Por lo tanto, hay que configurar las dos bases de datos para que el modulo funcione correctamente. Para eso hay que realizar los siguientes pasos:

* generar los usuarios en Oracle
* ejecutar los scripts de generación de las bases de datos

A continuación se detallan los pasos a seguir en cada uno de ellos.

## Generar los usuarios en Oracle

Como se indica en el apartado anterior, hay que generar un usuario para la base de datos. El usuario se debe generar en Oracle ya que el sistema de quejas y sugerencias utiliza Oracle.

Se aconseja que el nombre del usuario sea "general" ya que es el que se utiliza en el core de la aplicación. A continuación se muestran los comandos que deben utilizarse en la consola de SQL*Plus, si se utiliza dicha consola para realizar este paso.

Para generar el usuario, se utilizarían los comandos:
```
CREATE USER general IDENTIFIED BY "password";
```

Con este paso, ya estaría el usuario creado.

Para otorgar privilegios al usuario, se utilizaría el comando:
```
GRANT ALL PRIVILEGES TO general;
```

## Ejecutar los scripts de generación de las bases de datos

El siguiente paso es crear las tablas y cargar los datos de prueba disponibles para este módulo. Los scripts que se van a importar están disponibles en la carpeta [scripts-bbdd/](scripts-bbdd/).

El orden de ejecución es el siguiente:

* 1.1.db.sql
* 1.2.users_db.sql
* 1.3.users_data.sql
* 1.4.extra_users.sql
* 2.movil.sql
* 3.1.callejero.db.sql
* 3.2.callejero.data.sql
* 4.1.ticketing.db.sql
* 4.2.ticketing.data.sql
* 4.3.ticketing.package.sql
	
Una vez realizados estos pasos, ya estaría lista la base de datos en Oracle. Con estos pasos, se ha generado un usuario administrador necesario para las pruebas con los siguientes datos nombre `admin` y contraseña `prueba`.

# Clonar repositorios:

A continuación debe clonarse el repositorio del módulo de Quejas y Sugerencias: https://bitbucket.org/masilgado/qys.git


# Instalación de librerías en repositorio local:

Existen librerías que no están disponibles en repositorios maven, son las que se encuentran en la carpeta [librerias](librerias/) y se deben instalar en el repositorio maven de local ejecutando los siguientes comandos:

```
mvn install:install-file -DgroupId=net.sf.jasperreports -DartifactId=jasperreports -Dversion=0.0.1 -Dpackaging=jar -Dfile=jasperreports-6.14.0.jar -DgeneratePom=true
mvn install:install-file -DgroupId=org.opencity -DartifactId=ext.callejero -Dversion=0.0.1 -Dpackaging=jar -Dfile=ext.callejero-0.0.1.jar -DgeneratePom=true
mvn install:install-file -DgroupId=org.opencity -DartifactId=ext.citaprevia -Dversion=0.0.1 -Dpackaging=jar -Dfile=ext.citaprevia-0.0.1.jar -DgeneratePom=true
mvn install:install-file -DgroupId=org.opencity -DartifactId=ext.informes -Dversion=0.0.1 -Dpackaging=jar -Dfile=ext.informes-0.0.1.jar -DgeneratePom=true
mvn install:install-file -DgroupId=org.opencity -DartifactId=ext.core -Dversion=0.0.1 -Dpackaging=jar -Dfile=ext.core-0.0.1.jar -DgeneratePom=true
mvn install:install-file -DgroupId=org.opencity -DartifactId=ext.core.test -Dversion=0.0.1 -Dpackaging=jar -Dfile=ext.core.test-0.0.1.jar -DgeneratePom=true
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc5 -Dversion=11.2.0 -Dpackaging=jar -Dfile=ojdbc5.jar -DgeneratePom=true
mvn install:install-file -DgroupId=virtuoso.jena.driver -DartifactId=virtjdbc -Dversion=3 -Dpackaging=jar -Dfile=virtjdbc.jar -DgeneratePom=true
mvn install:install-file -DgroupId=virtuoso.jena -DartifactId=virt_jena -Dversion=3 -Dpackaging=jar -Dfile=virt_jena.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=geoapi -Dversion=2.0 -Dpackaging=jar -Dfile=geoapi.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=geoapi-nogenerics -Dversion=2.1-M2 -Dpackaging=jar -Dfile=geoapi-nogenerics.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=deegree2-pre -Dversion=2 -Dpackaging=jar -Dfile=deegree2-pre.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=gsl-coordinate-transformation -Dversion=1.0-jdk15 -Dpackaging=jar -Dfile=gsl-coordinate-transformation.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=gt2-epsg-wkt -Dversion=2.3.5 -Dpackaging=jar -Dfile=gt2-epsg-wkt.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=gt2-referencing -Dversion=2.3.0 -Dpackaging=jar -Dfile=gt2-referencing.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=iaaa_csct -Dversion=1.6.2 -Dpackaging=jar -Dfile=iaaa_csct.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=j3dcore -Dversion=1.3.0 -Dpackaging=jar -Dfile=j3dcore.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=j3dutils -Dversion=1.3.0 -Dpackaging=jar -Dfile=j3dutils.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=jai_codec -Dversion=1.1.1_01 -Dpackaging=jar -Dfile=jai_codec.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=jai_core -Dversion=1.1.1_01 -Dpackaging=jar -Dfile=jai_core.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=jGridShiftApi -Dversion=2.0 -Dpackaging=jar -Dfile=jGridShiftApi.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=jsr108 -Dversion=0.01 -Dpackaging=jar -Dfile=jsr108.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=jts -Dversion=1.6 -Dpackaging=jar -Dfile=jts.jar -DgeneratePom=true
mvn install:install-file -DgroupId=idezar -DartifactId=vecmath -Dversion=1.3.1 -Dpackaging=jar -Dfile=vecmath.jar -DgeneratePom=true
```


# Configuración del contenido estático en Apache
Una vez clonado el proyecto, se habran generado una sertie de carpetas en nuestro repositorio local, en la ruta **\opencity.ext.web\src\main\webapp** encontramos
el contenido estático de la aplicación, el cual, para mostrarse correctamente una vez desplegada debe servirse mediante un servidor Apache haciendo uso 
de su funcionalidad ProxyPass. Para ello, una vez instalado Apache, creamos una carpeta llamada **cont** dentro del directorio de publicacion **httdocs** y dentro 
de esta colocamos el contenido estático seguiendo la siguiente estructura:

```
cont
|--- assets (se coje el contenido de la carpeta opencity.ext.web\src\main\webapp\assets)
cont/vistas
|--- fragmentos (se coje el contenido de la carpeta opencity.ext.web\src\main\webapp\vistas\fragmentos)
|--- servicio (se coje el contenido de la carpeta opencity.ext.web\src\main\webapp\vistas\servicio)
|--- portal (se coje el contenido de la carpeta opencity.ext.web\src\main\webapp\vistas\portal)
|--- assets (se coje el contenido de la carpeta opencity.ext.web\src\main\webapp\vistas\assets)
|--- resto de archivos (se coje el contenido de la carpeta opencity.ext.web\src\main\webapp\vistas)

```

Una vez realizado este paso, hay que configurar un servidor apache para que pueda servir contenidos estáticos habilitando un proxy inverso e indicando su configuración.
En archivo de configuración de Apache **conf/httpd.conf** (en Windows), primero habilitamos los modulos descomentando las siguientes líneas:

```
LoadModule proxy_module modules/mod_proxy.so
LoadModule proxy_http_module modules/mod_proxy_http.so
```
Al final del mismo fichero de configuración añadimos las siguientes lineas:

```
ProxyPass /opencityext http://localhost:7777/opencityext
ProxyPassReverse /opencityext http://localhost:7777/opencityext
AddDefaultCharset utf-8
```

Además, añadimos la definicion del directorio y el Alias en el mismo archivo de configuración de apache:
```
Alias /cont ${SRVROOT}/htdocs/cont	
<Directory  ${SRVROOT}/htdocs/cont>
	Options Indexes FollowSymLinks
	AllowOverride None
	Require all granted
</Directory>
```


## Configuración del módulo


**nota previa:**
Par más comodidad en la edición de las propiedades y en la modificación del código se sugiere importar los proyectos opencity.ext.qys y opencity.ext.web mediante la función Import/Existing Maven Projects de Eclipse.


Lo primero que debemos hacer es ejecutar:

```
mvn clean install -Dmaven.test.skip=true
```

dentro del proyecto `opencity.ext.qys` 

Una vez instalado hay que modificar/crear los siguientes ficheros de configuración en el modulo web:

`/ext.web.qys/src/main/resources/META-INF/context.xml`
`/ext.web.qys/src/main/resources/application.properties`

Para obtener un ejemplo/plantilla de estos archivos de configuración podemos consultar o descargar el proyecto  **../zaragoza/shared-resources.git**

El contenido del fichero **context.xml** contiene los datos de conexión a la base de datos y debe ser el siguiente:

```
<?xml version="1.0" encoding="UTF-8"?>
<Context>
<Resource name="jdbc/WebGeneralDS" auth="Container"
              type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
              url="jdbc:oracle:thin:@localhost:<puerto>/<SID>"
              username="general" password="<pass>" maxActive="20" maxIdle="10"
              maxWait="-1"/>
<Resource name="jdbc/TicketingDS" auth="Container"
              type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
              url="jdbc:oracle:thin:@localhost:<puerto>/<SID>"
              username="general" password="<pass>" maxActive="20" maxIdle="10"
              maxWait="-1"/>
<Resource name="jdbc/WebIntraDS" auth="Container"
              type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
              url="jdbc:oracle:thin:@localhost:<puerto>/<SID>"
              username="general" password="<pass>" maxActive="20" maxIdle="10"
              maxWait="-1"/>
<Resource name="jdbc/WebParticipacionDS" auth="Container"
              type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
              url="jdbc:oracle:thin:@localhost:<puerto>/<SID>"
              username="general" password="<pass>" maxActive="20" maxIdle="10"
              maxWait="-1"/>			  
</Context>
```
sustituyendo **puerto**, **SID** y **pass** por los datos de nuestra configuración. 
Ejemplo a la hora de realizar este documento: 

```
              url="jdbc:oracle:thin:@localhost:1521/orcl"
              username="general" password="general" maxActive="20" maxIdle="10"
```

El contenido del fichero application.properties debe ser el siguiente:

```
contexto=/opencityext
path=localhost
thymeleaf.view=<ruta-opencity.ext.web>/src/main/webapp/vistas/
thymeleaf.strictMode=false
path.i18n=<ruta-opencity.ext.web>/src/main/webapp/i18n/messages
datasource.prefix=java:/comp/env/
path.solr=www.zaragoza.es
path.allowed=localhost:7777,localhost,localhost:9999,localhost:7001
solr.usuario=
solr.password=
proxy.host=
proxy.port=
mail.server=
mail.user=
mail.pass=
entorno=local
path.cont=http://localhost:<apache-port>/cont
path.cont.external=http://localhost:<apache-port>/cont/
path.cont.disk=<ruta-apache-httdocs>/cont
path.vistas.disk=<ruta-apache-httdocs>/cont/vistas
path.aplicaciones.disk=<ruta-apache-httdocs>/cont/aplicaciones/
virtuoso.sparql=http://datos.zaragoza.es/sparql
virtuoso.user=
virtuoso.pass=
virtuoso.connection=
sms.server=
```
Sustituyendo el contenido de  `ruta-opencity.ext.web` y  `ruta-apache-httdocs` por las rutas relativas/absolutas a cada carpeta. Por ejemplo, si el proyecto está en "/home/qys/opencity.ext.web", el fichero tendría los siguientes valores:

```
thymeleaf.view=/home/qys/opencity.ext.web/src/main/webapp/vistas/
path.i18n=/home/qys/opencity.ext.web/src/main/webapp/i18n/messages
...
```

Esta definición se puede realizar para cada uno de los entornos en `resources`, `resources-dev`, `resources-prod` y `resources-test`.

Dentro del fichero `opencity.ext.web/src/main/resources/application.properties` hay que definir también los siguientes campos:
```
path.aplicaciones.disk=/home/tmp/
mail.server=smtp.server.url
mail.user=user
mail.pass=pass
```

El primero de los campos `path.aplicaciones.disk` se utiliza para almacenar los ficheros que se pueden adjuntar a los registros. Los otros tres campos se utilizan para el envío de notificaciones mediante correo electrónico a los usuarios.



# Prueba del módulo

Para lanzar el módulo, hay que ejecutar la siguiente instrucción:
```
$ mvn -Dmaven.tomcat.port=8888 tomcat7:run
```

Para probar que funciona correctamente acceder a:
```
http://localhost:8888/opencityext/servicio/quejas-sugerencias/
```

Puede ser que haya elementos que no se muestren correctamente por lo que se aconseja que mejor se utilice 
```
http://localhost:<apache-port>/opencityext/servicio/quejas-sugerencias/
```
sustituyendo `apache-port` por el puerto habilitado en Apache mediante la intrucción `Listen <puerto>` utilizada en el archivo de configuración httpd.conf (Windows).

Ejemplos:
Usando `Listen 80` en httpd.conf:    http://localhost/opencityext/servicio/quejas-sugerencias/
Usando `Listen 8090` en httpd.conf:  http://localhost:8090/opencityext/servicio/quejas-sugerencias/

siempre asegurándonos de que el servidor Apache está arrancado.  `httpd -k start`

asegurándonos de que el servidor Apache está arrancado.

Para acceder a la parte de administración del módulo, se debe hacer con el usuario `admin` y contraseña `prueba` mediante la siguiente url: 
```
http://localhost/opencityext/servicio/quejas-sugerencias/admin
```

# Otras configuraciones

Con los pasos indicados anteriormente, ya podría trabajar con el módulo sin problemas. No obstante, existentes más configuraciones que pueden realizarse, las cuales se indican a continuación.

## Trabajar con proxy
Configurar maven en $HOME/.m2/ crear el fichero settings.xml
```
<settings>
  <proxies>
    <proxy>
      <id>http_proxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy.red.zaragoza.es</host>
      <port>8080</port>
    </proxy>
    <proxy>
      <id>https_proxy</id>
      <active>true</active>
      <protocol>https</protocol>
      <host>proxy.red.zaragoza.es</host>
      <port>8080</port>
    </proxy>
 </proxies>
</settings>
```
$ mvn -Dmaven.tomcat.port=8888 -Dhttp.proxyHost=<host> -Dhttp.proxyPort=<port> -Dhttps.proxyHost=<host> -Dhttps.proxyPort=<port> tomcat7:run

## Modificar la caché
Por defecto se almacenan en caché todas las peticiones `GET` para evitarlo, se debe anotar el método del controlador que no se quiera almacenar en caché con la anotación `@NoCache`, la configuración de la caché se encuentra en el fichero `src/main/resources/ehcache.xml`.

Por otro lado se puede utilizar la caché propia de spring:
* `@Cache(Cache.DURACION_1MIN)` el elemento se cachea durante 1 minuto
* `@Cache(Cache.DURACION_5MIN)` el elemento se cachea durante 5 minutos
* `@Cache(Cache.DURACION_30MIN)` el elemento se cachea durante 30 minutos
* `@Cache(Cache.DURACION_1DIA)` el elemento se cachea durante 1 día 
