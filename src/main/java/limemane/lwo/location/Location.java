package limemane.lwo.location;

import jakarta.persistence.*;
import limemane.lwo.rack.Rack;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Location {

    private @Id @GeneratedValue UUID id;
    private @ManyToOne @JoinColumn(name = "rack_id") Rack rack;
    private int height;
    private int column;
    private String sideNote;

    public Location() {}

    public Location(Rack rack, int height, int column, String sideNote) {
        this.rack = rack;
        this.height = height;
        this.column = column;
        this.sideNote = sideNote;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getSideNote() {
        return sideNote;
    }

    public void setSideNote(String sideNote) {
        this.sideNote = sideNote;
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
                ", height=" + height +
                ", column=" + column +
                ", sideNote='" + sideNote + '\'' +
                '}';
    }
}
