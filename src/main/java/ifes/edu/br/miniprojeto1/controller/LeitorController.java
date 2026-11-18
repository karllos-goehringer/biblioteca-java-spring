package ifes.edu.br.miniprojeto1.controller;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;
import ifes.edu.br.miniprojeto1.dto.LeitorResponse;
import ifes.edu.br.miniprojeto1.model.TipoPessoa;
import ifes.edu.br.miniprojeto1.service.LeitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/leitores")
@RequiredArgsConstructor
public class LeitorController {

    private final LeitorService leitorService;

    @GetMapping
    public List<LeitorResponse> listar(@RequestParam(required = false) TipoPessoa tipo) {
        return leitorService.listar(tipo);
    }

    @GetMapping("/{id}")
    public LeitorResponse buscarPorId(@PathVariable Long id) {
        return leitorService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<LeitorResponse> criar(@Valid @RequestBody LeitorRequest request) {
        LeitorResponse criado = leitorService.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.id()).toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @PutMapping("/{id}")
    public LeitorResponse atualizar(@PathVariable Long id, @Valid @RequestBody LeitorRequest request) {
        return leitorService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        leitorService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
