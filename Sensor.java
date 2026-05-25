/**
 * Interface que define o "contrato" de comportamento para todos os sensores.
 * Qualquer sensor da plataforma DEVE implementar estes métodos.
 *
 * Autores: Luis Otavio Santini Feitosa (RM 563556) e Lucas Andrade de Souza (RM 564066)
 */
public interface Sensor {

    /**
     * Realiza a leitura do valor atual do sensor.
     * @return valor lido (simulado com valores aleatórios)
     */
    double lerValor();

    /**
     * Verifica se o sensor está funcionando corretamente.
     * @return true se estiver OK, false se houver falha
     */
    boolean verificarFuncionamento();

    /**
     * Retorna o tipo do sensor (ex: "Temperatura", "Pressão", "Radiação").
     * @return string com o tipo do sensor
     */
    String retornarTipo();

    /**
     * Verifica se o valor atual ultrapassou o limite de alerta.
     * @return true se o valor estiver acima do limite
     */
    boolean valorAcimaDoLimite();

    /**
     * Retorna o limite máximo configurado para o sensor.
     * @return valor do limite
     */
    double getLimite();
}
