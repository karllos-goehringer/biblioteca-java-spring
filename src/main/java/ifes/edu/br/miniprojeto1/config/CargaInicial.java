package ifes.edu.br.miniprojeto1.config;

import ifes.edu.br.miniprojeto1.model.Emprestimo;
import ifes.edu.br.miniprojeto1.model.Leitor;
import ifes.edu.br.miniprojeto1.model.Livro;
import ifes.edu.br.miniprojeto1.model.Multa;
import ifes.edu.br.miniprojeto1.model.MultaAtraso;
import ifes.edu.br.miniprojeto1.model.MultaAvaria;
import ifes.edu.br.miniprojeto1.model.NivelDegradacao;
import ifes.edu.br.miniprojeto1.model.TipoPessoa;
import ifes.edu.br.miniprojeto1.repository.EmprestimoRepository;
import ifes.edu.br.miniprojeto1.repository.LeitorRepository;
import ifes.edu.br.miniprojeto1.repository.LivroRepository;
import ifes.edu.br.miniprojeto1.repository.MultaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;


@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "biblioteca.carga-inicial", havingValue = "true")
public class CargaInicial implements ApplicationRunner {

    private final LivroRepository livroRepository;
    private final LeitorRepository leitorRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final MultaRepository multaRepository;
    private final Clock clock;

    @Override
    public void run(ApplicationArguments args) {
        LocalDate hoje = LocalDate.now(clock);

        // Livros (ids 1..6)
        Livro domCasmurro = livro("Dom Casmurro", "Machado de Assis", "9788535910663", 1899, 3);
        Livro cortico = livro("O Cortiço", "Aluísio Azevedo", "9788508133556", 1890, 2);
        livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", "9788535911664", 1881, 2);
        Livro cleanCode = livro("Clean Code", "Robert C. Martin", "9780132350884", 2008, 1);
        Livro designPatterns = livro("Design Patterns", "Erich Gamma", "9780201633610", 1994, 4);
        livro("Vidas Secas", "Graciliano Ramos", "9788501067339", 1938, 2);

        // Leitores (ids 1..5)
        Leitor ana = leitor("Ana Souza", "ana.souza@aluno.ifes.edu.br", TipoPessoa.ALUNO);
        Leitor bruno = leitor("Bruno Lima", "bruno.lima@aluno.ifes.edu.br", TipoPessoa.ALUNO);
        Leitor carla = leitor("Carla Mendes", "carla.mendes@ifes.edu.br", TipoPessoa.PROFESSOR);
        leitor("Diego Rocha", "diego.rocha@aluno.ifes.edu.br", TipoPessoa.ALUNO);
        Leitor elisa = leitor("Elisa Prado", "elisa.prado@ifes.edu.br", TipoPessoa.PROFESSOR);

        // Empréstimos (ids 1..7)
        // Ana (ALUNO) no limite de 3 ativos -> RN04
        emprestimo(domCasmurro, ana, hoje.minusDays(2), hoje.plusDays(5), null);
        emprestimo(designPatterns, ana, hoje.minusDays(2), hoje.plusDays(5), null);
        emprestimo(cleanCode, ana, hoje.minusDays(1), hoje.plusDays(6), null);           // Clean Code esgotado -> RN03
        // Bruno (ALUNO) com empréstimo atrasado -> RN05
        emprestimo(cortico, bruno, hoje.minusDays(10), hoje.minusDays(3), null);
        // Carla (PROFESSOR): um ativo (esgota O Cortiço) e um devolvido com 4 dias de atraso
        emprestimo(cortico, carla, hoje.minusDays(3), hoje.plusDays(11), null);
        Emprestimo devolvidoComAtraso =
                emprestimo(domCasmurro, carla, hoje.minusDays(20), hoje.minusDays(6), hoje.minusDays(2));
        // Elisa (PROFESSOR): devolvido no prazo, mas com avaria
        Emprestimo devolvidoComAvaria =
                emprestimo(designPatterns, elisa, hoje.minusDays(15), hoje.minusDays(1), hoje.minusDays(5));

        // Multas (ids 1..2)
        MultaAtraso atraso = new MultaAtraso();
        atraso.setDiasAtraso(4L);
        multa(atraso, devolvidoComAtraso, hoje.minusDays(2), new BigDecimal("2.00"), null);   // 4 x R$ 0,50, em aberto

        MultaAvaria avaria = new MultaAvaria();
        avaria.setNivelDegradacao(NivelDegradacao.LEVE);
        multa(avaria, devolvidoComAvaria, hoje.minusDays(5), new BigDecimal("15.00"), hoje.minusDays(4)); // já paga

        log.info("Carga inicial concluída: {} livros, {} leitores, {} empréstimos, {} multas",
                livroRepository.buscarTodos().size(), leitorRepository.buscarTodos().size(),
                emprestimoRepository.buscarTodos().size(), multaRepository.buscarTodos().size());
    }

    private Livro livro(String titulo, String autor, String isbn, int ano, int quantidade) {
        return livroRepository.salvar(new Livro(null, titulo, autor, isbn, ano, quantidade));
    }

    private Leitor leitor(String nome, String email, TipoPessoa tipo) {
        return leitorRepository.salvar(new Leitor(null, nome, email, tipo));
    }

    private Emprestimo emprestimo(Livro livro, Leitor leitor, LocalDate data, LocalDate prevista, LocalDate devolucao) {
        return emprestimoRepository.salvar(
                new Emprestimo(null, livro.getId(), leitor.getId(), data, prevista, devolucao, new ArrayList<>()));
    }

    private void multa(Multa multa, Emprestimo emprestimo, LocalDate data, BigDecimal valor, LocalDate pagamento) {
        multa.setEmprestimoId(emprestimo.getId());
        multa.setLeitorId(emprestimo.getLeitorId());
        multa.setDataMulta(data);
        multa.setValorMulta(valor);
        multa.setDataPagamentoMulta(pagamento);
        emprestimo.adicionarMulta(multaRepository.salvar(multa));
    }
}
