package br.com.fiap.locadora.service;

public class MenuService {

    public String boasVindas() {
        return """

                ============================================================
                  Bem-vindo ao sistema da Locadora FIAP
                ============================================================
                """.stripIndent();
    }

    public String mostrarMenu() {
        return """

                ============================================================
                  MENU PRINCIPAL
                ============================================================
                  [1]  Cadastrar veículo
                  [2]  Cadastrar cliente
                  [3]  Efetivar locação
                  [4]  Listar veículos cadastrados
                  [5]  Listar clientes cadastrados
                  [6]  Excluir veículo (por placa)
                  [7]  Remover cliente (por nome)
                  [8]  Remover locação
                  [9]  Exibir todas as locacões
                  [10] Encerrar programa
                ------------------------------------------------------------
                  Digite o número da opção:
                """.stripIndent();
    }

}
