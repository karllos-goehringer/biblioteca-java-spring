package ifes.edu.br.miniprojeto1.exception;

public class LeitorNaoEncontradoException extends RecursoNaoEncontradoException {
    public LeitorNaoEncontradoException(Long id) {
        super("Leitor com id " + id + " não encontrado");
    }
}
