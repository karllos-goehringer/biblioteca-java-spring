package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLivro;
import ifes.edu.br.miniprojeto1.dto.filtro.LivroFiltro;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscaLivroPorAno implements BuscaLivro {

    private final LivroRepository livroRepository;

    @Override
    public CriterioBuscaLivro criterio() {
        return CriterioBuscaLivro.ANO;
    }

    @Override
    public List<Livro> buscar(LivroFiltro filtro) {
        return livroRepository.buscarPorAnoPublicacao(filtro.ano());
    }
}
