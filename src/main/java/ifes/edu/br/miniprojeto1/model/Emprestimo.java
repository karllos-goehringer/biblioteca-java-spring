package ifes.edu.br.miniprojeto1.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Emprestimo implements IEmprestavel {
    private Long id;
    private Long livroId;
    private Long leitorId;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private ArrayList<Multa> multas;

}
