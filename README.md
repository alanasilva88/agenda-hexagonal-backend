# 🗂️ Agenda Telefônica API — Arquitetura Hexagonal (Ports & Adapters)

Este repositório contém o backend de uma **Agenda Telefônica** desenvolvida em **Java 17** e **Spring Boot 3**, estruturada sob os pilares da **Arquitetura Hexagonal (Ports & Adapters)**. O principal objetivo deste projeto é demonstrar a separação estrita de conceitos, onde as regras de negócio (Domínio) são completamente independentes de frameworks, drivers ou tecnologias de persistência externa.

---

## 🎯 Objetivos de Arquitetura & Design

* **Desacoplamento Total (Core Agnóstico):** O coração da aplicação (Domínio e Casos de Uso) é desenvolvido em Java Puro. Nenhuma anotação do Spring Framework (`@Service`, `@Autowired`, `@Transactional`) invade essas camadas.
* **Inversão de Dependência (SOLID):** A camada de aplicação não depende de implementações de banco de dados ou controllers. Ela dita as regras através de interfaces (*Ports*), e o mundo externo se adapta a elas (*Adapters*).
* **Manutenibilidade e Testabilidade:** Com as regras isoladas, testes unitários de domínio podem ser executados sem a necessidade de subir o contexto do Spring ou simular bancos de dados complexos.
* **Design de API RESTful:** Implementação de endpoints padronizados com os códigos de status HTTP corretos (201 Created para inserções, 204 No Content para deleções e manipulação limpa de `Optional<T>`).

---

## 🏗️ Estrutura de Pastas e Camadas

A árvore do projeto foi organizada minuciosamente para refletir os limites arquiteturais do hexágono:

```text
src/main/java/com/example/agenda_hexagonal/
│
├── domain/                               # 🧠 CORE: Regras de negócio puras (Java Puro)
│   ├── model/
│   │   └── Contato.java                  # Entidade de Domínio com validações de negócio
│   └── ports/
│       ├── in/
│       │   └── ContatoUseCase.java       # Interface (Porta de Entrada) que expõe os Casos de Uso
│       └── out/
│           └── ContatoRepositoryPort.java # Interface (Porta de Saída) que exige persistência
│
├── application/                          # ⚙️ MOTOR: Orquestração e Casos de Uso (Java Puro)
│   └── usecases/
│       └── ContatoServiceImpl.java       # Implementação do motor de negócios (Sem @Service)
│
└── infrastructure/                       # 🔌 MUNDO EXTERNAL: Frameworks e Tecnologias
    ├── adapters/
    │   ├── controllers/
    │   │   └── ContatoController.java    # Adaptador REST (Spring Web)
    │   ├── entities/
    │   │   └── ContatoEntity.java        # Mapeamento Relacional (Jakarta/JPA)
    │   └── repositories/
    │       ├── ContatoJpaRepository.java # Interface do Spring Data JPA
    │       └── ContatoRepositoryImpl.java# Adaptador de Banco (Implementa a Porta de Saída)
    └── config/
        └── BeanConfig.java               # Central de Configuração (Apresenta o Service puro ao Spring)

```

---

## 🔌 Fluxo de Execução da Arquitetura

O circuito funciona através de transformações de dados em cada fronteira do hexágono para manter as camadas blindadas:

1. **Entrada:** O cliente faz um request HTTP para o `ContatoController` (`/api/contatos`).
2. **Direcionamento:** O Controller chama a interface `ContatoUseCase` (Porta de Entrada).
3. **Processamento:** O `ContatoServiceImpl` processa a lógica de negócio e aciona a interface `ContatoRepositoryPort` (Porta de Saída).
4. **Conversão & Persistência:** O `ContatoRepositoryImpl` intercepta a chamada, transforma o objeto de Domínio (`Contato`) em uma Entidade de Banco (`ContatoEntity`) e grava fisicamente no banco em memória através do `ContatoJpaRepository`.

---

## 🛠️ Tecnologias e Bibliotecas Utilizadas

* **Java 17:** Versão LTS estável utilizando recursos modernos como a API de `Optional` e streams.
* **Spring Boot 3.x:** Utilizado estritamente como mecanismo de suporte na camada de infraestrutura.
* **Spring Data JPA & Hibernate:** Para gerenciamento, criação de tabelas e persistência automatizada.
* **H2 Database:** Banco de dados relacional em memória para agilidade nos testes de desenvolvimento.
* **Springdoc OpenAPI (Swagger UI):** Documentação interativa e automatizada da API para consumo do Front-end.

---

## 📋 Documentação e Testes da API (Swagger)

A API possui documentação interativa completa integrada com o Swagger. Com o servidor rodando, você pode acessar o painel para testar todos os endpoints visualmente:

* **URL do Swagger UI (Produção):** [Acessar Swagger na Nuvem](https://agenda-hexagonal-backend.onrender.com/swagger-ui/index.html)
* **URL do Swagger UI (local):** `http://localhost:8080/swagger-ui/index.html`
* **Console do Banco H2:** `http://localhost:8080/h2-console` *(JDBC URL: `jdbc:h2:mem:agenda`)*

### Endpoints Mapeados:

| Verbo HTTP | Endpoint | Descrição | Status Sucesso |
| --- | --- | --- | --- |
| **POST** | `/api/contatos` | Cria um novo contato no sistema | `201 Created` |
| **GET** | `/api/contatos` | Lista todos os contatos cadastrados | `200 OK` |
| **GET** | `/api/contatos/{id}` | Busca os detalhes de um contato por ID | `200 OK` / `404 Not Found` |
| **PUT** | `/api/contatos/{id}` | Atualiza os dados de um contato existente | `200 OK` / `404 Not Found` |
| **DELETE** | `/api/contatos/{id}` | Remove um contato definitivamente do banco | `204 No Content` |

---

## 💻 Conectando o Front-end

Esta API foi projetada para ser totalmente agnóstica e consome/produz dados em formato JSON. Ela está integrada e homologada para rodar em conjunto com o cliente web desenvolvido em **React.js, Vite e TypeScript**, possuindo configurações nativas de CORS para comunicação segura entre domínios.

## 🚀 Como Executar o Projeto

1. Certifique-se de ter o **Java 17** instalado na sua máquina.
2. Abra a pasta raiz do projeto no terminal e execute o Wrapper do Maven:
```bash
./mvnw spring-boot:run

```


3. A aplicação estará ativa na porta `8080`. Você pode validar o funcionamento disparando uma requisição ou acessando a rota de listagem no navegador: `http://localhost:8080/api/contatos`.

