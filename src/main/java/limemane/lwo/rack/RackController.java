package limemane.lwo.rack;

import limemane.lwo.location.LocationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RackController {
    private final RackRepository rackRepository;
    private static final String baseUrl = "/racks";

    RackController(RackRepository rackRepository) {
        this.rackRepository = rackRepository;
    }

    @GetMapping(baseUrl)
    List<Rack> getAll() {
        return rackRepository.findAll();
    }

    @GetMapping(baseUrl+"/{id}")
    Rack getOne(@PathVariable UUID id) {
        return rackRepository.findById(id).orElseThrow(() -> new RackNotFoundException(id));
    }

    @PostMapping(baseUrl)
    Rack createRack(@RequestBody Rack createdRack) {
        return rackRepository.save(createdRack);
    }

    @PutMapping(baseUrl+"/{id}")
    Rack updateRack(@PathVariable UUID id, @RequestBody Rack updatedRack) {
        return rackRepository.findById(id).map(rack -> {
            rack.setName(updatedRack.getName());
            rack.setQueuePosition(updatedRack.getQueuePosition());
            rack.setWarehouse(updatedRack.getWarehouse());
            return rackRepository.save(rack);
        }).orElseThrow(() -> new RackNotFoundException(id));
    }

    @DeleteMapping(baseUrl+"/{id}")
    void deleteRack(@PathVariable UUID id) {
        rackRepository.deleteById(id);
    }

}
