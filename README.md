# Biblioteca

Aplicação de gerenciamento de biblioteca, composta por:

- `biblioteca-backend`: API REST em Spring Boot.
- `biblioteca-frontend`: interface web em Angular.

## Requisitos

Antes de iniciar, tenha instalado:

- Java 17
- Maven 3.9+
- Node.js 20+ (com npm)
- PostgreSQL 14+ (ou versão compatível)

## Estrutura do projeto

```text
Biblioteca/
|- biblioteca-backend/
`- biblioteca-frontend/
```

## Configuração do banco de dados (PostgreSQL)

O backend usa PostgreSQL com JPA/Hibernate (`ddl-auto=update`), então as tabelas são criadas/atualizadas automaticamente ao subir a API.

### 1) Criar o banco

No PostgreSQL, execute:

```sql
CREATE DATABASE biblioteca_db;
```

### 2) Configurar credenciais no backend

Edite o arquivo `biblioteca-backend/src/main/resources/application.properties` com os dados do seu ambiente:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Como executar a aplicação

### 1) Subir o backend

No terminal, dentro de `biblioteca-backend`:

```bash
mvn spring-boot:run
```

Backend disponível em:

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### 2) Subir o frontend

Em outro terminal, dentro de `biblioteca-frontend`:

```bash
npm install
npm start
```

Frontend disponível em:

- App: `http://localhost:4200`

## Fluxo de execução recomendado

1. Inicie o PostgreSQL.
2. Garanta que o banco `biblioteca_db` exista.
3. Configure usuário/senha no `application.properties`.
4. Suba o backend.
5. Suba o frontend.
6. Acesse `http://localhost:4200`.

## Comandos úteis

### Backend

```bash
mvn test
```

### Frontend

```bash
npm run build
npm test
```
