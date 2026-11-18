package ifes.edu.br.miniprojeto1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn; //13 digitos e unico
    private Integer anoPublicacao;
    private Integer quantidadeTotal;

}
