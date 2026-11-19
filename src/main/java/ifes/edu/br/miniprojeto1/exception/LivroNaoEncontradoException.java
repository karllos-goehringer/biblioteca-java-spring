package ifes.edu.br.miniprojeto1.exception;

public class LivroNaoEncontradoException extends RecursoNaoEncontradoException {
    public LivroNaoEncontradoException(Long id) {
        super("Livro com id " + id + " não encontrado");
    }

    public LivroNaoEncontradoException(String isbn) {
        super("Livro com ISBN " + isbn + " não encontrado");
    }
}
