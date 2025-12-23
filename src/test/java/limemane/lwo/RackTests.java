package limemane.lwo;

import limemane.lwo.location.Location;
import limemane.lwo.location.LocationRepository;
import limemane.lwo.rack.Rack;
import limemane.lwo.rack.RackController;
import limemane.lwo.rack.RackRepository;
import limemane.lwo.warehouse.Warehouse;
import limemane.lwo.warehouse.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.databind.SerializationFeature;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class RackTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    RackRepository rackRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @BeforeEach
    void contextLoads() {
        // Creating warehouse
        Warehouse warehouse = warehouseRepository.save(new Warehouse("Warehouse-1",""));
        // Creating rack A & rack B
        rackRepository.save(new Rack("A",1,warehouse));
        rackRepository.save(new Rack("B",2, warehouse));
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
        Rack rackC = rackRepository.save(new Rack("C",3,warehouseRepository.findAll().getFirst()));

        mockMvc.perform((get("/racks/"+rackC.getId().toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("C"));
    }

    @Test
    @Transactional
    void testCreate() throws Exception {
        // New rack instantiation
        Rack rackC = new Rack("C",3,warehouseRepository.findAll().getFirst());

        // Rack serialization
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String newRackJson = ow.writeValueAsString(rackC);

        long countBefore = rackRepository.count();

        mockMvc.perform(post("/racks")
                        .content((newRackJson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("C"));
        // TODO : not passing because generated JSON seems fucked up

        long countAfter = rackRepository.count();
        assertEquals(countBefore + 1, countAfter);
    }
}
