package ifes.edu.br.miniprojeto1.mapper;

import ifes.edu.br.miniprojeto1.dto.MultaResponse;
import ifes.edu.br.miniprojeto1.model.Multa;
import ifes.edu.br.miniprojeto1.model.MultaAvaria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MultaMapper {

    public MultaResponse toResponse(Multa multa) {
        // Só multas de avaria têm nível de degradação; nas demais o campo vai nulo.
        var nivel = multa instanceof MultaAvaria avaria ? avaria.getNivelDegradacao() : null;
        return new MultaResponse(multa.getId(), multa.getDataMulta(), multa.getDataPagamentoMulta(),
                multa.getValorMulta(), nivel);
    }

    public List<MultaResponse> toResponseList(List<Multa> multas) {
        return multas == null ? List.of() : multas.stream().map(this::toResponse).toList();
    }
}
