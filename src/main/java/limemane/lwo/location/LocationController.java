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

    /***
     * GET
     ***/
    @GetMapping(baseUrl)
    List<Location> getAll() {
        return locationRepository.findAll();
    }

    @GetMapping(baseUrl+"/{id}")
    Location getOne(@PathVariable UUID id) {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
    }

    /***
     * POST
     ***/
    @PostMapping(baseUrl)
    Location createLocation(@RequestBody Location createdLocation) {
        return locationRepository.save(createdLocation);
    }

    /***
     * PUT
     ***/
    @PutMapping(baseUrl+"/{id}")
    Location updateLocation(@RequestBody Location updatedLocation, @PathVariable UUID id) {
        return locationRepository.findById(id)
                .map(location -> {
                    location.setHeight(updatedLocation.getHeight());
                    location.setColumn(updatedLocation.getColumn());
                    location.setSideNote(updatedLocation.getSideNote());
                    return locationRepository.save(location);
                })
                .orElseThrow(() -> new LocationNotFoundException(id));
    }

    /***
     * DELETE
     ***/
    @DeleteMapping(baseUrl+"/{id}")
    void deleteLocation(@PathVariable UUID id) {
        locationRepository.deleteById(id);
    }

}
