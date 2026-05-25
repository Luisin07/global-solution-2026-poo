import java.util.Scanner;

/**
 * Classe principal do sistema — contém o menu interativo.
 * Integra todos os componentes: sensores, propulsão, dados da missão e alertas.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class SistemaMonitoramento {

    // Instâncias dos componentes do sistema
    private static SensorTemperatura sensorTemp    = new SensorTemperatura("S-001", 80.0);
    private static SensorPressao     sensorPressao = new SensorPressao("S-002", 120.0);
    private static SensorRadiacao    sensorRad     = new SensorRadiacao("S-003", 5.0);

    private static PropulsaoQuimica  propQuimica   = new PropulsaoQuimica("P-001");
    private static PropulsaoEletrica propEletrica  = new PropulsaoEletrica("P-002");

    private static DadosMissao missao = new DadosMissao(
        "Missão Órion-7",
        "SENHA123",       // código de acesso para dados restritos
        75.0,             // nível de combustível inicial
        "Terra → Lua → Marte",
        4                 // número de tripulantes
    );

    private static Scanner scanner = new Scanner(System.in);

    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String[] args) {
        // Liga todos os sensores ao iniciar
        sensorTemp.ligar();
        sensorPressao.ligar();
        sensorRad.ligar();

        exibirBoasVindas();
        menuPrincipal();

        System.out.println("\n  Encerrando sistema. Boa viagem espacial!");
        scanner.close();
    }

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================
    private static void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║     PLATAFORMA DE MONITORAMENTO ESPACIAL ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Verificar Sensores                   ║");
            System.out.println("║  2. Controlar Propulsão                  ║");
            System.out.println("║  3. Gerenciar Dados da Missão            ║");
            System.out.println("║  4. Simular Alertas                      ║");
            System.out.println("║  5. Exibir Status Completo               ║");
            System.out.println("║  0. Sair                                 ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("  Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> menuSensores();
                case 2 -> menuPropulsao();
                case 3 -> menuDadosMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("  Saindo do menu principal...");
                default -> System.out.println("[ERRO] Opção inválida. Tente novamente.");
            }
        }
    }

    // =========================================================
    // MENU: SENSORES
    // =========================================================
    private static void menuSensores() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE SENSORES ---");
            System.out.println("  1. Ler todos os sensores");
            System.out.println("  2. Verificar funcionamento");
            System.out.println("  3. Exibir status detalhado de cada sensor");
            System.out.println("  0. Voltar");
            System.out.print("  Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> lerTodosSensores();
                case 2 -> verificarFuncionamentoSensores();
                case 3 -> {
                    sensorTemp.lerValor();
                    sensorPressao.lerValor();
                    sensorRad.lerValor();
                    sensorTemp.exibirStatus();
                    sensorPressao.exibirStatus();
                    sensorRad.exibirStatus();
                }
                case 0 -> System.out.println("  Voltando...");
                default -> System.out.println("[ERRO] Opção inválida.");
            }
        }
    }

    private static void lerTodosSensores() {
        System.out.println("\n  Realizando leitura dos sensores...");
        double temp     = sensorTemp.lerValor();
        double pressao  = sensorPressao.lerValor();
        double radiacao = sensorRad.lerValor();

        System.out.printf("  Temperatura: %.2f °C%n", temp);
        System.out.printf("  Pressão:     %.2f kPa%n", pressao);
        System.out.printf("  Radiação:    %.2f mSv%n", radiacao);

        // Verifica automaticamente os limites após leitura
        verificarLimitesSensores();
    }

    private static void verificarFuncionamentoSensores() {
        System.out.println("\n  Verificando funcionamento dos sensores...");
        System.out.println("  Temperatura: " + (sensorTemp.verificarFuncionamento()    ? "OK" : "FALHA"));
        System.out.println("  Pressão:     " + (sensorPressao.verificarFuncionamento() ? "OK" : "FALHA"));
        System.out.println("  Radiação:    " + (sensorRad.verificarFuncionamento()     ? "OK" : "FALHA"));
    }

    // =========================================================
    // MENU: PROPULSÃO
    // =========================================================
    private static void menuPropulsao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE PROPULSÃO ---");
            System.out.println("  1. Ligar Propulsão Química");
            System.out.println("  2. Desligar Propulsão Química");
            System.out.println("  3. Acelerar Propulsão Química");
            System.out.println("  4. Ligar Propulsão Elétrica");
            System.out.println("  5. Desligar Propulsão Elétrica");
            System.out.println("  6. Acelerar Propulsão Elétrica");
            System.out.println("  7. Exibir status dos motores");
            System.out.println("  0. Voltar");
            System.out.print("  Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> propQuimica.ligar();
                case 2 -> propQuimica.desligar();
                case 3 -> {
                    System.out.print("  Digite a potência (0-100): ");
                    try {
                        double pot = Double.parseDouble(scanner.nextLine().trim());
                        propQuimica.acelerar(pot);
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor inválido.");
                    }
                }
                case 4 -> propEletrica.ligar();
                case 5 -> propEletrica.desligar();
                case 6 -> {
                    System.out.print("  Digite a potência (0-100): ");
                    try {
                        double pot = Double.parseDouble(scanner.nextLine().trim());
                        propEletrica.acelerar(pot);
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor inválido.");
                    }
                }
                case 7 -> {
                    propQuimica.exibirStatus();
                    propEletrica.exibirStatus();
                }
                case 0 -> System.out.println("  Voltando...");
                default -> System.out.println("[ERRO] Opção inválida.");
            }
        }
    }

    // =========================================================
    // MENU: DADOS DA MISSÃO
    // =========================================================
    private static void menuDadosMissao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE DADOS DA MISSÃO ---");
            System.out.println("  1. Ver dados gerais");
            System.out.println("  2. Ver dados restritos (requer senha)");
            System.out.println("  3. Atualizar nível de combustível");
            System.out.println("  4. Atualizar coordenadas (requer senha)");
            System.out.println("  0. Voltar");
            System.out.print("  Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> missao.exibirDadosGerais();
                case 2 -> {
                    System.out.print("  Digite a senha de acesso: ");
                    String senha = scanner.nextLine().trim();
                    missao.exibirDadosRestritos(senha);
                }
                case 3 -> {
                    System.out.print("  Novo nível de combustível (0-100): ");
                    try {
                        double nivel = Double.parseDouble(scanner.nextLine().trim());
                        missao.setNivelCombustivel(nivel);
                        System.out.println("  Combustível atualizado para: " + nivel + "%");
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor inválido.");
                    }
                }
                case 4 -> {
                    System.out.print("  Senha de acesso: ");
                    String senha = scanner.nextLine().trim();
                    System.out.print("  Coordenada X: ");
                    try {
                        double x = Double.parseDouble(scanner.nextLine().trim());
                        System.out.print("  Coordenada Y: ");
                        double y = Double.parseDouble(scanner.nextLine().trim());
                        missao.setCoordenadas(x, y, senha);
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor inválido.");
                    }
                }
                case 0 -> System.out.println("  Voltando...");
                default -> System.out.println("[ERRO] Opção inválida.");
            }
        }
    }

    // =========================================================
    // SIMULAR ALERTAS
    // =========================================================
    private static void simularAlertas() {
        System.out.println("\n  Simulando leituras e verificando alertas...\n");

        // Força leituras
        sensorTemp.lerValor();
        sensorPressao.lerValor();
        sensorRad.lerValor();

        verificarLimitesSensores();
    }

    /**
     * Verifica automaticamente os valores dos sensores e emite alertas
     * com três níveis: ATENÇÃO, ALERTA e CRÍTICO.
     */
    private static void verificarLimitesSensores() {
        verificarSensor("Temperatura", sensorTemp.getValorAtual(), sensorTemp.getLimite(), "°C");
        verificarSensor("Pressão",     sensorPressao.getValorAtual(), sensorPressao.getLimite(), "kPa");
        verificarSensor("Radiação",    sensorRad.getValorAtual(), sensorRad.getLimite(), "mSv");
    }

    private static void verificarSensor(String tipo, double valor, double limite, String unidade) {
        double percentual = (valor / limite) * 100;

        if (percentual >= 100) {
            emitirAlerta("CRÍTICO", tipo, valor, limite, unidade);
        } else if (percentual >= 85) {
            emitirAlerta("ALERTA", tipo, valor, limite, unidade);
        } else if (percentual >= 70) {
            emitirAlerta("ATENÇÃO", tipo, valor, limite, unidade);
        } else {
            System.out.println("  [OK] " + tipo + ": " + valor + " " + unidade + " (Normal)");
        }
    }

    private static void emitirAlerta(String nivel, String tipo, double valor, double limite, String unidade) {
        System.out.println("  !! [" + nivel + "] Sensor de " + tipo
                + ": " + valor + " " + unidade
                + " | Limite: " + limite + " " + unidade);
    }

    // =========================================================
    // STATUS COMPLETO
    // =========================================================
    private static void exibirStatusCompleto() {
        System.out.println("\n==========================================");
        System.out.println("         STATUS COMPLETO DO SISTEMA");
        System.out.println("==========================================");

        missao.exibirDadosGerais();

        sensorTemp.lerValor();
        sensorPressao.lerValor();
        sensorRad.lerValor();
        sensorTemp.exibirStatus();
        sensorPressao.exibirStatus();
        sensorRad.exibirStatus();

        propQuimica.exibirStatus();
        propEletrica.exibirStatus();

        System.out.println("\n  Verificação automática de alertas:");
        verificarLimitesSensores();
    }

    // =========================================================
    // BOAS-VINDAS
    // =========================================================
    private static void exibirBoasVindas() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   PLATAFORMA DE MONITORAMENTO ESPACIAL   ║");
        System.out.println("║   Global Solution 2026 — POO             ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  Luis Otavio Santini Feitosa  RM 563556  ║");
        System.out.println("║  Lucas Andrade de Souza       RM 564066  ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("\n  Sistema inicializado. Sensores ligados.");
    }
}
