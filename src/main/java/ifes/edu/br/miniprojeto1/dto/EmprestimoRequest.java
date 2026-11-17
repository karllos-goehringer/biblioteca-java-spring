package ifes.edu.br.miniprojeto1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmprestimoRequest(
        @NotNull(message = "livroId é obrigatório")
        @Positive(message = "livroId deve ser maior que zero")
        Long livroId,

        @NotNull(message = "leitorId é obrigatório")
        @Positive(message = "leitorId deve ser maior que zero")
        Long leitorId
) {
}
