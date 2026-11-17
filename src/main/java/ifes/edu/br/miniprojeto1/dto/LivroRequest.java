package ifes.edu.br.miniprojeto1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LivroRequest(
        @NotBlank(message = "titulo é obrigatório")
        @Size(max = 150, message = "titulo deve ter no máximo 150 caracteres")
        String titulo,

        @NotBlank(message = "autor é obrigatório")
        @Size(max = 100, message = "autor deve ter no máximo 100 caracteres")
        String autor,

        @NotBlank(message = "isbn é obrigatório")
        @Pattern(regexp = "\\d{13}", message = "isbn deve conter exatamente 13 dígitos numéricos")
        String isbn,

        @NotNull(message = "anoPublicacao é obrigatório")
        @Min(value = 1450, message = "anoPublicacao deve ser a partir de 1450")
        Integer anoPublicacao,

        @NotNull(message = "quantidadeTotal é obrigatória")
        @Positive(message = "quantidadeTotal deve ser maior que zero")
        Integer quantidadeTotal
) {
}
