package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;

public interface RegraCadastroLeitor {
    void validar(Long id, LeitorRequest request);
}
