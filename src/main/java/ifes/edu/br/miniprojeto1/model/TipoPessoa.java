package ifes.edu.br.miniprojeto1.model;

public enum TipoPessoa {

    PROFESSOR("Professor"),
    ALUNO("Aluno");

    private final String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
