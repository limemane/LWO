package limemane.lwo.location;

import jakarta.persistence.*;
import limemane.lwo.rack.Rack;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Location {

    private @Id @GeneratedValue UUID id;
    private @ManyToOne @JoinColumn(name = "rack_id") Rack rack;
    private int xPosition;
    private int yPosition;
    private String comment;

    public Location() {}

    public Location(Rack rack, int xPosition, int yPosition, String comment) {
        this.rack = rack;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", rack=" + rack +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", comment='" + comment + '\'' +
                '}';
    }
}
