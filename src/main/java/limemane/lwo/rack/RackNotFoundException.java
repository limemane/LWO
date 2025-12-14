package limemane.lwo.rack;

import java.util.UUID;

public class RackNotFoundException extends RuntimeException {
    RackNotFoundException(UUID id) {
        super("Could not find rack " + id);
    }
}
