package wiks.bikesharing.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Rental;
import wiks.bikesharing.repositories.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental startRental(int bikeId) {
        SecurityContextHolder.getContext().getAuthentication();
    }
}
