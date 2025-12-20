package limemane.lwo;

import limemane.lwo.location.Location;
import limemane.lwo.location.LocationRepository;
import limemane.lwo.rack.Rack;
import limemane.lwo.rack.RackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class LwoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	LocationRepository locationRepository;
	@Autowired
	RackRepository rackRepository;

	@BeforeEach
	void contextLoads() {
		// Creating Rack "A" with two locations, the one at heigh 2 has a note.
		Rack currentRack = rackRepository.save(new Rack("A",1));
		locationRepository.save(new Location(currentRack, 1, 1, ""));
		locationRepository.save(new Location(currentRack, 2, 1, "Keep this location for cardboard boxes only"));

		// Creating Rack "B" with two locations
		currentRack = rackRepository.save(new Rack("B",2));
		locationRepository.save(new Location(currentRack, 1, 1, ""));
		locationRepository.save(new Location(currentRack, 2, 1, ""));
	}

	@Test
	@Transactional
	void testGet() {

	}
}
