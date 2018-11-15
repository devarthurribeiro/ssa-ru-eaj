package controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import util.utilControle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleListaAlimentos extends utilControle implements Initializable {

    @FXML
    private void abrirFormulario(ActionEvent event) {
        abrirEmModal("CadastrarMaterial", "Novo usuário");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
