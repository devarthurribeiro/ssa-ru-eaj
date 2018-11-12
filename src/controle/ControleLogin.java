package controle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author ONILDO
 */
public class ControleLogin implements Initializable {

    @FXML
    private VBox rootPane;
    @FXML
    private JFXButton btnEntrar;
    @FXML
    private void entrar(ActionEvent event) throws IOException {
        btnEntrar.setDisable(true);
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("/visao/Administrativo.fxml"));
        Parent parent = loader.load();
        Scene scena = new Scene(parent);
        Stage satge = (Stage) ((Node) event.getSource()).getScene().getWindow();
        satge.close();
        satge.setScene(scena);
        satge.setResizable(true);
        satge.setMaximized(true);
        satge.setTitle("Administrativo - SSA");
        satge.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
