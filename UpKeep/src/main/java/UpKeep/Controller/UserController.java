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
            userService.registerUser(newUser);
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
        User other = userService.searchUser(newUser);
        if (other != null) {
            for (Laptop lap : newUser.getLaptops()) {
                lap.setUsrlaptop(other);
                laptopRepoUser.save(lap);
                other.getLaptops().add(lap);
            }
            userRepository.save(other);
        }
        else {      // if(userService.searchUser(usermullaps) == null)
            User usermullaps = new User();
            usermullaps.setUsername(newUser.getUsername());
            usermullaps.setPassword(newUser.getPassword());

            userRepository.save(usermullaps);
// inorder for user one to many relation ship to work first user should be added to database otherwise one to many exception occurs
            for (Laptop lap : newUser.getLaptops()) {
                lap.setUsrlaptop(usermullaps);
                laptopRepoUser.save(lap);
                usermullaps.getLaptops().add(lap);
            }
            userRepository.save(usermullaps);
        }
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