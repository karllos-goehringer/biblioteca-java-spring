package ifes.edu.br.miniprojeto1.controller;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;
import ifes.edu.br.miniprojeto1.dto.LivroResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.LivroFiltro;
import ifes.edu.br.miniprojeto1.mapper.LivroMapper;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @GetMapping
    public List<LivroResponse> listar(@ModelAttribute LivroFiltro filtro) {
        return livroMapper.toResponseList(livroService.listar(filtro));
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(@PathVariable Long id) {
        return livroMapper.toResponse(livroService.buscarPorId(id));
    }

    @GetMapping("/isbn/{isbn}")
    public LivroResponse buscarPorIsbn(@PathVariable String isbn) {
        return livroMapper.toResponse(livroService.buscarPorIsbn(isbn));
    }

    @PostMapping
    public ResponseEntity<LivroResponse> criar(@Valid @RequestBody LivroRequest request) {
        Livro criado = livroService.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(location).body(livroMapper.toResponse(criado));
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequest request) {
        return livroMapper.toResponse(livroService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        livroService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
