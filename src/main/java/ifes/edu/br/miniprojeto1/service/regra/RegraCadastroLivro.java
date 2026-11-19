package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;

/** Regra verificada ao criar ({@code id == null}) ou atualizar um livro. */
public interface RegraCadastroLivro {
    void validar(Long id, LivroRequest request);
}
