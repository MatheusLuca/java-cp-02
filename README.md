# Locadora FIAP — Sistema console em Java

Projeto acadêmico de **orientação a objetos** para gestão simplificada de uma locadora: cadastro de veículos e clientes, registro de locações em memória e exibição de dados via menu no terminal.

## Requisitos

- **Java 17** ou superior (text blocks, `stripIndent`, `String.formatted`, etc.)
- IDE com suporte a projetos Java (IntelliJ IDEA recomendado; o repositório inclui `.iml` e `.idea`) ou compilação por linha de comando

## Estrutura de pacotes

| Pacote | Responsabilidade |
|--------|------------------|
| `br.com.fiap.locadora.model` | Entidades: `Cliente`, `Veiculo`, `Locacao` |
| `br.com.fiap.locadora.service` | Regras auxiliares: busca, listagem, validação de locação, texto do menu |
| `br.com.fiap.locadora.view` | Ponto de entrada: `Main` com `Scanner` e fluxo do menu |

Documentação técnica detalhada: [docs/DOCUMENTACAO.md](docs/DOCUMENTACAO.md).

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

### Opções do menu

1. Cadastrar veículo  
2. Cadastrar cliente  
3. Efetivar locação  
4. Listar veículos cadastrados  
5. Listar clientes cadastrados  
10. Sair  

## Observações

- Dados ficam apenas **em memória**; ao encerrar o programa, listas são perdidas.
- A regra de **um veículo por cliente por vez** usa a lista de locações e comparação de CPF; veja limitações em [docs/DOCUMENTACAO.md](docs/DOCUMENTACAO.md).

## Licença e contexto

Trabalho voltado ao curso **FIAP** (Checkpoint Java / POO). Ajuste autores e disciplina conforme a entrega do professor.
