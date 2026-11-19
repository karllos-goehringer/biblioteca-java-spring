package ifes.edu.br.miniprojeto1.exception;

public class EmailJaCadastradoException extends RegraNegocioException {
    public EmailJaCadastradoException(String email) {
        super("Já existe um leitor cadastrado com o e-mail " + email);
    }
}
