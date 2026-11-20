package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.exception.LivroComEmprestimoAtivoException;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
