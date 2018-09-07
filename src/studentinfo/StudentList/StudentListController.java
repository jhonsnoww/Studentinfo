package studentinfo.StudentList;

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
import studentinfo.mainController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class StudentListController implements Initializable {

    @FXML
    private TableColumn<Student, String> sTableID;
    @FXML
    private TableColumn<Student, String> sNameTable;
    @FXML
    private TableColumn<Student, String> sRollTable;
    @FXML
    private TableColumn<Student, String> sMobileTable;
    @FXML
    private TableColumn<Student, String> sAddressTable;
    @FXML
    private TableView<Student> studentTableView;

    private StudentinfoDao sdao;
    @FXML
    private MenuItem editStudent;
    @FXML
    private MenuItem deleteStudent;

    /**
     * Initializes the controller class.
     */
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

    private void initColumn() {
        sTableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        sNameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        sRollTable.setCellValueFactory(new PropertyValueFactory<>("roll"));
        sMobileTable.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        sAddressTable.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    private void loadData() throws SQLException {

        ObservableList<Student> list;

        list = sdao.getStudents(ChooseDateController.startDate,ChooseDateController.endDate);
        studentTableView.getItems().setAll(list);

    }

    @FXML
    private void editStudent(ActionEvent event) throws IOException, SQLException {

        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo/EditStudent/editStudent.fxml"));
            Parent root = loader.load();
            EditStudentController edcontroller = loader.getController();
            edcontroller.setStudentInfo(selectedStudent);

            Stage stage = new Stage();
            stage.initOwner(studentTableView.getScene().getWindow());
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
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
       
         if (selectedStudent != null) {
             
             Optional <ButtonType> selectedOption = Messages.shwoConfimMessage("Are You Sure Want To Delete This ? ");
             
             if(selectedOption.get() == ButtonType.OK){
                 
                 try {
                     sdao.deleteStudentinfo(selectedStudent);
                     studentTableView.getItems().remove(selectedStudent);
                 } catch (SQLException ex) {
                     Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         
         }

    }

}
