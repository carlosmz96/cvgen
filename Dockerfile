# Etapa 1: build Angular
FROM node:20 AS frontend
WORKDIR /app
COPY cvgen-angular/ ./cvgen-angular/
RUN cd cvgen-angular && npm install && npm run build --configuration production

# Etapa 2: build Spring Boot + copiar frontend
FROM maven:3.9.9-eclipse-temurin-21 AS backend
WORKDIR /app
COPY cvgenapi/ ./cvgenapi/
COPY --from=frontend /app/cvgen-angular/dist/cvgen-angular/ ./cvgenapi/src/main/resources/static/
RUN cd cvgenapi && mvn clean package -DskipTests

# Etapa 3: run
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=backend /app/cvgenapi/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
