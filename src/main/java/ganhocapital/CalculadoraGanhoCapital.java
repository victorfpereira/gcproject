package ganhocapital;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraGanhoCapital {

    public static List<Imposto> calcularImpostos(List<Operacao> operacoes) {
        List<Imposto> impostos = new ArrayList<>();
        double precoMedio = 0.0;
        int quantidadeAtual = 0;
        double prejuizosAcumulados = 0.0;

        for (Operacao operacao : operacoes) {
            if (operacao.getTipoOperacao().equals("buy")) {
                // Atualiza o preço médio ponderado
                precoMedio = calcularPrecoMedio(precoMedio, quantidadeAtual, operacao);
                quantidadeAtual += operacao.getQuantidade();
                impostos.add(new Imposto(0.00));
            } else if (operacao.getTipoOperacao().equals("sell")) {
                quantidadeAtual -= operacao.getQuantidade();
                double valorVendaTotal = calcularValorVendaTotal(operacao);
                double lucroOuPrejuizo = calcularLucroOuPrejuizo(precoMedio, operacao);

                // Aplica os prejuízos acumulados ao lucro antes de calcular o imposto
                if (lucroOuPrejuizo > 0) {
                    if (prejuizosAcumulados > 0) {
                        if (lucroOuPrejuizo > prejuizosAcumulados) {
                            lucroOuPrejuizo -= prejuizosAcumulados;
                            prejuizosAcumulados = 0;
                        } else {
                            prejuizosAcumulados -= lucroOuPrejuizo;
                            lucroOuPrejuizo = 0;
                        }
                    }

                    if (lucroOuPrejuizo > 0) {
                        double imposto = (valorVendaTotal > 20000) ? lucroOuPrejuizo * 0.20 : 0.00;
                        impostos.add(new Imposto(arredondarParaDuasCasasDecimais(imposto)));
                    } else {
                        impostos.add(new Imposto(0.00));
                    }
                } else {
                    // Acumula prejuízo se houver
                    prejuizosAcumulados += Math.abs(lucroOuPrejuizo);
                    impostos.add(new Imposto(0.00));
                }
            }
        }
        return impostos;
    }

    private static double calcularPrecoMedio(double precoAtual, int quantidadeTotal, Operacao operacao) {
        double custoTotal = (quantidadeTotal * precoAtual) + (operacao.getQuantidade() * operacao.getCustoUnitario());
        return custoTotal / (quantidadeTotal + operacao.getQuantidade());
    }

    private static double calcularValorVendaTotal(Operacao operacao) {
        return operacao.getQuantidade() * operacao.getCustoUnitario();
    }

    private static double calcularLucroOuPrejuizo(double precoMedio, Operacao operacao) {
        return (operacao.getCustoUnitario() - precoMedio) * operacao.getQuantidade();
    }

    private static double arredondarParaDuasCasasDecimais(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
