package ifes.edu.br.miniprojeto1.model;

import ifes.edu.br.miniprojeto1.exception.EmprestimoJaDevolvidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo implements IEmprestavel {
    private Long id;
    private Long livroId;
    private Long leitorId;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private ArrayList<Multa> multas = new ArrayList<>();

    public void devolver(LocalDate data) {
        if (dataDevolucao != null) {
            throw new EmprestimoJaDevolvidoException(id);
        }
        this.dataDevolucao = data;
    }

    public long diasAtraso(LocalDate hoje) {
        LocalDate referencia = Optional.ofNullable(dataDevolucao).orElse(hoje);
        return Math.max(0, ChronoUnit.DAYS.between(dataPrevistaDevolucao, referencia));
    }

    public void adicionarMulta(Multa multa) {
        multas.add(multa);
    }
}
