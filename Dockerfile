# -----------------------
# Stage 1: Build the WAR
# -----------------------
FROM maven:3.9.4-eclipse-temurin-17 as builder
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your source code
COPY src/ ./src/

# Build the WAR
RUN mvn clean package

# -----------------------
# Stage 2: Run on Tomcat
# -----------------------
FROM tomcat:10.1.13-jdk17

# Copy the WAR from the builder stage
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Run Tomcat
CMD ["catalina.sh", "run"]

