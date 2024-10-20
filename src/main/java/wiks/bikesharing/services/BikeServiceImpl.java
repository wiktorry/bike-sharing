package wiks.bikesharing.services;

import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Bike;
import wiks.bikesharing.exceptions.BadRequestException;
import wiks.bikesharing.exceptions.NotFoundException;
import wiks.bikesharing.repositories.BikeRepository;

import java.util.List;

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

    @Override
    public Bike getBikeById(int id) {
        return bikeRepository.findById(id).orElseThrow(() -> new NotFoundException("Bike doesn't exist in database"));
    }

    @Override
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    @Override
    public Bike updateBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public void deleteBikeById(int id) {
        bikeRepository.deleteById(id);
    }


}
