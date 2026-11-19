package ifes.edu.br.miniprojeto1.repository.memory;

import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LivroRepositoryEmMemoria implements LivroRepository {

    private final Map<Long, Livro> dados = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    @Override
    public List<Livro> buscarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public Livro salvar(Livro livro) {
        if (livro.getId() == null) {
            livro.setId(sequencia.incrementAndGet());
        }
        dados.put(livro.getId(), livro);
        return livro;
    }

    @Override
    public void removerPorId(Long id) {
        dados.remove(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return dados.containsKey(id);
    }

    @Override
    public List<Livro> buscarPorAnoPublicacao(Integer ano) {
        return dados.values().stream()
                .filter(l -> Objects.equals(l.getAnoPublicacao(), ano))
                .toList();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        String trecho = titulo.toLowerCase(Locale.ROOT);
        return dados.values().stream()
                .filter(l -> l.getTitulo().toLowerCase(Locale.ROOT).contains(trecho))
                .toList();
    }

    @Override
    public List<Livro> buscarPorAutor(String autor) {
        String trecho = autor.toLowerCase(Locale.ROOT);
        return dados.values().stream()
                .filter(l -> l.getAutor().toLowerCase(Locale.ROOT).contains(trecho))
                .toList();
    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {
        return dados.values().stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public boolean existePorIsbn(String isbn) {
        return dados.values().stream()
                .anyMatch(l -> l.getIsbn().equals(isbn));
    }

    @Override
    public boolean existePorIsbnEIdDiferente(String isbn, Long id) {
        return dados.values().stream()
                .anyMatch(l -> !l.getId().equals(id) && l.getIsbn().equals(isbn));
    }
}
