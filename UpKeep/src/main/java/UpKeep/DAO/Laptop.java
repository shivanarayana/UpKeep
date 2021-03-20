package UpKeep.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue
    private long lid;
    private String lname;
    private String compname;
    @Transient
    private boolean reassign;
    private String prevUsers;

    @ManyToOne
    private User usrlaptop;

    public
    long getLid() {
        return lid;
    }

    public
    void setLid(long lid) {
        this.lid = lid;
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
    boolean isReassign() {
        return reassign;
    }

    public
    void setReassign(boolean reassign) {
        this.reassign = reassign;
    }

    public
    User getUsrlaptop() {
        return usrlaptop;
    }

    public
    void setUsrlaptop(User usrlaptop) {
        this.usrlaptop = usrlaptop;
    }

    public
    String getCompname() {
        return compname;
    }

    public
    void setCompname(String compname) {
        this.compname = compname;
    }

    public
    String getPrevUsers() {
        return prevUsers;
    }

    public
    void setPrevUsers(String prevUsers) {
        this.prevUsers = prevUsers;
    }

    @Override
    public
    boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return lid == laptop.lid;
    }

    @Override
    public
    int hashCode() {
        return Objects.hash(lid);
    }

    @Override
    public
    String toString() {
        return "Laptop{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                ", compname='" + compname + '\'' +
                ", reassign=" + reassign +
                ", prevUsers='" + prevUsers + '\'' +
                ", usrlaptop=" + usrlaptop +
                '}';
    }
}