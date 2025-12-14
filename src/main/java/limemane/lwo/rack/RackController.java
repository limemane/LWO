package limemane.lwo.rack;

import limemane.lwo.location.LocationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RackController {
    private final RackRepository rackRepository;
    private final LocationRepository locationRepository;
    private static final String baseUrl = "/racks";

    RackController(RackRepository rackRepository, LocationRepository locationRepository) {
        this.rackRepository = rackRepository;
        this.locationRepository = locationRepository;
    }

    /***
     * GET
     ***/
    @GetMapping(baseUrl)
    List<Rack> getAll() {
        return rackRepository.findAll();
    }

    @GetMapping(baseUrl+"/{id}")
    Rack getOne(@PathVariable UUID id) {
        return rackRepository.findById(id).orElseThrow(() -> new RackNotFoundException(id));
    }

    /***
     * POST
     ***/
    @PostMapping(baseUrl)
    Rack createRack(@RequestBody Rack createdRack) {
        return rackRepository.save(createdRack);
    }

    /***
     * PUT
     ***/

    // TODO

    /***
     * DELETE
     ***/
    @DeleteMapping(baseUrl+"/{id}")
    void deleteRack(@PathVariable UUID id) {
        // TODO
    }

}
