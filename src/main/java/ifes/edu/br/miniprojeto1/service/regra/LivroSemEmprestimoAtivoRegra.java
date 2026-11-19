package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.exception.LivroComEmprestimoAtivoException;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/** RN09: livro com empréstimo ativo não pode ser removido. */
@Component
@RequiredArgsConstructor
public class LivroSemEmprestimoAtivoRegra implements RegraRemocaoLivro {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public void validar(Livro livro) {
        if (emprestimoRepository.existeAtivoPorLivroId(livro.getId())) {
            throw new LivroComEmprestimoAtivoException(livro.getId());
        }
    }
}
