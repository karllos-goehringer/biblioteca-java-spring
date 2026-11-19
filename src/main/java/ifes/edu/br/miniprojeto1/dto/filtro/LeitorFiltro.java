package ifes.edu.br.miniprojeto1.dto.filtro;

import ifes.edu.br.miniprojeto1.model.TipoPessoa;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public record LeitorFiltro(
        TipoPessoa tipo
) {

    public CriterioBuscaLeitor criterio() {
        return Stream.of(
                Map.entry(CriterioBuscaLeitor.TIPO, Objects.nonNull(tipo)))
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(CriterioBuscaLeitor.TODOS);
    }
}
