package modelo;

/**
 *
 * @author arthur
 */
public class ItemSolicitacao {

    private int solicitacaoId;
    private int alimentoId;
    private float quantidade;
    private boolean atendio;

    public ItemSolicitacao() {
    }

    public ItemSolicitacao(int solicitacaoId, int alimentoId, float quantidade, boolean atendio) {
        this.solicitacaoId = solicitacaoId;
        this.alimentoId = alimentoId;
        this.quantidade = quantidade;
        this.atendio = atendio;
    }

    public boolean isAtendio() {
        return atendio;
    }

    public void setAtendio(boolean atendio) {
        this.atendio = atendio;
    }

    public int getSolicitacaoId() {
        return solicitacaoId;
    }

    public void setSolicitacaoId(int solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public int getAlimentoId() {
        return alimentoId;
    }

    public void setAlimentoId(int alimentoId) {
        this.alimentoId = alimentoId;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

}
