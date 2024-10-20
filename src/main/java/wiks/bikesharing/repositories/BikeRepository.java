package wiks.bikesharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiks.bikesharing.entity.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {
    boolean existsBySerialNumber(String serialNumber);
}
