package ifes.edu.br.miniprojeto1.exception;

public class LeitorComEmprestimoAtrasadoException extends RegraNegocioException {
    public LeitorComEmprestimoAtrasadoException(Long leitorId) {
        super("Leitor com id " + leitorId + " possui empréstimo em atraso");
    }
}
