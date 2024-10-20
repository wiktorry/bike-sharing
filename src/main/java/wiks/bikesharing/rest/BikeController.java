package wiks.bikesharing.rest;

import org.springframework.web.bind.annotation.*;
import wiks.bikesharing.entity.Bike;
import wiks.bikesharing.services.BikeService;

import java.util.List;

@RestController
@RequestMapping("/sharing/bike")
public class BikeController {
    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public Bike getBike(@PathVariable int id) {
        return bikeService.getBikeById(id);
    }

    @PostMapping
    public Bike addBike(@RequestBody Bike bike) {
        return bikeService.addBike(bike);
    }

    @PutMapping
    public Bike updateBike(@RequestBody Bike bike) {
        return bikeService.updateBike(bike);
    }

    @DeleteMapping("/{id}")
    public void deleteBike(@PathVariable int id) {
        bikeService.deleteBikeById(id);
    }
}
