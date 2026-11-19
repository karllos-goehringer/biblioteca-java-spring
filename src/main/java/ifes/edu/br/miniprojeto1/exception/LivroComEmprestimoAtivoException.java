package ifes.edu.br.miniprojeto1.exception;

public class LivroComEmprestimoAtivoException extends RegraNegocioException {
    public LivroComEmprestimoAtivoException(Long id) {
        super("Livro com id " + id + " possui empréstimo ativo e não pode ser removido");
    }
}
