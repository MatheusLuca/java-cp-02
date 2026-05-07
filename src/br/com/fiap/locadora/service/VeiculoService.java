package br.com.fiap.locadora.service;

import br.com.fiap.locadora.model.Veiculo;

import java.util.ArrayList;

public class VeiculoService {

    public String veiculoParaLocacao(ArrayList<Veiculo> lista) {
        String resultado = "";
        for (Veiculo v : lista) {
            resultado += v.exibirInformacaoVeiculo() + "\n";
        }
        return resultado;
    }

    public Veiculo veiculoDisponivel(ArrayList<Veiculo> lista, String nomeVeiculo) {
        for (Veiculo v : lista) {
            if (v.getModelo().equalsIgnoreCase(nomeVeiculo)) {
                return v;
            }
        }
        return null;
    }
}

