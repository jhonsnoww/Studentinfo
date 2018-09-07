/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.addStudent;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import studentinfo.Dao.StudentinfoDao;
import studentinfo.Messages.Messages;
import studentinfo.Model.Student;

public class AddstudentController implements Initializable {

    @FXML
    private JFXTextField studentId;
    @FXML
    private JFXTextField studentName;
    @FXML
    private JFXTextField studentRoll;
    @FXML
    private JFXTextField studentMobile;
    @FXML
    private JFXTextField StudentAddress;

    private StudentinfoDao sdao;
    @FXML
    private ComboBox<String> comboBox;
    
    ObservableList<String> list = FXCollections.observableArrayList("6-BE","5-BE","4-BE","3-BE","2-BE","1-BE");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sdao = new StudentinfoDao();
        comboBox.setValue("6-BE");
        comboBox.setItems(list);
        studentRoll.setText(comboBox.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void saveStudentInfoBtn(ActionEvent event) {

        if (studentId.getText().isEmpty() || studentName.getText().isEmpty()
                || studentRoll.getText().isEmpty() || studentMobile.getText().isEmpty()
                || StudentAddress.getText().isEmpty()) {
            Messages.showinfo("Can't Empty All The Fields");
        } else {

            String id = studentId.getText();
            String name = studentName.getText();
            String roll = studentRoll.getText();
            String Mobile = studentMobile.getText();
            String address = StudentAddress.getText();

            Student student = new Student(id, name, roll, Mobile, address);

            try {
                sdao.saveStudent(student);
                Messages.showinfo("Adding Student Success");
                studentId.clear();
                studentName.clear();
                studentRoll.clear();
                studentMobile.clear();
                StudentAddress.clear();
            } catch (SQLException ex) {
                Messages.showinfo("Adding Student Faild \n"+ex.getMessage());
                Logger.getLogger(AddstudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void chooseBox(ActionEvent event) {
        studentRoll.setText(comboBox.getSelectionModel().getSelectedItem());
    }

}
