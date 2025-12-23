package limemane.lwo.rack;

import jakarta.persistence.*;
import limemane.lwo.warehouse.Warehouse;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Rack {
    private @Id @GeneratedValue UUID id;
    private String name;
    private Integer queuePosition;
    private @ManyToOne @JoinColumn(name = "warehouse_id") Warehouse warehouse;

    public Rack() {}

    public Rack(String name, Integer queuePosition, Warehouse warehouse) {
        this.name = name;
        this.queuePosition = queuePosition;
        this.warehouse = warehouse;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(Integer queuePosition) {
        this.queuePosition = queuePosition;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rack rack = (Rack) o;
        return Objects.equals(id, rack.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Rack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", queuePosition=" + queuePosition +
                ", warehouse=" + warehouse +
                '}';
    }

}
