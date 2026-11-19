package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;
import ifes.edu.br.miniprojeto1.exception.EmailJaCadastradoException;
import ifes.edu.br.miniprojeto1.repository.LeitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/** RN02: o e-mail não pode se repetir entre leitores. */
@Component
@RequiredArgsConstructor
public class EmailUnicoRegra implements RegraCadastroLeitor {

    private final LeitorRepository leitorRepository;

    @Override
    public void validar(Long id, LeitorRequest request) {
        boolean duplicado = Optional.ofNullable(id)
                .map(leitorId -> leitorRepository.existePorEmailEIdDiferente(request.email(), leitorId))
                .orElseGet(() -> leitorRepository.existePorEmail(request.email()));
        if (duplicado) {
            throw new EmailJaCadastradoException(request.email());
        }
    }
}
