package studentinfo.ThreeBe;

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
import studentinfo.ChooseDate.ChooseDateController;
import studentinfo.Dao.StudentinfoDao;
import studentinfo.EditStudent.EditStudentController;
import studentinfo.Messages.Messages;
import studentinfo.Model.Student;
import studentinfo.StudentList.StudentListController;

public class ThreeBeController implements Initializable {

    @FXML
    private TableView<Student> sTableThree;
    @FXML
    private MenuItem editStudentSix;
    @FXML
    private MenuItem deleteStudentSix;
    @FXML
    private TableColumn<Student, String> idThree;
    @FXML
    private TableColumn<Student, String> nameThree;
    @FXML
    private TableColumn<Student, String> rollThree;
    @FXML
    private TableColumn<Student, String> mobileThree;
    @FXML
    private TableColumn<Student, String> addressThree;
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

        Student selectedStudent = sTableThree.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo/EditStudent/editStudent.fxml"));
            Parent root = loader.load();
            EditStudentController edcontroller = loader.getController();
            edcontroller.setStudentInfo(selectedStudent);

            Stage stage = new Stage();
            stage.initOwner(sTableThree.getScene().getWindow());
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
        Student selectedStudent = sTableThree.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            Optional<ButtonType> selectedOption = Messages.shwoConfimMessage("Are You Sure Want To Delete This ? ");

            if (selectedOption.get() == ButtonType.OK) {

                try {
                    sdao.deleteStudentinfo(selectedStudent);
                    sTableThree.getItems().remove(selectedStudent);
                } catch (SQLException ex) {
                    Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    private void initColumn() {
        idThree.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameThree.setCellValueFactory(new PropertyValueFactory<>("name"));
        rollThree.setCellValueFactory(new PropertyValueFactory<>("roll"));
        mobileThree.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressThree.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadData() throws SQLException {
        ObservableList<Student> list;

        list = sdao.getthreeBEStudents(ChooseDateController.startDate,ChooseDateController.endDate);
        sTableThree.getItems().setAll(list);

    }

}
