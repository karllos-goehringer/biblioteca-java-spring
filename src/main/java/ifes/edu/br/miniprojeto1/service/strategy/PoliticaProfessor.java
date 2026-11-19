package ifes.edu.br.miniprojeto1.service.strategy;

import ifes.edu.br.miniprojeto1.config.BibliotecaProperties;
import ifes.edu.br.miniprojeto1.model.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PoliticaProfessor implements PoliticaEmprestimo {

    private final BibliotecaProperties properties;

    @Override
    public TipoPessoa tipo() {
        return TipoPessoa.PROFESSOR;
    }

    @Override
    public int prazoEmDias() {
        return properties.professor().prazoDias();
    }

    @Override
    public int limiteEmprestimos() {
        return properties.professor().limiteEmprestimos();
    }

    @Override
    public BigDecimal calcularMulta(long diasAtraso) {
        return properties.professor().multaDiaria().multiply(BigDecimal.valueOf(Math.max(0, diasAtraso)));
    }
}
