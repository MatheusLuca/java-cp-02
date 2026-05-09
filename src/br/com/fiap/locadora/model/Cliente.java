package br.com.fiap.locadora.model;

/**
 * Representa o locatário: dados pessoais e identificação por CPF e CNH.
 */
public class Cliente {

    private String nome;
    private String cpf;
    private int idade;
    private String cnh;

    /**
     * Cria um cliente com os dados informados no cadastro.
     *
     * @param nome nome completo
     * @param cpf documento usado nas validações de locação
     * @param idade idade em anos
     * @param cnh identificação da carteira de habilitação
     */
    public Cliente(String nome, String cpf, int idade, String cnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.cnh = cnh;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * Monta um bloco ASCII com nome, CPF, idade e CNH para exibição no console.
     *
     * @return texto formatado (text block com {@code formatted})
     */
    public String exibirInformacaoCliente() {
        return """
            ┌── Cliente ──────────────────
            │ Nome . . . . . . . : %s
            │ CPF  . . . . . . . : %s
            │ Idade . . . . . . .: %d anos
            │ CNH  . . . . . . . : %s
            └──────────────────────────────
            """.stripIndent().formatted(nome, cpf, idade, cnh);
    }



}
