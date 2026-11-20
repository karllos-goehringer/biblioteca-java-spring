package ifes.edu.br.miniprojeto1.service.strategy;

import ifes.edu.br.miniprojeto1.model.TipoPessoa;

import java.math.BigDecimal;


public interface PoliticaEmprestimo {

    TipoPessoa tipo();

    int prazoEmDias();

    int limiteEmprestimos();

    BigDecimal calcularMulta(long diasAtraso);
}
