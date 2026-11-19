package ifes.edu.br.miniprojeto1.service.strategy;

import ifes.edu.br.miniprojeto1.config.BibliotecaProperties;
import ifes.edu.br.miniprojeto1.model.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PoliticaAluno implements PoliticaEmprestimo {

    private final BibliotecaProperties properties;

    @Override
    public TipoPessoa tipo() {
        return TipoPessoa.ALUNO;
    }

    @Override
    public int prazoEmDias() {
        return properties.aluno().prazoDias();
    }

    @Override
    public int limiteEmprestimos() {
        return properties.aluno().limiteEmprestimos();
    }

    @Override
    public BigDecimal calcularMulta(long diasAtraso) {
        return properties.aluno().multaDiaria().multiply(BigDecimal.valueOf(Math.max(0, diasAtraso)));
    }
}
