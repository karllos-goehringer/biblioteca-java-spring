package ifes.edu.br.miniprojeto1.repository.memory;

import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.repository.LeitorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LeitorRepositoryEmMemoria implements LeitorRepository {

    private final Map<Long, Leitor> dados = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    @Override
    public List<Leitor> findAll() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public Optional<Leitor> findById(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public Leitor save(Leitor leitor) {
        if (leitor.getId() == null) {
            leitor.setId(sequencia.incrementAndGet());
        }
        dados.put(leitor.getId(), leitor);
        return leitor;
    }

    @Override
    public void deleteById(Long id) {
        dados.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return dados.containsKey(id);
    }

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return dados.values().stream()
                .anyMatch(l -> l.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id) {
        return dados.values().stream()
                .anyMatch(l -> !l.getId().equals(id) && l.getEmail().equalsIgnoreCase(email));
    }
}
