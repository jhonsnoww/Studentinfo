/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studentinfo.ChooseDate.ChooseDateController;
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
    private VBox homeView;

    private StudentinfoDao sdao;

    private TeacherinfoDao tdao;
    @FXML
    private Text totalTeacherId;
    @FXML
    private Text sixBeStudents;
    @FXML
    private Text fiveBeStudents;
    @FXML
    private Text fourBeStudents;
    @FXML
    private Text threeBeStudents;
    @FXML
    private Text twobeStudentsId;
    @FXML
    private Text onebeStudentsId;
    @FXML
    private Text tID;
    @FXML
    private Text sixBE;
    @FXML
    private Text fiveBE;
    @FXML
    private Text fourBE;
    @FXML
    private Text threeBE;
    @FXML
    private Text twoBE;
    @FXML
    private Text oneBE;
    @FXML
    public Text startYear;
    @FXML
    public Text endYear;
    @FXML
    private HBox chooseY;
    
      static LocalDate getCurrentYear (){
             Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH);
        int d = now.get(Calendar.DATE);
        return LocalDate.of(y, m+1, d);
        }
      
      static LocalDate getLastYear (){
             Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH);
        int d = now.get(Calendar.DATE);
        return LocalDate.of(y-1, m+1, d);
        }
      

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
        
        ChooseDateController.startDate = getLastYear();
        ChooseDateController.endDate = getCurrentYear();
        
        startYear.setText(""+ChooseDateController.startDate);
        endYear.setText(""+ChooseDateController.endDate);

        sdao = new StudentinfoDao();
        totalStudent();

        tdao = new TeacherinfoDao();
        totalTeacher();
                System.out.println(""+ChooseDateController.endDate);


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

        int i6 = 0;
        int i5 = 0;
        int i4 = 0;
        int i3 = 0;
        int i2 = 0;
        int i1 = 0;

        try {
            i6 = sdao.totalStudent("LIKE '6-BE%'", ChooseDateController.startDate, ChooseDateController.endDate);
            i5 = sdao.totalStudent("LIKE '5-BE%'", ChooseDateController.startDate, ChooseDateController.endDate);
            i4 = sdao.totalStudent("LIKE '4-BE%'", ChooseDateController.startDate, ChooseDateController.endDate);
            i3 = sdao.totalStudent("LIKE '3-BE%'", ChooseDateController.startDate, ChooseDateController.endDate);
            i2 = sdao.totalStudent("LIKE '2-BE%'", ChooseDateController.startDate, ChooseDateController.endDate);
            i1 = sdao.totalStudent("LIKE '1-BE%'", ChooseDateController.startDate, ChooseDateController.endDate);
        } catch (SQLException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        sixBeStudents.setText("" + i6);
        fiveBeStudents.setText("" + i5);
        fourBeStudents.setText("" + i4);
        threeBeStudents.setText("" + i3);
        twobeStudentsId.setText("" + i2);
        onebeStudentsId.setText("" + i1);

    }

    private void totalTeacher() {
        int i = 0;
        try {
            i = tdao.totalTeacher(ChooseDateController.startDate, ChooseDateController.endDate);

        } catch (SQLException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalTeacherId.setText("" + i);

    }

    @FXML
    private void searchInfo(ActionEvent event) throws IOException, SQLException {

        Parent root = FXMLLoader.load(getClass().getResource("/studentinfo/searchView/searchVeiw.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);

    }

    @FXML
    private void totalTeacherList(MouseEvent event) {

        tID.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        sixBE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/SixBeList/SixBeStudentList.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void FiveBeStudentList(MouseEvent event) {
        fiveBE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/FiveBe/FiveBe.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void FourBeStudentList(MouseEvent event) {
        fourBE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/fourBe/fourBe.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void threeBeStudentLIst(MouseEvent event) {
        threeBE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/ThreeBe/threeBe.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void twoBeStudentList(MouseEvent event) {
        twoBE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/TwoBe/TwoBe.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void oneBeStudentList(MouseEvent event) {
        oneBE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(mainController.this.getClass().getResource("/studentinfo/OneBe/OneBe.fxml"));
                        centerPane.getChildren().clear();
                        centerPane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void dateClicked(MouseEvent event) {
        chooseY.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/studentinfo/ChooseDate/chooseDate.fxml"));
                        Scene scence = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle("Choose!");
                        stage.getIcons().add(new Image("/studentinfo/images/calendar.png"));
                        stage.setScene(scence);

                        stage.initOwner(centerPane.getScene().getWindow());
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.showAndWait();

                        startYear.setText("" + ChooseDateController.startDate);
                        endYear.setText("" + ChooseDateController.endDate);
                        totalTeacher();
                        totalStudent();

                    } catch (IOException ex) {
                        Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            }
        });
    }

}
