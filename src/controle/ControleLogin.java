/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 *
 * @author ONILDO
 */
public class ControleLogin implements Initializable {
    
    @FXML
    private VBox rootPane;
    
    @FXML
    private void entrar(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
