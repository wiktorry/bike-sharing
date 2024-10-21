package wiks.bikesharing.services;

import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Rental;

@Service
public interface RentalService {
    Rental startRental(int bikeId);
}
