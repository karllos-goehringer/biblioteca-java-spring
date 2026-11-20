package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.service.strategy.PoliticaEmprestimo;

import java.time.LocalDate;

public interface RegraEmprestimo {
    void validar(Livro livro, Leitor leitor, PoliticaEmprestimo politica, LocalDate hoje);
}
