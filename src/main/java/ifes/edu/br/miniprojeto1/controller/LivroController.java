package ifes.edu.br.miniprojeto1.controller;

import ifes.edu.br.miniprojeto1.dto.LivroRequest;
import ifes.edu.br.miniprojeto1.dto.LivroResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.LivroFiltro;
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

    @GetMapping
    public List<LivroResponse> listar(@ModelAttribute LivroFiltro filtro) {
        return livroService.listar(filtro);
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @GetMapping("/isbn/{isbn}")
    public LivroResponse buscarPorIsbn(@PathVariable String isbn) {
        return livroService.buscarPorIsbn(isbn);
    }

    @PostMapping
    public ResponseEntity<LivroResponse> criar(@Valid @RequestBody LivroRequest request) {
        LivroResponse criado = livroService.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.id()).toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequest request) {
        return livroService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        livroService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
