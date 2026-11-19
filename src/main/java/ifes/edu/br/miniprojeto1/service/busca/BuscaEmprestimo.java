package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaEmprestimo;
import ifes.edu.br.miniprojeto1.dto.filtro.EmprestimoFiltro;
import ifes.edu.br.miniprojeto1.model.Emprestimo;

import java.util.List;

public interface BuscaEmprestimo {

    CriterioBuscaEmprestimo criterio();

    List<Emprestimo> buscar(EmprestimoFiltro filtro);
}
