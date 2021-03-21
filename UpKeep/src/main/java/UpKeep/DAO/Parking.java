package UpKeep.DAO;

import com.zaxxer.hikari.util.ConcurrentBag;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "parking")
public class Parking {

    private @Id @GeneratedValue long parkid;
    private String vehicletype;
    private boolean status;

//    @ManyToMany
//    private List<User> parkuser = new ArrayList<>();

    public Parking(){

    }

    public
    long getParkid() {
        return parkid;
    }

    public
    void setParkid(long parkid) {
        this.parkid = parkid;
    }

    public
    String getVehicletype() {
        return vehicletype;
    }

    public
    void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public
    boolean isStatus() {
        return status;
    }

    public
    void setStatus(boolean status) {
        this.status = status;
    }

//    public
//    List<User> getParkuser() {
//        return parkuser;
//    }
//
//    public
//    void setParkuser(List<User> parkuser) {
//        this.parkuser = parkuser;
//    }
}
