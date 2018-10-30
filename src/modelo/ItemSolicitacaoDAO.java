package modelo;

import java.util.List;

/**
 *
 * @author arthur
 */
public interface ItemSolicitacaoDAO {
    void create(ItemSolicitacao itemSolicitacao);
    void delete(ItemSolicitacao itemSolicitacao);
    void update(ItemSolicitacao itemSolicitacao);
    List<ItemSolicitacao> all();
    List<ItemSolicitacao> ItensBySolicitacao(Solicitacao s);
    ItemSolicitacao findById(int id);
}
