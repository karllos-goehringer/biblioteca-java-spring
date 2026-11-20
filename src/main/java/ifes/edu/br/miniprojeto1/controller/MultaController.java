package ifes.edu.br.miniprojeto1.controller;

import ifes.edu.br.miniprojeto1.dto.MultaResponse;
import ifes.edu.br.miniprojeto1.dto.filtro.MultaFiltro;
import ifes.edu.br.miniprojeto1.mapper.MultaMapper;
import ifes.edu.br.miniprojeto1.service.MultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/multas")
@RequiredArgsConstructor
public class MultaController {

    private final MultaService multaService;
    private final MultaMapper multaMapper;

    @GetMapping
    public List<MultaResponse> listar(@ModelAttribute MultaFiltro filtro) {
        return multaMapper.toResponseList(multaService.listar(filtro));
    }

    @GetMapping("/{id}")
    public MultaResponse buscarPorId(@PathVariable Long id) {
        return multaMapper.toResponse(multaService.buscarPorId(id));
    }

    @PostMapping("/{id}/pagamento")
    public MultaResponse pagar(@PathVariable Long id) {
        return multaMapper.toResponse(multaService.pagar(id));
    }
}
