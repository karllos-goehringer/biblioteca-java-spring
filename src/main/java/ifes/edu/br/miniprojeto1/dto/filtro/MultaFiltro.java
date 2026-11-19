package ifes.edu.br.miniprojeto1.dto.filtro;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public record MultaFiltro(
        Long emprestimoId,
        Long leitorId
) {

    public CriterioBuscaMulta criterio() {
        return Stream.of(
                Map.entry(CriterioBuscaMulta.EMPRESTIMO, Objects.nonNull(emprestimoId)),
                Map.entry(CriterioBuscaMulta.LEITOR, Objects.nonNull(leitorId)))
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(CriterioBuscaMulta.TODOS);
    }
}
