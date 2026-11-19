package ifes.edu.br.miniprojeto1.exception;

public abstract class RegraNegocioException extends RuntimeException {
    protected RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
