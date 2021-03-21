package UpKeep.Controller;

import UpKeep.DAO.Parking;
import UpKeep.DAO.User;
import UpKeep.Repository.ParkingRepository;
import UpKeep.Repository.UserRepository;
import UpKeep.Service.ParkingService;
import UpKeep.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public
class ParkingController {

    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ParkingService parkingService;
    @Autowired
    UserService userparkService;
    @Autowired
    UserRepository userparkRepository;

    @PostMapping("/parking/occupy")
    public Status registerSpace(@Valid @RequestBody Parking park) {
//        if(parkingService.registerParkSpace(parking)) return Status.UPDATE_SUCCESS;
//        else return Status.FAILURE;

        User user1 = userparkRepository.findById(park.getParkid()).orElse(null);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        if(user1 == null) return Status.FAILURE;
        Parking parking = new Parking();
//        parking.setParkuser(userList);
        parkingRepository.save(parking);
        return Status.UPDATE_SUCCESS;
    }

    @PostMapping("/parking/unoccupy")
    public Status unregisterSpace(@Valid @RequestBody Parking parking) {
        if(parkingService.unregisterParkSpace(parking)) return Status.UPDATE_SUCCESS;
        else return Status.FAILURE;
    }

}
