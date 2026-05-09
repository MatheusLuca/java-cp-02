package br.com.fiap.locadora.model;

/**
 * Veículo da frota com identificação, características e flag de disponibilidade para locação.
 */
public class Veiculo {

    private String modelo;
    private String placa;
    private int ano;
    private String cor;
    private String fabricante;
    private boolean disponivel;


    /**
     * Instancia um veículo disponível para locação ({@code disponivel == true}).
     *
     * @param modelo modelo (usado na busca ao locar)
     * @param placa placa do veículo
     * @param ano ano de fabricação
     * @param cor cor
     * @param fabricante fabricante
     */
    public Veiculo(String modelo, String placa, int ano, String cor, String fabricante) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.cor = cor;
        this.fabricante = fabricante;
        disponivel = true;

    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Exibe ficha do veículo com situação {@code Disponível} ou {@code Alugado} conforme o atributo {@code disponivel}.
     *
     * @return texto formatado para o console
     */
    public String exibirInformacaoVeiculo() {
        String status = disponivel ? "Disponível" : "Alugado";
        return """
            ┌── Veículo ──────────────────
            │ Modelo . . . . . . : %s
            │ Placa  . . . . . . : %s
            │ Ano  . . . . . . . : %d
            │ Cor  . . . . . . . : %s
            │ Fabricante . . . . : %s
            │ Situação . . . . . : %s
            └──────────────────────────────
            """.stripIndent().formatted(modelo, placa, ano, cor, fabricante, status);
    }


}
