package br.com.fiap.locadora.service;

/**
 * Textos fixos de boas-vindas e de opções do menu principal do console.
 */
public class MenuService {

    /**
     * Mensagem inicial exibida ao usuário ao abrir o sistema (banner de boas-vindas).
     *
     * @return bloco de texto formatado com {@code stripIndent}
     */
    public String mensgemInicial() {
        return """
                
                ============================================================
                  Bem-vindo ao sistema da Locadora FIAP
                ============================================================
                """.stripIndent();
    }

    /**
     * Retorna o menu principal numerado com todas as operações disponíveis no programa.
     *
     * <p>Inclui cadastros, locação, listagens, exclusões, devolução e encerramento.</p>
     *
     * @return bloco de texto do menu pronto para {@code System.out}
     */
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
