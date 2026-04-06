#  Sistema de Gestão de Biblioteca 

Este projeto é uma solução fullstack para o gerenciamento de uma biblioteca, permitindo o controle de usuários, acervo de livros e empréstimos, com regras de negócio inteligentes e integração com a API externa do Google Books.

---

##  Tecnologias Utilizadas

### **Backend**
* **Java 17** com **Spring Boot 3**
* **Spring Data JPA** (Persistência de dados)
* **PostgreSQL** (Banco de dados relacional)
* **JUnit 5 & Mockito** (Testes unitários de serviço)
* **Maven** (Gerenciador de dependências)

### **Frontend**
* **Angular 17+** (Componentes Standalone e Roteamento)
* **Font Awesome 6** (Ícones profissionais)
* **FormsModule & HttpClient** (Integração com API e reatividade)

---

##  Funcionalidades e Regras de Negócio

O sistema foi desenvolvido atendendo a todos os requisitos do desafio técnico:

1.  **CRUD Completo:** Cadastro e listagem de Usuários e Livros.
2.  **Gestão de Empréstimos:** * Controle de datas de retirada e previsão de entrega.
    * **Regra de Bloqueio:** O sistema impede o empréstimo de um livro que já possua um registro ativo.
3.  **Sistema de Recomendação Inteligente:** Lógica baseada na categoria dos livros devolvidos pelo usuário. O sistema sugere títulos da mesma categoria que o usuário já demonstrou interesse, incentivando a leitura.
4.  **Integração com Google Books (Bônus):** Busca automatizada por título através da API do Google, permitindo cadastrar livros no acervo diretamente pelo frontend.

---

##  Como Executar o Projeto

De acordo com as instruções solicitadas, siga os passos abaixo para rodar a aplicação localmente:

### **1. Requisitos Prévios**
* Java 17 instalado.
* Node.js e Angular CLI instalados.
* PostgreSQL rodando localmente.
---
### **2. Configuração do Banco de Dados**
Crie um banco de dados chamado `biblioteca_db` no seu PostgreSQL e ajuste as credenciais no arquivo `biblioteca-backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

```
---
### **3. Executando o Backend**
```properties
cd biblioteca-backend
mvn spring-boot:run

```
---
### **4. Executando o Frontend**
```properties   
cd biblioteca-frontend
npm install
ng serve


```
---
### **Como Testar a Aplicação**
Usuários: Cadastre um usuário na aba de Usuários.

Livros: Vá na aba de Livros e utilize a busca do Google para popular seu acervo rapidamente ou cadastre manualmente.

Empréstimo: Na tela de Empréstimos, realize uma retirada.

Devolução e Recomendação: Clique em "Devolver" em um registro ativo. Ao selecionar o mesmo usuário novamente no campo de seleção, o quadro de Recomendações aparecerá sugerindo novos livros.

Testes de Código: No terminal do backend, rode mvn test para validar as regras de serviço com JUnit e Mockito.
