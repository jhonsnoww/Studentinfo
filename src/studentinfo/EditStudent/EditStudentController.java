/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.EditStudent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import studentinfo.Dao.StudentinfoDao;
import studentinfo.Messages.Messages;
import studentinfo.Model.Student;

public class EditStudentController implements Initializable {

    @FXML
    private JFXTextField edStudentid;
    @FXML
    private JFXTextField edName;
    @FXML
    private JFXTextField edRoll;
    @FXML
    private JFXTextField edMobile;
    @FXML
    private JFXTextField edAddress;

    private StudentinfoDao sdao;
    @FXML
    private JFXButton edSavebtn;
    @FXML
    private ComboBox<String> comboBox;
    
     ObservableList<String> list = FXCollections.observableArrayList("6-BE","5-BE","4-BE","3-BE","2-BE","1-BE");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sdao = new StudentinfoDao();
         
        comboBox.setItems(list);
        edRoll.setText(comboBox.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void edSave(ActionEvent event) throws SQLException {

        String id = edStudentid.getText();
        String name = edName.getText();
        String roll = edRoll.getText();
        String mobile = edMobile.getText();
        String address = edAddress.getText();

        if (edStudentid.getText().isEmpty() || edName.getText().isEmpty()) {
            Messages.showinfo("Please Fill all the fields");
            return;
        }
        Student student = new Student(id, name, roll, mobile, address);

        sdao.updateStudent(student);
        Messages.showinfo("Success Update");
        Stage stage = (Stage) edSavebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void edCancel(ActionEvent event) {
        Stage stage = (Stage) edSavebtn.getScene().getWindow();
        stage.close();
    }

    public void setStudentInfo(Student selectedStudent) {
        edStudentid.setText(selectedStudent.getId());
        edName.setText(selectedStudent.getName());
        edRoll.setText(selectedStudent.getRoll());
        edMobile.setText(selectedStudent.getMobile());
        edAddress.setText(selectedStudent.getAddress());
        comboBox.setValue(selectedStudent.getRoll());
    }

    @FXML
    private void chooseRoll(ActionEvent event) {
         edRoll.setText(comboBox.getSelectionModel().getSelectedItem());
    }

}
