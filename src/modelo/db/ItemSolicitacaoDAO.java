package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.control.Alert;
import modelo.*;
import util.AlertBox;

/**
 * @author arthur
 */
public class ItemSolicitacaoDAO extends Database implements Dao<ItemSolicitacao> {
    public void create(ItemSolicitacao itemSolicitacao) {
        open();
        try {
            String query = "INSERT INTO \"itensSolicitacao\"(\"alimentoId\", \"solicitacaoId\", quantidade) VALUES (?, ?, ?);";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, itemSolicitacao.getItem().getId());
            pst.setInt(2, itemSolicitacao.getSolicitacao().getId());
            pst.setInt(3, itemSolicitacao.getQuantidade());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            new AlertBox("Erro ao adicionar item! \n " + e.getMessage(), "Erro", new Alert(Alert.AlertType.ERROR));
        } finally {
            close();
        }
    }

    @Override
    public void delete(ItemSolicitacao object) {

    }

    @Override
    public void update(ItemSolicitacao object) {

    }

    @Override
    public List<ItemSolicitacao> all() {
        return null;
    }

    @Override
    public ItemSolicitacao findById(int id) {
        return null;
    }

    public List<ItemSolicitacao> itemSolicitacaosBySolicitacao(Solicitacao s) {
        List<ItemSolicitacao> lista = new ArrayList<>();
        open();
        try {
            String query = "SELECT * FROM \"itensSolicitacao\" WHERE  = \"solicitacaoId\" = ?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, s.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Alimento alimento = Alimento.findById(rs.getInt("alimentoId"));
                Solicitacao solicitacao = Solicitacao.findById(rs.getInt("solicitacaoId"));
                int quantidade = rs.getInt("quantidade");
                boolean atendido = rs.getBoolean("atendido");
                ItemSolicitacao item = new ItemSolicitacao(alimento, solicitacao, quantidade);

                lista.add(item);
            }

        } catch (SQLException e) {

        } finally {
            close();
        }
        return lista;
    }
}
