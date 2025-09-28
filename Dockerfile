# -------------------------
# Etapa 1: build Angular
# -------------------------
FROM node:20 AS frontend
WORKDIR /app/cvgen-angular

# Cacheo de dependencias
COPY cvgen-angular/package*.json ./
RUN npm install

# Copiamos el resto del código
COPY cvgen-angular/ ./

# Build producción
RUN npm run build


# -------------------------
# Etapa 2: build Spring Boot
# -------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS backend
WORKDIR /app/cvgenapi

# Cacheo de dependencias Maven
COPY cvgenapi/pom.xml ./
RUN mvn dependency:go-offline

# Copiamos el código backend
COPY cvgenapi/ ./

# Recursos estáticos compilados
COPY --from=frontend /app/cvgen-angular/dist/cvgen-angular/ ./src/main/resources/static/

# Build Spring Boot
RUN mvn clean package -DskipTests


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
