package ifes.edu.br.miniprojeto1.mapper;

import ifes.edu.br.miniprojeto1.dto.LeitorRequest;
import ifes.edu.br.miniprojeto1.dto.LeitorResponse;
import ifes.edu.br.miniprojeto1.model.Leitor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeitorMapper {

    public Leitor toEntity(LeitorRequest request) {
        return new Leitor(null, request.nome(), request.email(), request.tipo());
    }

    public void atualizarEntidade(Leitor leitor, LeitorRequest request) {
        leitor.setNome(request.nome());
        leitor.setEmail(request.email());
        leitor.setTipoPessoa(request.tipo());
    }

    public LeitorResponse toResponse(Leitor leitor) {
        return new LeitorResponse(leitor.getId(), leitor.getNome(), leitor.getEmail(), leitor.getTipoPessoa());
    }

    public List<LeitorResponse> toResponseList(List<Leitor> leitores) {
        return leitores.stream().map(this::toResponse).toList();
    }
}
