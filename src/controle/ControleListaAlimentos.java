package controle;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.Alimento;
import modelo.Setor;
import modelo.Usuario;
import util.utilControle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleListaAlimentos extends utilControle implements Initializable {
    @FXML
    private TableView<Alimento> tabela;
    @FXML
    private JFXTextField txtBusca;
    @FXML
    private void cadastrar(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/visao/CadastroAlimento.fxml"));
        abrirCadastro(parent, "Novo usuário");
    }
    @FXML
    private void editar() throws IOException {
        Alimento alimento = tabela.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/CadastroAlimento.fxml"));
        Parent parent = loader.load();
        ControleCadastrarAlimento controller = loader.getController();
        controller.setAlimento(alimento);
        abrirCadastro(parent, "Editar alimento");
    }
    @FXML
    private void remover() {
        Alimento alimento = tabela.getSelectionModel().getSelectedItem();
        alimento.delete();
        listarAlimentos(Alimento.all());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busca();
        listarAlimentos(Alimento.all());
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
                listarAlimentos(Alimento.all());
            }
        });
        stage.show();
    }

    private void busca() {
        txtBusca.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (txtBusca.getLength() > 1) {
                    listarAlimentos(Alimento.findByName(txtBusca.getText()));
                } else {
                    listarAlimentos(Alimento.all());
                }
            }
        });
    }

    public void listarAlimentos(List<Alimento> alimentos) {
        ObservableList<Alimento> lista = FXCollections.observableArrayList(alimentos);
        tabela.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tabela.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tabela.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("disponivel"));
        tabela.setItems(lista);
    }
    
}
