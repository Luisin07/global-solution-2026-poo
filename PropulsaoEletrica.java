/**
 * Propulsão Elétrica (Iônica) — herda de SistemaPropulsao.
 * Usa íons acelerados eletricamente. Empuxo menor, mas altamente eficiente.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class PropulsaoEletrica extends SistemaPropulsao {

    // Atributos específicos da propulsão elétrica
    private double consumoEnergia; // watts por segundo
    private double eficiencia;     // percentual de eficiência

    public PropulsaoEletrica(String id) {
        super(id, "Propulsão Elétrica", 200.0); // 200 kN de empuxo máximo
        this.consumoEnergia = 5000.0; // 5000 W
        this.eficiencia = 90.0;       // 90% de eficiência
    }

    /**
     * Acelera o motor elétrico iônico.
     * Usa super() para validar a potência antes de aplicar comportamento específico.
     */
    @Override
    public void acelerar(double porcentagem) {
        boolean valido = super.validarEAplicarPotencia(porcentagem);
        if (!valido) return;

        double empuxo = calcularEmpuxo();
        double energiaAtual = consumoEnergia * (porcentagem / 100.0);

        System.out.println("--------------------------------------------");
        System.out.println("  [PROPULSÃO ELÉTRICA] Acelerando...");
        System.out.printf ("  Potência:        %.1f%%%n", porcentagem);
        System.out.printf ("  Empuxo gerado:   %.1f kN%n", empuxo);
        System.out.printf ("  Energia consumida: %.1f W%n", energiaAtual);
        System.out.printf ("  Eficiência:      %.1f%%%n", eficiencia);
        System.out.println("  Tipo:            Propulsão Iônica");
        System.out.println("--------------------------------------------");
    }

    @Override
    public void exibirStatus() {
        System.out.println("+-----------------------------------------+");
        System.out.println("  PROPULSÃO ELÉTRICA [ID: " + getId() + "]");
        System.out.println("  Status:          " + getStatusTexto());
        System.out.printf ("  Potência:        %.1f%%%n", potenciaAtual);
        System.out.printf ("  Empuxo atual:    %.1f kN%n", calcularEmpuxo());
        System.out.printf ("  Empuxo máximo:   %.1f kN%n", empuxoMaximo);
        System.out.printf ("  Consumo base:    %.1f W%n", consumoEnergia);
        System.out.printf ("  Eficiência:      %.1f%%%n", eficiencia);
        System.out.println("+-----------------------------------------+");
    }

    public double getConsumoEnergia() { return consumoEnergia; }
    public double getEficiencia() { return eficiencia; }
}
