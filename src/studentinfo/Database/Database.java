/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import studentinfo.DatabaseConfig.DbConfigManeger;
import studentinfo.Model.DatabaseConfigProperty;

/**
 *
 * @author hp
 */
public class Database {

    private static Database db;
    private Connection con;

    private Database() throws SQLException {
        connect();
        createDatabase();
        createTable();
    }

    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Not Found >>> ");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DbConfigManeger dbManeger = new DbConfigManeger();
        DatabaseConfigProperty dbprop = dbManeger.getDatabaseProperties();

            con = DriverManager.getConnection("jdbc:mysql://"+dbprop.getHost()+":"+dbprop.getPort()+"/",dbprop.getUser(),dbprop.getPassword());
        System.out.println("Connect DataBase to Succefully");

    }

    private void createDatabase() {

        try {

            Statement stmt = con.createStatement();
            stmt.execute("create database if not exists studentinfo ");
            System.out.println("Create DataBasee Success .... ");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createTable() throws SQLException {

        Statement stmt1 = con.createStatement();
        Statement stmt2 = con.createStatement();

        stmt1.execute("create table if not exists studentinfo.studentinfo (id varchar(44) primary key,name varchar(224),roll varchar(224),mobile varchar(224),address varchar(224))");
        stmt2.execute("create table if not exists studentinfo.teacherinfo (id varchar(44) primary key,name varchar(224),roll varchar(224),mobile varchar(224),address varchar(224))");

        System.out.println("Create table Success");

    }

    public Connection getConnection() {
        return con;
    }

    public static Database getInstance() throws SQLException {
        if (db == null) {
           
                db = new Database();
            
        }
        return db;
    }

}
