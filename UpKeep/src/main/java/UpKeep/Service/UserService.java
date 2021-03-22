package UpKeep.Service;

import UpKeep.DAO.FullName;
import UpKeep.DAO.Laptop;
import UpKeep.DAO.User;
import UpKeep.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    LaptopService laptopService;
    @Autowired
    FullNameService fullNameService;

    public User searchUser(User userNew) {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.equals(userNew)) {
                return user;
            }
        }
        return null;
    }

    public boolean registerUser(User newUser){
        userRepo.save(newUser);
        Laptop newLaptopObj = new Laptop();
        newLaptopObj.setUsrlaptop(newUser);
        laptopService.addNewLaptop(newLaptopObj);
        FullName fullName= new FullName();
        fullName.setUsername(newUser.getUsername());
        fullName.setNamefield(newUser);
        fullNameService.addFieldFullName(fullName); // one to one relation not required can prove relation ship with out this
        return true;
    }

    public User searchUserByUsername(User userNew) {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.getUsername().equals(userNew.getUsername())) {
                return user;
            }
        }
        return null;
    }
}



