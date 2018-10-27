package modelo;

/**
 *
 * @author Arthur Ribeiro
 */
public class Material {
    private int id;
    private String descricao;
    private boolean disponivel;

    public Material(String descricao, boolean disponivel) {
        this.descricao = descricao;
        this.disponivel = disponivel;
    }

    public Material(int id, String descricao, boolean disponivel) {
        this.id = id;
        this.descricao = descricao;
        this.disponivel = disponivel;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
}
