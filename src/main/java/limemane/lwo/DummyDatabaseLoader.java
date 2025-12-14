package limemane.lwo;

import limemane.lwo.location.Location;
import limemane.lwo.rack.Rack;
import limemane.lwo.location.LocationRepository;
import limemane.lwo.rack.RackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyDatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DummyDatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase (LocationRepository locationRepository, RackRepository rackRepository) {
        return args -> {
            Rack currentRack = rackRepository.save(new Rack("A",1));
            log.info("Preloading rack " + currentRack);
            log.info("Preloading location " + locationRepository.save(new Location(currentRack, 1, 1, "")));
            log.info("Preloading location " + locationRepository.save(new Location(currentRack, 2, 1, "Keep this location for cardboard boxes only")));

            currentRack = rackRepository.save(new Rack("B",2));
            log.info("Preloading rack " + currentRack);
            log.info("Preloading location " + locationRepository.save(new Location(currentRack, 1, 1, "")));
            log.info("Preloading location " + locationRepository.save(new Location(currentRack, 2, 1, "")));
        };
    }
}
