package studentinfo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import studentinfo.Database.Database;
import studentinfo.Model.Student;

public class StudentinfoDao {

    public void saveStudent(Student student) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "insert into studentinfo.studentinfo (id,name,roll,mobile,address)  value (?,?,?,?,?)";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, student.getId());
        stmt.setString(2, student.getName());
        stmt.setString(3, student.getRoll());
        stmt.setString(4, student.getMobile());
        stmt.setString(5, student.getAddress());

        stmt.execute();

    }

    public Integer totalStudent(String str) throws SQLException {

        Connection con = Database.getInstance().getConnection();
       
        String sql = "select Count(*) From studentinfo.studentinfo where roll "+str;

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        int total = 0;

        while (rs.next()) {
            total = rs.getInt("Count(*)");
        }

        return total;
    }

    public ObservableList<Student> getStudents() throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Student> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Student student = new Student(id, name, roll, mobile, address);
            list.add(student);

        }

        return list;
    }

    public void updateStudent(Student student) throws SQLException {
        Connection con = Database.getInstance().getConnection();

        String sql = "update studentinfo.studentinfo set name=?, roll=?, mobile=?, address=? where id=?";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, student.getName());
        stmt.setString(2, student.getRoll());
        stmt.setString(3, student.getMobile());
        stmt.setString(4, student.getAddress());
        stmt.setString(5, student.getId());

        stmt.execute();

    }

    public void deleteStudentinfo(Student student) throws SQLException {
        Connection con = Database.getInstance().getConnection();

        String sql = "delete from studentinfo.studentinfo where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, student.getId());
        stmt.execute();
    }

    public Student searchStudentInfo(String studentId) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, studentId);
        ResultSet rs = stmt.executeQuery();

        Student student = null;

        if (rs.next()) {

            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            student = new Student(studentId, name, roll, mobile, address);
        }

        return student;
    }

    public ObservableList<Student> getSixBEStudents() throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '6-BE%'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Student> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Student student = new Student(id, name, roll, mobile, address);
            list.add(student);

        }

        return list;
        
    }
     public ObservableList<Student> getFiveBEStudents() throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '5-BE%'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Student> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Student student = new Student(id, name, roll, mobile, address);
            list.add(student);

        }

        return list;
        
    }
      public ObservableList<Student> getFourBEStudents() throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '4-BE%'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Student> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Student student = new Student(id, name, roll, mobile, address);
            list.add(student);

        }

        return list;
        
    }
      
       public ObservableList<Student> getthreeBEStudents() throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '3-BE%'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Student> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Student student = new Student(id, name, roll, mobile, address);
            list.add(student);

        }

        return list;
        
    }
       
        public ObservableList<Student> getTwoBEStudents() throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '2-BE%'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Student> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Student student = new Student(id, name, roll, mobile, address);
            list.add(student);

        }

        return list;
        
    }
         public ObservableList<Student> getOneBEStudents() throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '1-BE%'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Student> list = FXCollections.observableArrayList();

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Student student = new Student(id, name, roll, mobile, address);
            list.add(student);

        }

        return list;
        
    }

}
