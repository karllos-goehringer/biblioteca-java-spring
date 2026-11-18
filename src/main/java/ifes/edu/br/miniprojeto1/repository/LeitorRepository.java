package ifes.edu.br.miniprojeto1.repository;

import ifes.edu.br.miniprojeto1.model.Leitor;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de persistência de leitores. O service depende só desta interface,
 * então a implementação (em memória, banco etc.) pode ser trocada sem alterá-lo.
 */
public interface LeitorRepository {

    List<Leitor> buscarTodos();

    Optional<Leitor> buscarPorId(Long id);

    Leitor salvar(Leitor leitor);

    void removerPorId(Long id);

    boolean existePorId(Long id);

    /** RN02: verifica e-mail ignorando maiúsculas/minúsculas. */
    boolean existePorEmail(String email);

    /** RN02 na atualização: e-mail usado por outro leitor que não o de {@code id}. */
    boolean existePorEmailEIdDiferente(String email, Long id);
}
