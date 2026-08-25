# CP02-MS-Montadoras

API RESTful desenvolvida com **Spring Boot** para o gerenciamento de dados de uma montadora (montadoras e modelos de veículos), com persistência em **MySQL** via Spring Data JPA, documentação **Swagger/OpenAPI**, **profiles** de execução (`default` e `prd`) e empacotamento via **Docker**.


## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 4 (Spring Web MVC, Spring Data JPA, Bean Validation)
- MySQL
- springdoc-openapi (Swagger UI)
- Maven
- Docker

## 📦 1. Baixando a imagem do Docker Hub

```bash
docker pull lucasfroma/cp02-ms-montadoras:latest
```

Repositório no Docker Hub: `https://hub.docker.com/r/lucasfroma/cp02-ms-montadoras`

## 🚀 2. Executando a aplicação

A aplicação **precisa de um banco MySQL acessível**. O jeito mais simples é subir um container de MySQL na mesma rede Docker da aplicação.

### 2.1 Criar uma rede Docker (uma vez só)

```bash
docker network create montadora-net
```

### 2.2 Subir o banco de dados MySQL

```bash
docker run --name mysql-montadora \
  --network montadora-net \
  -e MYSQL_ROOT_PASSWORD=root_pwd \
  -e MYSQL_DATABASE=api \
  -p 3306:3306 \
  -d mysql:8
```

### 2.3 Rodar a aplicação — profile `default` (desenvolvimento)

```bash
docker run --name montadora-app \
  --network montadora-net \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=default \
  -e DB_HOST=mysql-montadora \
  -e DB_PORT=3306 \
  -e DB_NAME=api \
  -e DB_USER=root \
  -e DB_PASSWORD=root_pwd \
  -d lucasfroma/cp02-ms-montadoras:latest
```

No profile `default`, as tabelas são criadas/atualizadas automaticamente pelo Hibernate (`ddl-auto=update`), ideal para desenvolvimento.

### 2.4 Rodar a aplicação — profile `prd` (produção)

No profile `prd`, o banco **não** é criado automaticamente (`ddl-auto=validate`) — o schema já precisa existir previamente.

```bash
docker run --name montadora-app-prd \
  --network montadora-net \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prd \
  -e DB_HOST=mysql-montadora \
  -e DB_PORT=3306 \
  -e DB_NAME=api \
  -e DB_USER=root \
  -e DB_PASSWORD=root_pwd \
  -d lucasfroma/cp02-ms-montadoras:latest
```

A aplicação fica disponível em: `http://localhost:8080`

## 🔧 Variáveis de ambiente

| Variável | Obrigatória | Descrição | Exemplo |
|---|---|---|---|
| `SPRING_PROFILES_ACTIVE` | Não (padrão: `default`) | Profile de execução: `default` ou `prd` | `prd` |
| `DB_HOST` | Sim (em `prd`) | Host do banco MySQL | `mysql-montadora` |
| `DB_PORT` | Não (padrão: `3306`) | Porta do banco MySQL | `3306` |
| `DB_NAME` | Sim (em `prd`) | Nome do banco de dados | `api` |
| `DB_USER` | Sim (em `prd`) | Usuário do banco | `root` |
| `DB_PASSWORD` | Sim (em `prd`) | Senha do banco | `root_pwd` |

No profile `default`, todas as variáveis acima têm valores padrão (`localhost`, `3306`, `api`, `root`, `root_pwd`) e podem ser omitidas para rodar rapidamente em ambiente local.

## 📖 Documentação da API (Swagger/OpenAPI)

Disponível em **ambos os profiles** (`default` e `prd`):

- Swagger UI: `http://localhost:8080/`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## ⚙️ Profiles de execução

| Profile | Uso | `ddl-auto` | Swagger |
|---|---|---|---|
| `default` | Desenvolvimento local | `update` (cria/atualiza tabelas automaticamente) | Habilitado |
| `prd` | Produção | `validate` (**não** cria banco/tabelas automaticamente) | Habilitado |

## 🗂️ Rodando localmente sem Docker (opcional)

```bash
./mvnw spring-boot:run
```

Por padrão sobe no profile `default`, esperando um MySQL em `localhost:3306` (banco `api`, usuário `root`, senha `root_pwd`). Ajuste via variáveis de ambiente se necessário.

## 🗂️ Estrutura das entidades

- **Montadora**: `id`, `nome`, `pais`, `ramo`, `sede`
- **Modelo**: `id`, `nome`, `franquia`, `classificacao`, `fabricante`

Ambas expõem endpoints REST completos (`GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}`) em `/montadoras` e `/modelos`.
