package ifes.edu.br.miniprojeto1.exception;

public abstract class RecursoNaoEncontradoException extends RuntimeException {
    protected RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
