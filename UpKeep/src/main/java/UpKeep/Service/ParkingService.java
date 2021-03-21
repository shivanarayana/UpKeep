package UpKeep.Service;

import UpKeep.DAO.Parking;
import UpKeep.DAO.User;
import UpKeep.Repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public
class ParkingService {

    @Autowired
    ParkingRepository parkingRepo;

    public boolean registerParkSpace(Parking parking){
        Parking park = parkingRepo.findById(parking.getParkid()).orElse(null);
        if(park.isStatus()) return false;
        else{
        if(park == null){
            park.setParkid(park.getParkid());
        }
        park.setParkid(parking.getParkid());
        park.setVehicletype(parking.getVehicletype());
//        park.setParkuser(parking.getParkuser());
            park.setStatus(true);
            parkingRepo.save(park);
            return true;
        }
    }

    public boolean unregisterParkSpace(Parking parking){
        Parking park = parkingRepo.findById(parking.getParkid()).orElse(null);
        if(park.isStatus()) return false;
        else{
            park.setStatus(false);
            parkingRepo.save(park);
            return true;
        }
    }
}
