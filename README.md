# Plataforma de Monitoramento de Sistemas Espaciais

**Global Solution 2026 — Programação Orientada a Objetos | FIAP**

---

## Equipe

| Nome | RM |
|------|----|
| Luis Otavio Santini Feitosa | 563556 |
| Lucas Andrade de Souza | 564066 |

---

## Descrição

Sistema de monitoramento de uma estação espacial desenvolvido em Java como parte da Global Solution 2026 da FIAP. O projeto simula o controle de sensores, sistemas de propulsão e dados de missão, com foco na aplicação prática dos quatro pilares de Programação Orientada a Objetos: abstração, interfaces, encapsulamento e herança.

O sistema detecta leituras anormais nos sensores automaticamente e emite alertas por nível de criticidade, além de proteger dados sensíveis da missão com autenticação por senha.

---

## Estrutura do Projeto

```
projeto-espacial/
├── ComponenteEspacial.java       # Classe abstrata base para todos os componentes
├── Sensor.java                   # Interface que define o contrato dos sensores
├── SensorTemperatura.java        # Sensor de temperatura em graus Celsius
├── SensorPressao.java            # Sensor de pressão atmosférica em kPa
├── SensorRadiacao.java           # Sensor de radiação em mSv
├── DadosMissao.java              # Gerenciamento encapsulado dos dados da missão
├── SistemaPropulsao.java         # Classe abstrata para sistemas de propulsão
├── PropulsaoQuimica.java         # Motor químico (hidrogênio líquido + oxigênio)
├── PropulsaoEletrica.java        # Motor iônico elétrico
└── SistemaMonitoramento.java     # Classe principal com menu interativo
```

---

## Como Executar

**Pré-requisito:** Java JDK 11 ou superior.

```bash
# Compilar todos os arquivos
javac *.java

# Executar o sistema
java SistemaMonitoramento
```

A senha de acesso aos dados restritos da missão é `SENHA123`.

---

## Funcionalidades

### Sensores
- Leitura simulada de temperatura (°C), pressão (kPa) e radiação (mSv)
- Verificação de funcionamento individual de cada sensor
- Limites de alerta configurados por sensor
- Detecção automática de valores fora do limite após cada leitura

### Propulsão
- Ligar e desligar os motores químico e elétrico independentemente
- Acelerar com porcentagem de potência entre 0 e 100%
- Validação de valores inválidos (potência negativa ou acima de 100%)
- Cálculo de empuxo gerado em kN com base na potência atual

### Dados da Missão
- Visualização de dados gerais sem restrição
- Acesso a coordenadas e dados sensíveis protegido por senha
- Nível de combustível com validação de intervalo (0–100%)
- Alerta automático quando o combustível cai abaixo de 20%

### Sistema de Alertas

| Nível | Condição |
|-------|----------|
| ATENCAO | Valor entre 70% e 85% do limite máximo |
| ALERTA | Valor entre 85% e 100% do limite máximo |
| CRITICO | Valor acima do limite máximo configurado |

---

## Conceitos de POO Aplicados

### Classe Abstrata
`ComponenteEspacial` define os atributos e comportamentos comuns a todos os componentes da estação (`id`, `nome`, `status`, `temperatura`), além dos métodos concretos `ligar()` e `desligar()`. O método abstrato `exibirStatus()` obriga todas as subclasses a fornecerem sua própria implementação de exibição.

`SistemaPropulsao` estende `ComponenteEspacial` e acrescenta a lógica comum aos motores, incluindo o método `validarEAplicarPotencia()`, reutilizado pelas subclasses via `super()`.

### Interface
`Sensor` define o contrato que todos os sensores devem seguir: `lerValor()`, `verificarFuncionamento()`, `retornarTipo()`, `valorAcimaDoLimite()` e `getLimite()`. As três classes de sensor implementam essa interface de forma independente, com comportamentos e unidades de medida distintos.

### Encapsulamento
`DadosMissao` possui todos os atributos declarados como `private`. O acesso é feito exclusivamente por getters e setters, que validam os dados recebidos — por exemplo, o nível de combustível não aceita valores fora do intervalo 0–100. Coordenadas e dados restritos só são acessíveis mediante autenticação com senha correta.

### Herança
`PropulsaoQuimica` e `PropulsaoEletrica` herdam de `SistemaPropulsao`, chamam `super()` para reaproveitar a validação de potência da classe mãe e sobrescrevem o método `acelerar()` com comportamento específico de cada tecnologia de propulsão.
