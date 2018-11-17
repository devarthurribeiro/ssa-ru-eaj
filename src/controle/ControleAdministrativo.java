package controle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ONILDO
 */
public class ControleAdministrativo implements Initializable {
    Usuario usuarioLogado;
    @FXML
    private Label txtUsuario;
    @FXML
    private JFXButton btn;
    @FXML
    private JFXButton Principal;
    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane paneCenter;
    @FXML
    private VBox containerPrincipal;

    @FXML
    private void abrirTela(ActionEvent event) {
        if (btn != null) {
            btn.getStyleClass().removeAll("active");
        }
        btn = (JFXButton) event.getSource();
        btn.getStyleClass().add("active");
        String id = btn.getId();
        System.out.println(id);
        open("/visao/" + id + ".fxml");
    }

    @FXML
    public void sair() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        open("/visao/Principal.fxml");
        btn = Principal;
        btn.getStyleClass().add("active");
    }

    public void open(String formName) {
        try {
            Parent form = FXMLLoader.load(getClass().getResource(formName));
            containerPrincipal.getChildren().setAll(form);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void setUsuario(Usuario u) {
        usuarioLogado = u;
        txtUsuario.setText(u.getNome());
    }
}
