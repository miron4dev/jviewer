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

## Configure WildFly locally
1. Create the "keystore" directory inside standalone/configuration and put jviewer.tk.jks inside.
2. Copy integration/modules directory into root of your WildFly AS.
3. Put changed integration/standalone.xml into standalone/configuration directory of your WildFly AS.
- Change the keystore and key password in SSL block.
```xml
<security-realm name="JViewerRealm">
	<server-identities>
		<ssl>
			<keystore path="keystore/jviewer.tk.jks" relative-to="jboss.server.config.dir" keystore-password="changeit" alias="tomcat" key-password="changeit"/>
		</ssl>
	/server-identities>
</security-realm>
```
- Change the database username and password in security block.
```xml
<datasource jndi-name="java:jboss/datasources/jviewer" pool-name="jviewer" enabled="true" use-java-context="true">
	<connection-url>jdbc:postgresql://localhost:5432/jviewer</connection-url>
	<driver>postgresql</driver>
	<security>
		<user-name>changeit</user-name>
		<password>changeit</password>
	</security>
</datasource>
```	
4. Add jviewer.properties file inside standalone/configuration directory with next content:
```
security.encryptPassword='replace by the real secret value'
security.encryptSalt='replace by the real salt value'
```
5. Install <a href="http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html" target="_blank">Java Cryptography Extension (JCE)</a>
