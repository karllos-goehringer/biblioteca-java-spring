package ifes.edu.br.miniprojeto1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leitor {
    private Long id;
    private String nome;
    private String email;
    private TipoPessoa tipoPessoa;
}
