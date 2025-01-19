# API Usuários

# Tecnologias

## Backend
- Java (Versão 17)
- Maven
- Lombok
- Spring Boot
- JPA/Hibernate
- Spring Security
- MockMvc e JUnit
- Bancos de dados PostgresSQL
- Flyway
- Banco de dados H2 (Testes integrado)
- Swagger (Documentação)
- HATEOAS
- Jacoco
- Docker
- Redis

## Sobre o projeto
Esta aplicação consiste em criar usuário do tipo cliente ou administrador e que consiga criar anotações do tipo lembrete, período de estudo e rascunho. Arquitetura desse projeto foi utilizada a hexagonal architecture.

# Como executar o projeto
## Backend 

Pré requisito: Java 17 e Docker

```
# clonar o projeto loja usuario api 
git clone https://github.com/zGabrielZ/usuarios.git

# clonar o projeto que está com o script do docker yaml do banco de dados postgressql e também o rabbitmq, isso é o ambiente dev
git clone https://github.com/zGabrielZ/configs.git

# entrar na pasta do projeto que consiste o script o docker yaml do postgres 
 cd '.\Config API Usuário\postgres-dev\'

# executar o script docker yaml
docker-compose up -d

# entrar na pasta do projeto que consiste o script o docker yaml do redis
 cd '.\Config API Produto\redis-dev\'

# executar o script docker yaml
docker-compose up -d

# apos isso, entrar na pasta backend do projeto e subir a aplicação com o seguinte comando 
./mvnw spring-boot:run
```

Url documentação API Usuário: http://localhost:8080/api/swagger-ui/index.html#/

![Documentação API Usuário](https://github.com/zGabrielZ/assets/blob/main/API%20Usu%C3%A1rio/swagger.png)

# Autor

Gabriel Ferreira

https://www.linkedin.com/in/gabriel-ferreira-4b817717b/
