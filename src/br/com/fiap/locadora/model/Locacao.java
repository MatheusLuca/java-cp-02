package br.com.fiap.locadora.model;

import java.time.LocalDate;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private boolean ativa;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Locacao(Cliente cliente, Veiculo veiculo, boolean ativa, LocalDate dataInicio, LocalDate dataFim) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.ativa = ativa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }




}
