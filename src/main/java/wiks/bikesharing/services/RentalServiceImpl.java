package wiks.bikesharing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Bike;
import wiks.bikesharing.entity.BikeStatus;
import wiks.bikesharing.entity.Rental;
import wiks.bikesharing.repositories.RentalRepository;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final BikeService bikeService;
    private final UserService userService;

    @Override
    public Rental startRental(int bikeId) {
        //User user = userService.getCurrentUser();
        Bike bike = bikeService.getBikeById(bikeId);
        if (bike.getStatus() == BikeStatus.FREE) {

        }
        return null;
    }
}
