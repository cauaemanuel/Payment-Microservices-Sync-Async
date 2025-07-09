# Payment Microservices Clean Architecture

Este repositório apresenta um projeto de **microserviços** que simula uma aplicação de transferência de dinheiro, incluindo um **API Gateway** para centralização de autenticação e roteamento. O sistema foi desenvolvido com foco em Clean Architecture, melhores práticas de mercado e uso de comunicação assíncrona via RabbitMQ, além de integração via Eureka Server para service discovery.

---

## 🚀 Visão Geral

- **Arquitetura:** Microserviços independentes, comunicação via REST e RabbitMQ, descoberta de serviços com Eureka, autenticação JWT.
- **Objetivo:** Demonstrar um sistema financeiro distribuído, com separação de responsabilidades, escalabilidade e facilidade de manutenção.
- **Tecnologias:** Java (Spring Boot), RabbitMQ, Eureka, Docker, JWT, Swagger.

---

## 🏗️ Módulos/Microserviços

- **api-gateway:** Centraliza autenticação, roteamento e segurança das requisições.
- **eureka-server:** Serviço de discovery (Service Registry) para todos os micros.
- **user-service:** Gerenciamento de usuários (login, cadastro, autenticação).  
- **wallet-service:** Gerenciamento de carteiras e saldos dos usuários.  
- **payment-api-service:** Orquestra transações e integra com outros micros (user e wallet).  
- **payment-processor-service:** Processamento efetivo das transações, integração via RabbitMQ.

---

## ⚡ Foco do Projeto

O principal objetivo foi implementar uma arquitetura de **microserviços desacoplados**, utilizando:
- RabbitMQ para comunicação assíncrona entre os serviços.
- Clean Architecture para separação de camadas e responsabilidades.
- Eureka Server para discovery e registro dinâmico dos micros.
- API Gateway para centralizar autenticação e roteamento.

---

## 🌐 Acessos Importantes

- **Eureka Server (dashboard):**  
  [http://localhost:8761](http://localhost:8761)

- **User Service (Swagger):**  
  [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

- **Wallet Service (Swagger):**  
  [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

- **Payment API Service (Swagger):**  
  [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)

---

## 🧩 Como rodar

1. **Pré-requisitos:**  
   - Docker e Docker Compose instalados  
   - Java 17+

2. **Clone o repositório:**  
   ```bash
   git clone https://github.com/cauaemanuel/Payment-Microservices-Clean-Architecture.git
   ```

3. **Suba o ambiente:**  
   ```bash
   cd Payment-Microservices-Clean-Architecture
   docker-compose up --build
   ```

4. **Acesse o Eureka:**  
   - [http://localhost:8761](http://localhost:8761)

5. **Acesse os Swaggers dos serviços:**  
   - User: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)  
   - Wallet: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)  
   - Payment API: [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)

---

## 📄 Licença

Este projeto está sob a licença MIT.

---

> Feito com 💙 por [@cauaemanuel](https://github.com/cauaemanuel)
