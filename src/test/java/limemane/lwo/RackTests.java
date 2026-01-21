package limemane.lwo;

import limemane.lwo.rack.Rack;
import limemane.lwo.rack.RackRepository;
import limemane.lwo.warehouse.Warehouse;
import limemane.lwo.warehouse.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((newRackJson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("C"));

        long countAfter = rackRepository.count();
        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    @Transactional
    void testUpdate() throws Exception {
        // Get rack B and change entity name to C
        Rack rackBtoC = rackRepository.findByName("B");
        rackBtoC.setName("C");

        // Rack serialization
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String updateRackJson = ow.writeValueAsString(rackBtoC);

        UUID idBefore = rackBtoC.getId();
        long countBefore = rackRepository.count();

        mockMvc.perform(put("/racks/" + rackBtoC.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((updateRackJson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("C"));

        long countAfter = rackRepository.count();
        assertEquals(countBefore, countAfter);
        // also checking renamed rack still has de same UUID
        assertEquals(rackRepository.findByName("C").getId(),idBefore);
    }

    @Test
    @Transactional
    void testDelete() throws Exception {
        // Find rack A to delete
        Rack rackA = rackRepository.findByName("A");

        long countBefore = rackRepository.count();

        mockMvc.perform(delete("/racks/" + rackA.getId()))
                .andExpect(status().isOk());

        long countAfter = rackRepository.count();
        assertEquals(countBefore - 1, countAfter);

        // Remaining rack should be rack B
        assertNotNull(rackRepository.findByName("B"));
    }
}
