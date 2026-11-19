package ifes.edu.br.miniprojeto1.repository;

import ifes.edu.br.miniprojeto1.model.Leitor;

import java.util.List;
import java.util.Optional;

public interface LeitorRepository {

    List<Leitor> buscarTodos();

    Optional<Leitor> buscarPorId(Long id);

    Leitor salvar(Leitor leitor);

    void removerPorId(Long id);

    boolean existePorId(Long id);

    boolean existePorEmail(String email);

    boolean existePorEmailEIdDiferente(String email, Long id);
}
