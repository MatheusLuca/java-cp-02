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

    public Locacao encontrarLocacaoPorCpf(ArrayList<Locacao> lista, String cpf) {
        for (Locacao l : lista) {
            if (l.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                return l;
            }
        }
        return null;
    }
    public String exibirLocacoes(ArrayList<Locacao> lista){
        String locacoes = "";

        for(Locacao l : lista){
            locacoes += l.imprimirLocacao() + "\n";
        }
        return locacoes;
    }

    public String listarLocacaoIndisponiveis(ArrayList<Locacao> lista){
        String locacõesIndisponiveis = "";
        for(Locacao l : lista){
            if(!(l.getVeiculo().isDisponivel())){
                locacõesIndisponiveis += l.imprimirLocacao() + "\n";
            }
        }
        return locacõesIndisponiveis;
    }

    public void removerLocacao(ArrayList<Locacao> lista, String cpf){

        for(int i = 0; i < lista.size(); i++){

            Locacao l = lista.get(i);

            if(l.getCliente().getCpf().equalsIgnoreCase(cpf)){

                l.getVeiculo().setDisponivel(true);
                lista.remove(i);
                break;
            }
            }
        }
    }


