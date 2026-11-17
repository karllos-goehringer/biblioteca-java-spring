package ifes.edu.br.miniprojeto1.dto;

import java.time.LocalDate;
import java.util.List;

public record EmprestimoResponse(
        Long id,
        Long livroId,
        Long leitorId,
        LocalDate dataEmprestimo,
        LocalDate dataPrevistaDevolucao,
        LocalDate dataDevolucao,
        boolean atrasado,
        List<MultaResponse> multas
) {
}
