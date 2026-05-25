/**
 * Classe que representa os dados sigilosos de uma missão espacial.
 * Demonstra ENCAPSULAMENTO: todos os atributos são privados e
 * o acesso a dados sensíveis é protegido por senha.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class DadosMissao {

    // Atributos privados — nenhum acesso direto de fora da classe
    private String nomeMissao;
    private double coordenadaX;   // dados restritos
    private double coordenadaY;   // dados restritos
    private String codigoAcesso;  // senha para dados restritos
    private double nivelCombustivel; // em percentual (0 a 100)
    private String trajetoria;
    private int numeroDeTripulantes;

    // Limite para alerta de combustível
    private static final double LIMITE_COMBUSTIVEL_CRITICO = 20.0;

    /**
     * Construtor da missão.
     */
    public DadosMissao(String nomeMissao, String codigoAcesso, double nivelCombustivel,
                       String trajetoria, int numeroDeTripulantes) {
        this.nomeMissao = nomeMissao;
        this.codigoAcesso = codigoAcesso;
        setNivelCombustivel(nivelCombustivel); // já valida no setter
        this.trajetoria = trajetoria;
        setNumeroDeTripulantes(numeroDeTripulantes);
        this.coordenadaX = 0.0;
        this.coordenadaY = 0.0;
    }

    // -------------------------
    // Getters públicos (dados não sensíveis)
    // -------------------------

    public String getNomeMissao() { return nomeMissao; }
    public double getNivelCombustivel() { return nivelCombustivel; }
    public String getTrajetoria() { return trajetoria; }
    public int getNumeroDeTripulantes() { return numeroDeTripulantes; }

    // -------------------------
    // Getters de dados RESTRITOS — exigem senha
    // -------------------------

    public double getCoordenadaX(String senha) {
        if (autenticar(senha)) {
            return coordenadaX;
        }
        System.out.println("[ACESSO NEGADO] Senha incorreta para coordenada X.");
        return -1;
    }

    public double getCoordenadaY(String senha) {
        if (autenticar(senha)) {
            return coordenadaY;
        }
        System.out.println("[ACESSO NEGADO] Senha incorreta para coordenada Y.");
        return -1;
    }

    // -------------------------
    // Setters com VALIDAÇÃO
    // -------------------------

    public void setNomeMissao(String nomeMissao) {
        if (nomeMissao == null || nomeMissao.trim().isEmpty()) {
            System.out.println("[ERRO] Nome da missão não pode ser vazio.");
            return;
        }
        this.nomeMissao = nomeMissao;
    }

    public void setNivelCombustivel(double nivel) {
        if (nivel < 0 || nivel > 100) {
            System.out.println("[ERRO] Nível de combustível deve ser entre 0 e 100. Valor ignorado: " + nivel);
            return;
        }
        this.nivelCombustivel = nivel;
        verificarAlertaCombustivel();
    }

    public void setCoordenadas(double x, double y, String senha) {
        if (!autenticar(senha)) {
            System.out.println("[ACESSO NEGADO] Senha incorreta. Coordenadas não alteradas.");
            return;
        }
        this.coordenadaX = x;
        this.coordenadaY = y;
        System.out.println("[OK] Coordenadas atualizadas: (" + x + ", " + y + ")");
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria == null || trajetoria.trim().isEmpty()) {
            System.out.println("[ERRO] Trajetória não pode ser vazia.");
            return;
        }
        this.trajetoria = trajetoria;
    }

    public void setNumeroDeTripulantes(int numero) {
        if (numero < 0) {
            System.out.println("[ERRO] Número de tripulantes não pode ser negativo.");
            return;
        }
        this.numeroDeTripulantes = numero;
    }

    // -------------------------
    // Métodos auxiliares
    // -------------------------

    /** Verifica se a senha fornecida é correta. */
    private boolean autenticar(String senha) {
        return codigoAcesso.equals(senha);
    }

    /** Emite alerta automático quando o combustível está abaixo do limite crítico. */
    private void verificarAlertaCombustivel() {
        if (nivelCombustivel < LIMITE_COMBUSTIVEL_CRITICO) {
            System.out.println("============================================");
            System.out.println("  [ALERTA CRÍTICO] COMBUSTÍVEL BAIXO!");
            System.out.println("  Nível atual: " + nivelCombustivel + "%");
            System.out.println("  Limite crítico: " + LIMITE_COMBUSTIVEL_CRITICO + "%");
            System.out.println("============================================");
        }
    }

    /** Exibe os dados gerais da missão (sem dados restritos). */
    public void exibirDadosGerais() {
        System.out.println("============================================");
        System.out.println("  DADOS DA MISSÃO: " + nomeMissao);
        System.out.println("  Trajetória:      " + trajetoria);
        System.out.println("  Tripulantes:     " + numeroDeTripulantes);
        System.out.printf ("  Combustível:     %.1f%%%n", nivelCombustivel);
        if (nivelCombustivel < LIMITE_COMBUSTIVEL_CRITICO) {
            System.out.println("  [!] ATENÇÃO: Combustível abaixo de " + LIMITE_COMBUSTIVEL_CRITICO + "%!");
        }
        System.out.println("  (Coordenadas protegidas por senha)");
        System.out.println("============================================");
    }

    /** Exibe dados restritos após autenticação. */
    public void exibirDadosRestritos(String senha) {
        if (!autenticar(senha)) {
            System.out.println("[ACESSO NEGADO] Senha incorreta.");
            return;
        }
        System.out.println("============================================");
        System.out.println("  [DADOS RESTRITOS] MISSÃO: " + nomeMissao);
        System.out.println("  Coordenada X:  " + coordenadaX);
        System.out.println("  Coordenada Y:  " + coordenadaY);
        System.out.println("============================================");
    }
}
