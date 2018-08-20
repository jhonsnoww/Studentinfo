/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.addTeacher;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import studentinfo.Dao.TeacherinfoDao;
import studentinfo.Messages.Messages;
import studentinfo.Model.Teacherinfo;
import studentinfo.addStudent.AddstudentController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddTeacherController implements Initializable {

    @FXML
    private JFXTextField teacherId;
    @FXML
    private JFXTextField teacherName;
    @FXML
    private JFXTextField teacherRoll;
    @FXML
    private JFXTextField teacherMobile;
    @FXML
    private JFXTextField teacherAddress;
    
    private TeacherinfoDao tdao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tdao = new TeacherinfoDao();
        // TODO
    }    

    @FXML
    private void saveTeacherinfoBtn(ActionEvent event) {
        
        if (teacherId.getText().isEmpty() || teacherName.getText().isEmpty()
                || teacherRoll.getText().isEmpty() || teacherMobile.getText().isEmpty()
                || teacherAddress.getText().isEmpty()) {
            Messages.showinfo("Can't Empty All The Fields");
        } else {

            String id = teacherId.getText();
            String name = teacherName.getText();
            String roll = teacherRoll.getText();
            String Mobile = teacherMobile.getText();
            String address = teacherAddress.getText();

            Teacherinfo tinfo = new Teacherinfo(id, name, roll, Mobile, address);

            try {
                tdao.saveTeacher(tinfo);
                Messages.showinfo("Adding Teacher Success");
                teacherId.clear();
                teacherName.clear();
                teacherRoll.clear();
                teacherMobile.clear();
                teacherAddress.clear();
            } catch (SQLException ex) {
                Messages.showinfo("Adding Teacherinfo Faild");
                Logger.getLogger(AddstudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}}
