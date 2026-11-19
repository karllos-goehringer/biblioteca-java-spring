package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLeitor;
import ifes.edu.br.miniprojeto1.dto.filtro.LeitorFiltro;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.repository.LeitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscaTodosLeitores implements BuscaLeitor {

    private final LeitorRepository leitorRepository;

    @Override
    public CriterioBuscaLeitor criterio() {
        return CriterioBuscaLeitor.TODOS;
    }

    @Override
    public List<Leitor> buscar(LeitorFiltro filtro) {
        return leitorRepository.buscarTodos();
    }
}
