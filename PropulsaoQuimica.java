/**
 * Propulsão Química — herda de SistemaPropulsao.
 * Usa combustível químico (ex: hidrogênio líquido + oxigênio).
 * Gera alto empuxo, mas consome muito combustível.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class PropulsaoQuimica extends SistemaPropulsao {

    // Atributos específicos da propulsão química
    private String tipoCombustivel;
    private double consumoPorSegundo; // litros por segundo

    public PropulsaoQuimica(String id) {
        super(id, "Propulsão Química", 2000.0); // 2000 kN de empuxo máximo
        this.tipoCombustivel = "Hidrogênio Líquido + Oxigênio";
        this.consumoPorSegundo = 50.0;
    }

    /**
     * Acelera o motor químico.
     * Usa super() para validar a potência antes de aplicar comportamento específico.
     */
    @Override
    public void acelerar(double porcentagem) {
        // Chama a validação da classe mãe
        boolean valido = super.validarEAplicarPotencia(porcentagem);
        if (!valido) return;

        double empuxo = calcularEmpuxo();
        double consumoAtual = consumoPorSegundo * (porcentagem / 100.0);

        System.out.println("--------------------------------------------");
        System.out.println("  [PROPULSÃO QUÍMICA] Acelerando...");
        System.out.printf ("  Potência:      %.1f%%%n", porcentagem);
        System.out.printf ("  Empuxo gerado: %.1f kN%n", empuxo);
        System.out.printf ("  Consumo atual: %.1f L/s%n", consumoAtual);
        System.out.println("  Combustível:   " + tipoCombustivel);
        System.out.println("--------------------------------------------");
    }

    @Override
    public void exibirStatus() {
        System.out.println("+-----------------------------------------+");
        System.out.println("  PROPULSÃO QUÍMICA [ID: " + getId() + "]");
        System.out.println("  Status:        " + getStatusTexto());
        System.out.printf ("  Potência:      %.1f%%%n", potenciaAtual);
        System.out.printf ("  Empuxo atual:  %.1f kN%n", calcularEmpuxo());
        System.out.printf ("  Empuxo máximo: %.1f kN%n", empuxoMaximo);
        System.out.println("  Combustível:   " + tipoCombustivel);
        System.out.printf ("  Consumo base:  %.1f L/s%n", consumoPorSegundo);
        System.out.println("+-----------------------------------------+");
    }

    public String getTipoCombustivel() { return tipoCombustivel; }
    public double getConsumoPorSegundo() { return consumoPorSegundo; }
}
