package br.com.fiap.locadora.service;

import br.com.fiap.locadora.model.Cliente;
import br.com.fiap.locadora.model.Veiculo;

import java.util.ArrayList;

public class ClienteService {

    public String mostrarUsuariosCadastrados(ArrayList<Cliente> lista){
        String usuario = "";

        for(Cliente u : lista){
            usuario += u.exibirInformacaoCliente() + "\n";
        }

        return usuario;

    }

}
