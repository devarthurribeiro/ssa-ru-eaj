package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.db.SetorDAO;

/**
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

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Setor setor = (Setor) o;

        if (id != setor.id) return false;
        return nome != null ? nome.equals(setor.nome) : setor.nome == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
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

    public static ArrayList<Setor> findByName(String n) {
        return dao.findByName(n);
    }

    public static Setor findById(int id) {
        return dao.findById(id);
    }
}
