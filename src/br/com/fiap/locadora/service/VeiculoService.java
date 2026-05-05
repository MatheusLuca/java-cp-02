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
}