package limemane.lwo;

import limemane.lwo.location.Location;
import limemane.lwo.location.LocationRepository;
import limemane.lwo.rack.Rack;
import limemane.lwo.rack.RackController;
import limemane.lwo.rack.RackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class RackTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    RackRepository rackRepository;

    @BeforeEach
    void contextLoads() {
        // Creating rack A & rack B
        rackRepository.save(new Rack("A",1));
        rackRepository.save(new Rack("B",2));
    }

    @Test
    @Transactional
    void testGetAll() throws Exception {
        mockMvc.perform(get("/racks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("A"))
                .andExpect(jsonPath("$.[1].name").value("B"));
    }

    @Test
    @Transactional
    void testGetId() throws Exception {
        // Creating a new rack to get its ID
        Rack rackC = rackRepository.save(new Rack("C",3));

        mockMvc.perform((get("/racks/"+rackC.getId().toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("C"));
    }
}
