import java.util.Scanner;

/**
 * Classe principal do sistema - contém o menu interativo.
 * Integra todos os componentes: sensores, propulsão, dados da missão e alertas.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public class SistemaMonitoramento {

    private static SensorTemperatura sensorTemp    = new SensorTemperatura("S-001", 80.0);
    private static SensorPressao     sensorPressao = new SensorPressao("S-002", 120.0);
    private static SensorRadiacao    sensorRad     = new SensorRadiacao("S-003", 5.0);

    private static PropulsaoQuimica  propQuimica   = new PropulsaoQuimica("P-001");
    private static PropulsaoEletrica propEletrica  = new PropulsaoEletrica("P-002");

    private static DadosMissao missao = new DadosMissao(
        "Missao Orion-7",
        "SENHA123",
        75.0,
        "Terra -> Lua -> Marte",
        4
    );

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        sensorTemp.ligar();
        sensorPressao.ligar();
        sensorRad.ligar();

        exibirBoasVindas();
        menuPrincipal();

        System.out.println("\nEncerrando sistema. Boa viagem espacial!");
        scanner.close();
    }

    private static void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n==========================================");
            System.out.println("   PLATAFORMA DE MONITORAMENTO ESPACIAL");
            System.out.println("==========================================");
            System.out.println("  1. Verificar Sensores");
            System.out.println("  2. Controlar Propulsao");
            System.out.println("  3. Gerenciar Dados da Missao");
            System.out.println("  4. Simular Alertas");
            System.out.println("  5. Exibir Status Completo");
            System.out.println("  0. Sair");
            System.out.println("==========================================");
            System.out.print("  Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um numero valido.");
                continue;
            }

            switch (opcao) {
                case 1 -> menuSensores();
                case 2 -> menuPropulsao();
                case 3 -> menuDadosMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("[ERRO] Opcao invalida.");
            }
        }
    }

    private static void menuSensores() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE SENSORES ---");
            System.out.println("  1. Ler todos os sensores");
            System.out.println("  2. Verificar funcionamento");
            System.out.println("  3. Exibir status detalhado");
            System.out.println("  0. Voltar");
            System.out.print("  Opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um numero valido.");
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
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("[ERRO] Opcao invalida.");
            }
        }
    }

    private static void lerTodosSensores() {
        System.out.println("\nRealizando leitura dos sensores...");
        double temp     = sensorTemp.lerValor();
        double pressao  = sensorPressao.lerValor();
        double radiacao = sensorRad.lerValor();

        System.out.printf("  Temperatura: %.2f C%n", temp);
        System.out.printf("  Pressao:     %.2f kPa%n", pressao);
        System.out.printf("  Radiacao:    %.2f mSv%n", radiacao);

        verificarLimitesSensores();
    }

    private static void verificarFuncionamentoSensores() {
        System.out.println("\nVerificando funcionamento dos sensores...");
        System.out.println("  Temperatura: " + (sensorTemp.verificarFuncionamento()    ? "OK" : "FALHA"));
        System.out.println("  Pressao:     " + (sensorPressao.verificarFuncionamento() ? "OK" : "FALHA"));
        System.out.println("  Radiacao:    " + (sensorRad.verificarFuncionamento()     ? "OK" : "FALHA"));
    }

    private static void menuPropulsao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE PROPULSAO ---");
            System.out.println("  1. Ligar Propulsao Quimica");
            System.out.println("  2. Desligar Propulsao Quimica");
            System.out.println("  3. Acelerar Propulsao Quimica");
            System.out.println("  4. Ligar Propulsao Eletrica");
            System.out.println("  5. Desligar Propulsao Eletrica");
            System.out.println("  6. Acelerar Propulsao Eletrica");
            System.out.println("  7. Exibir status dos motores");
            System.out.println("  0. Voltar");
            System.out.print("  Opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um numero valido.");
                continue;
            }

            switch (opcao) {
                case 1 -> propQuimica.ligar();
                case 2 -> propQuimica.desligar();
                case 3 -> {
                    System.out.print("  Digite a potencia (0-100): ");
                    try {
                        double pot = Double.parseDouble(scanner.nextLine().trim());
                        propQuimica.acelerar(pot);
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor invalido.");
                    }
                }
                case 4 -> propEletrica.ligar();
                case 5 -> propEletrica.desligar();
                case 6 -> {
                    System.out.print("  Digite a potencia (0-100): ");
                    try {
                        double pot = Double.parseDouble(scanner.nextLine().trim());
                        propEletrica.acelerar(pot);
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor invalido.");
                    }
                }
                case 7 -> {
                    propQuimica.exibirStatus();
                    propEletrica.exibirStatus();
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("[ERRO] Opcao invalida.");
            }
        }
    }

    private static void menuDadosMissao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE DADOS DA MISSAO ---");
            System.out.println("  1. Ver dados gerais");
            System.out.println("  2. Ver dados restritos (requer senha)");
            System.out.println("  3. Atualizar nivel de combustivel");
            System.out.println("  4. Atualizar coordenadas (requer senha)");
            System.out.println("  0. Voltar");
            System.out.print("  Opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite um numero valido.");
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
                    System.out.print("  Novo nivel de combustivel (0-100): ");
                    try {
                        double nivel = Double.parseDouble(scanner.nextLine().trim());
                        missao.setNivelCombustivel(nivel);
                        System.out.println("  Combustivel atualizado para: " + nivel + "%");
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Valor invalido.");
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
                        System.out.println("[ERRO] Valor invalido.");
                    }
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("[ERRO] Opcao invalida.");
            }
        }
    }

    private static void simularAlertas() {
        System.out.println("\nSimulando leituras e verificando alertas...\n");
        sensorTemp.lerValor();
        sensorPressao.lerValor();
        sensorRad.lerValor();
        verificarLimitesSensores();
    }

    private static void verificarLimitesSensores() {
        verificarSensor("Temperatura", sensorTemp.getValorAtual(),     sensorTemp.getLimite(),     "C");
        verificarSensor("Pressao",     sensorPressao.getValorAtual(),  sensorPressao.getLimite(),  "kPa");
        verificarSensor("Radiacao",    sensorRad.getValorAtual(),      sensorRad.getLimite(),      "mSv");
    }

    private static void verificarSensor(String tipo, double valor, double limite, String unidade) {
        double percentual = (valor / limite) * 100;

        if (percentual >= 100) {
            emitirAlerta("CRITICO", tipo, valor, limite, unidade);
        } else if (percentual >= 85) {
            emitirAlerta("ALERTA", tipo, valor, limite, unidade);
        } else if (percentual >= 70) {
            emitirAlerta("ATENCAO", tipo, valor, limite, unidade);
        } else {
            System.out.println("  [OK] " + tipo + ": " + valor + " " + unidade + " (Normal)");
        }
    }

    private static void emitirAlerta(String nivel, String tipo, double valor, double limite, String unidade) {
        System.out.println("  [" + nivel + "] Sensor de " + tipo
                + ": " + valor + " " + unidade
                + " | Limite: " + limite + " " + unidade);
    }

    private static void exibirStatusCompleto() {
        System.out.println("\n==========================================");
        System.out.println("        STATUS COMPLETO DO SISTEMA");
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

        System.out.println("\nVerificacao automatica de alertas:");
        verificarLimitesSensores();
    }

    private static void exibirBoasVindas() {
        System.out.println("==========================================");
        System.out.println("  PLATAFORMA DE MONITORAMENTO ESPACIAL");
        System.out.println("  Global Solution 2026 - POO");
        System.out.println("==========================================");
        System.out.println("  Luis Otavio Santini Feitosa  RM 563556");
        System.out.println("  Lucas Andrade de Souza       RM 564066");
        System.out.println("==========================================");
        System.out.println("\nSistema inicializado. Sensores ligados.");
    }
}
