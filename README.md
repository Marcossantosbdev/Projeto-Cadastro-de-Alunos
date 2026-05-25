# 🎓 Sistema de Cadastro de Alunos (PBL - Integrado)

Este projeto é um sistema monolítico integrado desenvolvido em conformidade com as diretrizes do projeto PBL. A aplicação une uma interface web dinâmica (Front-end), uma API REST estruturada com Spring Boot (Back-end) e persistência de dados em tempo real com MySQL (Banco de Dados).

---

## 👥 Estrutura de Contribuição do Grupo

Para a execução deste projeto, a equipe dividiu-se em frentes de desenvolvimento e consolidação:
* **Membro A:** Desenvolvimento e blindagem do Back-end (Spring Boot, DTOs, Validações de Negócio com Bean Validation e Tratamento de Erros).
* **Membro B:** Criação da interface de usuário Front-end (HTML5 semântico, estilização CSS3 e interatividade nativa).
* **Membro C (Minha Contribuição):** Responsável pelo Fork do repositório, integração de sistemas (Front + Back + Banco), mapeamento e compatibilidade de nomenclaturas de dados, implementação de máscaras no JavaScript, tratamento dinâmico de erros da API na interface, testes de fumaça das operações CRUD e homologação do banco de dados MySQL.

---

## 🛠️ Tecnologias Utilizadas

### Front-end
* **HTML5:** Estrutura semântica dos formulários de cadastro e tabelas de exibição.
* **CSS3:** Layout responsivo e estilização visual dos componentes.
* **JavaScript (ES6):** Manipulação assíncrona do DOM (`fetch` API), controle de concorrência (`async/await`), máscaras de campos e tratamento dinâmico de retornos de erro.

### Back-end
* **Java 17 & Spring Boot 3:** Arquitetura de microsserviços/monolito integrado.
* **Spring Data JPA:** Abstração da camada de persistência e comunicação com o banco de dados.
* **Bean Validation:** Validação de integridade de dados na camada de controle.

### Banco de Dados
* **MySQL:** Banco de dados relacional para persistência de entidades.

---

## 🚀 Instruções de Execução e Configuração (Guia do Professor)

Siga os passos abaixo para rodar e testar a aplicação localmente de forma integrada.

### 1. Pré-requisitos
* **Java JDK 17** ou superior instalado.
* **MySQL Server** ativo (via XAMPP, WampServer ou instalação nativa na porta `3306`).
* Uma IDE de sua preferência (IntelliJ IDEA, Eclipse ou VS Code).

### 2. Configuração do Banco de Dados
Crie o banco de dados no seu console MySQL ou ferramenta de gerenciamento (MySQL Workbench / phpMyAdmin):

```sql
CREATE DATABASE IF NOT EXISTS cadastro_alunos;
USE cadastro_alunos;

CREATE TABLE IF NOT EXISTS aluno(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	
    nome VARCHAR(255) NOT NULL,
    
    matricula VARCHAR(255) NOT NULL UNIQUE,

    email VARCHAR(255) NOT NULL UNIQUE,
    
    curso VARCHAR(255) NOT NULL,
    
    telefone VARCHAR(11) NOT NULL UNIQUE,
    
    endereco VARCHAR(255) NOT NULL,
    
    data_matricula DATE NOT NULL
) Engine=InnoDB;

SELECT * FROM aluno; /*Para verificação dos dados da tabela de maneira crua*/
```

### 3. Configuração do Back-end
Verifique se as credenciais do seu MySQL local coincidem com as configuradas no arquivo
src/main/resources/application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cadastro_alunos?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
### 4. Executando a Aplicação
Abra o projeto na sua IDE (IntelliJ IDEA ou Eclipse).

Localize a classe principal que contém a anotação @SpringBootApplication.

Execute (Run) o projeto. O servidor embutido Tomcat iniciará na porta 8080.

### 5. Acessando a Interface
Como o front-end foi acoplado estrategicamente dentro da estrutura de recursos estáticos do Spring Boot (src/main/resources/static), você não precisa rodar nenhum servidor Live Server à parte.

Basta abrir o seu navegador e acessar o endereço centralizado:
👉 http://localhost:8080/index.html

---

* **🧪 Casos de Teste Homologados (Fluxos Integrados)**
* Como integrador do sistema, validei com sucesso as seguintes operações ponta a ponta:

* **Operação de Cadastro (POST):** Envio dos campos mapeados pelo formulário convertidos em payload JSON para a rota /alunos.

* **Validação de Negócio Exclusiva:** Testes forçando preenchimentos incorretos ou duplicados. A interface intercepta o retorno 400 Bad Request da API (configurada com @RestControllerAdvice) e exibe as mensagens amigáveis disparadas pelo Java.

* **Persistência Inteligente:** A coluna de Data de Matrícula foi totalmente automatizada no banco de dados via TIMESTAMP DEFAULT CURRENT_TIMESTAMP, garantindo a integridade temporal do registro sem intervenção manual do usuário.

* **Listagem Dinâmica (GET):** Atualização instantânea da tabela via manipulação do DOM e tratamento de compatibilidade de propriedades (Entidade/DTO), sem necessidade de recarga forçada da página.

* **Edição Segura (PUT):** Recuperação de registros por ID, preenchimento automático dos inputs e atualização reativa.

* **Exclusão de Registros (DELETE):** Confirmação prévia no client e remoção do dado no MySQL.
