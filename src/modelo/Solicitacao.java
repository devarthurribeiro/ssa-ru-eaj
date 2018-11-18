package modelo;

import modelo.db.SolicitacaoDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Arthur Ribeiro
 */
public class Solicitacao {

    private int id;
    private Setor setor;
    private Usuario usuario;
    private Date data;
    private String observacao;
    private boolean arquivada;
    private List<ItemSolicitacao> itens = new ArrayList<ItemSolicitacao>();

    private static SolicitacaoDAO dao = new SolicitacaoDAO();

    public Solicitacao() {
    }

    public Solicitacao(Setor setor, Usuario usuario, Date data, String observacao, boolean arquivada) {
        this.setor = setor;
        this.usuario = usuario;
        this.data = data;
        this.observacao = observacao;
        this.arquivada = arquivada;
    }

    public Solicitacao(int id, Setor setor, Usuario usuario, Date data, String observacao, boolean arquivada) {
        this.id = id;
        this.setor = setor;
        this.usuario = usuario;
        this.data = data;
        this.observacao = observacao;
        this.arquivada = arquivada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isArquivada() {
        return arquivada;
    }

    public void setArquivada(boolean arquivada) {
        this.arquivada = arquivada;
    }

    public List<ItemSolicitacao> getItens() {
        return itens;
    }

    public void setItens(List<ItemSolicitacao> ItemSolicitacao) {
        this.itens = ItemSolicitacao;
    }

    public void save() {
        if (id != 0) {
            dao.update(this);
        } else {
            dao.create(this);
        }
    }

    public static List<Solicitacao> all() {
        return dao.all();
    }

    public static Solicitacao findById(int id) {
        return dao.findById(id);
    }
    public static List<Solicitacao> findByUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }


}
