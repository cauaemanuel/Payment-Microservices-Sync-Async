

# Payment Microservices Clean Architecture

Este repositório apresenta um projeto completo de **microserviços** para uma aplicação de transferência de dinheiro, que inclui um **API Gateway** para centralizar autenticação e roteamento. Desenvolvido com foco em Clean Architecture, comunicação assíncrona via RabbitMQ e service discovery com Eureka Server, este sistema é um exemplo robusto e escalável para soluções financeiras distribuídas.

---

## 🚀 Visão Geral

* **Arquitetura:** Microserviços independentes com comunicação REST e RabbitMQ, descoberta dinâmica de serviços via Eureka e autenticação baseada em JWT.
* **Objetivo:** Criar um sistema financeiro distribuído que segue as melhores práticas do mercado, com alta modularidade, escalabilidade e facilidade de manutenção.
* **Tecnologias:** Java (Spring Boot), RabbitMQ, Eureka, Docker, JWT, Swagger.

---

## 🏗️ Módulos/Microserviços

* **api-gateway:** Centraliza autenticação, roteamento e segurança das requisições.
* **eureka-server:** Serviço de discovery (Service Registry) para todos os micros.
* **user-service:** Gerencia usuários, registro, login e autenticação.
* **wallet-service:** Gerencia carteiras digitais e saldos.
* **payment-api-service:** Orquestra transações entre os micros.
* **payment-processor-service:** Processa efetivamente as transações via RabbitMQ.

---

## ⚡ Foco do Projeto

* Arquitetura de **microserviços desacoplados** para escalabilidade.
* Comunicação assíncrona com RabbitMQ para maior resiliência.
* Clean Architecture para clareza e separação de responsabilidades.
* Service discovery dinâmico com Eureka Server.
* API Gateway para autenticação centralizada e roteamento inteligente.

---

## 🌐 Acessos Importantes

* **Eureka Server (Dashboard):**
  [http://localhost:8761](http://localhost:8761)

* **User Service (Swagger UI):**
  [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

* **Wallet Service (Swagger UI):**
  [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

* **Payment API Service (Swagger UI):**
  [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)

---

## 📑 Documentação das APIs

Para facilitar seus testes e integrações, toda a documentação das rotas está disponível no Postman, com exemplos prontos para importação e execução.

Acesse a documentação completa da API aqui:
👉 [Postman Collection - Payment Microservices](https://documenter.getpostman.com/view/37902450/2sB34oAbpZ)

---

## 🧩 Como rodar o projeto

### Pré-requisitos

* Docker e Docker Compose instalados
* Java 21+

### Passos

```bash
git clone https://github.com/cauaemanuel/Payment-Microservices-Clean-Architecture.git
cd Payment-Microservices-Clean-Architecture
docker-compose up --build
```

Depois, acesse o Eureka e os Swaggers para explorar os serviços.

> ⚠️ **Importante:** Caso as tabelas do banco de dados PostgreSQL não sejam criadas automaticamente na primeira inicialização, execute manualmente o script `data.sql` para criar a estrutura necessária.

---

## 🚧 Status do Projeto

Este projeto ainda está em construção e pode receber atualizações frequentes. Sugestões e contribuições são muito bem-vindas!

---

## 📄 Licença

Projeto licenciado sob a **MIT License**.

---

> Feito com 💙 por [@cauaemanuel](https://github.com/cauaemanuel)


