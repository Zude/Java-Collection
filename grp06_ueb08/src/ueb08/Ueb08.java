/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ueb08;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alex
 */
public class Ueb08 extends Application {
    
   
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Taschenrechner");
        stage.setMinHeight(320.0);
        stage.setMinWidth(240.0);
        stage.show();
    }

    /**
     * @throws java.lang.Exception
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
      
    
       launch(args);
        
    }
        
    
}
