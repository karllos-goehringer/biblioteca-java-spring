package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.model.Leitor;

/** Regra verificada antes de remover um leitor. */
public interface RegraRemocaoLeitor {
    void validar(Leitor leitor);
}
