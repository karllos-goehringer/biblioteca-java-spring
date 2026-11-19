package ifes.edu.br.miniprojeto1.service.busca;

import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLivro;
import ifes.edu.br.miniprojeto1.dto.filtro.LivroFiltro;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscaLivroPorDisponibilidade implements BuscaLivro {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    @Override
    public CriterioBuscaLivro criterio() {
        return CriterioBuscaLivro.DISPONIVEL;
    }

    @Override
    public List<Livro> buscar(LivroFiltro filtro) {
        return livroRepository.buscarTodos().stream()
                .filter(livro -> temExemplarDisponivel(livro) == filtro.disponivel())
                .toList();
    }

    private boolean temExemplarDisponivel(Livro livro) {
        return livro.getQuantidadeTotal() - emprestimoRepository.contarAtivosPorLivroId(livro.getId()) > 0;
    }
}
