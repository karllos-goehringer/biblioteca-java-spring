package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaMulta;
import ifes.edu.br.miniprojeto1.dto.filtro.MultaFiltro;
import ifes.edu.br.miniprojeto1.model.Multa;
import ifes.edu.br.miniprojeto1.repository.MultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscaTodasMultas implements BuscaMulta {

    private final MultaRepository multaRepository;

    @Override
    public CriterioBuscaMulta criterio() {
        return CriterioBuscaMulta.TODOS;
    }

    @Override
    public List<Multa> buscar(MultaFiltro filtro) {
        return multaRepository.buscarTodos();
    }
}
