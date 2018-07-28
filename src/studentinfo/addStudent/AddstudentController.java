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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sdao = new StudentinfoDao();

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
            } catch (SQLException ex) {
                Messages.showinfo("Adding Student Faild");
                Logger.getLogger(AddstudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
