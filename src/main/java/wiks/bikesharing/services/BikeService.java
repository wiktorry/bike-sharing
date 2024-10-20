package wiks.bikesharing.services;

import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Bike;

@Service
public interface BikeService {
    Bike addBike(Bike bike);
}
