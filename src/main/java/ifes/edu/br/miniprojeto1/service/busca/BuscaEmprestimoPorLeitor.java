package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaEmprestimo;
import ifes.edu.br.miniprojeto1.dto.filtro.EmprestimoFiltro;
import ifes.edu.br.miniprojeto1.model.Emprestimo;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscaEmprestimoPorLeitor implements BuscaEmprestimo {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public CriterioBuscaEmprestimo criterio() {
        return CriterioBuscaEmprestimo.LEITOR;
    }

    @Override
    public List<Emprestimo> buscar(EmprestimoFiltro filtro) {
        return emprestimoRepository.buscarPorLeitorId(filtro.leitorId());
    }
}
