package ifes.edu.br.miniprojeto1.repository;

import ifes.edu.br.miniprojeto1.model.Leitor;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de persistência de leitores. Usa os nomes do Spring Data para que a
 * implementação em memória possa ser trocada por um repositório JPA/SQL sem
 * alterar o service.
 */
public interface LeitorRepository {

    List<Leitor> findAll();

    Optional<Leitor> findById(Long id);

    Leitor save(Leitor leitor);

    void deleteById(Long id);

    boolean existsById(Long id);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
