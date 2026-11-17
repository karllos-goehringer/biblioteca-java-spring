package ifes.edu.br.miniprojeto1.dto;

import ifes.edu.br.miniprojeto1.model.TipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LeitorRequest(
        @NotBlank(message = "nome é obrigatório")
        @Size(max = 100, message = "nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "email é obrigatório")
        @Email(message = "email deve ter um formato válido")
        String email,

        @NotNull(message = "tipo é obrigatório (ALUNO ou PROFESSOR)")
        TipoPessoa tipo
) {
}
