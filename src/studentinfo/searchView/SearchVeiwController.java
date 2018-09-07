/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.searchView;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private JFXTextField searchViewName;
    @FXML
    private Text searchViewRoll;
    @FXML
    private Text searchVeiwMobile;
    @FXML
    private Text searchViewAddress;
    private JFXTextField searchById;

    StudentinfoDao sdao;
    TeacherinfoDao tdao;
    private JFXTextField t_id;
    @FXML
    private Text searchViewName11;
    @FXML
    private Text searchViewRoll11;
    @FXML
    private Text searchVeiwMobile11;
    @FXML
    private Text searchViewAddress11;
    @FXML
    private Text searchViewId;
    @FXML
    private Text searchViewNameresult;
    @FXML
    private JFXTextField t_Name;
    @FXML
    private Text searchViewId11;
    @FXML
    private Text searchViewYear;
    @FXML
    private Text searchViewDate;

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

        String name = searchViewName.getText();
        Student student = sdao.searchStudentInfo(name);
        if (name.isEmpty()) {
            Messages.showinfo("Please Enter Name First ...");
        }
        
     
     

        if (student != null) {

            searchViewNameresult.setText(student.getName());
            searchViewId.setText(student.getId());
            searchViewRoll.setText(student.getRoll());
            searchVeiwMobile.setText(student.getMobile());
            searchViewAddress.setText(student.getAddress());
            searchViewYear.setText(student.getDate().toString());

        } else {
            Messages.showinfo("Can't find Any Student for this Name ..");
            clearStudentView();
        }

    }

    @FXML
    private void searchTeacher(ActionEvent event) throws SQLException {
        String t_Name = this.t_Name.getText().toString();

        Teacherinfo tinfo = tdao.searchTearchInfo(t_Name);
        if (t_Name.isEmpty()) {
            Messages.showinfo("Please Enter Teacher Name");
        }

        if (tinfo != null) {
            searchViewId11.setText(tinfo.getId());
            searchViewName11.setText(tinfo.getName());
            searchViewRoll11.setText(tinfo.getRoll());
            searchVeiwMobile11.setText(tinfo.getMobile());
            searchViewAddress11.setText(tinfo.getAddress());
            searchViewDate.setText(tinfo.getDate().toString());
        } else {
            Messages.showinfo("Can't find Any Teacher for this id ..");
            clearTeacherView();
        }
    }

    public void clearStudentView() {
        searchViewNameresult.setText("---");
        searchViewRoll.setText("---");
        searchVeiwMobile.setText("---");
        searchViewAddress.setText("---");
        searchViewYear.setText("---");

    }

    public void clearTeacherView() {
        searchViewId11.setText("---");
        searchViewName11.setText("---");
        searchViewRoll11.setText("---");
        searchVeiwMobile11.setText("---");
        searchViewAddress11.setText("---");
        searchViewDate.setText("---");

    }

}
