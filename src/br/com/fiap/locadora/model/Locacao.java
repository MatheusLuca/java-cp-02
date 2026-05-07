package br.com.fiap.locadora.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private static final DateTimeFormatter BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Locacao(Cliente cliente, Veiculo veiculo,  LocalDate dataInicio, LocalDate dataFim) {
        this.cliente = cliente;
        this.veiculo = veiculo;
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


    public String imprimirLocacao() {
        String c = cliente.exibirInformacaoCliente().indent(4);
        String v = veiculo.exibirInformacaoVeiculo().indent(4);
        return """
            ═══ LOCAÇÃO ═══════════════════
            Período: %s  →  %s

            Cliente:
            %s
            Veículo:
            %s
            ═══════════════════════════════
            """.stripIndent().formatted(
                dataInicio.format(BR),
                dataFim.format(BR),
                c.stripTrailing(),
                v.stripTrailing()
        );
    }





}
