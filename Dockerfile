# Use Tomcat as base image
FROM tomcat:9.0

# Copy the WAR file into Tomcat's webapps folder
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Start Tomcat
CMD ["catalina.sh", "run"]
