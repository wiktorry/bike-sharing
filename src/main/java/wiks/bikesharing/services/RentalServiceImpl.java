package wiks.bikesharing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Bike;
import wiks.bikesharing.entity.BikeStatus;
import wiks.bikesharing.entity.Rental;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.exceptions.BadRequestException;
import wiks.bikesharing.repositories.RentalRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final BikeService bikeService;
    private final AuthService authService;
    private final UserService userService;

    @Override
    public Rental startRental(int bikeId) {
        User user = authService.getCurrentUser();
        Bike bike = bikeService.getBikeById(bikeId);
        if (bike.getStatus() == BikeStatus.FREE) {
            Rental rental = new Rental(
                    0,
                    LocalDateTime.now(),
                    null,
                    bike,
                    user
            );
            bike.setStatus(BikeStatus.RENTED);
            user.addRental(rental);
            bike.addRental(rental);
            userService.updateUser(user);
            bikeService.updateBike(bike);
            rentalRepository.save(rental);
            return rental;
        }
        throw new BadRequestException("Bike is not free");
    }
}
