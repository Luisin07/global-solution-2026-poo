/**
 * Sensor de Pressão — implementa a interface Sensor.
 * Mede a pressão atmosférica interna em kPa (quilopascals).
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteMaximo; // limite de alerta em kPa

    public SensorPressao(String id, double limiteMaximo) {
        super(id, "Sensor de Pressão", 20.0);
        this.limiteMaximo = limiteMaximo;
        this.valorAtual = 0.0;
    }

    @Override
    public double lerValor() {
        // Simula leitura entre 80 e 130 kPa (pressão normal ~101 kPa)
        valorAtual = 80 + Math.random() * 50;
        valorAtual = Math.round(valorAtual * 100.0) / 100.0;
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return Math.random() > 0.05;
    }

    @Override
    public String retornarTipo() {
        return "Pressão";
    }

    @Override
    public boolean valorAcimaDoLimite() {
        return valorAtual > limiteMaximo;
    }

    @Override
    public double getLimite() {
        return limiteMaximo;
    }

    public double getValorAtual() { return valorAtual; }

    @Override
    public void exibirStatus() {
        System.out.println("+-----------------------------------------+");
        System.out.println("  " + retornarTipo().toUpperCase() + " [ID: " + getId() + "]");
        System.out.println("  Status:        " + getStatusTexto());
        System.out.println("  Funcionando:   " + (verificarFuncionamento() ? "SIM" : "NÃO"));
        System.out.println("  Valor atual:   " + valorAtual + " kPa");
        System.out.println("  Limite:        " + limiteMaximo + " kPa");
        System.out.println("  Alerta:        " + (valorAcimaDoLimite() ? "*** ACIMA DO LIMITE ***" : "Normal"));
        System.out.println("+-----------------------------------------+");
    }
}
