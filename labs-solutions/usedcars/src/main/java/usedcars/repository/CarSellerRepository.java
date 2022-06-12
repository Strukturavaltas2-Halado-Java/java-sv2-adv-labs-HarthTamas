package usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usedcars.model.CarSeller;


@Repository
public interface CarSellerRepository extends JpaRepository<CarSeller,Long> {


}
