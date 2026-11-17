package ifes.edu.br.miniprojeto1.model;

public interface IEmprestavel {
    public default Emprestimo emprestar(){
        return new Emprestimo();
    }
}
