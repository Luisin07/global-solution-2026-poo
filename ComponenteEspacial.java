/**
 * Classe abstrata que representa um componente genérico de uma estação espacial.
 * Serve como "molde" para todos os componentes do sistema.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public abstract class ComponenteEspacial {

    // Atributos comuns a todos os componentes espaciais
    protected String id;
    protected String nome;
    protected boolean status; // true = ligado, false = desligado
    protected double temperatura; // em graus Celsius

    /**
     * Construtor da classe abstrata.
     */
    public ComponenteEspacial(String id, String nome, double temperatura) {
        this.id = id;
        this.nome = nome;
        this.temperatura = temperatura;
        this.status = false; // começa desligado por padrão
    }

    // -------------------------
    // Métodos concretos (comuns a todos)
    // -------------------------

    /** Liga o componente espacial. */
    public void ligar() {
        this.status = true;
        System.out.println("[" + nome + "] Componente LIGADO.");
    }

    /** Desliga o componente espacial. */
    public void desligar() {
        this.status = false;
        System.out.println("[" + nome + "] Componente DESLIGADO.");
    }

    /** Retorna o status atual do componente. */
    public String getStatusTexto() {
        return status ? "LIGADO" : "DESLIGADO";
    }

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isLigado() { return status; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    // -------------------------
    // Método abstrato — cada subclasse DEVE implementar
    // -------------------------

    /**
     * Exibe o status detalhado do componente.
     * Cada subclasse implementa de forma específica.
     */
    public abstract void exibirStatus();
}
