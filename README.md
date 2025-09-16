# Sistema Comex - API de Gerenciamento de Produtos e Categorias 🛒

Sistema desenvolvido em **Spring Boot** para gerenciamento de produtos e categorias com **CRUD completo**, **validações**, **soft delete** e **auditoria**.

## 🎯 Tarefas Implementadas

### ✅ **Tarefa 1: Endpoint POST /api/categorias**

**Implementação:**
- Endpoint: `POST /api/categorias`
- Validações: Nome mínimo 2 caracteres
- Status padrão: ATIVA (boolean `ativo = true`)

**Exemplo de uso:**
```json
POST /api/categorias
{
  "nome": "Eletrônicos"
}
```

**Resposta:**
```json
{
  "success": true,
  "message": "Categoria criada com sucesso",
  "data": {
    "id": 1,
    "nome": "Eletrônicos",
    "ativo": true
  }
}
```

### ✅ **Tarefa 2: Endpoint POST /api/produtos**

**Implementação:**
- Endpoint: `POST /api/produtos`
- Validações: Nome mínimo 2 caracteres, preço positivo, categoria válida
- Descrição opcional, demais campos obrigatórios

**Exemplo de uso:**
```json
POST /api/produtos
{
  "nome": "Smartphone",
  "preco": 1500.00,
  "descricao": "Smartphone Android",
  "quantidadeEstoque": 10,
  "categoriaId": 1
}
```

### ✅ **Tarefa 3: CategoriaRepository com Spring Data JPA**

**Implementação:**
```java
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // Query Methods - Spring Data gera automaticamente
    List<Categoria> findByAtivo(boolean ativo);
    Page<Categoria> findByAtivo(boolean ativo, Pageable pageable);
    Optional<Categoria> findByIdAndAtivo(Long id, boolean ativo);
    boolean existsByNomeAndAtivo(String nome, boolean ativo);
}
```

**Vantagens:**
- ✅ **Query Methods**: Spring Data gera SQL automaticamente
- ✅ **Paginação**: Suporte nativo com `Pageable`
- ✅ **Type Safety**: Métodos tipados e seguros

### ✅ **Tarefa 4: ProdutoRepository com Spring Data JPA**

**Implementação:**
```java
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Query Methods automáticos
    List<Produto> findByAtivo(boolean ativo);
    Page<Produto> findByAtivo(boolean ativo, Pageable pageable);
    Optional<Produto> findByIdAndAtivo(Long id, boolean ativo);
}
```

## 🚀 Funcionalidades Extras

- ✅ **CRUD Completo** - CREATE, READ, UPDATE, DELETE, REATIVAR
- ✅ **Paginação** - Endpoints com suporte a paginação
- ✅ **Padrão Mapper** - Conversões DTO ↔ Entity centralizadas
- ✅ **Migrations Flyway** - Controle de versão do banco
- ✅ **Auditoria JPA** - Campos createdAt/updatedAt automáticos
- ✅ **Tratamento de Exceções** - GlobalExceptionHandler
- ✅ **Testes Unitários** - JUnit 5 + Mockito
- ✅ **Soft Delete** - Desativação com `ativo = false`
- ✅ **Validações** - Bean Validation
- ✅ **Logs Estruturados** - SLF4J
- ✅ **Swagger** - Documentação automática da API

## 🛠️ Tecnologias

- **Spring Boot 3.5.5** + **Spring Data JPA** + **PostgreSQL**
- **Flyway** + **Lombok** + **Swagger** + **JUnit 5** + **Mockito**

## 🏃♂️ Como Executar

1. **Configurar PostgreSQL:**
   ```sql
   CREATE DATABASE comex;
   CREATE USER admin WITH PASSWORD 'admin';
   ```

2. **Executar:**
   ```bash
   mvn spring-boot:run
   ```

3. **Acessar:**
   - API: `http://localhost:8091/api`
   - Swagger: `http://localhost:8091/swagger-ui/index.html`

4. **Testes:**
   ```bash
   mvn test
   ```