package controle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;
import util.AlertBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ONILDO
 */
public class ControleLogin implements Initializable {

    @FXML
    private VBox rootPane;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtSenha;
    @FXML
    private JFXButton btnEntrar;

    @FXML
    private void entrar(ActionEvent event) {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        System.out.println(email);
        boolean logado = false;
        if (!email.isEmpty() && !senha.isEmpty()) {
            Usuario usuario = Usuario.login(email, senha);
            logado = (usuario.getEmail() == email);
            if (logado) {
                Stage satge = (Stage) ((Node) event.getSource()).getScene().getWindow();
                if (usuario.isAdmin()) abrirAdmin(usuario, satge);
                else abrirSolicitacoe(usuario, satge);
            } else {
                new AlertBox("Email ou senha inválido!", "Falha no login", new Alert(Alert.AlertType.WARNING));
            }

        } else {
            new AlertBox("Informe o email e a senha!", "Campos", new Alert(Alert.AlertType.WARNING));
        }
    }
    private void abrirAdmin(Usuario usuario, Stage satge) {
        try {
            btnEntrar.setDisable(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Administrativo.fxml"));
            Parent parent = loader.load();
            ControleAdministrativo ctrl = loader.getController();
            ctrl.setUsuario(usuario);
            Scene scena = new Scene(parent);
            satge.close();
            satge.setScene(scena);
            satge.setResizable(true);
            satge.setMaximized(true);
            satge.setTitle("Administrativo - SSA");
            satge.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void abrirSolicitacoe(Usuario usuario, Stage satge) {
        try {
            btnEntrar.setDisable(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Solicitacoes.fxml"));
            Parent parent = loader.load();
            ControleSolicitacao ctrl = loader.getController();
            ctrl.setUsuario(usuario);
            Scene scena = new Scene(parent);
            satge.close();
            satge.setScene(scena);
            satge.setResizable(true);
            satge.setMaximized(true);
            satge.setTitle("Solicitação - SSA");
            satge.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
