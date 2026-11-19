package ifes.edu.br.miniprojeto1.exception;

public class LivroIndisponivelException extends RegraNegocioException {
    public LivroIndisponivelException(Long livroId) {
        super("Livro com id " + livroId + " não possui exemplar disponível");
    }
}
