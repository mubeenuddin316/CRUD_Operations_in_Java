# Use Tomcat as base image
FROM tomcat:latest

# Set working directory
WORKDIR /usr/local/tomcat/webapps/ROOT

# Copy WAR file
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Copy SQLite database into container
COPY database.db /usr/local/tomcat/webapps/ROOT/database.db

# Start Tomcat
CMD ["catalina.sh", "run"]
