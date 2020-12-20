package mk.finki.dians.sawebapp.model;

import lombok.Data;

@Data
public class Location {
    public String type;
    public Double[] coordinates;
    
    public Location(String type, Double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
