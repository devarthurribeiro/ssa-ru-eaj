package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Setor;
import modelo.SetorDAO;

/**
 *
 * @author arthur
 */
public class SetorDbDAO extends Database implements SetorDAO {

    @Override
    public void create(Setor setor) {
        open();
        String query = "INSERT INTO setor(nome) VALUES (?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, setor.getNome());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar o setor! " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void delete(Setor setor) {
        open();
        String query = "DELETE FROM setor WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, setor.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a material " + setor.getId() + ":" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void update(Setor setor) {
        open();
        String query = "UPDATE material SET nome = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, setor.getNome());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o setor: " + setor.getId() + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public List<Setor> all() {
        open();
        ArrayList<Setor> setorList = new ArrayList<>();
        String query = "SELECT * FROM setor;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome").trim();
                Setor setor = new Setor(id, nome);
                setorList.add(setor);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os materiais:" + e.getMessage());
        } finally {
            close();
        }
        return setorList;
    }

    @Override
    public Setor findById(int id) {
        open();
        Setor setor = new Setor();
        String query = "SELECT * FROM setor WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                setor.setId(rs.getInt("id"));
                setor.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao procurar setor: " + id + e.getMessage());
        } finally {
            close();
        }
        return setor;
    }

}
