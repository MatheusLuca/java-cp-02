package br.com.fiap.locadora.service;

import br.com.fiap.locadora.model.Cliente;

import java.util.ArrayList;

/**
 * Operações de consulta, listagem e remoção de {@link Cliente} em coleções em memória.
 */
public class ClienteService {

    /**
     * Monta o texto formatado de todos os clientes da lista.
     *
     * <p>Para cada item chama {@link Cliente#exibirInformacaoCliente()} e concatena com quebra de linha.</p>
     *
     * @param lista coleção de clientes cadastrados
     * @return resumo textual dos clientes ou mensagem fixa se a lista estiver vazia
     */
    public String mostrarUsuariosCadastrados(ArrayList<Cliente> lista) {
        String usuario = "";
        if (!(lista.isEmpty())) {
            for (Cliente u : lista) {
                usuario += u.exibirInformacaoCliente() + "\n";
            }
        } else {
            return "Nenhum usuario cadastrado!";
        }
        return usuario;

    }

    /**
     * Retorna o primeiro cliente cujo nome coincide com o parâmetro, ignorando maiúsculas e minúsculas.
     *
     * <p>Utilizada antes de iniciar uma locação para validar se o locatário existe.</p>
     *
     * @param lista coleção onde a busca é feita
     * @param nome nome informado pelo usuário
     * @return referência ao {@code Cliente} encontrado ou {@code null} se não houver correspondência
     */
    public Cliente buscarUsuarioParaLocacao(ArrayList<Cliente> lista, String nome) {
        for (Cliente u : lista) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Remove da lista todos os clientes cujo nome coincide com o parâmetro (comparação sem diferenciar caixa).
     *
     * @param lista coleção mutável de clientes
     * @param nome nome utilizado como critério de exclusão
     * @return {@code true} se pelo menos um elemento foi removido; caso contrário {@code false}
     */
    public boolean removerCliente(ArrayList<Cliente> lista, String nome) {
        return lista.removeIf(c -> c.getNome().equalsIgnoreCase(nome));
    }

}
