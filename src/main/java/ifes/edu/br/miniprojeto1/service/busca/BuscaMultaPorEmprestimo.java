package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaMulta;
import ifes.edu.br.miniprojeto1.dto.filtro.MultaFiltro;
import ifes.edu.br.miniprojeto1.exception.EmprestimoNaoEncontradoException;
import ifes.edu.br.miniprojeto1.model.Multa;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import ifes.edu.br.miniprojeto1.repository.MultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/** RN10: o empréstimo informado precisa existir. */
@Component
@RequiredArgsConstructor
public class BuscaMultaPorEmprestimo implements BuscaMulta {

    private final MultaRepository multaRepository;
    private final EmprestimoRepository emprestimoRepository;

    @Override
    public CriterioBuscaMulta criterio() {
        return CriterioBuscaMulta.EMPRESTIMO;
    }

    @Override
    public List<Multa> buscar(MultaFiltro filtro) {
        Long emprestimoId = emprestimoRepository.buscarPorId(filtro.emprestimoId())
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(filtro.emprestimoId()))
                .getId();
        return multaRepository.buscarPorEmprestimoId(emprestimoId);
    }
}
