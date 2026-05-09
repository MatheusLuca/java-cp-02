# Locadora FIAP — Sistema console em Java

Projeto acadêmico de **orientação a objetos** para gestão simplificada de uma locadora: cadastro de veículos e clientes, registro e consulta de locações em memória, devolução (liberação do veículo), exclusão de cadastros e exibição de dados via menu no terminal.

## Requisitos

- **Java 17** ou superior (text blocks, `stripIndent`, `String.formatted`, etc.)
- IDE com suporte a projetos Java (IntelliJ IDEA recomendado; o repositório inclui `.iml` e `.idea`) ou compilação por linha de comando

## Estrutura de pacotes

| Pacote | Responsabilidade |
|--------|------------------|
| `br.com.fiap.locadora.model` | Entidades: `Cliente`, `Veiculo`, `Locacao` |
| `br.com.fiap.locadora.service` | Regras: menu, busca, listagem, validação de locação, exclusão de veículo/cliente, listagem e remoção de locações |
| `br.com.fiap.locadora.view` | Ponto de entrada: `Main` com `Scanner` e fluxo do menu |

Documentação técnica detalhada: [docs/DOCUMENTACAO.md](docs/DOCUMENTACAO.md).

## Funcionalidades atuais do sistema

- **Cadastro e listagem:** veículos e clientes com dados informados pelo usuário.
- **Locação:** busca de cliente por **nome** e veículo por **modelo** (comparação sem diferenciar maiúsculas/minúsculas); validação de disponibilidade do veículo e de **uma locação ativa por CPF** na lista em memória.
- **Datas:** início e devolução prevista no formato **`dd/MM/yyyy`** (use **`MM`** para mês; `mm` refere-se a minutos e quebra o parse com `LocalDate`).
- **Devolução / fim de locação (menu 8):** lista as locações, remove a locação pelo **CPF** do cliente e **marca o veículo como disponível** novamente.
- **Consulta de locações (menu 9):** exibe todas as locações registradas.
- **Manutenção da frota e cadastro:** exclusão de veículo por **placa** (menu 6) e remoção de cliente por **nome** (menu 7).

Atualizações recentes no código incluem padronização de estilo (espaçamento em `if`/`for`), correção da estrutura de chaves em `LocacaoService.removerLocacao` e remoção do método não utilizado `listarLocacaoIndisponiveis`, mantendo apenas `exibirLocacoes` para listagem geral.

## Como executar

### IntelliJ / Eclipse

Abra a pasta do projeto, marque `src` como raiz de código-fonte e execute a classe `br.com.fiap.locadora.view.Main`.

### Linha de comando (Windows PowerShell, a partir da raiz do repositório)

Compilar:

```powershell
javac -d out -encoding UTF-8 (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

Executar:

```powershell
java -cp out br.com.fiap.locadora.view.Main
```

## Uso do programa

1. Escolha a opção no menu (número inteiro).
2. Para **locação** (opção 3), informe o **nome** do cliente cadastrado e o **modelo** do veículo.
3. Datas de início e devolução no formato **`dd/MM/yyyy`** (o mês no padrão é **`MM`** maiúsculo; evite `mm`, que significa minutos e causa erro com `LocalDate`).
4. Para **devolver** veículo e encerrar a locação na lista (opção 8), use o **CPF** do cliente conforme exibido nas locações listadas.

### Opções do menu

| Opção | Ação |
|------:|------|
| 1 | Cadastrar veículo |
| 2 | Cadastrar cliente |
| 3 | Efetivar locação |
| 4 | Listar veículos cadastrados |
| 5 | Listar clientes cadastrados |
| 6 | Excluir veículo (por placa) |
| 7 | Remover cliente (por nome) |
| 8 | Remover locação (devolução — informa CPF; libera o veículo) |
| 9 | Exibir todas as locações |
| 10 | Encerrar programa |

## Observações

- Dados ficam apenas **em memória**; ao encerrar o programa, listas são perdidas.
- A regra de **um veículo por cliente por vez** na lista de locações usa comparação de **CPF**; após a **opção 8**, o cliente pode locar outro veículo na mesma execução, desde que a locação anterior tenha sido removida. Detalhes e limitações adicionais em [docs/DOCUMENTACAO.md](docs/DOCUMENTACAO.md) (nota: a doc pode estar desatualizada quanto à devolução — o fluxo da opção 8 já implementa liberação do veículo).

## Licença e contexto

Trabalho voltado ao curso **FIAP** (Checkpoint Java / POO). Ajuste autores e disciplina conforme a entrega do professor.
