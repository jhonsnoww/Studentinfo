/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.fourBe;

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
import javafx.scene.image.Image;
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
public class FourBeController implements Initializable {

    @FXML
    private TableView<Student> sTableFour;
    @FXML
    private MenuItem editStudentSix;
    @FXML
    private MenuItem deleteStudentSix;
    @FXML
    private TableColumn<Student, String> idFour;
    @FXML
    private TableColumn<Student, String> nameFour;
    @FXML
    private TableColumn<Student, String> rollfour;
    @FXML
    private TableColumn<Student, String> mobileFour;
    @FXML
    private TableColumn<Student, String> addressFour;
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

        Student selectedStudent = sTableFour.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo/EditStudent/editStudent.fxml"));
            Parent root = loader.load();
            EditStudentController edcontroller = loader.getController();
            edcontroller.setStudentInfo(selectedStudent);

            Stage stage = new Stage();
            stage.initOwner(sTableFour.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            stage.setTitle("Edit Student");
            stage.getIcons().add(new Image("/studentinfo/images/student.png"));
            stage.setScene(scene);
            stage.showAndWait();
            loadData();

        }
    }

    @FXML
    private void deleteStudent(ActionEvent event) {

        Student selectedStudent = sTableFour.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            Optional<ButtonType> selectedOption = Messages.shwoConfimMessage("Are You Sure Want To Delete This ? ");

            if (selectedOption.get() == ButtonType.OK) {

                try {
                    sdao.deleteStudentinfo(selectedStudent);
                    sTableFour.getItems().remove(selectedStudent);
                } catch (SQLException ex) {
                    Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    private void initColumn() {
        idFour.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameFour.setCellValueFactory(new PropertyValueFactory<>("name"));
        rollfour.setCellValueFactory(new PropertyValueFactory<>("roll"));
        mobileFour.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressFour.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    private void loadData() throws SQLException {
        ObservableList<Student> list;

        list = sdao.getFourBEStudents();
        sTableFour.getItems().setAll(list);
    }

}
