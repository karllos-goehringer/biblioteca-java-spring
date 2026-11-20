package ifes.edu.br.miniprojeto1.service;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;
import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLeitor;
import ifes.edu.br.miniprojeto1.dto.filtro.LeitorFiltro;
import ifes.edu.br.miniprojeto1.exception.LeitorNaoEncontradoException;
import ifes.edu.br.miniprojeto1.mapper.LeitorMapper;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.repository.LeitorRepository;
import ifes.edu.br.miniprojeto1.service.busca.BuscaLeitor;
import ifes.edu.br.miniprojeto1.service.regra.RegraCadastroLeitor;
import ifes.edu.br.miniprojeto1.service.regra.RegraRemocaoLeitor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LeitorService {

    private final LeitorRepository leitorRepository;
    private final LeitorMapper leitorMapper;
    private final Map<CriterioBuscaLeitor, BuscaLeitor> buscas;
    private final List<RegraCadastroLeitor> regrasCadastro;
    private final List<RegraRemocaoLeitor> regrasRemocao;

    public LeitorService(LeitorRepository leitorRepository,
                         LeitorMapper leitorMapper,
                         List<BuscaLeitor> buscas,
                         List<RegraCadastroLeitor> regrasCadastro,
                         List<RegraRemocaoLeitor> regrasRemocao) {
        this.leitorRepository = leitorRepository;
        this.leitorMapper = leitorMapper;
        this.buscas = buscas.stream()
                .collect(Collectors.toMap(BuscaLeitor::criterio, Function.identity()));
        this.regrasCadastro = regrasCadastro;
        this.regrasRemocao = regrasRemocao;
    }

    public List<Leitor> listar(LeitorFiltro filtro) {
        return buscas.get(filtro.criterio()).buscar(filtro).stream()
                .sorted(Comparator.comparing(Leitor::getId))
                .toList();
    }

    public Leitor buscarPorId(Long id) {
        return leitorRepository.buscarPorId(id)
                .orElseThrow(() -> new LeitorNaoEncontradoException(id));
    }

    public Leitor criar(LeitorRequest request) {
        regrasCadastro.forEach(regra -> regra.validar(null, request));        // RN02
        Leitor leitor = leitorMapper.toEntity(request);
        return leitorRepository.salvar(leitor);
    }

    public Leitor atualizar(Long id, LeitorRequest request) {
        Leitor leitor = buscarPorId(id);                                        // RN10
        regrasCadastro.forEach(regra -> regra.validar(id, request));          // RN02
        leitorMapper.atualizarEntidade(leitor, request);
        return leitorRepository.salvar(leitor);
    }

    public void remover(Long id) {
        Leitor leitor = buscarPorId(id);                                        // RN10
        regrasRemocao.forEach(regra -> regra.validar(leitor));                 // RN09
        leitorRepository.removerPorId(id);
    }
}
