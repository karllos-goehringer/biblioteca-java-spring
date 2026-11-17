package ifes.edu.br.miniprojeto1.model;

public enum NivelDegradacao {
    LEVE(1, "Leve"),
    MODERADA(2, "Moderada"),
    PERDA_TOTAL(3, "Perda Total");

    private final int nivel;
    private final String descricao;

    NivelDegradacao(int nivel, String descricao) {
        this.nivel = nivel;
        this.descricao = descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public static NivelDegradacao porNivel(int nivel) {
        for (NivelDegradacao item : NivelDegradacao.values()) {
            if (item.getNivel() == nivel) {
                return item;
            }
        }
        throw new IllegalArgumentException("Nível de degradação inválido: " + nivel);
    }
}
