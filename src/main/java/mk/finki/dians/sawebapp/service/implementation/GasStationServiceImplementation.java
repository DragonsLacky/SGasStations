package mk.finki.dians.sawebapp.service.implementation;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import mk.finki.dians.sawebapp.repository.GasStationRepository;
import mk.finki.dians.sawebapp.service.GasStationsService;
import mk.finki.dians.sawebapp.service.SearchConditionBuilder;
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
    private final SearchConditionBuilder builder;
    
    public GasStationServiceImplementation(MongoOperations mongoOperations, GasStationRepository gasStationRepository) {
        this.mongoOperations = mongoOperations;
        this.gasStationRepository = gasStationRepository;
        this.builder = SearchConditionBuilder.SearchBuilder();
        this.builder.addSearchMethod("findByTermDistanceTypeAndOrderByDistance").addTerm("").addDistance(10.0).addBioDiesel(null).addDiesel(null).addLpg(null).addOctane100(null).addOctane98(null).addOctane95(null).addUserLocation(new Location( "Point",new Double[]{-97.8220,37.7510})).setAny();
    }
    
    
    @Override
    public List<GasStation> find() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (List<GasStation>) builder.call(this);
    }
    
    @Override
    public List<GasStation> findAll() {
        return gasStationRepository.findAll();
    }
    
    @Override
    public List<GasStation> findByDistance(Location location, double distance) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        builder.addUserLocation(location);
        builder.addDistance(distance);
        return (List<GasStation>) builder.call(this);
    }
    
    @Override
    public List<GasStation> findAndOrderByDistance(Location location, double distance) {
        Point point = new Point(location.coordinates[0],location.coordinates[1]);
        Distance radius = new Distance(distance, Metrics.KILOMETERS);
        Circle area = new Circle(point,radius);
        Query query = new Query();
        query.addCriteria(Criteria.where("location").withinSphere(area));
        List<GasStation> gasStations = mongoOperations.find(query, GasStation.class);
        gasStations.forEach(gasStation -> gasStation.distance(location));
        return gasStations.stream().sorted(Comparator.comparing(gasStation -> gasStation.distance)).collect(Collectors.toList());
    }
    
    @Override
    public List<GasStation> findByTermDistanceTypeAndOrderByDistance(String term, Location userLocation, Double Distance, Boolean bioDiesel, Boolean lpg, Boolean diesel, Boolean octane100, Boolean octane98, Boolean octane95, Boolean any) {
        List<GasStation> gasStations = findAndOrderByDistance(userLocation, Distance);
        return gasStations.stream()
                .filter(gasStation -> gasStation.getName().toLowerCase().contains(term) ||
                gasStation.getBrand().toLowerCase().contains(term) ||
                gasStation.getEnName().toLowerCase().contains(term))
                .filter(gasStation -> any || ( (gasStation.getBioDiesel() == bioDiesel || gasStation.getBioDiesel() == null) &&
                        (gasStation.getLpg() == lpg || gasStation.getLpg() == null) &&
                        (gasStation.getDiesel() == diesel || gasStation.getDiesel() == null) &&
                        (gasStation.getOctane95() == octane95 || gasStation.getOctane95() == null) &&
                        (gasStation.getOctane98() == octane98 || gasStation.getOctane98() == null) &&
                        (gasStation.getOctane100() == octane100 || gasStation.getOctane100() == null ))).collect(Collectors.toList());
    }
    
    @Override
    public SearchConditionBuilder getSearchBuilder() {
        return this.builder;
    }
    
    
}
