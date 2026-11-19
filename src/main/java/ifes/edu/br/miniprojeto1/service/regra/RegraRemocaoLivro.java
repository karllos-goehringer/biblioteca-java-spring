package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.model.Livro;

/** Regra verificada antes de remover um livro. */
public interface RegraRemocaoLivro {
    void validar(Livro livro);
}
