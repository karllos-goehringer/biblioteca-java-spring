package ifes.edu.br.miniprojeto1.dto.filtro;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public record LivroFiltro(
        String titulo,
        String autor,
        Integer ano
) {

    public CriterioBuscaLivro criterio() {
        return Stream.of(
                Map.entry(CriterioBuscaLivro.TITULO, Objects.nonNull(titulo)),
                Map.entry(CriterioBuscaLivro.AUTOR, Objects.nonNull(autor)),
                Map.entry(CriterioBuscaLivro.ANO, Objects.nonNull(ano)))
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(CriterioBuscaLivro.TODOS);
    }
}
