#!/usr/bin/env bash
unzip -u jviewer-build/target/jviewer-build-2.0-build.zip
echo Installing JViewer-Info...
mv jviewer-info-2.0.war /c/tomcat/webapps/info.war
echo Installing Jviewer-Main...
mv jviewer-main-2.0.war /c/tomcat/webapps/main.war
rm -rf /c/tomcat/webapps/info
rm -rf /c/tomcat/webapps/main
echo Deploy was successful!