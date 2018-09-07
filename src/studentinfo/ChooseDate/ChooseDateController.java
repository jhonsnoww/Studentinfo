/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.ChooseDate;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ChooseDateController implements Initializable {

    @FXML
    private JFXDatePicker chooseStartDate;
    @FXML
    private JFXDatePicker chooseEndDate;

    public static LocalDate getCurrentYear() {

        Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH);
        int d = now.get(Calendar.DATE);

        return LocalDate.of(y, m, d);
    }

    public static LocalDate getLastYear() {

        Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH);
        int d = now.get(Calendar.DATE);

        return LocalDate.of(y - 1, m, d);
    }

    public static LocalDate startDate;
    
    public static LocalDate endDate;

    @FXML
    private AnchorPane homeViewChoose;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void onClickOK(ActionEvent event) throws SQLException {

        homeViewChoose.getScene().getWindow().hide();

    }

    @FXML
    private void chooseStartDate(ActionEvent event) {
        startDate = chooseStartDate.getValue();
    }

    @FXML
    private void ChooseEndDate(ActionEvent event) {
        endDate = chooseEndDate.getValue();
    }

}
