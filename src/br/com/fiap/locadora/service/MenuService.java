package br.com.fiap.locadora.service;

public class MenuService {

    public String mostrarMenu(){
        return """
                Digite o numero correspondente ao menu\n
                (1) Cadastrar veiculo\n
                (2) Cadastrar cliente\n
                (3) Efetivar locação\n
                (4) Verificar carros para locacao\n
                (5) Verificar clientes cadastrados\n
                (10) Sair do programa\n
                """;
    }

}
