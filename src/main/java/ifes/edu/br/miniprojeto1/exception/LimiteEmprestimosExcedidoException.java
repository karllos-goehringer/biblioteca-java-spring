package ifes.edu.br.miniprojeto1.exception;

public class LimiteEmprestimosExcedidoException extends RegraNegocioException {
    public LimiteEmprestimosExcedidoException(Long leitorId, int limite) {
        super("Leitor com id " + leitorId + " atingiu o limite de " + limite + " empréstimos ativos");
    }
}
