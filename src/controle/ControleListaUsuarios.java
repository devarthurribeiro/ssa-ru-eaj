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
import modelo.Usuario;
import util.utilControle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ONILDO
 */
public class ControleListaUsuarios extends utilControle implements Initializable {
    @FXML
    private TableView<Usuario> tabela;
    @FXML
    private JFXTextField txtBusca;

    @FXML
    private void cadastrar(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/visao/CadastroUsuario.fxml"));
        abrirCadastro(parent, "Novo usuário");
    }

    @FXML
    private void editar() throws IOException {
        Usuario usuario = tabela.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/CadastroUsuario.fxml"));
        Parent parent = loader.load();
        ControleCadastrarUsuario controller = loader.getController();
        controller.setUsuario(usuario);
        abrirCadastro(parent, "Editar usuário");
    }

    @FXML
    private void remover() {
        Usuario usuario = tabela.getSelectionModel().getSelectedItem();
        usuario.delete();
        listarUsuarios(Usuario.all());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busca();
        listarUsuarios(Usuario.all());
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
                listarUsuarios(Usuario.all());
            }
        });
        stage.show();
    }


    private void busca() {
        txtBusca.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (txtBusca.getLength() > 1) {
                    listarUsuarios(Usuario.findByName(txtBusca.getText()));
                } else {
                    listarUsuarios(Usuario.all());
                }
            }
        });
    }

    public void listarUsuarios(List<Usuario> usuarios) {
        ObservableList<Usuario> lista = FXCollections.observableArrayList(usuarios);
        tabela.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tabela.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabela.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tabela.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tabela.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("setor"));
        tabela.setItems(lista);
    }

}
