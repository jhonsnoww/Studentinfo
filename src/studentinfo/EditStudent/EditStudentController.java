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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import studentinfo.Dao.StudentinfoDao;
import studentinfo.Messages.Messages;
import studentinfo.Model.Student;
import studentinfo.Model.Teacherinfo;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sdao = new StudentinfoDao();

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
    }

}
