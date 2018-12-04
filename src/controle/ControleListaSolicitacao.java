package controle;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ItemSolicitacao;
import modelo.Setor;
import modelo.Solicitacao;
import javafx.scene.control.TableView;
import util.Report;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControleListaSolicitacao implements Initializable {
    @FXML
    private TableView<Solicitacao> tabelaSolicitacoes;
    @FXML
    private TableView<ItemSolicitacao> tabelaItens;

    @FXML
    public void selectSolicitacao() {
        Solicitacao s = tabelaSolicitacoes.getSelectionModel().getSelectedItem();
        ObservableList<ItemSolicitacao> lista = FXCollections.observableArrayList(ItemSolicitacao.itemSolicitacaosBySolicitacao(s));
        tabelaItens.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item"));
        tabelaItens.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        System.out.println(lista);
        tabelaItens.setItems(lista);
    }

    @FXML
    public void imprimir() throws DocumentException {
        Report report = new Report();
        Solicitacao s = tabelaSolicitacoes.getSelectionModel().getSelectedItem();
        report.setTitle("Solicitação")

        report.AddParagraph("Setor: " + s.getSetor());
        report.AddParagraph("Solicitante: " + s.getUsuario());
        report.AddParagraph("Data: " + s.getData());

        PdfPTable novaTabela = report.newTable(2);
        novaTabela.addCell("Código");
        novaTabela.addCell("Nome");
        for (ItemSolicitacao item : s.getItens()) {
            novaTabela.addCell(item.getItem().getDescricao());
            novaTabela.addCell(item.getQuantidade()+"");
        }
        report.addTable(novaTabela);
        report.generate();
        report.open();
    }

    @FXML
    public void arquivar() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listarSolicitacao(Solicitacao.all());
    }

    public void listarSolicitacao(List<Solicitacao> solicitacaos) {
        ObservableList<Solicitacao> lista = FXCollections.observableArrayList(solicitacaos);
        tabelaSolicitacoes.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tabelaSolicitacoes.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("data"));
        tabelaSolicitacoes.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("setor"));
        tabelaSolicitacoes.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tabelaSolicitacoes.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("arquivada"));
        tabelaSolicitacoes.setItems(lista);
    }
}
