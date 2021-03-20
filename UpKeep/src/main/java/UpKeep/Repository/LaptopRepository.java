package UpKeep.Repository;

import UpKeep.DAO.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository  extends JpaRepository<Laptop, Long> {
}
