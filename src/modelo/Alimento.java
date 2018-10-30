package modelo;

import java.util.List;
import modelo.db.AlimentoDbDAO;

/**
 *
 * @author Arthur Ribeiro
 */
public class Alimento {

    private int id;
    private String descricao;
    private boolean disponivel;
    private static AlimentoDbDAO dao = new AlimentoDbDAO();

    public Alimento() {
    }

    public Alimento(String descricao, boolean disponivel) {
        this.descricao = descricao;
        this.disponivel = disponivel;
    }

    public Alimento(int id, String descricao, boolean disponivel) {
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

    public static List<Alimento> all() {
        return dao.all();
    }

    public void save() {
        if (id != 0) {
            dao.update(this);
        } else {
            dao.create(this);
        }
    }

    public void delete() {
        dao.delete(this);
    }

    public static Alimento findById(int id) {
        return dao.findById(id);
    }
}
