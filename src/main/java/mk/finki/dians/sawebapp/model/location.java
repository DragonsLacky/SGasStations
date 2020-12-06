package mk.finki.dians.sawebapp.model;

public class location {
    public String type;
    public Double[] coordinates;
    
    public location(String type, Double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
