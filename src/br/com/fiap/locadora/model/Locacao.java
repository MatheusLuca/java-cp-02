package br.com.fiap.locadora.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Associa um {@link Cliente} a um {@link Veiculo} em um intervalo de datas de locação.
 */
public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    /** Formatador compartilhado para exibir período em {@code dd/MM/yyyy}. */
    private static final DateTimeFormatter BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    /**
     * Cria a locação ligando cliente, veículo e datas de início e fim previstas.
     *
     * @param cliente quem loca
     * @param veiculo unidade locada
     * @param dataInicio início do período
     * @param dataFim data prevista de devolução
     */
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


    /**
     * Monta relatório da locação: período formatado e blocos indentados de cliente e veículo.
     *
     * <p>Reutiliza {@link Cliente#exibirInformacaoCliente()} e {@link Veiculo#exibirInformacaoVeiculo()}.</p>
     *
     * @return string multilinha para impressão no console
     */
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
