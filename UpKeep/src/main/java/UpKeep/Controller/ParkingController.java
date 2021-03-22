package UpKeep.Controller;

import UpKeep.DAO.Parking;
import UpKeep.DAO.User;
import UpKeep.Repository.ParkingRepository;
import UpKeep.Repository.UserRepository;

import UpKeep.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public
class ParkingController {

    @Autowired
    UserService userServiceParking;

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    UserRepository userparkRepository;

    @PostMapping("/parking/occupy")
    public Status registerSpace(@Valid @RequestBody Parking park) {

        List<User> userList = park.getParkuser();

        for (User user : userList) {
            user = userServiceParking.searchUser(user);
            if ( user !=null)
            {
            Parking mypark = parkingRepository.findById(park.getParkid()).orElse(new Parking());
            mypark.setVehicletype(park.getVehicletype() != null ? park.getVehicletype() : "name not mentioned");
            if (mypark.isStatus()) {
                return Status.USER_ALREADY_EXISTS;
            }
            mypark.setStatus(true);
            mypark.getParkuser().add(user);
            parkingRepository.save(mypark);

            mypark.setParkuser(null);//garbage collection
            user.getMyparking().add(mypark);
            userparkRepository.save(user);
            return Status.UPDATE_SUCCESS;
            }
        }
        return Status.UNREGISTER_USER;
    }

    @PostMapping("/parking/unoccupy")
    public Status unregisterSpace(@Valid @RequestBody Parking parking) {
        Parking mypark = parkingRepository.findById(parking.getParkid()).orElse(null);
        if(mypark==null) return Status.FAILURE;
        mypark.setStatus(false);
        parkingRepository.save(mypark);
        return Status.UPDATE_SUCCESS;
    }
}
