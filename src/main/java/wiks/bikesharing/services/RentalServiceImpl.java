package wiks.bikesharing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Bike;
import wiks.bikesharing.entity.BikeStatus;
import wiks.bikesharing.entity.Rental;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.exceptions.BadRequestException;
import wiks.bikesharing.exceptions.NotFoundException;
import wiks.bikesharing.exceptions.UnauthorizedException;
import wiks.bikesharing.repositories.RentalRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final BikeService bikeService;
    private final AuthService authService;

    @Override
    public Rental startRental(int bikeId) {
        User user = authService.getCurrentUser();
        Bike bike = bikeService.getBikeById(bikeId);
        if (bike.getStatus() != BikeStatus.FREE) {
            throw new BadRequestException("Bike is not free");
        }
        Rental rental = new Rental(
                0,
                LocalDateTime.now(),
                null,
                bike.getId(),
                user
        );
        bike.setStatus(BikeStatus.RENTED);
        user.addRental(rental);
        rentalRepository.save(rental);
        return rental;
    }

    @Override
    public Rental endRental(int bikeId) {
        User user = authService.getCurrentUser();
        Bike bike = bikeService.getBikeById(bikeId);
        for (Rental rental : user.getRentals()) {
            if (rental.getBikeId() == bikeId && rental.getEndDate() == null) {
                bike.setStatus(BikeStatus.FREE);
                rental.setEndDate(LocalDateTime.now());
                return rentalRepository.save(rental);
            }
        }
        throw new UnauthorizedException("You don't have access to this rental");
    }

}
