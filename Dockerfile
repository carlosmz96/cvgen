# Etapa 1: build Angular
FROM node:20 AS frontend
WORKDIR /app/cvgen-angular

# Solo copiamos package.json + package-lock.json para cachear npm install
COPY cvgen-angular/package*.json ./
RUN npm install

# Ahora copiamos el resto (esto invalida solo si cambian fuentes, no deps)
COPY cvgen-angular/ ./
RUN npm run build


# Etapa 2: build Spring Boot + copiar frontend
FROM maven:3.9.9-eclipse-temurin-21 AS backend
WORKDIR /app/cvgenapi

# Igual: primero cacheamos dependencias de Maven
COPY cvgenapi/pom.xml ./
RUN mvn dependency:go-offline

# Luego copiamos el resto del código
COPY cvgenapi/ ./

# Copiamos Angular ya compilado al static/
COPY --from=frontend /app/cvgen-angular/dist/cvgen-angular/browser/ ./cvgenapi/src/main/resources/static/

RUN mvn clean package -DskipTests


# Etapa 3: run
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=backend /app/cvgenapi/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
