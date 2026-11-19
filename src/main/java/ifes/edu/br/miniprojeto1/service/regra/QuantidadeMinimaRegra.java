package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;
import ifes.edu.br.miniprojeto1.exception.QuantidadeMenorQueEmprestimosAtivosException;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/** Na atualização, quantidadeTotal não pode ficar menor que os empréstimos ativos do livro. */
@Component
@Order(2)
@RequiredArgsConstructor
public class QuantidadeMinimaRegra implements RegraCadastroLivro {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public void validar(Long id, LivroRequest request) {
        Optional.ofNullable(id).ifPresent(livroId -> {
            long ativos = emprestimoRepository.contarAtivosPorLivroId(livroId);
            if (request.quantidadeTotal() < ativos) {
                throw new QuantidadeMenorQueEmprestimosAtivosException(livroId, ativos);
            }
        });
    }
}
