package limemane.lwo.warehouse;


import jakarta.persistence.*;
import limemane.lwo.rack.Rack;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Warehouse {
    private @Id @GeneratedValue UUID id;
    private String code;
    private String comment;

    public Warehouse() {}

    public Warehouse(String code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(id, warehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
