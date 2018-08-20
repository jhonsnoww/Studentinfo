package studentinfo.teacherLIst;

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
import studentinfo.Dao.TeacherinfoDao;
import studentinfo.EditTeacher.EditTeacherController;
import studentinfo.Messages.Messages;
import studentinfo.Model.Teacherinfo;

public class TeacherListController implements Initializable {

    @FXML
    private MenuItem editTeacher;
    @FXML
    private MenuItem deleteTeacherInfo;
    @FXML
    private TableColumn<Teacherinfo, String> tId;
    @FXML
    private TableColumn<Teacherinfo, String> tName;
    @FXML
    private TableColumn<Teacherinfo, String> tRoll;
    @FXML
    private TableColumn<Teacherinfo, String> tMobile;
    @FXML
    private TableColumn<Teacherinfo, String> tAddress;
    @FXML
    private TableView<Teacherinfo> tTableView;

    private TeacherinfoDao tdao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tdao = new TeacherinfoDao();
        initColumn();
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void editTeacherbtn(ActionEvent event) throws IOException, SQLException {

        Teacherinfo selectedTeacher = tTableView.getSelectionModel().getSelectedItem();

        if (selectedTeacher != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo/EditTeacher/EditTeacher.fxml"));
            Parent root = loader.load();
            EditTeacherController edcontroller = loader.getController();
            edcontroller.setTeachersinfo(selectedTeacher);

            Stage stage = new Stage();
            stage.initOwner(tTableView.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Edit Teacher");
            stage.getIcons().add(new Image("/studentinfo/images/teacher.png"));
            stage.showAndWait();
            loadData();

        }

    }

    @FXML
    private void deleteTeacherInfo(ActionEvent event) throws SQLException {
        Teacherinfo selectedTeacher = tTableView.getSelectionModel().getSelectedItem();

        if (selectedTeacher != null) {
            Optional<ButtonType> selectedOption = Messages.shwoConfimMessage("Are You Sure Want To Delete This ? ");

            if (selectedOption.get() == ButtonType.OK) {
                tdao.deleteTeacherinfo(selectedTeacher);
                tTableView.getItems().remove(selectedTeacher);
            }
        }

    }

    private void initColumn() {
        tId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tRoll.setCellValueFactory(new PropertyValueFactory<>("roll"));
        tMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    private void loadData() throws SQLException {

        ObservableList<Teacherinfo> list;

        list = tdao.getTeachers();
        tTableView.getItems().setAll(list);

    }

}
