package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import com.jfoenix.controls.JFXButton;
/**
 *
 * @author ONILDO
 */
public class ControleAdministrativo implements Initializable {
    
    @FXML
    private BorderPane borderpane;
    @FXML 
    private void abrirTela(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();
        String id = btn.getId();
        System.out.println(id);
        open("/visao/"+id+".fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void open(String formName) throws IOException {
        Parent form = FXMLLoader.load(getClass().getResource(formName));
        borderpane.setCenter(form);
    }
}
