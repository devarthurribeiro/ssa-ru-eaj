package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import modelo.Alimento;
import modelo.Setor;
import modelo.Usuario;
import util.AlertBox;

/**
 * @author arthur
 */
public class AlimentoDAO extends Database implements Dao<Alimento> {
    @Override
    public void create(Alimento alimento) {
        open();
        String query = "INSERT INTO alimento(descricao, disponivel) VALUES (?,?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, alimento.getDescricao());
            pst.setBoolean(2, alimento.isDisponivel());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar o porco! " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void delete(Alimento alimento) {
        open();
        String query = "DELETE FROM alimento WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, alimento.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a alimento " + alimento.getId() + ":" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void update(Alimento alimento) {
        open();
        String query = "UPDATE alimento SET descricao = ?, disponivel = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, alimento.getDescricao());
            pst.setBoolean(2, alimento.isDisponivel());
            pst.setInt(3, alimento.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o materiais: " + alimento.getId() + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public List<Alimento> all() {
        open();
        ArrayList<Alimento> alimentoList = new ArrayList<>();
        String query = "SELECT * FROM alimento;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String desc = rs.getString("descricao").trim();
                boolean disponivel = rs.getBoolean("disponivel");
                Alimento m = new Alimento(id, desc, disponivel);
                alimentoList.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os materiais:" + e.getMessage());
        } finally {
            close();
        }
        return alimentoList;
    }

    @Override
    public Alimento findById(int id) {
        open();
        Alimento materail = new Alimento();
        String query = "SELECT * FROM alimento WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                materail.setId(rs.getInt("id"));
                materail.setDescricao(rs.getString("descricao").trim());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao procurar alimento: " + id + e.getMessage());
        } finally {
            close();
        }
        return materail;
    }

    public ArrayList<Alimento> findByName(String n) {
        open();
        ArrayList<Alimento> lista = new ArrayList<>();
        String query = "SELECT * FROM alimento WHERE descricao ILIKE ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, "%" + n + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String desc = rs.getString("descricao").trim();
                boolean disponivel = rs.getBoolean("disponivel");
                Alimento m = new Alimento(id, desc, disponivel);
                lista.add(m);
            }

        } catch (SQLException e) {
            new AlertBox("Erro ao procurar alimentos: " + n + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
        } finally {
            close();
        }
        return lista;
    }
}
