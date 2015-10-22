JViewer
=======

It is a webinar platform for conferences about HTML, JavaScript, CSS and Java, which allows to:
- create separate rooms (Web and Java)
- demonstrate entered code and result of this code in real time
- get tested on specific questions of topic

Technologies and frameworks, which are used:
- Java
- Spring & Spring Security
- JSF
- PrimeFaces
- WebSocket
- SQLite
- Maven
- JMX

## Available platforms
- Windows
- Linux
- MAC OS

## Download
Links to download JViewer are available <a href="http://jviewer.tk:8080/download.xhtml" target="_blank">here</a>

## Configure Tomcat locally
1. Add the next connector into the ${catalina.home}/conf/server.xml:

    ```
    <Connector port="8443" SSLEnabled="true" protocol="org.apache.coyote.http11.Http11NioProtocol"
            maxThreads="150" scheme="https" secure="true"
            clientAuth="false" sslProtocol="TLS"
            keystoreFile="${catalina.home}/keystore/jviewer.tk.jks" keystorePass="secret" keystoreType="JKS"
            keyAlias="tomcat"/>
    ```
    and next jndi resource inside ```<GlobalNamingResources>``` block:
    
     ```
    <Resource name="jdbc/jviewer" auth="Container"
          type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
		  factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
          url="jdbc:postgresql://localhost:5432/jviewer"
          username="postgres" password="databasePassword" maxTotal="20" maxIdle="10" maxWaitMillis="-1"/>
          
    ```
2. Replace "secret" with a real value for the keystorePass, "postgres" and "secret" with a real data to your database. Url also could be different.
3. Create the "keystore" directory inside the ${catalina.home} and put jviewer.tk.jks key file inside
4. Download <a href="https://jdbc.postgresql.org/download.html" target="_blank">PostgreSQL JDBC driver</a> and put it inside ${catalina.home}/lib directory. 
