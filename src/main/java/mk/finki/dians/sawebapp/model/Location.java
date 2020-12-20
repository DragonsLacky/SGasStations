package mk.finki.dians.sawebapp.model;

import lombok.Data;

/**
 * The location class used by GasStation class to represent a GeoLocation point.
 */
@Data
public class Location {
    public String type;
    public Double[] coordinates;
    
    public Location(String type, Double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
