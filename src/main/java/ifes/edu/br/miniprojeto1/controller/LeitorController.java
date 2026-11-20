package ifes.edu.br.miniprojeto1.controller;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;
import ifes.edu.br.miniprojeto1.dto.LeitorResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.LeitorFiltro;
import ifes.edu.br.miniprojeto1.mapper.LeitorMapper;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.service.LeitorService;
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
@RequestMapping("/leitores")
@RequiredArgsConstructor
public class LeitorController {

    private final LeitorService leitorService;
    private final LeitorMapper leitorMapper;

    @GetMapping
    public List<LeitorResponse> listar(@ModelAttribute LeitorFiltro filtro) {
        return leitorMapper.toResponseList(leitorService.listar(filtro));
    }

    @GetMapping("/{id}")
    public LeitorResponse buscarPorId(@PathVariable Long id) {
        return leitorMapper.toResponse(leitorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LeitorResponse> criar(@Valid @RequestBody LeitorRequest request) {
        Leitor criado = leitorService.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(location).body(leitorMapper.toResponse(criado));
    }

    @PutMapping("/{id}")
    public LeitorResponse atualizar(@PathVariable Long id, @Valid @RequestBody LeitorRequest request) {
        return leitorMapper.toResponse(leitorService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        leitorService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
