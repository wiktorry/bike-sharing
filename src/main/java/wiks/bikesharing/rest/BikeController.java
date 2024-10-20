package wiks.bikesharing.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiks.bikesharing.entity.Bike;
import wiks.bikesharing.repositories.BikeRepository;

@RestController
@RequestMapping("/sharing/bike")
public class BikeController {
    private BikeRepository bikeRepository;

    public BikeController(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @PostMapping
    public Bike addBike(@RequestBody Bike bike) {
        return bikeRepository.save(bike);
    }
}
