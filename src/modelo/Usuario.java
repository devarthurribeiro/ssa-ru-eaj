package modelo;

import java.util.List;

import modelo.db.UsuarioDAO;

/**
 *
 * @author Arthur Ribeiro
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private boolean admin;
    private Setor setor;
    private static UsuarioDAO dao = new UsuarioDAO();

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String telefone, boolean admin, Setor setor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.admin = admin;
        this.setor = setor;
    }

    public Usuario(int id, String nome, String email, String senha, String telefone, boolean admin, Setor setor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.admin = admin;
        this.setor = setor;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public static List<Usuario> all() {
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
    
    public static Usuario findById(int id) {
        return dao.findById(id);
    }

    public static Usuario login(String email, String senha) {
        return dao.login(email, senha);
    }
}
