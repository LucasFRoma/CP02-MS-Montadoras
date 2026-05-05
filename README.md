# CP02-MS-Montadoras

📝 Descrição do ProjetoEste projeto consiste em uma API RESTful desenvolvida com Spring Boot para o gerenciamento de dados de uma montadora, com persistência em banco de dados relacional. A aplicação foi evoluída a partir do Check Point 1 para incluir operações completas de CRUD (Create, Read, Update, Delete) utilizando Spring Data JPA.  O projeto utiliza o padrão MVC (Model, View, Controller) e está configurado para rodar na porta 8080.  

🛠️ Tecnologias UtilizadasJava 17+Spring Boot 3.x  Spring Data JPA  MySQL (via Docker)  Maven  Swagger/OpenAPI para documentação das rotas  

🚀 Como Rodar a Aplicação1. Pré-requisitosPossuir o Docker instalado em sua máquina. 
1. Possuir o Maven instalado ou utilizar o wrapper (./mvnw) incluso no projeto.
2. 2. Subir o Banco de Dados (Docker)Para que a aplicação funcione corretamente, é necessário subir uma instância do MySQL. Utilize o comando abaixo no seu terminal:Bashdocker run --name mysql-montadora -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=montadora_db -p 3306:3306 -d mysql:latest
(Certifique-se de que as credenciais no arquivo src/main/resources/application.properties coincidem com as configuradas acima).

3. Executar a AplicaçãoNo diretório raiz do projeto, execute:Bash./mvnw spring-boot:run
4. 
A aplicação estará disponível em: http://localhost:8080.

📖 Documentação da API (Swagger)Com a aplicação rodando, você pode acessar a documentação interativa das rotas através do Swagger UI:http://localhost:8080/swagger-ui.html

🗂️ Estrutura de EntidadesConforme os requisitos, as entidades possuem:  Nome da classe no singular e tabela no plural.Pelo menos 5 atributos.Atributos configurados como nullable.
