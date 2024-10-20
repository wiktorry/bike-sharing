package wiks.bikesharing.services;

import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Bike;
import wiks.bikesharing.exceptions.BadRequestException;
import wiks.bikesharing.repositories.BikeRepository;

@Service
public class BikeServiceImpl implements BikeService {
    private final BikeRepository bikeRepository;

    public BikeServiceImpl(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public Bike addBike(Bike bike) {
        if (bikeRepository.existsBySerialNumber(bike.getSerialNumber())) {
            throw new BadRequestException("Bike already exist in database");
        }
        return bikeRepository.save(bike);
    }
}
