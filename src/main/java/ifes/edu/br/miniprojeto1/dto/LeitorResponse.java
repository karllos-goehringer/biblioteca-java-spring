package ifes.edu.br.miniprojeto1.dto;

import ifes.edu.br.miniprojeto1.model.TipoPessoa;

public record LeitorResponse(
        Long id,
        String nome,
        String email,
        TipoPessoa tipo
) {
}
