package ifes.edu.br.miniprojeto1.service.strategy;

import ifes.edu.br.miniprojeto1.model.TipoPessoa;

import java.math.BigDecimal;

/**
 * Strategy com as regras que variam conforme o tipo de leitor (RN04, RN06, RN07).
 * O {@code EmprestimoService} indexa as implementações por {@link #tipo()}.
 */
public interface PoliticaEmprestimo {

    TipoPessoa tipo();

    int prazoEmDias();

    int limiteEmprestimos();

    /** RN07: dias de atraso × valor diário; sem atraso, zero. */
    BigDecimal calcularMulta(long diasAtraso);
}
