package br.com.fiap.locadora.model;

public class Veiculo {

    private String modelo;
    private String placa;
    private int ano;
    private String cor;
    private String fabricante;

    public Veiculo(String modelo, String placa, int ano, String cor, String fabricante) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.cor = cor;
        this.fabricante = fabricante;
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

    public String exibirInformacaoVeiculo(){
        return """
                modelo: %s
                placa: %s
                ano: %d
                cor: %s
                fabricante: %s
                """.formatted(this.modelo, this.placa ,this.ano, this.cor, this.fabricante);
    }



}
