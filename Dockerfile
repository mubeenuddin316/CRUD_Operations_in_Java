# Dockerfile in your GitHub repo
FROM tomcat:10.1.13-jdk17

# Copy your WAR file into Tomcat (inside the container)
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Start Tomcat when the container launches
CMD ["catalina.sh", "run"]

