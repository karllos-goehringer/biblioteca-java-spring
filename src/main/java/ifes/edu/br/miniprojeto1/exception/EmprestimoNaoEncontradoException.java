package ifes.edu.br.miniprojeto1.exception;

public class EmprestimoNaoEncontradoException extends RecursoNaoEncontradoException {
    public EmprestimoNaoEncontradoException(Long id) {
        super("Empréstimo com id " + id + " não encontrado");
    }
}
