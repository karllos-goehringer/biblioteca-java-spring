package ifes.edu.br.miniprojeto1.service;

import ifes.edu.br.miniprojeto1.dto.EmprestimoRequest;
import ifes.edu.br.miniprojeto1.dto.EmprestimoResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaEmprestimo;
import ifes.edu.br.miniprojeto1.dto.filtro.EmprestimoFiltro;
import ifes.edu.br.miniprojeto1.exception.EmprestimoNaoEncontradoException;
import ifes.edu.br.miniprojeto1.mapper.EmprestimoMapper;
import ifes.edu.br.miniprojeto1.model.Emprestimo;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.model.MultaAtraso;
import ifes.edu.br.miniprojeto1.model.TipoPessoa;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import ifes.edu.br.miniprojeto1.repository.MultaRepository;
import ifes.edu.br.miniprojeto1.service.busca.BuscaEmprestimo;
import ifes.edu.br.miniprojeto1.service.regra.RegraEmprestimo;
import ifes.edu.br.miniprojeto1.service.strategy.PoliticaEmprestimo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final MultaRepository multaRepository;
    private final LivroService livroService;
    private final LeitorService leitorService;
    private final EmprestimoMapper emprestimoMapper;
    private final Clock clock;
    private final Map<TipoPessoa, PoliticaEmprestimo> politicas;
    private final Map<CriterioBuscaEmprestimo, BuscaEmprestimo> buscas;
    private final List<RegraEmprestimo> regras;



    public List<EmprestimoResponse> listar(EmprestimoFiltro filtro) {
        List<Emprestimo> emprestimos = buscas.get(filtro.criterio()).buscar(filtro).stream()
                .sorted(Comparator.comparing(Emprestimo::getId))
                .toList();
        return emprestimoMapper.toResponseList(emprestimos, hoje());
    }

    public List<EmprestimoResponse> listarAtrasados() {
        LocalDate hoje = hoje();
        List<Emprestimo> atrasados = emprestimoRepository.buscarAtrasados(hoje).stream()
                .sorted(Comparator.comparing(Emprestimo::getId))
                .toList();
        return emprestimoMapper.toResponseList(atrasados, hoje);
    }

    public EmprestimoResponse buscarPorId(Long id) {
        return emprestimoMapper.toResponse(buscarEntidade(id), hoje());
    }

    public EmprestimoResponse emprestar(EmprestimoRequest request) {
        Livro livro = livroService.buscarEntidade(request.livroId());      // RN10
        Leitor leitor = leitorService.buscarEntidade(request.leitorId());  // RN10
        PoliticaEmprestimo politica = politicas.get(leitor.getTipoPessoa());
        LocalDate hoje = hoje();

        regras.forEach(regra -> regra.validar(livro, leitor, politica, hoje)); // RN03, RN04, RN05

        LocalDate dataPrevista = hoje.plusDays(politica.prazoEmDias());       // RN06
        Emprestimo emprestimo = emprestimoMapper.toEntity(livro.getId(), leitor.getId(), hoje, dataPrevista);
        return emprestimoMapper.toResponse(emprestimoRepository.salvar(emprestimo), hoje);
    }

    public EmprestimoResponse devolver(Long id) {
        Emprestimo emprestimo = buscarEntidade(id);
        Leitor leitor = leitorService.buscarEntidade(emprestimo.getLeitorId());
        PoliticaEmprestimo politica = politicas.get(leitor.getTipoPessoa());
        LocalDate hoje = hoje();

        emprestimo.devolver(hoje);                                             // RN08

        long diasAtraso = emprestimo.diasAtraso(hoje);
        Optional.of(politica.calcularMulta(diasAtraso))                        // RN07
                .filter(valor -> valor.signum() > 0)
                .ifPresent(valor -> registrarMultaAtraso(emprestimo, diasAtraso, valor, hoje));

        return emprestimoMapper.toResponse(emprestimoRepository.salvar(emprestimo), hoje);
    }

    public Emprestimo buscarEntidade(Long id) {
        return emprestimoRepository.buscarPorId(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(id));
    }

    private void registrarMultaAtraso(Emprestimo emprestimo, long diasAtraso, BigDecimal valor, LocalDate hoje) {
        MultaAtraso multa = new MultaAtraso();
        multa.setEmprestimoId(emprestimo.getId());
        multa.setLeitorId(emprestimo.getLeitorId());
        multa.setDataMulta(hoje);
        multa.setDiasAtraso(diasAtraso);
        multa.setValorMulta(valor);
        emprestimo.adicionarMulta(multaRepository.salvar(multa));
    }

    private LocalDate hoje() {
        return LocalDate.now(clock);
    }
}
