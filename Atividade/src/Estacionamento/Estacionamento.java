package Estacionamento;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<Vaga> vagas;
    private List<Veiculo> historicoVeiculos;

    public Estacionamento() {
        vagas = new ArrayList<>();
        historicoVeiculos = new ArrayList<>();
    }

    public void adicionarVaga(int numero, String tamanho) {
        vagas.add(new Vaga(numero, tamanho));
    }

    public Vaga buscarVagaDisponivel(String tamanho) {
        for (Vaga vaga : vagas) {
            if (vaga.isDisponivel() && vaga.getTamanho().equalsIgnoreCase(tamanho)) {
                return vaga;
            }
        }
        return null;
    }

    public void registrarEntradaVeiculo(Veiculo veiculo) {
        Vaga vaga = buscarVagaDisponivel(veiculo.getTamanho());
        if (vaga != null) {
            vaga.ocuparVaga(veiculo);
            System.out.println("Veículo " + veiculo.getPlaca() + " entrou na vaga " + vaga.getNumero());
        } else {
            System.out.println("Nenhuma vaga disponível para o tamanho do veículo.");
        }
    }

    public void registrarSaidaVeiculo(String placa) {
        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel() && vaga.getVeiculoOcupante().getPlaca().equals(placa)) {
                Veiculo veiculo = vaga.getVeiculoOcupante();
                veiculo.setHoraSaida();
                vaga.liberarVaga();
               
                long tempoPermanencia = calcularTempoPermanencia(veiculo);
                double valor = calcularValor(tempoPermanencia);
               
                historicoVeiculos.add(veiculo);
               
                System.out.println("Veículo " + placa + " saiu. Tempo: " + tempoPermanencia + " minutos. Valor: R$ " + valor);
                return;
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    private long calcularTempoPermanencia(Veiculo veiculo) {
        return Duration.between(veiculo.getHoraEntrada(), veiculo.getHoraSaida()).toMinutes();
    }

    private double calcularValor(long tempoPermanencia) {
        if (tempoPermanencia <= 60) return 5.0;
        else if (tempoPermanencia <= 180) return 10.0;
        else return 15.0;
    }

    public void gerarRelatorioVagasOcupadas() {
        System.out.println("Vagas ocupadas:");
        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel()) {
                Veiculo veiculo = vaga.getVeiculoOcupante();
                System.out.println("Vaga " + vaga.getNumero() + " (" + vaga.getTamanho() + ") - Placa: " + veiculo.getPlaca());
            }
        }
    }

    public void gerarHistoricoVeiculos() {
        System.out.println("Histórico de Veículos:");
        for (Veiculo veiculo : historicoVeiculos) {
            System.out.println("Placa: " + veiculo.getPlaca() +
                               ", Entrada: " + veiculo.getHoraEntrada() +
                               ", Saída: " + veiculo.getHoraSaida() +
                               ", Valor Pago: R$ " + calcularValor(calcularTempoPermanencia(veiculo)));
        }
    }
}

