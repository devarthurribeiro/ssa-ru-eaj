package modelo.db;

import javafx.scene.control.Alert;
import modelo.Setor;
import util.AlertBox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author arthur
 */
public class SetorDAO extends Database implements Dao<Setor> {

    @Override
    public void create(Setor setor) {
        open();
        String query = "INSERT INTO setor(nome) VALUES (?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, setor.getNome());
            pst.executeUpdate();
        } catch (SQLException e) {
            new AlertBox("Erro ao criar o setor! \n " + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
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
            new AlertBox("Erro ao deletar a material! \n " + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
        } finally {
            close();
        }
    }

    @Override
    public void update(Setor setor) {
        open();
        String query = "UPDATE setor SET nome = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, setor.getNome());
            pst.setInt(2, setor.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            new AlertBox("Erro ao atualizar o setor: " + setor.getId() + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
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
            new AlertBox("Erro ao listar os setores! \n " + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
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
            new AlertBox("Erro ao procurar setor: " + id + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
        } finally {
            close();
        }
        return setor;
    }


    public ArrayList<Setor> findByName(String nome) {
        open();
        ArrayList<Setor> setorList = new ArrayList<>();
        String query = "SELECT * FROM setor WHERE nome ILIKE ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, "%" + nome + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String n = rs.getString("nome").trim();
                Setor setor = new Setor(id, n);
                setorList.add(setor);
            }

        } catch (SQLException e) {
            new AlertBox("Erro ao procurar setor: " + nome + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
        } finally {
            close();
        }
        return setorList;
    }

}
