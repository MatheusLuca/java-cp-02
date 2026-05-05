package br.com.fiap.locadora.view;

import br.com.fiap.locadora.model.Cliente;
import br.com.fiap.locadora.model.Locacao;
import br.com.fiap.locadora.model.Veiculo;
import br.com.fiap.locadora.service.ClienteService;
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

        System.out.println(menu.mostrarMenu());

        int opcaoMenu = sc.nextInt();
        ArrayList<Cliente> clientesList = new ArrayList<>();
        ArrayList<Veiculo> veiculosList = new ArrayList<>();
        sc.nextLine();
        while( opcaoMenu != 10){
            switch (opcaoMenu){
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

                    Cliente cliente = new Cliente(clienteNome, clienteCPF, clienteIdade,clienteCnh );

                    clientesList.add(cliente);

                    System.out.println(menu.mostrarMenu());
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("Dados para locação: ");
                    System.out.println("Ativa ou nao");
                    boolean locacaoInput = sc.nextBoolean();
                    sc.nextLine();

                    System.out.println("Data locacao: ");
                    String locacaoDataInicio = sc.nextLine();
                    LocalDate locacaoDataInicioFormatada = LocalDate.parse(locacaoDataInicio, formatter);

                    System.out.println("Data de devolução: ");
                    String locacaoDataFim = sc.nextLine();

                    LocalDate locacaoDataFinalFormatada = LocalDate.parse(locacaoDataFim, formatter);

                    Locacao locacao = new Locacao(locacaoInput, locacaoDataInicioFormatada, locacaoDataFinalFormatada);
                    System.out.println("Dados da locacao veiculo e cliente: " + locacao.imprimirLocacao());
                    break;
                case 4:
                    System.out.println("Exibir Veiculos Cadastrados!");
                    VeiculoService veiculoService = new VeiculoService();
                    System.out.println(veiculoService.veiculoParaLocacao(veiculosList));
                    menu.mostrarMenu();
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                case 5:
                    System.out.println("Exibir Usuarios cadastrados!");
                    ClienteService clienteService = new ClienteService();
                    System.out.println(clienteService.mostrarUsuariosCadastrados(clientesList));
                    menu.mostrarMenu();
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opcaoMenu);
            }
            

           

            
        }
        
              

      
        




    }
}
