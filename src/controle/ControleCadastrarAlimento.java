package controle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import modelo.Alimento;
import modelo.Setor;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleCadastrarAlimento implements Initializable {
    private Alimento alimento;
    @FXML
    private VBox container;
    @FXML
    private JFXTextField descricao;
    @FXML
    private JFXCheckBox disponivel;

    @FXML
    private void cancelar() {
        container.getScene().getWindow().hide();
    }
    @FXML
    private void salvar() {
        if(alimento == null) {
            alimento = new Alimento();
        }

        alimento.setDescricao(descricao.getText());
        alimento.setDisponivel(disponivel.isSelected());
        alimento.save();

        descricao.setText("");
        cancelar();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setAlimento(Alimento a) {
        alimento = a;
        descricao.setText(a.getDescricao());
        disponivel.setSelected(a.isDisponivel());
    }
    
}
