package ifes.edu.br.miniprojeto1.service;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;
import ifes.edu.br.miniprojeto1.dto.LivroResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLivro;
import ifes.edu.br.miniprojeto1.dto.filtro.LivroFiltro;
import ifes.edu.br.miniprojeto1.exception.LivroNaoEncontradoException;
import ifes.edu.br.miniprojeto1.mapper.LivroMapper;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import ifes.edu.br.miniprojeto1.service.busca.BuscaLivro;
import ifes.edu.br.miniprojeto1.service.regra.RegraCadastroLivro;
import ifes.edu.br.miniprojeto1.service.regra.RegraRemocaoLivro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    private final Map<CriterioBuscaLivro, BuscaLivro> buscas;
    private final List<RegraCadastroLivro> regrasCadastro;
    private final List<RegraRemocaoLivro> regrasRemocao;

    

    public List<LivroResponse> listar(LivroFiltro filtro) {
        List<Livro> livros = buscas.get(filtro.criterio()).buscar(filtro).stream()
                .sorted(Comparator.comparing(Livro::getId))
                .toList();
        return livroMapper.toResponseList(livros);
    }

    public LivroResponse buscarPorId(Long id) {
        return livroMapper.toResponse(buscarEntidade(id));
    }

    public LivroResponse buscarPorIsbn(String isbn) {
        return livroRepository.buscarPorIsbn(isbn)
                .map(livroMapper::toResponse)
                .orElseThrow(() -> new LivroNaoEncontradoException(isbn));
    }

    public LivroResponse criar(LivroRequest request) {
        regrasCadastro.forEach(regra -> regra.validar(null, request));        // RN01
        Livro livro = livroMapper.toEntity(request);
        return livroMapper.toResponse(livroRepository.salvar(livro));
    }

    public LivroResponse atualizar(Long id, LivroRequest request) {
        Livro livro = buscarEntidade(id);                                       // RN10
        regrasCadastro.forEach(regra -> regra.validar(id, request));          // RN01 + quantidade mínima
        livroMapper.atualizarEntidade(livro, request);
        return livroMapper.toResponse(livroRepository.salvar(livro));
    }

    public void remover(Long id) {
        Livro livro = buscarEntidade(id);                                       // RN10
        regrasRemocao.forEach(regra -> regra.validar(livro));                  // RN09
        livroRepository.removerPorId(id);
    }

    public Livro buscarEntidade(Long id) {
        return livroRepository.buscarPorId(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
    }
}
