# CP02-MS-Montadoras

API RESTful desenvolvida com **Spring Boot** para o gerenciamento de montadoras e modelos de veículos, com persistência em **SQL Server** via Spring Data JPA, documentação **Swagger/OpenAPI**, **profiles** de execução (`default` e `prd`) e empacotamento via **Docker**.

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 4 (Spring Web MVC, Spring Data JPA, Bean Validation)
- SQL Server (driver `mssql-jdbc`)
- springdoc-openapi (Swagger UI)
- Maven
- Docker

## 📋 Pré-requisitos

- Java 17 e Maven (ou use o `mvnw` do projeto)
- Um banco **SQL Server** acessível (local, em container ou remoto) com um *database* já criado

## 🗄️ 1. Configuração da conexão com o SQL Server

A conexão é configurada em `src/main/resources/application.properties` (profile `default`) e `application-prd.properties` (profile `prd`), usando variáveis de ambiente:

| Variável | Obrigatória | Descrição | Padrão (profile `default`) |
|---|---|---|---|
| `DB_HOST` | Sim (em `prd`) | Host/IP do SQL Server | `localhost` |
| `DB_PORT` | Não | Porta do SQL Server | `1433` |
| `DB_NAME` | Sim (em `prd`) | Nome do database | `api` |
| `DB_USER` | Sim (em `prd`) | Usuário do banco | `sa` |
| `DB_PASSWORD` | Sim (em `prd`) | Senha do banco | `1q2w3e4R@` |
| `DB_ENCRYPT` | Não | Habilita criptografia na conexão | `true` |
| `DB_TRUST_CERT` | Não | Confia no certificado do servidor | `true` |
| `SPRING_PROFILES_ACTIVE` | Não | `default` ou `prd` | `default` |

URL de conexão resultante:

```text
jdbc:sqlserver://<DB_HOST>:<DB_PORT>;databaseName=<DB_NAME>;encrypt=<DB_ENCRYPT>;trustServerCertificate=<DB_TRUST_CERT>
```

> O SQL Server **não cria o database automaticamente**. Ele precisa existir antes de subir a API. As **tabelas** são criadas/atualizadas pelo Hibernate no profile `default` (`ddl-auto=update`).

### Criando o database (caso ainda não exista)

```sql
CREATE DATABASE api;
```

### Subindo um SQL Server local com Docker (opcional)

```bash
docker run --name sqlserver-montadora \
  -e "ACCEPT_EULA=Y" \
  -e "MSSQL_SA_PASSWORD=1q2w3e4R@" \
  -p 1433:1433 \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

Depois, crie o database `api` (comando SQL acima) usando Azure Data Studio, DBeaver ou `sqlcmd`.

## 🚀 2. Executando a aplicação

### Com o banco local/remoto já configurado

Defina as variáveis de acordo com o banco disponibilizado.

**Linux / macOS**

```bash
export DB_HOST=localhost
export DB_PORT=1433
export DB_NAME=api
export DB_USER=sa
export DB_PASSWORD='1q2w3e4R@'
./mvnw spring-boot:run
```

**Windows PowerShell**

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="1433"
$env:DB_NAME="api"
$env:DB_USER="sa"
$env:DB_PASSWORD="1q2w3e4R@"
.\mvnw.cmd spring-boot:run
```

Se o seu banco usar exatamente os valores padrão da tabela acima, basta rodar `./mvnw spring-boot:run`.

A aplicação sobe em `http://localhost:8080`.

### Com Docker

```bash
docker build -t cp02-ms-montadoras .

docker run --name montadora-app \
  -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=1433 \
  -e DB_NAME=api \
  -e DB_USER=sa \
  -e DB_PASSWORD='1q2w3e4R@' \
  cp02-ms-montadoras
```

> `host.docker.internal` permite que o container acesse o SQL Server rodando na máquina host. Para um servidor remoto, use o host/IP dele em `DB_HOST`.

### Profile `prd`

No profile `prd` o Hibernate apenas **valida** o schema (`ddl-auto=validate`): as tabelas já devem existir. Adicione `-e SPRING_PROFILES_ACTIVE=prd` ao `docker run` (ou exporte a variável) e informe todas as variáveis `DB_*`.

## 📖 3. Testando a API

Swagger UI (permite testar todos os endpoints pelo navegador):

- `http://localhost:8080/`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

### Endpoints

**Montadoras** — base `/montadoras`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/montadoras` | Lista todas as montadoras |
| `GET` | `/montadoras/{id}` | Busca montadora por id |
| `POST` | `/montadoras` | Cria uma montadora |
| `PUT` | `/montadoras/{id}` | Atualiza uma montadora |
| `DELETE` | `/montadoras/{id}` | Remove uma montadora |

**Modelos** — base `/modelos`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/modelos` | Lista todos os modelos |
| `GET` | `/modelos/{id}` | Busca modelo por id |
| `POST` | `/modelos` | Cria um modelo |
| `PUT` | `/modelos/{id}` | Atualiza um modelo |
| `DELETE` | `/modelos/{id}` | Remove um modelo |

### Exemplos com curl

Criar uma montadora (grava no SQL Server):

```bash
curl -X POST http://localhost:8080/montadoras \
  -H "Content-Type: application/json" \
  -d '{"nome":"Toyota","pais":"Japão","ramo":"Automotivo","sede":"Toyota City"}'
```

Listar montadoras (consulta no SQL Server):

```bash
curl http://localhost:8080/montadoras
```

Atualizar e remover:

```bash
curl -X PUT http://localhost:8080/montadoras/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Toyota Motor","pais":"Japão","ramo":"Automotivo","sede":"Toyota City"}'

curl -X DELETE http://localhost:8080/montadoras/1
```

Criar um modelo:

```bash
curl -X POST http://localhost:8080/modelos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Corolla","franquia":"2024","classificacao":"Sedan","fabricante":"Toyota"}'
```

## 🗂️ Estrutura das entidades

- **Montadora** (tabela `montadora`): `id`, `nome`, `pais`, `ramo`, `sede`
- **Modelo** (tabela `modelo`): `id`, `nome`, `franquia`, `classificacao`, `fabricante`

## 📁 Organização do projeto

```text
src/main/java/com/github/lucasfroma/montadora
├── controller   # Endpoints REST
├── dto          # Requests/Responses e mappers
├── model        # Entidades JPA
├── repository   # Spring Data JPA
└── service      # Regras de negócio
```
