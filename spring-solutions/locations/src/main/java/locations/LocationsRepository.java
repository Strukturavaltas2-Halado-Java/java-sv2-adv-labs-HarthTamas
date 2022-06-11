package locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationsRepository extends JpaRepository<Location,Long> {

    @Query("select l from Location l where (:prefix is null or l.name like :prefix%) and" +
            "(:minLat is null or l.lat >= :minLat) and"+
            "(:minLon is null or l.lon >= :minLon) and"+
         "(:maxLat is null or l.lat <= :maxLat) and"+
            "(:maxLon is null or l.lon  <= :maxLon)")
    public List<Location> findByOptionalParameters(@Param("prefix") Optional<String> prefix,
                                             @Param("minLat") Optional<Double> minLat,
                                             @Param("minLon") Optional<Double> minLon,
                                             @Param("maxLat") Optional<Double> maxLat,
                                             @Param("maxLon") Optional<Double> maxLon
                                             );
}
