package ifes.edu.br.miniprojeto1.exception;

public class MultaJaPagaException extends RegraNegocioException {
    public MultaJaPagaException(Long id) {
        super("Multa com id " + id + " já foi paga");
    }
}
