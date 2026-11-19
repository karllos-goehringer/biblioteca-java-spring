package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.exception.LivroIndisponivelException;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import ifes.edu.br.miniprojeto1.service.strategy.PoliticaEmprestimo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/** RN03: só há empréstimo se houver exemplar disponível (quantidadeTotal - empréstimos ativos). */
@Component
@Order(1)
@RequiredArgsConstructor
public class LivroDisponivelRegra implements RegraEmprestimo {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public void validar(Livro livro, Leitor leitor, PoliticaEmprestimo politica, LocalDate hoje) {
        long disponiveis = livro.getQuantidadeTotal() - emprestimoRepository.contarAtivosPorLivroId(livro.getId());
        if (disponiveis <= 0) {
            throw new LivroIndisponivelException(livro.getId());
        }
    }
}
