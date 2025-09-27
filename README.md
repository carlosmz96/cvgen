# CVGen APP

Generador de CV (API Spring Boot + Front Angular).

## 🧰 Stack
- **Backend**: Spring Boot 3.5.6, Spring Web, Spring Validation, Spring Data JPA, PostgreSQL, MapStruct, OpenAPI/Swagger.
- **Frontend**: Angular 19, PrimeNG, Reactive Forms.
- **Infra (dev)**: Docker Compose (API + Postgres).

## 🗂️ Estructura
```
cvgen/
 ├─ cvgenapi/         # Spring Boot API
 └─ cvgen-angular/    # Angular app
```

## 🚀 Cómo arrancar en DEV

### Requisitos
- Java 21, Maven 3.9+
- Node 20+ / PNPM o NPM
- Docker (para DB local)

### Variables de entorno
**cvgenapi/.env**
```
SPRING_PROFILES_ACTIVE=dev
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/cvgen
SPRING_DATASOURCE_USERNAME=cvgen
SPRING_DATASOURCE_PASSWORD=cvgen
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

### Backend
```bash
cd cvgenapi
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Frontend
```bash
cd cvgen-angular
npm install
npm start
# Servirá en http://localhost:4200
```

## 🔌 Endpoints clave
- `GET /api/cv` – listar CVs
- `POST /api/cv` – crear CV
- `GET /api/cv/{id}` – detalle
- `PUT /api/cv/{id}` – actualizar
- `DELETE /api/cv/{id}` – eliminar

**Swagger/OpenAPI**: http://localhost:8080/swagger-ui/index.html  
**Docs JSON**: http://localhost:8080/v3/api-docs

## 🖼️ Capturas
- **New**: _[screenshot aquí]_  
- **Details**: _[screenshot aquí]_

## 🗺️ Roadmap corto
- [ ] Autenticación (JWT)
- [✓] Exportar a PDF desde front
- [ ] Versionado de CVs
- [ ] Tests e2e básicos

## 🧪 Tests
- Backend: `mvn test`
- Frontend: `npm run test`

## 🐳 Docker (dev reproducible)
```bash
docker compose up -d
```

## 🆘 Troubleshooting
- **500 en `/v3/api-docs`**: revisa dependencias `springdoc-openapi` y `@Validated` en controladores.
- **CORS**: habilitar origin `http://localhost:4200`.
