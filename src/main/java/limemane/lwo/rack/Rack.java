package limemane.lwo.rack;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Rack {
    private @Id @GeneratedValue UUID id;
    private String name;
    private Integer queuePosition;

    public Rack() {}

    public Rack(String name, Integer queuePosition) {
        this.name = name;
        this.queuePosition = queuePosition;
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
                '}';
    }
}
