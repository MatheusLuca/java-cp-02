package br.com.fiap.locadora.view;

import br.com.fiap.locadora.model.Cliente;
import br.com.fiap.locadora.model.Locacao;
import br.com.fiap.locadora.model.Veiculo;
import br.com.fiap.locadora.service.ClienteService;
import br.com.fiap.locadora.service.LocacaoService;
import br.com.fiap.locadora.service.MenuService;
import br.com.fiap.locadora.service.VeiculoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ponto de entrada do sistema: menu em console, listas em memória e delegação às classes de serviço.
 *
 * <p>As operações de negócio (busca, regras de locação, formatação de menus) estão em
 * {@link MenuService}, {@link ClienteService}, {@link VeiculoService} e {@link LocacaoService}.</p>
 */
public class Main {

    /**
     * Inicia o {@link Scanner}, o formatador de datas {@code dd/MM/yyyy}, os serviços e o laço do menu até a opção 10.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        MenuService menu = new MenuService();
        VeiculoService veiculoService = new VeiculoService();
        ClienteService clienteService = new ClienteService();
        LocacaoService locacaoService = new LocacaoService();

        System.out.println(menu.mostrarMenu());

        int opcaoMenu = sc.nextInt();
        ArrayList<Cliente> clientesList = new ArrayList<>();
        ArrayList<Veiculo> veiculosList = new ArrayList<>();
        ArrayList<Locacao> locacaoList = new ArrayList<>();
        sc.nextLine();

        while (opcaoMenu != 10) {
            switch (opcaoMenu) {
                case 1:
                    System.out.println("Cadastrar veiculo ");
                    System.out.println("Modelo: ");
                    String veiculoModelo = sc.nextLine();
                    System.out.println("Placa: ");
                    String veiculoPlaca = sc.nextLine();
                    System.out.println("Ano: ");
                    int veiculoAno = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Cor: ");
                    String veiculoCor = sc.nextLine();
                    System.out.println("Fabricante: ");
                    String veiculoFabricante = sc.nextLine();

                    Veiculo veiculo = new Veiculo(veiculoModelo, veiculoPlaca, veiculoAno, veiculoCor, veiculoFabricante);

                    veiculosList.add(veiculo);

                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 2:
                    System.out.println("Cadastrar Cliente:  ");
                    System.out.println("Nome: ");
                    String clienteNome = sc.nextLine();
                    System.out.println("CPF: ");
                    String clienteCPF = sc.nextLine();
                    System.out.println("Idade: ");
                    int clienteIdade = sc.nextInt();
                    sc.nextLine();
                    System.out.println("CNH: ");
                    String clienteCnh = sc.nextLine();

                    Cliente cliente = new Cliente(clienteNome, clienteCPF, clienteIdade, clienteCnh);

                    clientesList.add(cliente);

                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("Digite um nome para verificar se o usuario já está cadastrado!");
                    String usuarioCadastrado = sc.nextLine();
                    System.out.println();

                    /**
                     * Localiza o cliente na lista pelo nome digitado (sem diferenciar maiúsculas/minúsculas).
                     *
                     * <p>Chama {@link ClienteService#buscarUsuarioParaLocacao(java.util.ArrayList, String)}.</p>
                     */
                    Cliente clienteEncontrado = clienteService.buscarUsuarioParaLocacao(clientesList, usuarioCadastrado);

                    if (clienteEncontrado == null) {
                        System.out.println("Cliente não encontrado");
                        System.out.println(menu.mostrarMenu());
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();
                        break;
                    }

                    System.out.println("Digite o modelo do veiculo: ");
                    String modeloInput = sc.nextLine();

                    /**
                     * Obtém o primeiro veículo cujo modelo coincide com a entrada do usuário.
                     *
                     * <p>Chama {@link VeiculoService#veiculoDisponivel(java.util.ArrayList, String)}.</p>
                     */
                    Veiculo veiculoEncontrado = veiculoService.veiculoDisponivel(veiculosList, modeloInput);

                    if (veiculoEncontrado == null) {
                        System.out.println("Veiculo não encontrado");
                        System.out.println(menu.mostrarMenu());
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();
                        break;
                    }

                    /**
                     * Só permite nova locação se o veículo estiver disponível e
                     * {@link LocacaoService#verificarSeClienteJaLocou(java.util.ArrayList, br.com.fiap.locadora.model.Cliente)} retornar {@code true}.
                     */
                    if (veiculoEncontrado.isDisponivel() && (locacaoService.verificarSeClienteJaLocou(locacaoList, clienteEncontrado))) {
                        System.out.println(veiculoEncontrado.exibirInformacaoVeiculo());
                        System.out.println(clienteEncontrado.exibirInformacaoCliente());
                        System.out.println("Data inicial locacao: dd/mm/aaaa ");
                        String locacaoDataInicio = sc.nextLine();
                        LocalDate locacaoDataInicioFormatada = LocalDate.parse(locacaoDataInicio, formatter);

                        System.out.println("Data de devolução: dd/mm/aaaa");
                        String locacaoDataFim = sc.nextLine();

                        LocalDate locacaoDataFinalFormatada = LocalDate.parse(locacaoDataFim, formatter);
                        veiculoEncontrado.setDisponivel(false);
                        Locacao locacaoFeita = new Locacao(clienteEncontrado, veiculoEncontrado, locacaoDataInicioFormatada, locacaoDataFinalFormatada);
                        locacaoList.add(locacaoFeita);
                        System.out.println(locacaoFeita.imprimirLocacao());
                        System.out.println(menu.mostrarMenu());
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();
                    } else if (!locacaoService.verificarSeClienteJaLocou(locacaoList, clienteEncontrado)) {

                        /**
                         * Recupera a locação ativa do cliente para informar qual modelo já está locado.
                         *
                         * <p>Chama {@link LocacaoService#encontrarLocacaoPorCpf(java.util.ArrayList, String)}.</p>
                         */
                        Locacao locacaoEncontrada = locacaoService.encontrarLocacaoPorCpf(locacaoList, clienteEncontrado.getCpf());
                        System.out.printf("Cliente %s ja possui  o  veiculo %s locado\n", clienteEncontrado.getNome(), locacaoEncontrada.getVeiculo().getModelo());
                        System.out.println(menu.mostrarMenu());
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();
                        break;
                    } else {
                        System.out.printf("O veiculo %s está indisponivel para locacao!", veiculoEncontrado.getModelo());
                        System.out.println(menu.mostrarMenu());
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();
                        break;
                    }
                    break;
                case 4:
                    System.out.println("Exibir Veiculos Cadastrados!");

                    /**
                     * Lista todos os veículos formatados para o console.
                     *
                     * <p>Chama {@link VeiculoService#veiculoParaLocacao(java.util.ArrayList)}.</p>
                     */
                    System.out.println(veiculoService.veiculoParaLocacao(veiculosList));
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 5:
                    System.out.println("Exibir Usuarios cadastrados!");

                    /**
                     * Lista todos os clientes ou mensagem se a coleção estiver vazia.
                     *
                     * <p>Chama {@link ClienteService#mostrarUsuariosCadastrados(java.util.ArrayList)}.</p>
                     */
                    System.out.println(clienteService.mostrarUsuariosCadastrados(clientesList));
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 6:
                    System.out.println("Excluir veículo — informe a placa:");
                    String placaExcluir = sc.nextLine();

                    /**
                     * Remove o veículo pela placa informada.
                     *
                     * <p>Chama {@link VeiculoService#excluirVeiculo(java.util.ArrayList, String)}.</p>
                     */
                    if (veiculoService.excluirVeiculo(veiculosList, placaExcluir)) {
                        System.out.println("Veículo removido com sucesso.");
                    } else {
                        System.out.println("Nenhum veículo encontrado com essa placa.");
                    }
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 7:
                    System.out.println("Remover cliente — informe o nome:");
                    String nomeRemover = sc.nextLine();

                    /**
                     * Remove o cliente pelo nome informado.
                     *
                     * <p>Chama {@link ClienteService#removerCliente(java.util.ArrayList, String)}.</p>
                     */
                    if (clienteService.removerCliente(clientesList, nomeRemover)) {
                        System.out.println("Cliente removido com sucesso.");
                    } else {
                        System.out.println("Nenhum cliente encontrado com esse nome.");
                    }
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 8:
                    System.out.println("Liberar veiculo para locacao");

                    /**
                     * Mostra todas as locações antes da devolução.
                     *
                     * <p>Chama {@link LocacaoService#exibirLocacoes(java.util.ArrayList)}.</p>
                     */
                    System.out.println(locacaoService.exibirLocacoes(locacaoList));
                    System.out.println("Digite o cpf do cliente para efetuar devolução");
                    String devolucaoCpf = sc.nextLine();

                    /**
                     * Encerra a locação pelo CPF, remove da lista e marca o veículo como disponível.
                     *
                     * <p>Chama {@link LocacaoService#removerLocacao(java.util.ArrayList, String)}.</p>
                     */
                    locacaoService.removerLocacao(locacaoList, devolucaoCpf);
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 9:
                    System.out.println("Exibir todas as locacões!");

                    /**
                     * Exibe o relatório textual de todas as locações registradas.
                     *
                     * <p>Chama {@link LocacaoService#exibirLocacoes(java.util.ArrayList)}.</p>
                     */
                    System.out.println(locacaoService.exibirLocacoes(locacaoList));
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                default:
                    while (opcaoMenu != 10) {
                        System.out.println("Digite uma opção valida correspondente ao menu: ");
                        System.out.println(menu.mostrarMenu());
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();
                    }
            }
        }
    }
}
