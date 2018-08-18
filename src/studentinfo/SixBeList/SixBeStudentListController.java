/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.SixBeList;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studentinfo.Dao.StudentinfoDao;
import studentinfo.EditStudent.EditStudentController;
import studentinfo.Messages.Messages;
import studentinfo.Model.Student;
import studentinfo.StudentList.StudentListController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SixBeStudentListController implements Initializable {

    @FXML
    private MenuItem editStudentSix;
    @FXML
    private MenuItem deleteStudentSix;
    @FXML
    private TableColumn<Student, String> idSix;
    @FXML
    private TableColumn<Student, String> nameSix;
    @FXML
    private TableColumn<Student, String> rollSix;
    @FXML
    private TableColumn<Student, String> mobileSix;
    @FXML
    private TableColumn<Student, String> addressSix;
    @FXML
    private TableView<Student> sTableSix;
    StudentinfoDao sdao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sdao = new StudentinfoDao();
        initColumn();
        try {
            loadData();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void editStudent(ActionEvent event) throws IOException, SQLException {
        Student selectedStudent = sTableSix.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo/EditStudent/editStudent.fxml"));
            Parent root = loader.load();
            EditStudentController edcontroller = loader.getController();
            edcontroller.setStudentInfo(selectedStudent);

            Stage stage = new Stage();
            stage.initOwner(sTableSix.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            loadData();

        }

    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        Student selectedStudent = sTableSix.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            Optional<ButtonType> selectedOption = Messages.shwoConfimMessage("Are You Sure Want To Delete This ? ");

            if (selectedOption.get() == ButtonType.OK) {

                try {
                    sdao.deleteStudentinfo(selectedStudent);
                    sTableSix.getItems().remove(selectedStudent);
                } catch (SQLException ex) {
                    Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    private void initColumn() {
        idSix.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameSix.setCellValueFactory(new PropertyValueFactory<>("name"));
        rollSix.setCellValueFactory(new PropertyValueFactory<>("roll"));
        mobileSix.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressSix.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    private void loadData() throws SQLException {
        ObservableList<Student> list;

        list = sdao.getSixBEStudents();
        sTableSix.getItems().setAll(list);

    }

}
