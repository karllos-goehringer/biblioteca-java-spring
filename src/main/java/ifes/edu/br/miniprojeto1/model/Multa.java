package ifes.edu.br.miniprojeto1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double valorMulta;
}
