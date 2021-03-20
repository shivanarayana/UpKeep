package UpKeep.Service;

import UpKeep.DAO.FullName;
import UpKeep.DAO.User;
import UpKeep.Repository.FullNameRepository;
import UpKeep.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FullNameService {

    @Autowired
    FullNameRepository fullNameRepository;

    @Autowired
    UserService userServiceName;

    public boolean addFullName(FullName fullName) {
        FullName duplicatefullName = new FullName();
                duplicatefullName = searchFullName(fullName);
        User user = userServiceName.searchUser(fullName.getNamefield());
        if (duplicatefullName != null){
            duplicatefullName.setLname(fullName.getLname());
            duplicatefullName.setMname(fullName.getMname());
            duplicatefullName.setFname(fullName.getFname());
            duplicatefullName.setNamefield(user);
            fullNameRepository.save(duplicatefullName);
            return true;
        }
        return false;
    }

    //not required can be deleted and can still reproduce one to one relationship
    public void addFieldFullName(FullName fullName) {
      //  FullName fullName = new FullName(user);
        fullNameRepository.save(fullName);
    }

    public FullName searchFullName(FullName fullName) {
        List<FullName> fullNameList = fullNameRepository.findAll();
        for (FullName foundfullname : fullNameList) {
            if (foundfullname.getUsername().equals(fullName.getNamefield().getUsername())) {
                    return foundfullname;
            }
        }
        return null;
    }
}
