package br.com.fiap.locadora.service;

import br.com.fiap.locadora.model.Cliente;
import br.com.fiap.locadora.model.Locacao;

import java.util.ArrayList;

public class LocacaoService {

    public boolean verificarSeClienteJaLocou(ArrayList<Locacao> lista, Cliente cliente){
        for(Locacao l : lista){
            if(l.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf())){
               return false;
            }
        }
        return true;
    }

}
