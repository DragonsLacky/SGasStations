package mk.finki.dians.sawebapp.service.implementation;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import mk.finki.dians.sawebapp.repository.GasStationRepository;
import mk.finki.dians.sawebapp.service.GasStationsService;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.ISearchBuilderSimplified;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.SearchBuilder;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.SearchBuilderSimplified;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GasStationServiceImplementation implements GasStationsService {
    
    private final MongoOperations mongoOperations;
    private final GasStationRepository gasStationRepository;
    private final ISearchBuilderSimplified builder;
    
    public GasStationServiceImplementation(MongoOperations mongoOperations, GasStationRepository gasStationRepository) throws NoSuchFieldException {
        this.mongoOperations = mongoOperations;
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
        List<GasStation> gasStations = queryGasStationsByDistance(location,distance);
        gasStations.forEach(gasStation -> gasStation.distance(location));
        return gasStations.stream().sorted(Comparator.comparing(gasStation -> gasStation.distance)).collect(Collectors.toList());
    }
    
    /**
     * sets up the query, and sends it to the database, getting the objects with a geolocation withing a radius around the given location.
     * @param location the location of the user
     * @param distance the maximum distance to search for in a radius around location
     * @return
     */
    private List<GasStation> queryGasStationsByDistance(Location location, Double distance){
        Point point = new Point(location.coordinates[0],location.coordinates[1]);
        Distance radius = new Distance(distance, Metrics.KILOMETERS);
        Circle area = new Circle(point,radius);
        Query query = new Query();
        query.addCriteria(Criteria.where("location").withinSphere(area));
        return mongoOperations.find(query, GasStation.class);
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
