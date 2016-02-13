JViewer
=======

It is a webinar platform for the conferences about HTML, JavaScript, CSS and Java, which allows to:
- create separate rooms (Web and Java)
- demonstrate entered code and result of this code in real time
- get tested on specific questions of topic

Technologies and frameworks, which are used:
- Java
- Spring & Spring Security
- JSF
- PrimeFaces
- WebSocket
- PostgreSQL
- Maven
- JMX

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
3. Create the "keystore" directory inside ${catalina.home} and put jviewer.tk.jks key file inside
4. Download <a href="https://jdbc.postgresql.org/download.html" target="_blank">PostgreSQL JDBC42 driver</a> and put it inside ${catalina.home}/lib directory. 
5. Add jviewer.properties file inside ${catalina.home}/conf directory with next content:
     ```
     security.encryptPassword='replace by the real secret value'
     
     security.encryptSalt='replace by the real salt value'
     ```
6. Install <a href="http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html" target="_blank">Java Cryptography Extension (JCE)</a>
