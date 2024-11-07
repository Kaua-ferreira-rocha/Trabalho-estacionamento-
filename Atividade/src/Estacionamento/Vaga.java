package Estacionamento;

public class Vaga {
    private int numero;
    private String tamanho; // pequeno, médio ou grande
    private boolean disponivel;
    private Veiculo veiculoOcupante; // para associar um veículo à vaga
   
    public Vaga(int numero, String tamanho) {
        this.numero = numero;
        this.tamanho = tamanho;
        this.disponivel = true; // por padrão, a vaga começa como disponível
    }

    public int getNumero() {
        return numero;
    }

    public String getTamanho() {
        return tamanho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void ocuparVaga(Veiculo veiculo) {
        this.disponivel = false;
        this.veiculoOcupante = veiculo;
    }

    public void liberarVaga() {
        this.disponivel = true;
        this.veiculoOcupante = null;
    }
   
    public Veiculo getVeiculoOcupante() {
        return veiculoOcupante;
    }
}

