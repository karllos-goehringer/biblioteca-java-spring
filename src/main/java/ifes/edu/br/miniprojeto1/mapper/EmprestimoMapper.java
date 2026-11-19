package ifes.edu.br.miniprojeto1.mapper;

import ifes.edu.br.miniprojeto1.dto.EmprestimoResponse;
import ifes.edu.br.miniprojeto1.model.Emprestimo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmprestimoMapper {

    private final MultaMapper multaMapper;

    public Emprestimo toEntity(Long livroId, Long leitorId, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {
        return new Emprestimo(null, livroId, leitorId, dataEmprestimo, dataPrevistaDevolucao, null, new ArrayList<>());
    }

    public EmprestimoResponse toResponse(Emprestimo emprestimo, LocalDate hoje) {
        return new EmprestimoResponse(
                emprestimo.getId(),
                emprestimo.getLivroId(),
                emprestimo.getLeitorId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataPrevistaDevolucao(),
                emprestimo.getDataDevolucao(),
                emprestimo.diasAtraso(hoje) > 0,
                multaMapper.toResponseList(emprestimo.getMultas())
        );
    }

    public List<EmprestimoResponse> toResponseList(List<Emprestimo> emprestimos, LocalDate hoje) {
        return emprestimos.stream().map(e -> toResponse(e, hoje)).toList();
    }
}
