package UpKeep.DAO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "fullname")
public class FullName {

    @Id
    private String username;
    private String fname;
    private String mname;
    private String lname;

    @OneToOne
    private User namefield;

    public
    FullName(User user) {
        this.username = user.getUsername();
    }

    public
    String getUsername() {
        return username;
    }

    public
    void setUsername(String username) {
        this.username = username;
    }

    public
    String getFname() {
        return fname;
    }

    public
    void setFname(String fname) {
        this.fname = fname;
    }

    public
    String getMname() {
        return mname;
    }

    public
    void setMname(String mname) {
        this.mname = mname;
    }

    public
    String getLname() {
        return lname;
    }

    public
    void setLname(String lname) {
        this.lname = lname;
    }

    public
    User getNamefield() {
        return namefield;
    }

    public
    void setNamefield(User namefield) {
        this.namefield = namefield;
    }

    @Override
    public
    boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return username.equals(fullName.username);
    }

    @Override
    public
    int hashCode() {
        return Objects.hash(username);
    }

    public FullName(){

    }
}