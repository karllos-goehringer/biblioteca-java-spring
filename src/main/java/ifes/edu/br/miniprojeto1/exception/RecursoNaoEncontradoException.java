package ifes.edu.br.miniprojeto1.exception;

/** Base para recursos inexistentes (RN10), respondida com 404. */
public abstract class RecursoNaoEncontradoException extends RuntimeException {
    protected RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
