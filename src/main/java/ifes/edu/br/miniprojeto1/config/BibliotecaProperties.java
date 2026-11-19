package ifes.edu.br.miniprojeto1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "biblioteca")
public record BibliotecaProperties(Regra aluno, Regra professor) {

    public record Regra(int prazoDias, int limiteEmprestimos, BigDecimal multaDiaria) {
    }
}
