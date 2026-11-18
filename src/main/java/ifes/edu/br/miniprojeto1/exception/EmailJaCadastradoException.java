package ifes.edu.br.miniprojeto1.exception;

/** RN02: o e-mail não pode se repetir entre leitores. */
public class EmailJaCadastradoException extends RegraNegocioException {
    public EmailJaCadastradoException(String email) {
        super("Já existe um leitor cadastrado com o e-mail " + email);
    }
}
