# Use Tomcat as base image
FROM tomcat:9.0

# Copy WAR file to the correct Tomcat webapps folder
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Copy SQLite database into the container (if used)
COPY database.db /usr/local/tomcat/webapps/ROOT/database.db

# Expose the port for Tomcat
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
