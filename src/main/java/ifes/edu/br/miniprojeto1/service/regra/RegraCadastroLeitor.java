package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;

/** Regra verificada ao criar ({@code id == null}) ou atualizar um leitor. */
public interface RegraCadastroLeitor {
    void validar(Long id, LeitorRequest request);
}
