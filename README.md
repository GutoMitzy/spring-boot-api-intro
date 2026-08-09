# Spring Boot API — Estudos e Fundamentos

Repositório criado para registrar e consolidar conhecimentos adquiridos no estudo do ecossistema **Spring Boot**, com foco no desenvolvimento de APIs REST, persistência de dados e segurança de aplicações.

O projeto acompanha a evolução dos estudos desde conceitos fundamentais do **Spring Web** até a implementação de autenticação e autorização utilizando **Spring Security**.

O objetivo principal deste repositório não é disponibilizar uma aplicação para uso pela comunidade, mas servir como **registro pessoal de aprendizado e consulta futura**, reunindo conceitos, estruturas e práticas importantes do desenvolvimento backend com Java e Spring.

---

## Conteúdos estudados

### Spring Web

Primeira etapa do projeto, voltada para os fundamentos do desenvolvimento de APIs REST com Spring Boot.

Principais conceitos:

* Estrutura de uma aplicação Spring Boot
* Arquitetura em camadas
* Controllers
* Services
* DTOs
* Models/Entities
* HTTP Methods
* HTTP Status Codes
* CRUD
* Injeção de dependências
* Tratamento de exceções
* `@RestController`
* `@RequestMapping`
* `@GetMapping`
* `@PostMapping`
* `@PutMapping`
* `@DeleteMapping`
* `@RequestBody`
* `@PathVariable`
* `@RequestParam`
* `ResponseEntity`
* `@ExceptionHandler`
* `@RestControllerAdvice`

Também foram estudados conceitos relacionados à organização e separação de responsabilidades dentro de uma aplicação backend.

---

## Spring Data JPA

A segunda etapa introduz a persistência de dados utilizando **Spring Data JPA**, permitindo trabalhar com bancos de dados relacionais através do mapeamento objeto-relacional.

### Principais conceitos

* JPA
* Hibernate
* Entidades
* ORM (Object-Relational Mapping)
* Repositories
* `JpaRepository`
* Relacionamentos entre entidades
* `@OneToOne`
* `@OneToMany`
* `@ManyToOne`
* `@ManyToMany`
* Chaves primárias e estrangeiras
* Paginação
* Transações
* Bean Validation
* JPQL
* Native Query
* Cascade
* Fetch Type
* Lazy Loading
* Eager Loading

### Paginação

Estudo da utilização de paginação para evitar o carregamento de grandes quantidades de registros de uma única vez.

O conceito envolve:

* `Page`
* `Pageable`
* Ordenação
* Número da página
* Tamanho da página

---

### Transações

Estudo do conceito de transação e da utilização de `@Transactional` para garantir que operações relacionadas ao banco de dados sejam executadas dentro de um contexto transacional.

O principal objetivo é preservar a **consistência dos dados**, permitindo que um conjunto de operações seja confirmado ou revertido de maneira controlada.

---

### Bean Validation

Utilização das validações fornecidas pelo Jakarta Bean Validation para garantir que os dados recebidos pela aplicação atendam às regras definidas.

Conceitos estudados:

* `@NotNull`
* `@NotBlank`
* `@Size`
* `@Email`
* `@Valid`
* Validação de objetos recebidos pela API

---

### JPQL e Native Query

Estudo das duas formas de criação de consultas personalizadas.

**JPQL (Java Persistence Query Language)** trabalha com as entidades e seus atributos, abstraindo detalhes específicos do banco de dados.

**Native Query** permite utilizar diretamente a linguagem SQL do banco de dados, sendo útil quando uma consulta específica não é convenientemente representada através de JPQL.

---

### Cascade

Estudo do comportamento de operações realizadas em entidades relacionadas.

O Cascade permite definir se determinadas operações realizadas em uma entidade devem ser propagadas para suas entidades relacionadas.

Exemplos:

* Persistência
* Atualização
* Remoção

---

### Fetch Type

Estudo da estratégia utilizada pelo JPA para carregar entidades relacionadas.

**LAZY**

Os dados relacionados são carregados somente quando necessários.

**EAGER**

Os dados relacionados são carregados junto com a entidade principal.

A escolha da estratégia de carregamento possui impacto direto no comportamento e na performance da aplicação.

---

# Spring Security

A terceira etapa do projeto é dedicada à segurança da aplicação utilizando **Spring Security**.

Foram estudados os principais mecanismos necessários para implementar autenticação e autorização em uma API REST.

## Principais conceitos

* Spring Security
* Authentication
* Authorization
* Roles
* Password Encoding
* BCrypt
* Tokens
* JWT
* Security Filter Chain
* Login
* Register
* `@PreAuthorize`
* Proteção de endpoints
* Controle de acesso baseado em roles

---

## Autenticação

A autenticação é responsável por verificar **quem é o usuário**.

No projeto, foi estudado um fluxo baseado em credenciais, no qual o usuário realiza login e a aplicação valida suas informações antes de permitir o acesso aos recursos protegidos.

O processo envolve conceitos como:

1. Recebimento das credenciais
2. Localização do usuário
3. Verificação da senha
4. Geração de token
5. Utilização do token em requisições posteriores

---

## BCrypt

Senhas não devem ser armazenadas diretamente no banco de dados.

Foi estudada a utilização do **BCrypt** para realizar o hashing das senhas antes de sua persistência.

O princípio é:

```text
Senha original
      ↓
   BCrypt
      ↓
Hash armazenado
```

Durante a autenticação, a senha fornecida pelo usuário é comparada com o hash armazenado, sem necessidade de recuperar a senha original.

---

## Token

Após a autenticação, o usuário recebe um token que representa sua sessão/autorização.

O token pode ser enviado nas requisições subsequentes para que a aplicação consiga identificar o usuário autenticado.

Conceitualmente:

```text
Login
  ↓
Validação das credenciais
  ↓
Token
  ↓
Requisições autenticadas
  ↓
Validação do token
  ↓
Acesso ao recurso
```

---

## Security Filter Chain

A **Security Filter Chain** é um dos principais componentes do Spring Security.

Ela intercepta as requisições HTTP antes que elas cheguem aos controllers e permite aplicar as regras de segurança definidas para a aplicação.

Entre suas responsabilidades estão:

* Interceptar requisições
* Verificar autenticação
* Processar credenciais
* Validar tokens
* Aplicar regras de autorização
* Permitir ou bloquear requisições

O conceito é fundamental para entender como o Spring Security controla o acesso aos endpoints.

---

## Roles

Roles representam diferentes níveis de acesso dentro da aplicação.

Por exemplo:

```text
USER
ADMIN
```

Uma aplicação pode utilizar essas roles para determinar quais recursos cada usuário pode acessar.

Isso permite implementar **controle de acesso baseado em funções (RBAC — Role-Based Access Control)**.

---

## `@PreAuthorize`

O `@PreAuthorize` permite definir regras de autorização diretamente nos métodos da aplicação.

Dessa forma, o acesso a determinada operação pode depender da role ou autoridade do usuário autenticado.

Conceitualmente:

```text
Usuário autenticado
        ↓
Verificação da autoridade
        ↓
Possui permissão?
   ↙           ↘
 Sim           Não
  ↓             ↓
Executa      Acesso negado
```

Esse mecanismo permite aplicar autorização de maneira declarativa nos métodos protegidos.

---

# Arquitetura estudada

O projeto utiliza uma organização baseada na separação de responsabilidades:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Com objetos auxiliares para representar os dados:

```text
Request
  ↓
DTO
  ↓
Service
  ↓
Entity
  ↓
Repository
  ↓
Database
```

E, no fluxo de segurança:

```text
Request
   ↓
Security Filter Chain
   ↓
Authentication
   ↓
Authorization
   ↓
Controller
```

Essa separação permite manter responsabilidades distintas e facilita a manutenção e evolução da aplicação.

---

# Tecnologias utilizadas

| Tecnologia                    | Utilização                            |
| ----------------------------- | ------------------------------------- |
| **Java**                      | Linguagem principal                   |
| **Spring Boot**               | Framework base da aplicação           |
| **Spring Web**                | Desenvolvimento da API REST           |
| **Spring Data JPA**           | Persistência e acesso aos dados       |
| **Hibernate**                 | Implementação JPA / ORM               |
| **Spring Security**           | Autenticação e autorização            |
| **BCrypt**                    | Hashing de senhas                     |
| **JWT/Token**                 | Autenticação baseada em token         |
| **Jakarta Bean Validation**   | Validação de dados                    |
| **JPQL**                      | Consultas orientadas às entidades     |
| **SQL / Native Query**        | Consultas SQL específicas             |
| **Banco de dados relacional** | Persistência das informações          |
| **Maven**                     | Gerenciamento de dependências e build |
| **Lombok**                    | Redução de código boilerplate         |

---
---

# Objetivo do repositório

Este projeto funciona como um **registro prático dos estudos de Spring Boot e desenvolvimento backend com Java**.

A implementação serve principalmente para transformar conceitos teóricos em código e criar uma referência pessoal para consultas futuras.

O foco do repositório está nos **conceitos, arquitetura e tecnologias utilizadas**, e não na construção de uma aplicação destinada a produção ou distribuição.
