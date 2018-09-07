package studentinfo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import studentinfo.ChooseDate.ChooseDateController;
import studentinfo.Database.Database;
import studentinfo.Model.Student;

public class StudentinfoDao {

    public void saveStudent(Student student) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "insert into studentinfo.studentinfo (id,name,roll,mobile,address,date)  value (?,?,?,?,?,'"+ChooseDateController.endDate+"')";

        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, student.getId());
        stmt.setString(2, student.getName());
        stmt.setString(3, student.getRoll());
        stmt.setString(4, student.getMobile());
        stmt.setString(5, student.getAddress());

        stmt.execute();

    }

    public Integer totalStudent(String str,LocalDate fd,LocalDate ld) throws SQLException {

        Connection con = Database.getInstance().getConnection();
       
        String sql = "select Count(*) From studentinfo.studentinfo where roll "+str+"And date between '"+fd+"' And '"+ld+"'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        int total = 0;

        while (rs.next()) {
            total = rs.getInt("Count(*)");
        }

        return total;
    }

    public ObservableList<Student> getStudents(LocalDate fd,LocalDate ld) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where date between '"+fd+"' And '"+ld+"'";
        System.out.println(sql);

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

    public Student searchStudentInfo(String name) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where name Like"+"'%"+name+"%'";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        Student student = null;

        if (rs.next()) {

            String id = rs.getString("id");
            String rname = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");
            Date date = rs.getDate("date");

            student = new Student(id, rname, roll, mobile, address,date);
        }

        return student;
    }

    public ObservableList<Student> getSixBEStudents(LocalDate fd,LocalDate ld) throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '6-BE%' And date between '"+fd+"' And '"+ld+"'";
        System.out.println("6-BE : "+sql);
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
     public ObservableList<Student> getFiveBEStudents(LocalDate fd,LocalDate ld) throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '5-BE%' and date between '"+fd+"' And '"+ld+"'";

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
      public ObservableList<Student> getFourBEStudents(LocalDate fd,LocalDate ld) throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '4-BE%' and date between '"+fd+"' And '"+ld+"'";

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
      
       public ObservableList<Student> getthreeBEStudents(LocalDate fd,LocalDate ld) throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '3-BE%' and date between '"+fd+"' And '"+ld+"'";

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
       
        public ObservableList<Student> getTwoBEStudents(LocalDate fd,LocalDate ld) throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '2-BE%' and date between '"+fd+"' And '"+ld+"'";

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
         public ObservableList<Student> getOneBEStudents(LocalDate fd,LocalDate ld) throws SQLException {
        
         Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.studentinfo where roll LIKE '1-BE%' and date between '"+fd+"' And '"+ld+"'";

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
