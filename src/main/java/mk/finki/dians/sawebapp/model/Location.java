package mk.finki.dians.sawebapp.model;

public class Location {
    public String type;
    public Double[] coordinates;
    
    public Location(String type, Double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
