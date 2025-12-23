package limemane.lwo.location;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class LocationController {
    private final LocationRepository locationRepository;
    private final static String baseUrl = "/locations";

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping(baseUrl)
    List<Location> getAll() {
        return locationRepository.findAll();
    }

    @GetMapping(baseUrl+"/{id}")
    Location getOne(@PathVariable UUID id) {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
    }

    @PostMapping(baseUrl)
    Location createLocation(@RequestBody Location createdLocation) {
        return locationRepository.save(createdLocation);
    }

    @PutMapping(baseUrl+"/{id}")
    Location updateLocation(@RequestBody Location updatedLocation, @PathVariable UUID id) {
        return locationRepository.findById(id)
                .map(location -> {
                    location.setXPosition(updatedLocation.getXPosition());
                    location.setYPosition(updatedLocation.getYPosition());
                    location.setComment(updatedLocation.getComment());
                    return locationRepository.save(location);
                })
                .orElseThrow(() -> new LocationNotFoundException(id));
    }

    @DeleteMapping(baseUrl+"/{id}")
    void deleteLocation(@PathVariable UUID id) {
        locationRepository.deleteById(id);
    }

}
