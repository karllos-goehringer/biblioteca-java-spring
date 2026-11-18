package ifes.edu.br.miniprojeto1.exception;

/** Base para violações de regra de negócio, respondida com 409. */
public abstract class RegraNegocioException extends RuntimeException {
    protected RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
