package ifes.edu.br.miniprojeto1.dto.filtro;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public record EmprestimoFiltro(
        Long leitorId,
        Long livroId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
        Boolean ativos
) {

    public CriterioBuscaEmprestimo criterio() {
        return Stream.of(
                Map.entry(CriterioBuscaEmprestimo.LEITOR, Objects.nonNull(leitorId)),
                Map.entry(CriterioBuscaEmprestimo.LIVRO, Objects.nonNull(livroId)),
                Map.entry(CriterioBuscaEmprestimo.DATA, Objects.nonNull(data)),
                Map.entry(CriterioBuscaEmprestimo.ATIVOS, Objects.nonNull(ativos)))
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(CriterioBuscaEmprestimo.TODOS);
    }
}
