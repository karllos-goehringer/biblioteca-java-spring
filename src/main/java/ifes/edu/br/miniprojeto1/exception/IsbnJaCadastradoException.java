package ifes.edu.br.miniprojeto1.exception;

public class IsbnJaCadastradoException extends RegraNegocioException {
    public IsbnJaCadastradoException(String isbn) {
        super("Já existe um livro cadastrado com o ISBN " + isbn);
    }
}
