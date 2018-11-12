package controle;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.Setor;
import util.Report;
import util.utilControle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleListaSetores implements Initializable {
    @FXML
    private TableView<Setor> tabela;

    @FXML
    private void cadastrar(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/visao/CadastrarSetor.fxml"));
        abrirCadastro(parent, "Cadastrar setor");
    }

    @FXML
    private void editar() throws IOException {
        Setor setor = tabela.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/visao/CadastrarSetor.fxml"));
        Parent parent = loader.load();
        ControleCadastrarSetor controller = loader.getController();
        controller.setSetor(setor);
        abrirCadastro(parent, "Editar setor");
    }

    @FXML
    private void remover() {
        Setor setor = tabela.getSelectionModel().getSelectedItem();
        setor.delete();
        listarSetores();
    }

    @FXML
    private void relatorio() {
        Report report = new Report();
        report.setTitle("Relatório de setores atendidos");
        PdfPTable novaTabela = report.newTable(2);
        novaTabela.addCell("Código");
        novaTabela.addCell("Nome");
        for (Setor s : Setor.all()) {
            novaTabela.addCell(s.getId() + "");
            novaTabela.addCell(s.getNome());
        }
        report.addTable(novaTabela);
        report.generate();
        report.open();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarSetores();
    }

    public void abrirCadastro(Parent parent, String titulo) {
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                listarSetores();
            }
        });
        stage.show();
    }

    public void listarSetores() {
        ObservableList<Setor> lista = FXCollections.observableArrayList(Setor.all());
        tabela.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tabela.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabela.setItems(lista);
    }

}
