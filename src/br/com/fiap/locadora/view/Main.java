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

public class Main {
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
                    //Efetuar Locacao
                    System.out.println("Digite um nome para verificar se o usuario já está cadastrado!");
                    String usuarioCadastrado = sc.nextLine();
                    System.out.println();

                    //Retorna se o cliente foi encontrado dado um nome.
                    Cliente clienteEncontrado = clienteService.buscarUsuarioParaLocacao(clientesList, usuarioCadastrado);
                    System.out.println(clienteEncontrado.exibirInformacaoCliente());

                    //Retorna se o veiculo esta disponivel para locacao
                    System.out.println("Digite o modelo do veiculo: ");
                    String modeloInput = sc.nextLine();
                    Veiculo veiculoEncontrado = veiculoService.veiculoDisponivel(veiculosList, modeloInput);


                    //Verifica se o veiculo está disponivel para locação e se o cliente ja fez locacao antes
                    if (veiculoEncontrado.isDisponivel() && (locacaoService.verificarSeClienteJaLocou(locacaoList, clienteEncontrado))) {
                        System.out.println(veiculoEncontrado.exibirInformacaoVeiculo());
                        System.out.println(clienteEncontrado.exibirInformacaoCliente());
                        System.out.println("Data inicial locacao: ");
                        String locacaoDataInicio = sc.nextLine();
                        LocalDate locacaoDataInicioFormatada = LocalDate.parse(locacaoDataInicio, formatter);

                        System.out.println("Data de devolução: ");
                        String locacaoDataFim = sc.nextLine();

                        LocalDate locacaoDataFinalFormatada = LocalDate.parse(locacaoDataFim, formatter);
                        veiculoEncontrado.setDisponivel(false);
                        Locacao locacaoFeita = new Locacao(clienteEncontrado, veiculoEncontrado, locacaoDataInicioFormatada, locacaoDataFinalFormatada);
                        locacaoList.add(locacaoFeita);
                        System.out.println(locacaoFeita.imprimirLocacao());
                        System.out.println(menu.mostrarMenu());
                        opcaoMenu = sc.nextInt();
                        sc.nextLine();

                        //Se nao tiver disponivel
                    } else if (!locacaoService.verificarSeClienteJaLocou(locacaoList, clienteEncontrado)) {
                        Locacao locacaoEncontrada = locacaoService.encontrarVeiculoLocadoPeloCliente(clienteEncontrado.getNome(), locacaoList);
                        System.out.printf("Cliente %s ja possui  o  veiculo %s locado\n", clienteEncontrado.getNome() ,locacaoEncontrada.getVeiculo().getModelo());
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
                    System.out.println(veiculoService.veiculoParaLocacao(veiculosList));
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 5:
                    System.out.println("Exibir Usuarios cadastrados!");
                    System.out.println(clienteService.mostrarUsuariosCadastrados(clientesList));
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 6:
                    System.out.println("Excluir veículo — informe a placa:");
                    String placaExcluir = sc.nextLine();
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
                    System.out.println("Excluir locacao");


                case 9:
                    System.out.println("Exibir todas as locacões!");
                    System.out.println(locacaoService.exibirLocacoes(locacaoList));
                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opcaoMenu);
            }
        }
    }
}
        
              

      
        




