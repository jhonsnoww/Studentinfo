/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.searchView;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import studentinfo.Dao.StudentinfoDao;
import studentinfo.Dao.TeacherinfoDao;
import studentinfo.Messages.Messages;
import studentinfo.Model.Student;
import studentinfo.Model.Teacherinfo;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SearchVeiwController implements Initializable {

    @FXML
    private Text searchViewName;
    @FXML
    private Text searchViewRoll;
    @FXML
    private Text searchVeiwMobile;
    @FXML
    private Text searchViewAddress;
    @FXML
    private JFXTextField searchById;

    StudentinfoDao sdao;
    TeacherinfoDao tdao;
    @FXML
    private JFXTextField t_id;
    @FXML
    private Text searchViewName11;
    @FXML
    private Text searchViewRoll11;
    @FXML
    private Text searchVeiwMobile11;
    @FXML
    private Text searchViewAddress11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sdao = new StudentinfoDao();
        tdao = new TeacherinfoDao();

        // TODO
    }

    public void setInfo(Student student) throws SQLException {

    }

    @FXML
    private void searchByIdBtn(ActionEvent event) throws SQLException {

        String id = searchById.getText().toString();
        Student student = sdao.searchStudentInfo(id);
        if (id.isEmpty()) {
            Messages.showinfo("Please Enter Id First ...");
        }

        if (student != null) {

            searchViewName.setText(student.getName());
            searchViewRoll.setText(student.getRoll());
            searchVeiwMobile.setText(student.getMobile());
            searchViewAddress.setText(student.getAddress());

        } else {
            Messages.showinfo("Can't find Any Student for this id ..");
        }

    }

    @FXML
    private void searchTeacher(ActionEvent event) throws SQLException {
        String t_id = this.t_id.getText().toString();
        
         Teacherinfo tinfo = tdao.searchTearchInfo(t_id);
         if(t_id.isEmpty()){
             Messages.showinfo("Please Enter Teacher Id");
         }

        if (tinfo != null) {
            searchViewName11.setText(tinfo.getName());
            searchViewRoll11.setText(tinfo.getRoll());
            searchVeiwMobile11.setText(tinfo.getMobile());
            searchViewAddress11.setText(tinfo.getAddress());
        }
        else {
            Messages.showinfo("Can't find Any Teacher for this id ..");
        }
    }

}
