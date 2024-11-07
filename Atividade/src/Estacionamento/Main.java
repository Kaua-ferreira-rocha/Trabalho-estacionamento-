package Estacionamento;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Scanner scanner = new Scanner(System.in);

        // Adicionar vagas
        estacionamento.adicionarVaga(1, "pequeno");
        estacionamento.adicionarVaga(2, "médio");
        estacionamento.adicionarVaga(3, "grande");

        boolean executando = true;
        while (executando) {
            System.out.println("1. Registrar entrada de veículo");
            System.out.println("2. Registrar saída de veículo");
            System.out.println("3. Gerar relatório de vagas ocupadas");
            System.out.println("4. Gerar histórico de veículos");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tamanho (pequeno, médio, grande): ");
                    String tamanho = scanner.nextLine();
                   
                    Veiculo veiculo = new Veiculo(placa, modelo, tamanho);
                    estacionamento.registrarEntradaVeiculo(veiculo);
                    break;

                case 2:
                    System.out.print("Placa do veículo que vai sair: ");
                    String placaSaida = scanner.nextLine();
                    estacionamento.registrarSaidaVeiculo(placaSaida);
                    break;

                case 3:
                    estacionamento.gerarRelatorioVagasOcupadas();
                    break;

                case 4:
                    estacionamento.gerarHistoricoVeiculos();
                    break;

                case 5:
                    executando = false;
                    break;
                   
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
       
        scanner.close();
    }
}