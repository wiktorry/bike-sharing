package wiks.bikesharing.services;

import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Bike;

import java.util.List;

@Service
public interface BikeService {
    Bike addBike(Bike bike);

    Bike getBikeById(int id);

    List<Bike> getAllBikes();

    Bike updateBike(Bike bike);

    void deleteBikeById(int id);
}
