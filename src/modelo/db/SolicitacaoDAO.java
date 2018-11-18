package modelo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Setor;
import modelo.Solicitacao;
import modelo.Usuario;

/**
 * @author arthur
 */
public class SolicitacaoDAO extends Database {

    public void create(Solicitacao solicitacao) {
        open();
        String query = "INSERT INTO solicitacao(data, \"usuarioId\", \"setorId\", observacao) VALUES (?,?,?,?);";
        try {
            PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            pst.setInt(2, solicitacao.getUsuario().getId());
            pst.setInt(3, solicitacao.getSetor().getId());
            pst.setString(4, solicitacao.getObservacao());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                solicitacao.setId(id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar solicitacao! " + e.getMessage());
        } finally {
            close();
        }
    }

    public void delete(Solicitacao solicitacao) {
        open();
        String query = "DELETE FROM solicitacao WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, solicitacao.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a solicitacao " + solicitacao.getId() + ":" + e.getMessage());
        } finally {
            close();
        }
    }

    public void update(Solicitacao solicitacao) {
        open();
        String query = "UPDATE solicitacao SET arquivada = ?, observaocao = ? WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setBoolean(1, solicitacao.isArquivada());
            pst.setString(2, solicitacao.getObservacao());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o solicitacao: " + solicitacao.getId() + e.getMessage());
        } finally {
            close();
        }
    }

    public List<Solicitacao> all() {
        open();
        ArrayList<Solicitacao> solicitacaoList = new ArrayList<>();
        String query = "SELECT * FROM solicitacao;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Setor setor = Setor.findById(rs.getInt("setorId"));
                Usuario usuario = Usuario.findById(rs.getInt("UsuarioId"));
                String observacao = rs.getString("observacao");
                Date data = rs.getDate("data");
                boolean disponivel = rs.getBoolean("disponivel");
                Solicitacao m = new Solicitacao(id, setor, usuario, data, observacao, disponivel);
                solicitacaoList.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar as solicitacaos:" + e.getMessage());
        } finally {
            close();
        }
        return solicitacaoList;
    }

    public Solicitacao findById(int id) {
        open();
        Solicitacao solicitacao = new Solicitacao();
        String query = "SELECT * FROM solicitacao WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                solicitacao.setId(rs.getInt("id"));
                solicitacao.setData(rs.getDate("data"));
                solicitacao.setUsuario(Usuario.findById(rs.getInt("usuarioId")));
                solicitacao.setSetor(Setor.findById(rs.getInt("setorId")));
                solicitacao.setObservacao(rs.getString("observacao"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao procurar solicitacao: " + id + e.getMessage());
        } finally {
            close();
        }
        return solicitacao;
    }

}
