package UpKeep.Service;

import UpKeep.DAO.User;
import UpKeep.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User searchUser(User userNew) {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.equals(userNew)) {
                return user;
            }
        }
        return null;
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



