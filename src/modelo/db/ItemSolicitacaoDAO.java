package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.Alert;
import modelo.Alimento;
import modelo.ItemSolicitacao;
import modelo.Solicitacao;
import util.AlertBox;

/**
 * @author arthur
 */
public class ItemSolicitacaoDAO extends Database {
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
}
