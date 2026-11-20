package ifes.edu.br.miniprojeto1.controller;

import ifes.edu.br.miniprojeto1.dto.EmprestimoRequest;
import ifes.edu.br.miniprojeto1.dto.EmprestimoResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.EmprestimoFiltro;
import ifes.edu.br.miniprojeto1.mapper.EmprestimoMapper;
import ifes.edu.br.miniprojeto1.model.Emprestimo;
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
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final EmprestimoMapper emprestimoMapper;
    private final Clock clock;

    @GetMapping
    public List<EmprestimoResponse> listar(@ModelAttribute EmprestimoFiltro filtro) {
        return emprestimoMapper.toResponseList(emprestimoService.listar(filtro), hoje());
    }

    @GetMapping("/atrasados")
    public List<EmprestimoResponse> listarAtrasados() {
        return emprestimoMapper.toResponseList(emprestimoService.listarAtrasados(), hoje());
    }

    @GetMapping("/{id}")
    public EmprestimoResponse buscarPorId(@PathVariable Long id) {
        return emprestimoMapper.toResponse(emprestimoService.buscarPorId(id), hoje());
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponse> emprestar(@Valid @RequestBody EmprestimoRequest request) {
        Emprestimo criado = emprestimoService.emprestar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(location).body(emprestimoMapper.toResponse(criado, hoje()));
    }

    @PostMapping("/{id}/devolucao")
    public EmprestimoResponse devolver(@PathVariable Long id) {
        return emprestimoMapper.toResponse(emprestimoService.devolver(id), hoje());
    }

    private LocalDate hoje() {
        return LocalDate.now(clock);
    }
}
