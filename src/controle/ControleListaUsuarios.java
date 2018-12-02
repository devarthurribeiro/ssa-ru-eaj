package controle;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modelo.Usuario;
import util.utilControle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleListaUsuarios extends utilControle implements Initializable {
    @FXML
    private TableView<Usuario> tabela;
    @FXML
    private JFXTextField txtBusca;
    @FXML
    private void abrirFormulario(ActionEvent event) {
        abrirEmModal("CadastrarUsuarios", "Novo usuário");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busca();
        listarUsuarios(Usuario.all());
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
