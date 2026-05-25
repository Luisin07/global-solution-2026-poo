/**
 * Sensor de Radiação — implementa a interface Sensor.
 * Mede o nível de radiação em mSv (milisieverts).
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class SensorRadiacao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteMaximo; // limite de alerta em mSv

    public SensorRadiacao(String id, double limiteMaximo) {
        super(id, "Sensor de Radiação", 20.0);
        this.limiteMaximo = limiteMaximo;
        this.valorAtual = 0.0;
    }

    @Override
    public double lerValor() {
        // Simula leitura entre 0 e 10 mSv
        valorAtual = Math.random() * 10;
        valorAtual = Math.round(valorAtual * 100.0) / 100.0;
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return Math.random() > 0.05;
    }

    @Override
    public String retornarTipo() {
        return "Radiação";
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
        System.out.println("  Valor atual:   " + valorAtual + " mSv");
        System.out.println("  Limite:        " + limiteMaximo + " mSv");
        System.out.println("  Alerta:        " + (valorAcimaDoLimite() ? "*** ACIMA DO LIMITE ***" : "Normal"));
        System.out.println("+-----------------------------------------+");
    }
}
