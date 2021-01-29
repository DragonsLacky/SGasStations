package mk.finki.dians.sawebapp.service.implementation;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import mk.finki.dians.sawebapp.repository.GasStationRepository;
import mk.finki.dians.sawebapp.service.GasStationsService;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.ISearchBuilderSimplified;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.SearchBuilder;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.SearchBuilderSimplified;
import org.bson.BSONObject;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class GasStationServiceImplementation implements GasStationsService {
    

    private final GasStationRepository gasStationRepository;
    private final ISearchBuilderSimplified builder;
    
    public GasStationServiceImplementation(GasStationRepository gasStationRepository) throws NoSuchFieldException {

        this.gasStationRepository = gasStationRepository;
        this.builder = new SearchBuilderSimplified(new SearchBuilder("listByTermDistanceTypeAndOrderByDistance", this.getClass()));
    }
    
    @Override
    public List<GasStation> listByBuilderConditions() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return builder.call(this);
    }
    
    @Override
    public List<GasStation> list() {
        return gasStationRepository.findAll();
    }
    
    /**
     * Sets the parameters of the searchBuilder and calls the searchBuilder function which accesses the repository object that has access to the database and returns all the available elements while filtering by the distance and current user location.
     * Sends a custom created query to the database to filter and acquire the required data ordered by the distance in ascending.
     * @param location custom class created consisting of longitude and latitude for the current location of the user.
     * @param distance distance in meters to filter by.
     * @return List returns the List of the objects within distance from the current location ordered by distance in ascending.
     */
    private List<GasStation> findAndOrderByDistance(Location location, Double distance) {
        List<GasStation> gasStations = gasStationRepository.findByLocationCoordinatesWithin(location.coordinates,distance*1000) ;
        gasStations.forEach(gasStation -> gasStation.distance(location));
        return gasStations.stream().sorted(Comparator.comparing(gasStation -> gasStation.distance)).collect(Collectors.toList());
    }
    
    @Override
    public List<GasStation> listByTermDistanceTypeAndOrderByDistance(String term, Location userLocation, Double Distance, Boolean bioDiesel, Boolean lpg, Boolean diesel, Boolean octane100, Boolean octane98, Boolean octane95, Boolean any) {
        List<GasStation> gasStations = findAndOrderByDistance(userLocation, Distance);
        return gasStations.stream()
                .filter(gasStation -> (gasStation.getName() != null && gasStation.getName().toLowerCase().contains(term)) ||
                (gasStation.getBrand() != null && gasStation.getBrand().toLowerCase().contains(term)) ||
                (gasStation.getEnName()!= null && gasStation.getEnName().toLowerCase().contains(term)))
                .filter(gasStation -> any || ( (gasStation.getBioDiesel() == bioDiesel || gasStation.getBioDiesel() == null) &&
                        (gasStation.getLpg() == lpg || gasStation.getLpg() == null) &&
                        (gasStation.getDiesel() == diesel || gasStation.getDiesel() == null) &&
                        (gasStation.getOctane95() == octane95 || gasStation.getOctane95() == null) &&
                        (gasStation.getOctane98() == octane98 || gasStation.getOctane98() == null) &&
                        (gasStation.getOctane100() == octane100 || gasStation.getOctane100() == null ))).collect(Collectors.toList());
    }
    
    @Override
    public ISearchBuilderSimplified getSearchBuilder() {
        return this.builder;
    }
    
    
}
