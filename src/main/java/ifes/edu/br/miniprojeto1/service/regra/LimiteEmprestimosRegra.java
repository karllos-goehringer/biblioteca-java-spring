package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.exception.LimiteEmprestimosExcedidoException;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import ifes.edu.br.miniprojeto1.service.strategy.PoliticaEmprestimo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/** RN04: limite de empréstimos ativos conforme a política do tipo de leitor. */
@Component
@Order(2)
@RequiredArgsConstructor
public class LimiteEmprestimosRegra implements RegraEmprestimo {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public void validar(Livro livro, Leitor leitor, PoliticaEmprestimo politica, LocalDate hoje) {
        if (emprestimoRepository.contarAtivosPorLeitorId(leitor.getId()) >= politica.limiteEmprestimos()) {
            throw new LimiteEmprestimosExcedidoException(leitor.getId(), politica.limiteEmprestimos());
        }
    }
}
