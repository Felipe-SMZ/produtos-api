# 🛍️ Produtos API

API REST para gerenciamento de produtos, desenvolvida com Java Spring Boot seguindo a arquitetura MVC com boas práticas de mercado.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido com o objetivo de demonstrar a construção de uma API REST completa, contemplando autenticação de camadas, validação de dados, tratamento de erros e testes unitários.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **Spring Validation**
- **MySQL 8.0**
- **Docker & Docker Compose**
- **JUnit 5 & Mockito**
- **Maven**

## 🏗️ Arquitetura

```
src/
├── controller/     → Recebe requisições HTTP e devolve respostas
├── service/        → Regras de negócio
├── repository/     → Comunicação com o banco de dados
├── model/          → Entidades JPA
├── dto/
│   ├── request/    → Dados recebidos da API
│   └── response/   → Dados devolvidos pela API
├── exception/      → Exceções personalizadas
└── handler/        → Tratamento global de erros
```

## ⚙️ Pré-requisitos

- [Docker](https://www.docker.com/) instalado e rodando
- [Insomnia](https://insomnia.rest/) ou Postman para testar os endpoints

## 🐳 Como Rodar

**1. Clone o repositório**

```bash
git clone https://github.com/Felipe-SMZ/produtos-api.git
cd produtos-api
```

**2. Configure as variáveis de ambiente**

Copie o arquivo de exemplo e preencha com seus dados:

```bash
cp application.properties.example src/main/resources/application.properties
```

**3. Gere o jar**

```bash
./mvnw clean package -DskipTests
```

**4. Suba os containers**

```bash
docker-compose up --build
```

A aplicação estará disponível em `http://localhost:8080`.

O banco de dados MySQL estará disponível na porta `3307`.

## 📡 Endpoints

### Produtos

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `POST` | `/produtos` | Cria um novo produto | 201 |
| `GET` | `/produtos` | Lista todos os produtos | 200 |
| `GET` | `/produtos/{id}` | Busca produto por ID | 200 |
| `PUT` | `/produtos/{id}` | Atualiza um produto | 200 |
| `DELETE` | `/produtos/{id}` | Remove um produto | 204 |

---

### POST /produtos

**Request body:**
```json
{
  "nome": "Teclado Mecânico",
  "preco": 299.90,
  "quantidade": 10
}
```

**Response 201:**
```json
{
  "id": 1,
  "nome": "Teclado Mecânico",
  "preco": 299.90,
  "quantidade": 10
}
```

---

### GET /produtos

**Response 200:**
```json
[
  {
    "id": 1,
    "nome": "Teclado Mecânico",
    "preco": 299.90,
    "quantidade": 10
  }
]
```

---

### GET /produtos/{id}

**Response 200:**
```json
{
  "id": 1,
  "nome": "Teclado Mecânico",
  "preco": 299.90,
  "quantidade": 10
}
```

**Response 404 — produto não encontrado:**
```json
{
  "erro": "Produto não encontrado com id: 99"
}
```

---

### PUT /produtos/{id}

**Request body:**
```json
{
  "nome": "Teclado Mecânico Barato",
  "preco": 100.00,
  "quantidade": 10
}
```

**Response 200:**
```json
{
  "id": 1,
  "nome": "Teclado Mecânico Barato",
  "preco": 100.00,
  "quantidade": 10
}
```

---

### DELETE /produtos/{id}

**Response 204:** sem corpo

---

## ✅ Validações

Os campos são validados automaticamente. Caso algum dado seja inválido, a API retorna `400 Bad Request` com as mensagens de erro:

**Request inválido:**
```json
{
  "nome": "",
  "preco": -10,
  "quantidade": -1
}
```

**Response 400:**
```json
{
  "nome": "Nome é obrigatório",
  "preco": "Preço deve ser maior que zero",
  "quantidade": "Quantidade não pode ser negativa"
}
```

## 🧪 Testes

Para rodar os testes unitários:

```bash
./mvnw test
```

Cobertura atual: **6 testes unitários** no `ProdutoService` cobrindo todos os cenários — incluindo casos de sucesso e exceções.

## 📁 Variáveis de Ambiente

| Variável | Descrição | Padrão |
|----------|-----------|--------|
| `DB_USERNAME` | Usuário do banco | `root` |
| `DB_PASSWORD` | Senha do banco | — |
| `SPRING_DATASOURCE_URL` | URL de conexão | `jdbc:mysql://localhost:3306/produtos_db` |

## 👨‍💻 Autor

**Felipe SMZ**  
[GitHub](https://github.com/Felipe-SMZ)