package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLivro;
import ifes.edu.br.miniprojeto1.dto.filtro.LivroFiltro;
import ifes.edu.br.miniprojeto1.model.Livro;

import java.util.List;

public interface BuscaLivro {

    CriterioBuscaLivro criterio();

    List<Livro> buscar(LivroFiltro filtro);
}
