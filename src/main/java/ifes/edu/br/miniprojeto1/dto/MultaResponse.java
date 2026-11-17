package ifes.edu.br.miniprojeto1.dto;

import ifes.edu.br.miniprojeto1.model.NivelDegradacao;

import java.time.LocalDate;

public record MultaResponse(
        Long id,
        LocalDate dataMulta,
        LocalDate dataPagamentoMulta,
        Double valorMulta,
        NivelDegradacao nivelDegradacao
) {
}
