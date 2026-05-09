package br.com.fiap.locadora.service;

import br.com.fiap.locadora.model.Cliente;

import java.util.ArrayList;

public class ClienteService {

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

    public Cliente buscarUsuarioParaLocacao(ArrayList<Cliente> lista, String nome) {
        for (Cliente u : lista) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }

    public boolean removerCliente(ArrayList<Cliente> lista, String nome) {
        return lista.removeIf(c -> c.getNome().equalsIgnoreCase(nome));
    }

}
