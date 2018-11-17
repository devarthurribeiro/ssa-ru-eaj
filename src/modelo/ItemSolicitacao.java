package modelo;

import modelo.db.ItemSolicitacaoDAO;

import java.util.Objects;

/**
 *
 * @author arthur
 */
public class ItemSolicitacao {

    private Alimento item;
    private Solicitacao solicitacao;
    private int quantidade;
    private boolean atendido;
    private ItemSolicitacaoDAO dao = new ItemSolicitacaoDAO();

    public ItemSolicitacao() {
    }

    public ItemSolicitacao(Alimento item, Solicitacao solicitacao, int quantidade) {
        this.item = item;
        this.solicitacao = solicitacao;
        this.quantidade = quantidade;
    }

    public Alimento getItem() {
        return item;
    }

    public void setItem(Alimento item) {
        this.item = item;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSolicitacao that = (ItemSolicitacao) o;
        return Objects.equals(item, that.item);
    }
}
