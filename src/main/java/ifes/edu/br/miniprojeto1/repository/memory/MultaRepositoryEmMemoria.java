package ifes.edu.br.miniprojeto1.repository.memory;

import ifes.edu.br.miniprojeto1.model.Multa;
import ifes.edu.br.miniprojeto1.repository.MultaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MultaRepositoryEmMemoria implements MultaRepository {

    private final Map<Long, Multa> dados = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    @Override
    public List<Multa> buscarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Optional<Multa> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public Multa salvar(Multa multa) {
        if (multa.getId() == null) {
            multa.setId(sequencia.incrementAndGet());
        }
        dados.put(multa.getId(), multa);
        return multa;
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
    public List<Multa> buscarPorEmprestimoId(Long emprestimoId) {
        return dados.values().stream()
                .filter(m -> m.getEmprestimoId().equals(emprestimoId))
                .toList();
    }

    @Override
    public List<Multa> buscarPorLeitorId(Long leitorId) {
        return dados.values().stream()
                .filter(m -> m.getLeitorId().equals(leitorId))
                .toList();
    }
}
