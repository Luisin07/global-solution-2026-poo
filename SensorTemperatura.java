/**
 * Sensor de Temperatura — implementa a interface Sensor.
 * Mede a temperatura em graus Celsius dentro da estação espacial.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteMaximo; // limite de alerta em °C

    public SensorTemperatura(String id, double limiteMaximo) {
        super(id, "Sensor de Temperatura", 20.0);
        this.limiteMaximo = limiteMaximo;
        this.valorAtual = 0.0;
    }

    @Override
    public double lerValor() {
        // Simula leitura com valor aleatório entre -50 e 150 °C
        valorAtual = -50 + Math.random() * 200;
        valorAtual = Math.round(valorAtual * 100.0) / 100.0;
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        // Simula: 95% de chance de estar funcionando
        return Math.random() > 0.05;
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
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
        System.out.println("  Valor atual:   " + valorAtual + " °C");
        System.out.println("  Limite:        " + limiteMaximo + " °C");
        System.out.println("  Alerta:        " + (valorAcimaDoLimite() ? "*** ACIMA DO LIMITE ***" : "Normal"));
        System.out.println("+-----------------------------------------+");
    }
}
