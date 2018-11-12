package modelo;

import java.util.List;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import modelo.db.SetorDAO;

/**
 *
 * @author Arthur Ribeiro
 */
public class Setor {

    private int id;
    private String nome;
    private static SetorDAO dao = new SetorDAO();

    public Setor() {
    }

    public Setor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Setor(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static List<Setor> all() {
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

    public static Setor findById(int id) {
        return dao.findById(id);
    }
}
