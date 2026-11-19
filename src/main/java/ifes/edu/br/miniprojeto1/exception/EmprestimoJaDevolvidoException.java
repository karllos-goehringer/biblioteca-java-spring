package ifes.edu.br.miniprojeto1.exception;

public class EmprestimoJaDevolvidoException extends RegraNegocioException {
    public EmprestimoJaDevolvidoException(Long id) {
        super("Empréstimo com id " + id + " já foi devolvido");
    }
}
