package ifes.edu.br.miniprojeto1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultaAvaria extends Multa {
    private NivelDegradacao nivelDegradacao;
}
