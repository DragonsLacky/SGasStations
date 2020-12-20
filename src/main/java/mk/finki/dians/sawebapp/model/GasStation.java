package mk.finki.dians.sawebapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "gasStations")
public class GasStation {
    @Id
    public String _id;
    public String name;
    @Field("name_en")
    public String enName;
    @Field("fuel_biodiesel")
    public Boolean bioDiesel;
    @Field("fuel_lpg")
    public Boolean lpg;
    public Boolean diesel;
    @Field("fuel_octane100")
    public Boolean octane100;
    @Field("fuel_octane95")
    public Boolean octane95;
    @Field("fuel_octane98")
    public Boolean octane98;
    public String brand;
    public String lat;
    public String lon;
    @Field("location")
    public Location location;
    public Double distance;
    
    @PersistenceConstructor
    public GasStation(String _id, String name, String enName, Boolean bioDiesel, Boolean lpg, String lat, String lon) {
        this._id = _id;
        this.name = name;
        this.enName = enName;
        this.bioDiesel = bioDiesel;
        this.lpg = lpg;
        this.lat = lat;
        this.lon = lon;
    }
    
    @Override
    public String toString() {
        return "GasStation{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", enName='" + enName + '\'' +
                ", bioDiessel='" + bioDiesel + '\'' +
                ", lpg='" + lpg + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
    
    public String get_id() {
        return _id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEnName() {
        return enName;
    }
    
    public Boolean getBioDiesel() {
        return bioDiesel;
    }
    
    public Boolean getLpg() {
        return lpg;
    }
    
    public String getLat() {
        return lat;
    }
    
    public String getLon() {
        return lon;
    }
    
    /***
     * Calculates the distance between the current object and location passed as a parameter.
     * @param loc location given (usually the user location) by which to get the distance
     */
    public void distance(Location loc){
        double pi = 0.017453292519943295;
        double a = 0.5 - Math.cos((loc.coordinates[1]-location.coordinates[1]) *pi)/2 + Math.cos(location.coordinates[1] * pi) * Math.cos(loc.coordinates[1] * pi) * (1 - Math.cos((loc.coordinates[0]-location.coordinates[0]) * pi ))/2;
        this.distance = (double)Math.round((12742 * Math.asin(Math.sqrt(a)))*10000)/100;
    }
}
