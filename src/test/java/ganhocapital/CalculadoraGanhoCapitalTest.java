package ganhocapital;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class CalculadoraGanhoCapitalTest {

    @Test
    @DisplayName("Teste Caso 1: Operações com vendas abaixo de 20.000 sem lucro")
    void testeCaso1() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 100),
                new Operacao("sell", 15.00, 50),
                new Operacao("sell", 15.00, 50)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(0.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }

    @Test
    @DisplayName("Teste Caso 2: Lucro e prejuízo sequencial")
    void testeCaso2() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 10000),
                new Operacao("sell", 20.00, 5000),
                new Operacao("sell", 5.00, 5000)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(10000.00),
                new Imposto(0.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }

    @Test
    @DisplayName("Teste Caso 3: Prejuízo deduzindo lucros futuros")
    void testeCaso3() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 10000),
                new Operacao("sell", 5.00, 5000),
                new Operacao("sell", 20.00, 3000)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(1000.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }

    @Test
    @DisplayName("Teste Caso 4: Venda sem lucro nem prejuízo após múltiplas compras")
    void testeCaso4() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 10000),
                new Operacao("buy", 25.00, 5000),
                new Operacao("sell", 15.00, 10000)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(0.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }

    @Test
    @DisplayName("Teste Caso 5: Lucro após prejuízo zerado")
    void testeCaso5() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 10000),
                new Operacao("buy", 25.00, 5000),
                new Operacao("sell", 15.00, 10000),
                new Operacao("sell", 25.00, 5000)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(10000.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }

    @Test
    @DisplayName("Teste Caso 6: Múltiplas vendas com prejuízo e lucro deduzido")
    void testeCaso6() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 10000),
                new Operacao("sell", 2.00, 5000),
                new Operacao("sell", 20.00, 2000),
                new Operacao("sell", 20.00, 2000),
                new Operacao("sell", 25.00, 1000)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(3000.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }

    @Test
    @DisplayName("Teste Caso 7: Compra adicional após vendas e dedução de prejuízo")
    void testeCaso7() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 10000),
                new Operacao("sell", 2.00, 5000),
                new Operacao("sell", 20.00, 2000),
                new Operacao("sell", 20.00, 2000),
                new Operacao("sell", 25.00, 1000),
                new Operacao("buy", 20.00, 10000),
                new Operacao("sell", 15.00, 5000),
                new Operacao("sell", 30.00, 4350),
                new Operacao("sell", 30.00, 650)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(3000.00),
                new Imposto(0.00),
                new Imposto(0.00),
                new Imposto(3700.00),
                new Imposto(0.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }

    @Test
    @DisplayName("Teste Caso 8: Grandes lucros em sequência")
    void testeCaso8() {
        List<Operacao> operacoes = Arrays.asList(
                new Operacao("buy", 10.00, 10000),
                new Operacao("sell", 50.00, 10000),
                new Operacao("buy", 20.00, 10000),
                new Operacao("sell", 50.00, 10000)
        );

        List<Imposto> impostosEsperados = Arrays.asList(
                new Imposto(0.00),
                new Imposto(80000.00),
                new Imposto(0.00),
                new Imposto(60000.00)
        );

        List<Imposto> impostosCalculados = CalculadoraGanhoCapital.calcularImpostos(operacoes);

        assertEquals(impostosEsperados.size(), impostosCalculados.size(), "O número de resultados de impostos está incorreto.");
        for (int i = 0; i < impostosEsperados.size(); i++) {
            assertEquals(impostosEsperados.get(i).getTax(), impostosCalculados.get(i).getTax(), 0.01, "Valor do imposto incorreto na operação " + (i + 1));
        }
    }
}
