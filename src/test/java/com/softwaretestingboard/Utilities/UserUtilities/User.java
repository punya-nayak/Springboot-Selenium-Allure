package com.softwaretestingboard.Utilities.UserUtilities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/*Class marked with @Entity are classes having data that can be persisted in the database*/
@Entity

/*@Table annotation allows you to specify the details that will be used to persist the entity in the database.*/
@Table
public class User implements Serializable {

    @Id
    private String username;
    private String password;
    private String usertype;
    private String status;


    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
