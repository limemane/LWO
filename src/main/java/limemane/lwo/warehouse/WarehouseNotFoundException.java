package limemane.lwo.warehouse;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException() {
        super("Could not find this warehouse");
    }
}
