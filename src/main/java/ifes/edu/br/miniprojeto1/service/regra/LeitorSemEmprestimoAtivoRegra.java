package ifes.edu.br.miniprojeto1.service.regra;

import ifes.edu.br.miniprojeto1.exception.LeitorComEmprestimoAtivoException;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeitorSemEmprestimoAtivoRegra implements RegraRemocaoLeitor {

    private final EmprestimoRepository emprestimoRepository;

    @Override
    public void validar(Leitor leitor) {
        if (emprestimoRepository.existeAtivoPorLeitorId(leitor.getId())) {
            throw new LeitorComEmprestimoAtivoException(leitor.getId());
        }
    }
}
