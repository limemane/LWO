package limemane.lwo.rack;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RackRepository extends JpaRepository<Rack, UUID> {
    Rack findByName(String name);
}
