package wiks.bikesharing.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiks.bikesharing.entity.Rental;
import wiks.bikesharing.services.RentalService;

@RestController
@RequestMapping("/sharing/bike")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/{bikeId}/rent")
    public Rental startRental(@PathVariable int bikeId) {
        return rentalService.startRental(bikeId);
    }

    @PostMapping("/{bikeId}/endRent")
    public Rental endRental(@PathVariable int bikeId) {
        return rentalService.endRental(bikeId);
    }
}
