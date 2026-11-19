package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;
import ifes.edu.br.miniprojeto1.exception.IsbnJaCadastradoException;
import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/** RN01: o ISBN não pode se repetir entre livros. */
@Component
@Order(1)
@RequiredArgsConstructor
public class IsbnUnicoRegra implements RegraCadastroLivro {

    private final LivroRepository livroRepository;

    @Override
    public void validar(Long id, LivroRequest request) {
        boolean duplicado = Optional.ofNullable(id)
                .map(livroId -> livroRepository.existePorIsbnEIdDiferente(request.isbn(), livroId))
                .orElseGet(() -> livroRepository.existePorIsbn(request.isbn()));
        if (duplicado) {
            throw new IsbnJaCadastradoException(request.isbn());
        }
    }
}
