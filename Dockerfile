# ===========================
# Etapa 1: Build Angular
# ===========================
FROM node:20 AS frontend
WORKDIR /app

# Copiamos solo package.json + package-lock.json primero (mejor cache)
COPY cvgen-angular/package*.json ./cvgen-angular/
RUN cd cvgen-angular && npm install

# Ahora copiamos el resto del frontend
COPY cvgen-angular/ ./cvgen-angular/
RUN cd cvgen-angular && npm run build


# ===========================
# Etapa 2: Build Spring Boot
# ===========================
FROM maven:3.9.9-eclipse-temurin-21 AS backend
WORKDIR /app

# Copiamos solo pom.xml para cachear dependencias
COPY cvgenapi/pom.xml ./cvgenapi/
RUN mvn -f cvgenapi/pom.xml dependency:go-offline -B

# Ahora copiamos el código y la build del frontend
COPY cvgenapi/ ./cvgenapi/
COPY --from=frontend /app/cvgen-angular/dist/cvgen-angular/browser/ \
    ./cvgenapi/src/main/resources/static/

# Compilamos el proyecto
RUN mvn -f cvgenapi/pom.xml clean package -DskipTests


# ===========================
# Etapa 3: Imagen final
# ===========================
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiamos el JAR
COPY --from=backend /app/cvgenapi/target/*.jar app.jar

# Railway asigna $PORT automáticamente
EXPOSE 8080

# Usamos shell form para expansión de variables
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]