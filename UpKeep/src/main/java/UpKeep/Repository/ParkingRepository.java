package UpKeep.Repository;

import UpKeep.DAO.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface ParkingRepository extends JpaRepository<Parking, Long> {
}
