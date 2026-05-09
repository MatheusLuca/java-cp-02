package br.com.fiap.locadora.service;

import br.com.fiap.locadora.model.Veiculo;

import java.util.ArrayList;

/**
 * Operações de listagem, busca por modelo e exclusão por placa para {@link Veiculo}.
 */
public class VeiculoService {

    /**
     * Gera texto com o relatório de todos os veículos cadastrados.
     *
     * <p>Cada veículo é formatado por {@link Veiculo#exibirInformacaoVeiculo()}.</p>
     *
     * @param lista frota em memória
     * @return string com um bloco por veículo, separados por nova linha
     */
    public String veiculoParaLocacao(ArrayList<Veiculo> lista) {
        String resultado = "";
        for (Veiculo v : lista) {
            resultado += v.exibirInformacaoVeiculo() + "\n";
        }
        return resultado;
    }

    /**
     * Localiza o primeiro veículo cujo modelo é igual ao informado, sem diferenciar maiúsculas e minúsculas.
     *
     * <p>Não valida aqui o atributo {@code disponivel}; a regra de negócio complementar fica na camada de visão.</p>
     *
     * @param lista frota onde a busca ocorre
     * @param nomeVeiculo modelo digitado pelo usuário
     * @return o {@code Veiculo} encontrado ou {@code null} se não existir modelo correspondente
     */
    public Veiculo veiculoDisponivel(ArrayList<Veiculo> lista, String nomeVeiculo) {
        for (Veiculo v : lista) {
            if (v.getModelo().equalsIgnoreCase(nomeVeiculo)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Remove o veículo cuja placa coincide com o parâmetro (comparação sem diferenciar caixa).
     *
     * @param lista frota mutável
     * @param placa placa informada para exclusão
     * @return {@code true} se algum item foi removido; {@code false} se nenhuma placa coincidir
     */
    public boolean excluirVeiculo(ArrayList<Veiculo> lista, String placa) {
        return lista.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
    }
}
