package UpKeep.Controller;

import UpKeep.DAO.FullName;
import UpKeep.DAO.Laptop;
import UpKeep.DAO.User;
import UpKeep.Repository.LaptopRepository;
import UpKeep.Repository.UserRepository;
import UpKeep.Service.FullNameService;
import UpKeep.Service.LaptopService;
import UpKeep.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LaptopRepository laptopRepoUser;
    @Autowired
    UserService userService;

    @Autowired
    LaptopService laptopService;

    @Autowired
    FullNameService fullNameService;

    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
            if (userService.searchUser(newUser) != null) {
                return Status.USER_ALREADY_EXISTS;
            }

        userRepository.save(newUser);
        Laptop newLaptopObj = new Laptop();
        newLaptopObj.setUsrlaptop(newUser);
        laptopService.addNewLaptop(newLaptopObj);
        FullName fullName= new FullName();
        fullName.setUsername(newUser.getUsername());
        fullName.setNamefield(newUser);
        fullNameService.addFieldFullName(fullName); // one to one relation not required can prove relation ship with out this
        return Status.USER_REGISTERED_WITH_LAPTOP;
    }

    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user) {
        User other = userService.searchUser(user);
            if (other != null) {
                other.setLoggedIn(true);
                userRepository.save(other);
                return Status.USER_LOGGED_IN;
            }
        return Status.FAILED_TO_LOGIN;
    }

    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        User other = userService.searchUser(user);
        if (other != null) {
            other.setLoggedIn(false);
            userRepository.save(other);
            return Status.USER_LOGGED_OUT;
        }
        return Status.FAILED_TO_LOGOUT;
    }

    @PostMapping("/users/registermultiplelaptops")
    public Status registerUserLaptops(@Valid @RequestBody User newUser) {

        List<Laptop> morelaptops = new ArrayList<>();
        morelaptops = newUser.getLaptops();

        User usermullaps = new User();
       // getuser.setId(newUser.getId());
        usermullaps.setUsername(newUser.getUsername());
        usermullaps.setPassword(newUser.getPassword());
        usermullaps.setId(newUser.getId());

        if(userService.searchUser(usermullaps) == null) userRepository.save(usermullaps);
// inorder for user one to many relation ship to work first user should be added to database otherwise one to many exception occurs

        for (Laptop lap : newUser.getLaptops()){
            usermullaps.getLaptops().add(lap);
            lap.setUsrlaptop(usermullaps);
            laptopRepoUser.save(lap);
        }

        userRepository.save(usermullaps);

        return Status.UPDATE_SUCCESS;
    }

    @PostMapping("/users/laptop")
    public String userForLaptops(@Valid @RequestBody User user) {
        if (userService.searchUser(user) != null) {
            List<Laptop> laptopList = laptopService.searchLaptopsForUser(user);
            return laptopList.toString();
        }
        else return Status.NO_USER_EXIST.toString();
    }

    @PostMapping("/users/userdetails")
    public String getUserDetails(@Valid @RequestBody User user){
        return userService.searchUser(user) !=null ? userService.searchUser(user).toString() : Status.NO_USER_EXIST.toString();
    }

    @DeleteMapping("/users/deleteall")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.ALL_USERS_DELETED_SUCCESS;
    }
}