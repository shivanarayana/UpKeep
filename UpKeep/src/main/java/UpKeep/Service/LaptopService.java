package UpKeep.Service;

import UpKeep.DAO.Laptop;
import UpKeep.DAO.User;
import UpKeep.Repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class LaptopService {

    @Autowired
    LaptopRepository laptopRepository;

    public boolean addNewLaptop(@Valid Laptop newRequest){
        newRequest.setLname(newRequest.getUsrlaptop().getUsername());
        laptopRepository.save(newRequest);
        return true;

        /*
        User newUser = newRequest.getUsrlaptop();
        Laptop newLaptopObj = new Laptop();
        newLaptopObj.setLid(newRequest.getLid());
        newLaptopObj.setCompname(newRequest.getCompname());
        newLaptopObj.setLname(newUser.getUsername());
        newLaptopObj.setUsrlaptop(newUser);
        if((searchLaptop(newLaptopObj)!=false) ||
                (searchLaptop(newLaptopObj)!=true && newRequest.isReassign()==true)){
            laptopRepository.save(newLaptopObj);
        }*/
    }

    public Laptop searchLaptop(Laptop newLaptop) {
        List<Laptop> laptops = laptopRepository.findAll();
        for (Laptop laptop : laptops) {
            if (laptop.equals(newLaptop)) {
                return laptop;
            }
        }
        return null;
    }

    public List<Laptop> searchLaptopsForUser(User user){
        List<Laptop> laptops = laptopRepository.findAll();
        List<Laptop> laptopFilter = new ArrayList<>();
        for (Laptop laptop : laptops) {
            if (laptop.getLname().equals(user.getUsername())) {
                laptopFilter.add(laptop);
            }
        }
        return laptopFilter;
    }
}