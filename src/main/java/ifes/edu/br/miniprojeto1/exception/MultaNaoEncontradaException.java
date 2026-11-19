package ifes.edu.br.miniprojeto1.exception;

public class MultaNaoEncontradaException extends RecursoNaoEncontradoException {
    public MultaNaoEncontradaException(Long id) {
        super("Multa com id " + id + " não encontrada");
    }
}
