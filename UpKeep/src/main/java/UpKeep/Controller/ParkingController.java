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
    ParkingRepository parkingRepository;

    @Autowired
    UserRepository userparkRepository;

    @PostMapping("/parking/occupy")
    public Status registerSpace(@Valid @RequestBody Parking park) {

        List<User> userList = park.getParkuser();
        for (User user : userList) {
            if (userparkRepository.findById(user.getId()) == null) {
                userparkRepository.save(user);
            }
            Parking mypark = parkingRepository.findById(park.getParkid()).orElse(null);
            mypark.setVehicletype(park.getVehicletype());
            if (mypark.isStatus()) {
                return Status.USER_ALREADY_EXISTS;
            }
            mypark.setStatus(true);
            mypark.getParkuser().add(user);
            parkingRepository.save(mypark);

            user.getMyparking().add(mypark);
            userparkRepository.save(user);
            return Status.UPDATE_SUCCESS;
        }
        return Status.FAILURE;
    }

    @PostMapping("/parking/unoccupy")
    public Status unregisterSpace(@Valid @RequestBody Parking parking) {
        Parking mypark = parkingRepository.findById(parking.getParkid()).orElse(null);
        if(mypark==null) return Status.FAILURE;
        mypark.setStatus(false);
        return Status.UPDATE_SUCCESS;
    }
}
