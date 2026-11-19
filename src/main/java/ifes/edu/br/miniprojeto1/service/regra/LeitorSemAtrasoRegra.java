package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.exception.LeitorComEmprestimoAtrasadoException;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import ifes.edu.br.miniprojeto1.service.strategy.PoliticaEmprestimo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/** RN05: leitor com empréstimo atrasado não pode fazer novo empréstimo. */
@Component
@Order(3)
@RequiredArgsConstructor
public class LeitorSemAtrasoRegra implements RegraEmprestimo {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public void validar(Livro livro, Leitor leitor, PoliticaEmprestimo politica, LocalDate hoje) {
        if (emprestimoRepository.existeAtrasadoPorLeitorId(leitor.getId(), hoje)) {
            throw new LeitorComEmprestimoAtrasadoException(leitor.getId());
        }
    }
}
