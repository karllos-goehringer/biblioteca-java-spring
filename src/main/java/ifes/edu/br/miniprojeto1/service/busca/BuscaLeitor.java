package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLeitor;
import ifes.edu.br.miniprojeto1.dto.filtro.LeitorFiltro;
import ifes.edu.br.miniprojeto1.model.Leitor;

import java.util.List;

public interface BuscaLeitor {

    CriterioBuscaLeitor criterio();

    List<Leitor> buscar(LeitorFiltro filtro);
}
