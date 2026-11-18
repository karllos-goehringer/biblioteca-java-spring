package ifes.edu.br.miniprojeto1.repository;

import ifes.edu.br.miniprojeto1.model.Multa;

import java.util.List;
import java.util.Optional;

public interface MultaRepository {

    List<Multa> buscarTodos();

    Optional<Multa> buscarPorId(Long id);

    Multa salvar(Multa multa);

    void removerPorId(Long id);

    boolean existePorId(Long id);

    List<Multa> buscarPorEmprestimoId(Long emprestimoId);

    List<Multa> buscarPorLeitorId(Long leitorId);
}
