package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Setor;
import modelo.Usuario;
import modelo.Material;

/**
 *
 * @author Arthur Ribeiro
 */
public class Solicitacao {
    private Setor setor;
    private Usuario usuario;
    private Date data;
    private String observacao;
    private List<Material> Materiais = new ArrayList<Material>();
    
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

    public List<Material> getMateriais() {
        return Materiais;
    }

    public void setMateriais(List<Material> Materiais) {
        this.Materiais = Materiais;
    }
    
    
}
