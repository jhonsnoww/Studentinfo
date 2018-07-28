/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import studentinfo.Database.Database;
import studentinfo.Messages.Messages;

/**
 *
 * @author hp
 */
public class StudentInfo extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
       try{
               Database db = Database.getInstance();
       }
           catch(SQLException e){
              Messages.showAndWait("Can't Connect to Database");
                   e.printStackTrace();
                   }
       
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Information");
        stage.getIcons().add(new Image("/studentinfo/images/icon.png"));
        stage.show();
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
