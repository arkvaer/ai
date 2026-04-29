# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
# Build all modules
mvn compile

# Run tests (all modules)
mvn test

# Run a specific module's tests
mvn test -pl chat

# Run a single test class
mvn test -pl chat -Dtest=ChatApplicationTests

# Package all modules
mvn package -DskipTests

# Start a specific module
mvn spring-boot:run -pl chat
mvn spring-boot:run -pl ollama
mvn spring-boot:run -pl chat-model-vs-chat-client
```

## Architecture

Multi-module Maven project (Java 25, Spring Boot 3.5.14, Spring AI 1.1.x) experimenting with AI model integrations. The root POM (`com.arkvaer:ai`) manages shared dependencies and plugins; each submodule is an independent Spring Boot application.

### Modules

| Module | Port | Context Path | Purpose |
|--------|------|-------------|---------|
| `chat` | 8899 | `/api` | DashScope (Alibaba LLM) via `ChatModel` — sync and streaming chat |
| `ollama` | 8082 | `/ollama` | Local Ollama (`qwen3:4b`) via `ollamaChatModel` — streaming chat |
| `chat-model-vs-chat-client` | default | — | Experiment comparing ChatModel vs ChatClient APIs (skeleton) |

### Key Dependencies

- **Spring AI + Alibaba DashScope** (`spring-ai-alibaba-starter-dashscope`) — the primary AI integration
- **Spring AI Ollama** (`spring-ai-starter-model-ollama`) — local model support in the ollama module
- **Lombok** — annotation processing for boilerplate reduction
- **Project Reactor** (`Flux`) — reactive streaming responses

### Configuration Pattern

- API keys come from environment variables: `${AI_DASHSCOPE_API_KEY}`
- The `chat` module defines explicit `DashScopeApi` and `ChatClient` beans in `SaaLLMConfig`
- The `ollama` module relies on Spring AI auto-configuration (`spring.ai.ollama.*` properties)
- Each module has its own `application.yaml` with independent server port and context path
- The root `src/` module (`ai`) has a trivial config — it's not the primary application

### Endpoints

- `GET /api/chat/chatclientv2/dochat?msg=...` — synchronous DashScope chat (chat module)
- `GET /api/chat/chatclientv2/stream?msg=...` — streaming DashScope chat (chat module)
- `GET /ollama/chat/stream?msg=...` — streaming Ollama chat (ollama module)

### Injection Style

Uses `@Resource` (Jakarta) for field injection, not `@Autowired`. Constructor injection is preferred per project rules for new code.

### Environment

- Requires `AI_DASHSCOPE_API_KEY` environment variable for DashScope API access
- Ollama module expects Ollama running locally at `http://localhost:11434`
