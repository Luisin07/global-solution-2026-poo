# 🚀 Plataforma de Monitoramento de Sistemas Espaciais

> **Global Solution 2026 — Programação Orientada a Objetos | FIAP**

---

## 👨‍🚀 Equipe

| Nome | RM |
|------|----|
| Luis Otavio Santini Feitosa | 563556 |
| Lucas Andrade de Souza | 564066 |

---

## 📋 Sobre o Projeto

Sistema de monitoramento de uma estação espacial desenvolvido em Java, aplicando os quatro pilares de POO exigidos na Global Solution 2026:

- **Classe Abstrata** — `ComponenteEspacial` e `SistemaPropulsao`
- **Interface** — `Sensor` implementada em 3 sensores distintos
- **Encapsulamento** — `DadosMissao` com atributos privados e acesso por senha
- **Herança** — `PropulsaoQuimica` e `PropulsaoEletrica` herdando de `SistemaPropulsao`

---

## 🗂️ Estrutura do Projeto

```
projeto-espacial/
├── ComponenteEspacial.java     # Classe abstrata base
├── Sensor.java                 # Interface dos sensores
├── SensorTemperatura.java      # Implementa Sensor — leitura em °C
├── SensorPressao.java          # Implementa Sensor — leitura em kPa
├── SensorRadiacao.java         # Implementa Sensor — leitura em mSv
├── DadosMissao.java            # Encapsulamento — dados protegidos por senha
├── SistemaPropulsao.java       # Classe abstrata de propulsão
├── PropulsaoQuimica.java       # Herda SistemaPropulsao — motor químico
├── PropulsaoEletrica.java      # Herda SistemaPropulsao — motor iônico
└── SistemaMonitoramento.java   # Classe principal com menu interativo
```

---

## ⚙️ Como Executar

### Pré-requisito
- Java JDK 11 ou superior instalado

### Compilar
```bash
javac *.java
```

### Rodar
```bash
java SistemaMonitoramento
```

---

## 🖥️ Funcionalidades

### 1. Sistema de Sensores
- Leitura simulada de temperatura (°C), pressão (kPa) e radiação (mSv)
- Verificação de funcionamento de cada sensor
- Detecção automática quando valor ultrapassa o limite configurado

### 2. Sistema de Propulsão
- Ligar/desligar motores químico e elétrico
- Acelerar com % de potência (0–100) com validação
- Cálculo de empuxo gerado em kN

### 3. Dados da Missão (Encapsulamento)
- Dados gerais acessíveis livremente
- Coordenadas e dados restritos protegidos por senha (`SENHA123`)
- Nível de combustível com validação e alerta automático abaixo de 20%

### 4. Sistema de Alertas
Três níveis automáticos após cada leitura de sensor:

| Nível | Condição |
|-------|----------|
| ⚠️ ATENÇÃO | Valor entre 70% e 85% do limite |
| 🔶 ALERTA | Valor entre 85% e 100% do limite |
| 🔴 CRÍTICO | Valor acima do limite |

---

## 📐 Conceitos de POO Aplicados

### Classe Abstrata
`ComponenteEspacial` define os atributos comuns (`id`, `nome`, `status`, `temperatura`) e o método abstrato `exibirStatus()`, obrigando todas as subclasses a implementá-lo.

### Interface
`Sensor` define o contrato com os métodos `lerValor()`, `verificarFuncionamento()`, `retornarTipo()`, `valorAcimaDoLimite()` e `getLimite()`. Os três sensores implementam essa interface de forma independente.

### Encapsulamento
`DadosMissao` tem todos os atributos `private`. Getters e setters validam os dados (ex: combustível não aceita valores fora de 0–100). Coordenadas só são acessadas com a senha correta.

### Herança
`PropulsaoQuimica` e `PropulsaoEletrica` herdam de `SistemaPropulsao`, utilizam `super()` para chamar a validação da classe mãe e sobrescrevem `acelerar()` com comportamento específico de cada tipo de motor.