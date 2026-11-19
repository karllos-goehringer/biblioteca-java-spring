package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaMulta;
import ifes.edu.br.miniprojeto1.dto.filtro.MultaFiltro;
import ifes.edu.br.miniprojeto1.model.Multa;

import java.util.List;

public interface BuscaMulta {

    CriterioBuscaMulta criterio();

    List<Multa> buscar(MultaFiltro filtro);
}
