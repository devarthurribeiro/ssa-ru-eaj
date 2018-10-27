package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface SolicitacaoDAO {
    void create(Solicitacao solicitacao);
    void delete(Solicitacao solicitacao);
    void update(Solicitacao solicitacao);
    List<Solicitacao> all();
    Solicitacao findById(int id);
}
