package ifes.edu.br.miniprojeto1.service;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;
import ifes.edu.br.miniprojeto1.dto.filtro.CriterioBuscaLivro;
import ifes.edu.br.miniprojeto1.dto.filtro.LivroFiltro;
import ifes.edu.br.miniprojeto1.exception.LivroNaoEncontradoException;
import ifes.edu.br.miniprojeto1.mapper.LivroMapper;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import ifes.edu.br.miniprojeto1.service.busca.BuscaLivro;
import ifes.edu.br.miniprojeto1.service.regra.RegraCadastroLivro;
import ifes.edu.br.miniprojeto1.service.regra.RegraRemocaoLivro;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    private final Map<CriterioBuscaLivro, BuscaLivro> buscas;
    private final List<RegraCadastroLivro> regrasCadastro;
    private final List<RegraRemocaoLivro> regrasRemocao;

    public LivroService(LivroRepository livroRepository,
                        LivroMapper livroMapper,
                        List<BuscaLivro> buscas,
                        List<RegraCadastroLivro> regrasCadastro,
                        List<RegraRemocaoLivro> regrasRemocao) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
        this.buscas = buscas.stream()
                .collect(Collectors.toMap(BuscaLivro::criterio, Function.identity()));
        this.regrasCadastro = regrasCadastro;
        this.regrasRemocao = regrasRemocao;
    }

    public List<Livro> listar(LivroFiltro filtro) {
        return buscas.get(filtro.criterio()).buscar(filtro).stream()
                .sorted(Comparator.comparing(Livro::getId))
                .toList();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.buscarPorId(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
    }

    public Livro buscarPorIsbn(String isbn) {
        return livroRepository.buscarPorIsbn(isbn)
                .orElseThrow(() -> new LivroNaoEncontradoException(isbn));
    }

    public Livro criar(LivroRequest request) {
        regrasCadastro.forEach(regra -> regra.validar(null, request));        // RN01
        Livro livro = livroMapper.toEntity(request);
        return livroRepository.salvar(livro);
    }

    public Livro atualizar(Long id, LivroRequest request) {
        Livro livro = buscarPorId(id);                                          // RN10
        regrasCadastro.forEach(regra -> regra.validar(id, request));          // RN01 + quantidade mínima
        livroMapper.atualizarEntidade(livro, request);
        return livroRepository.salvar(livro);
    }

    public void remover(Long id) {
        Livro livro = buscarPorId(id);                                          // RN10
        regrasRemocao.forEach(regra -> regra.validar(livro));                  // RN09
        livroRepository.removerPorId(id);
    }
}
