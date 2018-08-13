/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studentinfo.Dao.StudentinfoDao;
import studentinfo.Dao.TeacherinfoDao;
import studentinfo.Messages.Messages;

/**
 *
 * @author hp
 */
public class mainController implements Initializable {

    @FXML
    private StackPane centerPane;
    @FXML
    private HBox homeView;
    @FXML
    private Text totalTeacherId;
    @FXML
    private Text totalStudentId;

    private StudentinfoDao sdao;

    private TeacherinfoDao tdao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sdao = new StudentinfoDao();
        totalStudent();

        tdao = new TeacherinfoDao();
        totalTeacher();

    }

    @FXML
    private void homeBtn(ActionEvent event) throws IOException {

        centerPane.getChildren().clear();
        centerPane.getChildren().add(homeView);
        totalStudent();
        totalTeacher();
    }

    @FXML
    private void settingBtn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/studentinfo/DatabaseConfig/dbConfing.fxml"));
        Scene scence = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Setting");
        stage.getIcons().add(new Image("/studentinfo/images/setting.png"));
        stage.setScene(scence);

        stage.initOwner(centerPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        Messages.showAndWait("Please restart your app");

        Stage currentStage = (Stage) centerPane.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void addStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/studentinfo/addStudent/addstudent.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);

    }

    @FXML
    private void addTeacher(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/studentinfo/addTeacher/addTeacher.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
    }

    @FXML
    private void studentList(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/studentinfo/StudentList/studentList.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
    }

    @FXML
    private void teacherList(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/studentinfo/teacherLIst/teacherList.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);

    }

    private void totalStudent() {
        int i = 0;
        try {
            i = sdao.totalStudent();
        } catch (SQLException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalStudentId.setText("Total Students ... " + i);

    }

    private void totalTeacher() {
        int i = 0;
        try {
            i = tdao.totalTeacher();
        } catch (SQLException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalTeacherId.setText("Total Teachers ... " + i);

    }

    @FXML
    private void searchInfo(ActionEvent event) throws IOException, SQLException {

        Parent root = FXMLLoader.load(getClass().getResource("/studentinfo/searchView/searchVeiw.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);

    }

   

    @FXML
    private void totalTeacherList(MouseEvent event) {
        
         totalTeacherId.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/teacherLIst/teacherList.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                }
            }
        });
    }

    @FXML
    private void totalStudentList(MouseEvent event) {
          totalStudentId.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/StudentList/studentList.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

}
