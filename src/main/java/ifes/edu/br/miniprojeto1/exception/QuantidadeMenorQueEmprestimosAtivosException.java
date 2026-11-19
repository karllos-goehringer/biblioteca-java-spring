package ifes.edu.br.miniprojeto1.exception;

public class QuantidadeMenorQueEmprestimosAtivosException extends RegraNegocioException {
    public QuantidadeMenorQueEmprestimosAtivosException(Long livroId, long ativos) {
        super("Livro com id " + livroId + " possui " + ativos + " empréstimos ativos; quantidadeTotal não pode ser menor que isso");
    }
}
