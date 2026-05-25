/**
 * Classe abstrata que representa um sistema de propulsão genérico.
 * Herda de ComponenteEspacial e define comportamentos comuns
 * a todos os tipos de propulsão.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public abstract class SistemaPropulsao extends ComponenteEspacial {

    protected double potenciaAtual;    // percentual de 0 a 100
    protected double empuxoMaximo;     // empuxo máximo em kN

    public SistemaPropulsao(String id, String nome, double empuxoMaximo) {
        super(id, nome, 25.0);
        this.empuxoMaximo = empuxoMaximo;
        this.potenciaAtual = 0.0;
    }

    /**
     * Acelera o sistema com uma porcentagem de potência (0–100).
     * Cada subclasse implementa de forma específica.
     */
    public abstract void acelerar(double porcentagem);

    /**
     * Calcula o empuxo gerado com base na potência atual.
     * @return empuxo em kN
     */
    public double calcularEmpuxo() {
        return (potenciaAtual / 100.0) * empuxoMaximo;
    }

    // Getters
    public double getPotenciaAtual() { return potenciaAtual; }
    public double getEmpuxoMaximo() { return empuxoMaximo; }

    /**
     * Valida e aplica a potência desejada.
     * Reutilizado pelas subclasses via super().
     */
    protected boolean validarEAplicarPotencia(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 100) {
            System.out.println("[ERRO] Potência inválida: " + porcentagem
                    + "%. Use um valor entre 0 e 100.");
            return false;
        }
        if (!isLigado()) {
            System.out.println("[ERRO] " + getNome() + " está desligado. Ligue antes de acelerar.");
            return false;
        }
        this.potenciaAtual = porcentagem;
        return true;
    }
}
