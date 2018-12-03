package controle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import modelo.Alimento;
import modelo.Setor;
import modelo.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleCadastrarUsuario implements Initializable {
    private Usuario usuario;
    @FXML
    private VBox container;
    @FXML
    private JFXTextField nome;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField senha;
    @FXML
    private JFXTextField telefone;
    @FXML
    private JFXComboBox<Setor> setor;
    @FXML
    private JFXCheckBox admin;
    @FXML
    private void cancelar() {
        container.getScene().getWindow().hide();
    }

    @FXML
    private void salvar() {
        if(usuario == null) {
            usuario = new Usuario();
        }

        usuario.setNome(nome.getText());
        usuario.setEmail(email.getText());
        usuario.setSenha(senha.getText());
        usuario.setTelefone(telefone.getText());
        usuario.setSetor(setor.getSelectionModel().getSelectedItem());
        usuario.setAdmin(admin.isSelected());
        usuario.save();

        nome.setText("");
        email.setText("");
        senha.setText("");
        telefone.setText("");

        cancelar();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Setor> lista = FXCollections.observableArrayList(Setor.all());
        setor.getItems().addAll(lista);
    }

    public void setUsuario(Usuario u) {
        usuario = u;
        nome.setText(u.getNome());
        email.setText(u.getEmail());
        senha.setText(u.getSenha());
        telefone.setText(u.getTelefone());
        System.out.println(u.getSetor());
        setor.setValue(u.getSetor());
        admin.setSelected(u.isAdmin());
    }
}
