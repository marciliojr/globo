# 🏆 Desafio Técnico — Serviço de Assinaturas

## 🚀 URLs e Acessos

| Serviço    | URL / Valor                              |
|------------|------------------------------------------|
| Swagger    | http://localhost:8081/swagger-ui.html    |
| MongoDB    | mongodb://localhost:27017/assinatura_db  |
| Kafka UI   | http://localhost:8080                    |

---

## 🛠️ Tecnologias

### Backend / Aplicação

| Tecnologia              | Uso no projeto                                                                 |
|-------------------------|--------------------------------------------------------------------------------|
| Java 21                 | Linguagem e runtime da aplicação                                               |
| Spring Boot 4.0.2       | Framework principal (configuração, injeção de dependência, execução)           |
| Spring Web MVC          | API REST (controllers e endpoints HTTP para usuários e assinaturas)            |
| Spring Data MongoDB     | Persistência: repositórios, conexão e operações no MongoDB                     |
| Spring Kafka            | Envio de mensagens para o Kafka (evento de cancelamento de assinatura)         |
| Jackson                 | Serialização/desserialização JSON (requests/responses da API e mensagens Kafka)|
| Lombok                  | Redução de boilerplate (getters/setters, construtores, etc.)                   |

### Documentação da API

| Tecnologia              | Uso no projeto                                                                 |
|-------------------------|--------------------------------------------------------------------------------|
| Springdoc OpenAPI 2.8.5 | Geração da spec OpenAPI 3 e Swagger UI para documentar e testar a API          |

### Infraestrutura / Serviços Externos

| Tecnologia      | Uso no projeto                                                                                       |
|-----------------|------------------------------------------------------------------------------------------------------|
| MongoDB         | Banco NoSQL: armazena usuários e assinaturas (coleções `usuario` e `assinatura`)                     |
| Apache Kafka    | Mensageria: publicação do evento de assinatura cancelada (tópico `assinatura.cancelada`)             |

### Build 

| Tecnologia | Uso no projeto                        |
|------------|---------------------------------------|
| Gradle     | Build, gerenciamento de dependências e tarefas |

# ✅ Requisitos Atendidos

## 1. Cadastro e Gerenciamento de Assinaturas

| Requisito | Status | Detalhes |
|-----------|--------|----------|
| API para cadastrar usuários | ✅ Atendido | `POST /usuarios` com body `{ "email", "nome" }` — salvo na collection `usuario` |
| API para criar assinatura | ✅ Atendido | `POST /assinaturas` com body `{ "idUsuario", "plano" }` — salvo na collection `assinatura` |
| Apenas uma assinatura ativa por usuário | ✅ Atendido | `CriarAssinaturaUseCase` verifica via `buscarAtivaPorUsuario`; lança `AssinaturaJaAtivaException` se já existir |
| Estrutura da assinatura | ✅ Atendido | Modelo com `id`, `usuarioId`, `plano`, `dataInicio`, `dataExpiracao`, `status` e `tentativasRenovacao` |
| Planos com valores definidos | ✅ Atendido | Enum `Plano`: BASICO (R$ 19,90) · PREMIUM (R$ 39,90) · FAMILIA (R$ 59,90) |

---

## 2. Renovação Automática

| Requisito | Status | Detalhes |
|-----------|--------|----------|
| Agendador no dia do vencimento | ✅ Atendido | `RenovacaoAutomaticaScheduler` com `@Scheduled(cron = "0 */30 * * * *")` — busca assinaturas com `dataExpiracao <= hoje` |
| Suspensão após 3 falhas de renovação | ✅ Atendido | `RenovarAssinaturaUseCase` chama `incrementarTentativaRenovacao()`; ao atingir 3 tentativas o status passa para `SUSPENSO` |

---

## 3. Cancelamento

| Requisito | Status | Detalhes |
|-----------|--------|----------|
| Endpoint para cancelar assinatura | ✅ Atendido | `PATCH /assinaturas/cancelar/{usuarioId}` |
| Uso até o fim do ciclo após cancelamento | ✅ Atendido | `cancelaAssinatura()` altera apenas o status para `CANCELADO`; `dataExpiracao` é preservada |

---

## 4. Regras de Negócio

| Regra | Status |
|-------|--------|
| Usuário pode ter apenas um plano ativo por vez | ✅ Atendido |
| Suspensão após 3 tentativas de renovação falhadas | ✅ Atendido |
| Renovação automática no dia do vencimento | ✅ Atendido |

---

## 5. Cobertura por Testes

| Requisito | Status | Detalhes |
|-----------|--------|----------|
| Testes unitários dos use cases | ❌ Não atendido | Nenhum teste implementado para `CriarAssinaturaUseCase`, `RenovarAssinaturaUseCase` e demais casos de uso |
| Testes dos endpoints REST | ❌ Não atendido | Sem testes para os controllers (`/usuarios`, `/assinaturas`) |
| Testes do agendador de renovação | ❌ Não atendido | `RenovacaoAutomaticaScheduler` sem cobertura de testes |
| Testes de regras de negócio | ❌ Não atendido | Cenários como assinatura duplicada, suspensão por falha e cancelamento sem cobertura |

