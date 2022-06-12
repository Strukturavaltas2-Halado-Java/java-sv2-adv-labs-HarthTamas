package usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usedcars.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

    @Query("select distinct c.brand from Car c order by c.brand asc")
    public Set<String> getBrandsInAlphabeticalOrder();

    @Query("select c from Car c where (:brand is null or c.brand = :brand) and (:age is null or c.age <= :age)")
    public List<Car> findByOptionalParameters(Optional<String> brand, Optional<Integer> age);

    @Query("select c from Car c where (:brand is null or c.brand = :brand) and (:age is null or c.age <= :age) order by c.lastKilometerState asc")
    public List<Car> findByOptionalParametersAsc(Optional<String> brand, Optional<Integer> age);

    @Query("select c from Car c where (:brand is null or c.brand = :brand) and (:age is null or c.age <= :age) order by c.lastKilometerState desc")
    public List<Car> findByOptionalParametersDesc(Optional<String> brand, Optional<Integer> age);

    public List<Car> findCarsByCarSeller_Id(Long id);

    @Query("select distinct c.brand from Car c where c.carSeller.id = :id order by c.brand asc")
    public List<String> getBrandsInAlphabeticalOrderBySellerId(long id);

}
