/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ONILDO
 */
public class ControleLogin implements Initializable {

    @FXML
    private VBox rootPane;

    @FXML
    private void entrar(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/visao/Administrativo.fxml"));
        Scene scena = new Scene(parent);
        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.close();
        main_stage.setScene(scena);
        main_stage.setResizable(true);
        main_stage.setMaximized(true);
        main_stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
