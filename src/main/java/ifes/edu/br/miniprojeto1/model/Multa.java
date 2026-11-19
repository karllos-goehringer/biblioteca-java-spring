package ifes.edu.br.miniprojeto1.model;

import ifes.edu.br.miniprojeto1.exception.MultaJaPagaException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public abstract class Multa {
    private Long id;
    private Long emprestimoId;
    private Long leitorId;
    private LocalDate dataMulta;
    private LocalDate dataPagamentoMulta;
    private BigDecimal valorMulta;

    public void pagar(LocalDate dataPagamento) {
        if (dataPagamentoMulta != null) {
            throw new MultaJaPagaException(id);
        }
        this.dataPagamentoMulta = dataPagamento;
    }
}
