/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.EditTeacher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import studentinfo.Dao.TeacherinfoDao;
import studentinfo.Messages.Messages;
import studentinfo.Model.Teacherinfo;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EditTeacherController implements Initializable {

    @FXML
    private JFXTextField edtId;
    @FXML
    private JFXTextField edTName;
    @FXML
    private JFXTextField edTRoll;
    @FXML
    private JFXTextField edTMobile;
    @FXML
    private JFXTextField edAddress;
    @FXML
    private JFXButton edSavebtnID;
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
    private void edTSave(ActionEvent event) throws SQLException {
        String id = edtId.getText();
        String name = edTName.getText();
        String roll = edTRoll.getText();
        String mobile = edTMobile.getText();
        String address = edAddress.getText();
        
        if(edtId.getText().isEmpty() || edTName.getText().isEmpty()){
            
            Messages.showinfo("Please Fill All The Fields");
            return;
        }
        Teacherinfo tinfo = new Teacherinfo(id, name, roll, mobile, address);
        tdao.updateTeacherinfo(tinfo);
         Messages.showinfo("Success Update");
        Stage stage = (Stage) edSavebtnID.getScene().getWindow();
        stage.close();
        
               
    }

    @FXML
    private void edTCancel(ActionEvent event) {
        Stage stage = (Stage) edSavebtnID.getScene().getWindow();
        stage.close();
    }

    public void setTeachersinfo(Teacherinfo selectedTeacher) {
        edtId.setText(selectedTeacher.getId());
        edTName.setText(selectedTeacher.getName());
        edTRoll.setText(selectedTeacher.getRoll());
        edTMobile.setText(selectedTeacher.getMobile());
        edAddress.setText(selectedTeacher.getAddress());
    }

}
