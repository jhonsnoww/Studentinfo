/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.DatabaseConfig;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studentinfo.Model.DatabaseConfigProperty;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DbConfingController implements Initializable {

    @FXML
    private TextField hostField;
    @FXML
    private Spinner<Integer> portSpinner;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passFileld;
    @FXML
    private JFXButton saveBtn;

    private DbConfigManeger dbManeger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dbManeger = new DbConfigManeger();

       DatabaseConfigProperty dbProperty = dbManeger.getDatabaseProperties();

        hostField.setText(dbProperty.getHost());
        nameField.setText(dbProperty.getUser());
        passFileld.setText(dbProperty.getPassword());  

        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(3300, 3320,Integer.parseInt(dbProperty.getPort()));
        
        portSpinner.setValueFactory(valueFactory);
      
        

    }

    @FXML
    private void saveDataBaseConfig(ActionEvent event) {
        
        String host = hostField.getText();
        String port = portSpinner.getValue().toString();
        String user = nameField.getText();
        String password = passFileld.getText();
        
        DatabaseConfigProperty dbprop = new DatabaseConfigProperty(host, port, user, password);
        dbManeger.saveDatabaseConfigProperty(dbprop);
        
         Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

}
