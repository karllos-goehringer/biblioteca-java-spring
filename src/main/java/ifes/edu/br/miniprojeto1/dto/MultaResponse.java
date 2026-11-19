package ifes.edu.br.miniprojeto1.dto;

import ifes.edu.br.miniprojeto1.model.NivelDegradacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MultaResponse(
        Long id,
        LocalDate dataMulta,
        LocalDate dataPagamentoMulta,
        BigDecimal valorMulta,
        NivelDegradacao nivelDegradacao
) {
}
