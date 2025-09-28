# Etapa 1: build Angular
FROM node:20 AS frontend
WORKDIR /app
COPY cvgen-angular/ ./cvgen-angular/
RUN cd cvgen-angular && npm install && npm run build

# Etapa 2: build Spring Boot + copiar frontend
FROM maven:3.9.9-eclipse-temurin-21 AS backend
WORKDIR /app
COPY cvgenapi/ ./cvgenapi/
COPY --from=frontend /app/cvgen-angular/dist/cvgen-angular/browser/ ./cvgenapi/src/main/resources/static/
RUN cd cvgenapi && mvn clean package -DskipTests


# -------------------------
# Etapa 3: Imagen final
# -------------------------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiamos el JAR
COPY --from=backend /app/cvgenapi/target/*.jar app.jar

# Render pasa la variable $PORT automáticamente
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","--server.port=${PORT}"]
