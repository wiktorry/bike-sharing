package wiks.bikesharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiks.bikesharing.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
