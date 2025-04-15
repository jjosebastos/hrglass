
# 🧊 HRGlass - Solução de RH

**HRGlass** é uma aplicação desenvolvida em **Java 17**, pensada para facilitar a gestão de Recursos Humanos. A solução utiliza tecnologias modernas do ecossistema Spring, proporcionando uma estrutura simples e eficiente para desenvolvimento, testes e documentação de APIs.

## 🚀 Tecnologias e Dependências

- **Java 17**
- **Spring Boot**
- **Lombok** – Redução de código boilerplate
- **springdoc-openapi** – Geração automática de documentação da API com Swagger UI
- **H2 Database** – Banco de dados em memória para testes locais

---

## 📦 Como executar o projeto

### ✅ Pré-requisitos

Certifique-se de ter instalado em sua máquina:

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- (Opcional) Um IDE como IntelliJ IDEA ou VS Code para facilitar o desenvolvimento

### 🔧 Passos para rodar o projeto

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/hrglass.git
cd hrglass
```

2. **Compile o projeto com Maven:**

```bash
./mvnw clean install
```

3. **Execute a aplicação:**

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080`.

---

## 📑 Acesso à documentação da API

Uma vez que a aplicação estiver rodando, acesse a interface Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

Lá você poderá visualizar e testar os endpoints disponíveis na aplicação.

---

## 🗃️ Banco de Dados H2

Durante a execução local, a aplicação utiliza o banco de dados H2 em memória.

Acesse o console H2:

```
http://localhost:8080/h2-console
```

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Usuário:** `sa`
- **Senha:** (em branco)

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Fique à vontade para abrir issues e pull requests com melhorias, correções ou sugestões.

---
