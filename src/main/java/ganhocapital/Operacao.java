package ganhocapital;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operacao {
    @JsonProperty("operation")
    private String tipoOperacao;
    @JsonProperty("unit-cost")
    private double custoUnitario;
    @JsonProperty("quantity")
    private int quantidade;
}
