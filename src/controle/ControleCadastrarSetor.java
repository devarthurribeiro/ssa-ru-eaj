package controle;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import modelo.Setor;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleCadastrarSetor implements Initializable {
    private Setor setor;
    @FXML
    private VBox container;
    @FXML
    private JFXTextField nome;
    @FXML
    private void cancelar() {
        container.getScene().getWindow().hide();
    }
    @FXML
    private void salvar() {
        if(setor == null) {
            setor = new Setor();
        }

        setor.setNome(nome.getText());
        setor.save();

        nome.setText("");
        cancelar();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void setSetor(Setor s) {
        setor = s;
        nome.setText(setor.getNome());
    }
}
