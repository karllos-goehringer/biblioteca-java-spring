package ifes.edu.br.miniprojeto1.repository;

import ifes.edu.br.miniprojeto1.model.Emprestimo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository {

    List<Emprestimo> buscarTodos();

    Optional<Emprestimo> buscarPorId(Long id);

    Emprestimo salvar(Emprestimo emprestimo);

    void removerPorId(Long id);

    boolean existePorId(Long id);

    List<Emprestimo> buscarPorLivroId(Long livroId);

    List<Emprestimo> buscarPorLeitorId(Long leitorId);

    List<Emprestimo> buscarPorDataEmprestimo(LocalDate dataEmprestimo);

    long contarAtivosPorLivroId(Long livroId);

    long contarAtivosPorLeitorId(Long leitorId);

    boolean existeAtivoPorLivroId(Long livroId);

    boolean existeAtivoPorLeitorId(Long leitorId);

    boolean existeAtrasadoPorLeitorId(Long leitorId, LocalDate hoje);

    List<Emprestimo> buscarPorAtivo(boolean ativo);

    List<Emprestimo> buscarAtrasados(LocalDate hoje);
}
