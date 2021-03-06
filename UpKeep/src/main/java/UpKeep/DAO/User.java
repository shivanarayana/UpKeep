package UpKeep.DAO;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    private @Id @GeneratedValue long id;
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank boolean loggedIn;

    @OneToMany(mappedBy = "usrlaptop")
    private List<Laptop> laptops=new ArrayList<>();

    @OneToOne(mappedBy = "namefield")
    private FullName fullNameUser;

    @ManyToMany(mappedBy = "parkuser")
    private List<Parking> myparking;

    public User() {
    }
    public User(@NotBlank String username,
                @NotBlank String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }
    public long getId() {
        return id;
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
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public void setId(long id) {
        this.id = id;
    }

    public
    FullName getFullNameUser() {
        return fullNameUser;
    }

    public
    void setFullNameUser(FullName fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    public
    List<Laptop> getLaptops() {
        return laptops;
    }

    public
    void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public
    List<Parking> getMyparking() {
        return myparking;
    }

    public
    void setMyparking(List<Parking> myparking) {
        this.myparking = myparking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password,
                loggedIn);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}