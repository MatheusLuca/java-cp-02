package br.com.fiap.locadora.service;

import br.com.fiap.locadora.model.Cliente;
import br.com.fiap.locadora.model.Locacao;

import java.util.ArrayList;

/**
 * Regras sobre a coleção de {@link Locacao}: unicidade de locação por CPF, busca, listagem e devolução.
 */
public class LocacaoService {

    /**
     * Indica se o cliente ainda pode registrar uma nova locação na lista atual.
     *
     * <p>Percorre {@code lista} e compara o CPF do {@code cliente} com o CPF do cliente de cada locação
     * ({@code equalsIgnoreCase}). Se existir qualquer locação com o mesmo CPF, retorna {@code false}
     * (cliente já possui locação ativa na lista). Se nenhuma coincidir, retorna {@code true}.</p>
     *
     * @param lista locações registradas na execução
     * @param cliente locatário candidato a nova locação
     * @return {@code true} se não houver locação com o mesmo CPF; {@code false} se já houver
     */
    public boolean verificarSeClienteJaLocou(ArrayList<Locacao> lista, Cliente cliente) {
        for (Locacao l : lista) {
            if (l.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna a primeira locação cujo cliente possui CPF igual ao parâmetro (ignorando caixa).
     *
     * <p>Usada, por exemplo, para exibir qual veículo o cliente já tem locado quando uma nova locação é bloqueada.</p>
     *
     * @param lista locações cadastradas
     * @param cpf identificador do cliente
     * @return a {@code Locacao} encontrada ou {@code null} se o CPF não aparecer em nenhuma locação
     */
    public Locacao encontrarLocacaoPorCpf(ArrayList<Locacao> lista, String cpf) {
        for (Locacao l : lista) {
            if (l.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Concatena a representação textual de todas as locações.
     *
     * <p>Cada entrada usa {@link Locacao#imprimirLocacao()}.</p>
     *
     * @param lista todas as locações em memória
     * @return texto acumulado (string vazia se a lista estiver vazia)
     */
    public String exibirLocacoes(ArrayList<Locacao> lista) {
        String locacoes = "";

        for (Locacao l : lista) {
            locacoes += l.imprimirLocacao() + "\n";
        }
        return locacoes;
    }

    /**
     * Finaliza a locação do cliente identificado pelo CPF e libera o veículo para nova locação.
     *
     * <p>Localiza a primeira locação com CPF correspondente, define o veículo como disponível
     * ({@link br.com.fiap.locadora.model.Veiculo#setDisponivel(boolean)}) e remove a locação da lista.
     * Se não houver CPF correspondente, a lista permanece inalterada.</p>
     *
     * @param lista coleção mutável de locações
     * @param cpf CPF do cliente que está devolvendo o veículo
     */
    public void removerLocacao(ArrayList<Locacao> lista, String cpf) {

        for (int i = 0; i < lista.size(); i++) {

            Locacao l = lista.get(i);

            if (l.getCliente().getCpf().equalsIgnoreCase(cpf)) {

                l.getVeiculo().setDisponivel(true);
                lista.remove(i);
                break;
            }
        }
    }
}

