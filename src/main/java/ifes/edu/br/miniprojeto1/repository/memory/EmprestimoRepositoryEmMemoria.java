package ifes.edu.br.miniprojeto1.repository.memory;

import ifes.edu.br.miniprojeto1.model.Emprestimo;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmprestimoRepositoryEmMemoria implements EmprestimoRepository {

    private final Map<Long, Emprestimo> dados = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    @Override
    public List<Emprestimo> buscarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Optional<Emprestimo> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        if (emprestimo.getId() == null) {
            emprestimo.setId(sequencia.incrementAndGet());
        }
        dados.put(emprestimo.getId(), emprestimo);
        return emprestimo;
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
    public List<Emprestimo> buscarPorLivroId(Long livroId) {
        return dados.values().stream()
                .filter(e -> e.getLivroId().equals(livroId))
                .toList();
    }

    @Override
    public List<Emprestimo> buscarPorLeitorId(Long leitorId) {
        return dados.values().stream()
                .filter(e -> e.getLeitorId().equals(leitorId))
                .toList();
    }

    @Override
    public List<Emprestimo> buscarPorDataEmprestimo(LocalDate dataEmprestimo) {
        return dados.values().stream()
                .filter(e -> e.getDataEmprestimo().equals(dataEmprestimo))
                .toList();
    }

    @Override
    public long contarAtivosPorLivroId(Long livroId) {
        return dados.values().stream()
                .filter(e -> e.getLivroId().equals(livroId) && estaAtivo(e))
                .count();
    }

    @Override
    public long contarAtivosPorLeitorId(Long leitorId) {
        return dados.values().stream()
                .filter(e -> e.getLeitorId().equals(leitorId) && estaAtivo(e))
                .count();
    }

    @Override
    public boolean existeAtivoPorLivroId(Long livroId) {
        return dados.values().stream()
                .anyMatch(e -> e.getLivroId().equals(livroId) && estaAtivo(e));
    }

    @Override
    public boolean existeAtivoPorLeitorId(Long leitorId) {
        return dados.values().stream()
                .anyMatch(e -> e.getLeitorId().equals(leitorId) && estaAtivo(e));
    }

    @Override
    public boolean existeAtrasadoPorLeitorId(Long leitorId, LocalDate hoje) {
        return dados.values().stream()
                .anyMatch(e -> e.getLeitorId().equals(leitorId)
                        && estaAtivo(e)
                        && e.getDataPrevistaDevolucao().isBefore(hoje));
    }

    private boolean estaAtivo(Emprestimo emprestimo) {
        return emprestimo.getDataDevolucao() == null;
    }
}
