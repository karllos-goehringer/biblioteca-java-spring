package ifes.edu.br.miniprojeto1.controller;

import ifes.edu.br.miniprojeto1.dto.EmprestimoRequest;
import ifes.edu.br.miniprojeto1.dto.EmprestimoResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.EmprestimoFiltro;
import ifes.edu.br.miniprojeto1.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @GetMapping
    public List<EmprestimoResponse> listar(@ModelAttribute EmprestimoFiltro filtro) {
        return emprestimoService.listar(filtro);
    }

    @GetMapping("/{id}")
    public EmprestimoResponse buscarPorId(@PathVariable Long id) {
        return emprestimoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponse> emprestar(@Valid @RequestBody EmprestimoRequest request) {
        EmprestimoResponse criado = emprestimoService.emprestar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.id()).toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @PostMapping("/{id}/devolucao")
    public EmprestimoResponse devolver(@PathVariable Long id) {
        return emprestimoService.devolver(id);
    }
}
