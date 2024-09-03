# Calculadora de Ganho de Capital

Este projeto implementa uma calculadora de ganho de capital em Java, que calcula o imposto devido sobre operações de compra e venda de ações. A calculadora leva em consideração a regra de isenção de imposto para vendas de até R$ 20.000,00 e permite a dedução de prejuízos de operações anteriores.

## Estrutura do Projeto

- **`CalculadoraGanhoCapital`**: Classe principal que contém a lógica de cálculo de impostos.
- **`Operacao`**: Classe que representa uma operação de compra ou venda.
- **`Imposto`**: Classe que representa o imposto calculado para cada operação.
- **`Principal`**: Classe que processa a entrada de dados (JSON) e executa a calculadora, exibindo os resultados no terminal.
- **`CalculadoraGanhoCapitalTest`**: Classe de testes que valida a lógica da calculadora usando JUnit.

## Requisitos

- **Java 17** ou superior
- **Maven** para gerenciamento de dependências e execução de testes


## Como Executar

### 1. Empacotar o projeto para .JAR

Executar o comando a seguir:

```bash
mvn clean package
```

### 2. Executar a Classe Principal
   Após criar o arquivo .JAR, executar o script abaixo dependendo do seu S.O.

No terminal, execute:
- Windows
  - ```bash
    ganhocapital.bat
    ```
- Linux
  1) ```bash
        chmod +x ganhocapital.sh
        ```
  2)  ```bash
       ganhocapital.sh
       ```

   Após executar o comando, o programa ficará esperando a entrada de dados no formato JSON.

### Exemplo de Entrada e Saída:

Entrada JSON:
```
[{"operation":"buy", "unit-cost":10.00, "quantity": 100},{"operation":"sell", "unit-cost":15.00, "quantity": 50},{"operation":"sell", "unit-cost":15.00, "quantity": 50}]
```
Saída JSON:
```
[{"Tax":0.00},{"Tax":0.00},{"Tax":0.00}]
```