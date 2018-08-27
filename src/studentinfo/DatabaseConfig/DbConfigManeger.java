package studentinfo.DatabaseConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import studentinfo.Model.DatabaseConfigProperty;


public class DbConfigManeger {
    
    public void saveDatabaseConfigProperty(DatabaseConfigProperty dbproperty){
        
        Properties prop = new Properties();
        
        try(OutputStream os = new FileOutputStream("dbConfig.properties")){
            
            prop.setProperty("host", dbproperty.getHost());
            prop.setProperty("port", dbproperty.getPort());
            prop.setProperty("user", dbproperty.getUser());
            prop.setProperty("password", dbproperty.getPassword());
            
            prop.store(os, "Properties for DataBaseConfig");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DbConfigManeger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DbConfigManeger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public DatabaseConfigProperty getDatabaseProperties (){
        
        Properties prop = new Properties();
        
         DatabaseConfigProperty dbproperty = null;
         
         
        try(InputStream is = new FileInputStream("dbConfig.properties")){
            
            prop.load(is);
            
             String host = prop.getProperty("host");
            String port = prop.getProperty("port");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            
            dbproperty = new DatabaseConfigProperty(host, port, user, password);     
            
        } catch (Exception ex) {
            ex.printStackTrace();
    }
        
        return dbproperty;
    } 
    
}
