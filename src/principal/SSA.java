/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

/**
 *
 * @author ONILDO
 */
public class SSA extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/visao/Administrativo.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        VBox rootLogin = new FXMLLoader(getClass().getResource("/visao/Login.fxml")).load();
        Scene sceneLogin = new Scene(rootLogin);
        
        Stage stageLogin = new Stage(StageStyle.TRANSPARENT);
        stageLogin.initModality(Modality.APPLICATION_MODAL);
        stageLogin.centerOnScreen();
        
        stageLogin.initOwner(stage);
        stageLogin.setScene(sceneLogin);
        
        stage.centerOnScreen();
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        
        stage.show();
        stageLogin.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
