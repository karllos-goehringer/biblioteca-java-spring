package ifes.edu.br.miniprojeto1.service;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;
import ifes.edu.br.miniprojeto1.dto.LeitorResponse;
import ifes.edu.br.miniprojeto1.exception.EmailJaCadastradoException;
import ifes.edu.br.miniprojeto1.exception.LeitorNaoEncontradoException;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.model.TipoPessoa;
import ifes.edu.br.miniprojeto1.repository.LeitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeitorService {

    private final LeitorRepository leitorRepository;

    public List<LeitorResponse> listar(TipoPessoa tipo) {
        return leitorRepository.findAll().stream()
                .filter(l -> tipo == null || l.getTipoPessoa() == tipo)
                .sorted(Comparator.comparing(Leitor::getId))
                .map(this::toResponse)
                .toList();
    }

    public LeitorResponse buscarPorId(Long id) {
        return toResponse(buscarEntidade(id));
    }

    public LeitorResponse criar(LeitorRequest request) {
        if (leitorRepository.existsByEmailIgnoreCase(request.email())) {
            throw new EmailJaCadastradoException(request.email());
        }
        Leitor leitor = new Leitor(null, request.nome(), request.email(), request.tipo());
        return toResponse(leitorRepository.save(leitor));
    }

    public LeitorResponse atualizar(Long id, LeitorRequest request) {
        Leitor leitor = buscarEntidade(id);
        if (leitorRepository.existsByEmailIgnoreCaseAndIdNot(request.email(), id)) {
            throw new EmailJaCadastradoException(request.email());
        }
        leitor.setNome(request.nome());
        leitor.setEmail(request.email());
        leitor.setTipoPessoa(request.tipo());
        return toResponse(leitorRepository.save(leitor));
    }

    public void remover(Long id) {
        if (!leitorRepository.existsById(id)) {
            throw new LeitorNaoEncontradoException(id);
        }
        // TODO RN09: impedir remoção se o leitor tiver empréstimo ativo (quando o EmprestimoRepository existir)
        leitorRepository.deleteById(id);
    }

    /** Usado também por outros services (ex.: empréstimo) para obter a entidade. */
    public Leitor buscarEntidade(Long id) {
        return leitorRepository.findById(id)
                .orElseThrow(() -> new LeitorNaoEncontradoException(id));
    }

    private LeitorResponse toResponse(Leitor leitor) {
        return new LeitorResponse(leitor.getId(), leitor.getNome(), leitor.getEmail(), leitor.getTipoPessoa());
    }
}
