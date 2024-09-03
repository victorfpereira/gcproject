package ganhocapital;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapeadorObjeto = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine().trim();
            if (!linha.isEmpty()) {
                // Processa a linha para encontrar múltiplas listas JSON
                List<String> listasJson = dividirListasJson(linha);

                for (String listaJson : listasJson) {
                    List<Operacao> operacoes = mapeadorObjeto.readValue(listaJson, mapeadorObjeto.getTypeFactory().constructCollectionType(List.class, Operacao.class));
                    List<Imposto> impostos = CalculadoraGanhoCapital.calcularImpostos(operacoes);
                    System.out.println(mapeadorObjeto.writeValueAsString(impostos));
                }
            }
        }
    }

    // Método que divide a linha em múltiplas listas JSON
    private static List<String> dividirListasJson(String entrada) {
        List<String> listasJson = new ArrayList<>();
        int contagemColchetes = 0;
        StringBuilder jsonAtual = new StringBuilder();

        for (char c : entrada.toCharArray()) {
            if (c == '[') {
                if (contagemColchetes == 0 && jsonAtual.length() > 0) {
                    listasJson.add(jsonAtual.toString());
                    jsonAtual.setLength(0);
                }
                contagemColchetes++;
            }
            if (c == ']') {
                contagemColchetes--;
            }
            jsonAtual.append(c);

            if (contagemColchetes == 0 && jsonAtual.length() > 0) {
                listasJson.add(jsonAtual.toString());
                jsonAtual.setLength(0);
            }
        }

        return listasJson;
    }
}
