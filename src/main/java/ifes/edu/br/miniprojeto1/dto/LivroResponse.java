package ifes.edu.br.miniprojeto1.dto;

public record LivroResponse(
        Long id,
        String titulo,
        String autor,
        String isbn,
        Integer anoPublicacao,
        Integer quantidadeTotal
) {
}
