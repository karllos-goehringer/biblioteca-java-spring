package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;

public interface RegraCadastroLivro {
    void validar(Long id, LivroRequest request);
}
