package ifes.edu.br.miniprojeto1.repository;

import ifes.edu.br.miniprojeto1.model.Livro;
import java.util.List;
import java.util.Optional;

public interface LivroRepository {

    List<Livro> buscarTodos();

    Optional<Livro> buscarPorId(Long id);

    Livro salvar(Livro livro);

    void removerPorId(Long id);

    boolean existePorId(Long id);

    List<Livro> buscarPorAnoPublicacao(Integer ano);

    List<Livro> buscarPorTitulo(String titulo);

    Optional<Livro> buscarPorIsbn(String isbn);

    boolean existePorIsbn(String isbn);

    boolean existePorIsbnEIdDiferente(String isbn, Long id);
}
