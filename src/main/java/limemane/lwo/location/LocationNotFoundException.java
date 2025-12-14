package limemane.lwo.location;

import java.util.UUID;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(UUID id) {
        super("Could not find location " + id);
    }
}
