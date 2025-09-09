# Projeto CRUD com Spring Boot

## Descrição

Este projeto foi criado como parte da disciplina **Desenvolvimento de Serviços com Spring Boot**. O objetivo é aplicar conceitos do desenvolvimento de aplicações usando Spring Boot, incluindo:

- Aplicação de **padrões de projeto** e boas práticas.
- Implementação de **testes unitários** utilizando **JUnit 5** e **Mockito**.
- Criação de **endpoints RESTful** para operações CRUD (Create, Read, Update, Delete) de entidades como Cliente, Fornecedor, Produto, Funcionário e Transportadora.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.5
- Spring Web
- Spring Data JPA
- H2 Database
- MapStruct (para mapeamento entre DTOs e entidades)
- Lombok
- Spring Validation
- JUnit 5 + Mockito (testes unitários)
## Estrutura do Projeto

O projeto segue uma arquitetura **camada de serviços** com:

- `entity/` → Entidades JPA
- `dto/` → Data Transfer Objects
- `mapper/` → MapStruct para conversão entre DTOs e entidades
- `repository/` → Repositórios JPA
- `service/` → Serviços com regras de negócio
- `controller/` → Endpoints REST
- `test/` → Testes unitários com JUnit e Mockito
- `util/` → package utilitário com classes globais utilitárias
