package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Setor;
import modelo.Usuario;

/**
 *
 * @author arthur
 */
public class UsuarioDAO extends Database {

    public void create(Usuario usuario) {
        open();
        String query = "INSERT INTO usuario(nome, email, senha, telefone, admin, setorId) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getTelefone());
            pst.setBoolean(5, usuario.isAdmin());
            pst.setInt(6, usuario.getSetor().getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar o usuario! " + e.getMessage());
        } finally {
            close();
        }
    }

    public void delete(Usuario usuario) {
        open();
        String query = "DELETE FROM usuario WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, usuario.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a usuario " + usuario.getId() + ":" + e.getMessage());
        } finally {
            close();
        }
    }

    public void update(Usuario usuario) {
        open();
        String query = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ?, admin = ?, setorId = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getTelefone());
            pst.setBoolean(5, usuario.isAdmin());
            pst.setInt(6, usuario.getSetor().getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o usuario: " + usuario.getId() + e.getMessage());
        } finally {
            close();
        }
    }

    public List<Usuario> all() {
        open();
        ArrayList<Usuario> usuarioList = new ArrayList<>();
        String query = "SELECT * FROM usuario;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");
                boolean admin = rs.getBoolean("admin");
                Setor s = Setor.findById(rs.getInt("setorId"));
                Usuario u = new Usuario(nome, email, senha, telefone, admin, s);
                usuarioList.add(u);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os alimentos:" + e.getMessage());
        } finally {
            close();
        }
        return usuarioList;
    }

    public Usuario findById(int id) {
        open();
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM usuario WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");
                boolean admin = rs.getBoolean("admin");
                Setor setor = Setor.findById(rs.getInt("setorId"));

                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);
                usuario.setTelefone(telefone);
                usuario.setAdmin(admin);
                usuario.setSetor(setor);

            }

        } catch (SQLException e) {
            System.err.println("Erro ao procurar usuario: " + id + e.getMessage());
        } finally {
            close();
        }
        return usuario;
    }

}
