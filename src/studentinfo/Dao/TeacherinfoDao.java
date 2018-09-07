/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import studentinfo.Model.Teacherinfo;

/**
 *
 * @author hp
 */
public class TeacherinfoDao {
    

    public void saveTeacher(Teacherinfo tinfo) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "insert into studentinfo.teacherinfo (T_id,name,roll,mobile,address,date)  value (?,?,?,?,?,'"+ChooseDateController.endDate+"')";
      
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, tinfo.getId());
        stmt.setString(2, tinfo.getName());
        stmt.setString(3, tinfo.getRoll());
        stmt.setString(4, tinfo.getMobile());
        stmt.setString(5, tinfo.getAddress());
       

        stmt.execute();

    }

    public Integer totalTeacher(LocalDate sd,LocalDate ld) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "select Count(*) From studentinfo.teacherinfo where date between '"+sd+"' And '"+ld+"'";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        int total = 0;

        while (rs.next()) {
            total = rs.getInt("Count(*)");
        }

        return total;
    }

    public ObservableList<Teacherinfo> getTeachers(LocalDate sd,LocalDate ld) throws SQLException {

        Connection con = Database.getInstance().getConnection();
        String sql = "Select * from studentinfo.teacherinfo where date between '"+sd+"' and '"+ld+"'";
        System.out.println(sql);
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Teacherinfo> list = FXCollections.observableArrayList();

        while (rs.next()) {

            String id = rs.getString("T_id");
            String name = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String address = rs.getString("address");

            Teacherinfo tinfo = new Teacherinfo(id, name, roll, mobile, address);
            list.add(tinfo);

        }
        return list;

    }

    public void updateTeacherinfo(Teacherinfo tinfo) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "update studentinfo.teacherinfo set name=?, roll=?,mobile=?,address=?,date=now() where T_id=? ";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, tinfo.getName());
        stmt.setString(2, tinfo.getRoll());
        stmt.setString(3, tinfo.getMobile());
        stmt.setString(4, tinfo.getAddress());
        stmt.setString(5, tinfo.getId());

        stmt.execute();
    }

    public void deleteTeacherinfo(Teacherinfo selectedTeacher) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "delete from studentinfo.teacherinfo where T_id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, selectedTeacher.getId());
        stmt.execute();

    }

    public Teacherinfo searchTearchInfo(String name) throws SQLException {

        Connection con = Database.getInstance().getConnection();

        String sql = "select * from studentinfo.teacherinfo where name Like"+"'%"+name+"%'";
        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        Teacherinfo tinfo = null;

        if (rs.next()) {
            String id = rs.getString("T_id");
            String rname = rs.getString("name");
            String roll = rs.getString("roll");
            String mobile = rs.getString("mobile");
            String adress = rs.getString("address");
            Date date = rs.getDate("date");

            tinfo = new Teacherinfo(id, rname, roll, mobile, adress,date);

        }

        return tinfo;
    }

}
