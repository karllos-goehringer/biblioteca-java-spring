package ifes.edu.br.miniprojeto1.service;

import ifes.edu.br.miniprojeto1.dto.MultaResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaMulta;
import ifes.edu.br.miniprojeto1.dto.filtro.MultaFiltro;
import ifes.edu.br.miniprojeto1.exception.MultaNaoEncontradaException;
import ifes.edu.br.miniprojeto1.mapper.MultaMapper;
import ifes.edu.br.miniprojeto1.model.Multa;
import ifes.edu.br.miniprojeto1.repository.MultaRepository;
import ifes.edu.br.miniprojeto1.service.busca.BuscaMulta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MultaService {

    private final MultaRepository multaRepository;
    private final MultaMapper multaMapper;
    private final Clock clock;
    private final Map<CriterioBuscaMulta, BuscaMulta> buscas;


    public List<MultaResponse> listar(MultaFiltro filtro) {
        List<Multa> multas = buscas.get(filtro.criterio()).buscar(filtro).stream()
                .sorted(Comparator.comparing(Multa::getId))
                .toList();
        return multaMapper.toResponseList(multas);
    }

    public MultaResponse buscarPorId(Long id) {
        return multaMapper.toResponse(buscarEntidade(id));
    }

    public MultaResponse pagar(Long id) {
        Multa multa = buscarEntidade(id);
        multa.pagar(LocalDate.now(clock));
        return multaMapper.toResponse(multaRepository.salvar(multa));
    }

    private Multa buscarEntidade(Long id) {
        return multaRepository.buscarPorId(id)
                .orElseThrow(() -> new MultaNaoEncontradaException(id));
    }
}
