package ifes.edu.br.miniprojeto1.exception;

public class LeitorComEmprestimoAtivoException extends RegraNegocioException {
    public LeitorComEmprestimoAtivoException(Long id) {
        super("Leitor com id " + id + " possui empréstimo ativo e não pode ser removido");
    }
}
