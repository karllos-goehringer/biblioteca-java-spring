package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaMulta;
import ifes.edu.br.miniprojeto1.dto.filtro.MultaFiltro;
import ifes.edu.br.miniprojeto1.exception.LeitorNaoEncontradoException;
import ifes.edu.br.miniprojeto1.model.Multa;
import ifes.edu.br.miniprojeto1.repository.LeitorRepository;
import ifes.edu.br.miniprojeto1.repository.MultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscaMultaPorLeitor implements BuscaMulta {

    private final MultaRepository multaRepository;
    private final LeitorRepository leitorRepository;

    @Override
    public CriterioBuscaMulta criterio() {
        return CriterioBuscaMulta.LEITOR;
    }

    @Override
    public List<Multa> buscar(MultaFiltro filtro) {
        Long leitorId = leitorRepository.buscarPorId(filtro.leitorId())
                .orElseThrow(() -> new LeitorNaoEncontradoException(filtro.leitorId()))
                .getId();
        return multaRepository.buscarPorLeitorId(leitorId);
    }
}
