package UpKeep.Controller;
import UpKeep.DAO.Laptop;
import UpKeep.DAO.User;
import UpKeep.Repository.LaptopRepository;
import UpKeep.Service.LaptopService;
import UpKeep.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    @Autowired
    LaptopRepository laptopRepository;

    @Autowired
    LaptopService lapServ;

    @Autowired
    UserService usrserv;

    @PostMapping("/laptop/register")
    public Status registerLaptop(@Valid @RequestBody Laptop newLaptop) {
        if(newLaptop.getUsrlaptop() == null){
            return Status.NO_USER_EXIST_TO_ASSIGN;
        }
        Laptop searchLaptop = lapServ.searchLaptop(newLaptop);
        if(searchLaptop.getLid()!=0 && newLaptop.isReassign()){
            return Status.LAPTOP_ALREADY_ASSIGNED_CANNOT_REGISTER;
        }
        if(lapServ.addNewLaptop(newLaptop)){
            return Status.LAPTOP_ADDED;
        }
        else return Status.FAILURE;
    }
 //re-register for same person & re-register different person
    @PostMapping("/laptop/reregister")
    public Status reregisterLaptop(@Valid @RequestBody Laptop oldLaptop) {
        if(oldLaptop.getUsrlaptop() == null){
            return Status.NO_USER_EXIST_TO_ASSIGN;
        }
        if(oldLaptop.isReassign()==false){
            return Status.LAPTOP_ALREADY_ASSIGNED_CANNOT_REGISTER;
        }
        Laptop searchLaptop = lapServ.searchLaptop(oldLaptop);
//        if(searchLaptop!=null){
//            oldLaptop.setPrevUsers(searchLaptop.getPrevUsers()+"-->"+searchLaptop.getLname());
//        }
        if(oldLaptop.getUsrlaptop().getUsername().equals(searchLaptop.getLname())){
            return Status.LAPTOP_ALREADY_ASSIGNED_TO_SAME_PERSON;
        }
        oldLaptop.setPrevUsers(searchLaptop.getPrevUsers());
        if(lapServ.addNewLaptop(oldLaptop)){
            return Status.LAPTOP_REASSIGN_SUCCESSFUL;
        }
        else return Status.FAILURE;
    }

    @PostMapping("/laptop/unregister")
    public Status logLaptopOut(@Valid @RequestBody Laptop laptop) {
        Laptop searchLaptop = lapServ.searchLaptop(laptop);
        if(searchLaptop.getLid() == 0){
            return Status.NO_SUCH_LAPTOP_TO_UNASSIGN;
        }
        laptopRepository.delete(searchLaptop);
        return Status.LAPTOP_UNASSIGNED_SUCCESSFULLY;
    }

    @DeleteMapping("/laptop/unregisterall")
    public Status deleteLaptops() {
        laptopRepository.deleteAll();
        return Status.ALL_LAPTOPS_UNASSIGNED_SUCCESSFULLY;
    }

    @GetMapping("/laptop/show")
    public String getAllLaptops(){
        List<Laptop> laptops = laptopRepository.findAll();
        return laptops.toString();
    }

    @GetMapping("/laptop/{lid}")
    public String getLaptopById(@PathVariable long lid){
        Optional<Laptop> laptopId = laptopRepository.findById(lid);//laptopId.setLid(lid);//laptopId = lapServ.searchLaptop(laptopId);
        return laptopId != null ? laptopId.toString() : Status.NO_SUCH_LAPTOP_EXISTS.toString();
    }
}
