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
import modelo.Alimento;
import modelo.Usuario;
import util.utilControle;

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
    private void abrirFormulario(ActionEvent event) {
        abrirEmModal("CadastrarMaterial", "Novo usuário");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busca();
        listarAlimentos(Alimento.all());
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
