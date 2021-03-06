package studentinfo.Model;

import java.util.Date;

public class Student {

    private String id;
    private String name;
    private String roll;
    private String mobile;
    private String address;
    private Date date;

    public Student(String id, String name, String roll, String mobile, String address, Date date) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.mobile = mobile;
        this.address = address;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    

    public Student(String id, String name, String roll, String mobile, String address) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.mobile = mobile;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
