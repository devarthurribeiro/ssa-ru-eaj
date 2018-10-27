package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Material;
import modelo.MaterialDAO;
/**
 *
 * @author arthur
 */
public class MaterialDbDAO extends Database implements MaterialDAO {

    @Override
    public void create(Material material) {
        open();
        String query = "INSERT INTO material(descricao) VALUES (?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, material.getDescricao());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar o porco! " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void delete(Material material) {
        open();
        String query = "DELETE FROM material WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, material.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a material " + material.getId()+ ":" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void update(Material material) {
        open();
        String query = "UPDATE material SET descricao = ?, disponivel = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, material.getDescricao());
            pst.setBoolean(2, material.isDisponivel());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o materiais: " + material.getId()+ e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public List<Material> all() {
        open();
        ArrayList<Material> materialList = new ArrayList<>();
        String query = "SELECT * FROM material;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String desc = rs.getString("descricao").trim();
                boolean disponivel = rs.getBoolean("disponivel");
                Material m = new Material(id, desc, disponivel);
                materialList.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os materiais:" + e.getMessage());
        } finally {
            close();
        }
        return materialList;
    }

    @Override
    public Material findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
