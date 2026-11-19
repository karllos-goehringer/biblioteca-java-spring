package ifes.edu.br.miniprojeto1.mapper;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;
import ifes.edu.br.miniprojeto1.dto.LivroResponse;
import ifes.edu.br.miniprojeto1.model.Livro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequest request) {
        return new Livro(null, request.titulo(), request.autor(), request.isbn(),
                request.anoPublicacao(), request.quantidadeTotal());
    }

    public void atualizarEntidade(Livro livro, LivroRequest request) {
        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setIsbn(request.isbn());
        livro.setAnoPublicacao(request.anoPublicacao());
        livro.setQuantidadeTotal(request.quantidadeTotal());
    }

    public LivroResponse toResponse(Livro livro) {
        return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getIsbn(),
                livro.getAnoPublicacao(), livro.getQuantidadeTotal());
    }

    public List<LivroResponse> toResponseList(List<Livro> livros) {
        return livros.stream().map(this::toResponse).toList();
    }
}
