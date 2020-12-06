package mk.finki.dians.sawebapp.service.implementation;

import com.mongodb.client.model.geojson.Position;
import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.location;
import mk.finki.dians.sawebapp.repository.GasStationRepository;
import mk.finki.dians.sawebapp.service.GasStationsService;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class GasStationServiceImplementation implements GasStationsService {
    
    private final MongoOperations mongoOperations;
    private final GasStationRepository gasStationRepository;
    
    public GasStationServiceImplementation(MongoOperations mongoOperations, GasStationRepository gasStationRepository) {
        this.mongoOperations = mongoOperations;
        this.gasStationRepository = gasStationRepository;
    }
    
    
    @Override
    public List<GasStation> findAll() {
        return gasStationRepository.findAll();
    }
    
    @Override
    public List<GasStation> findByDistance(location loc, double distance) {
        //db.gasStations.find({"location" : {$geoWithin :{ $centerSphere : [[21.0798146,42.1003965], 0.5/6371]}}});
        Point point = new Point(loc.coordinates[0],loc.coordinates[1]);
        Distance radius = new Distance(distance, Metrics.KILOMETERS);
        Circle area = new Circle(point,radius);
        Query query = new Query();
        query.addCriteria(Criteria.where("location").withinSphere(area));
        return mongoOperations.find(query, GasStation.class);
    }
    
    @Override
    public void setDistance(location loc) {
        gasStationRepository.findAll().forEach(gasStation -> gasStation.distance(loc));
    }
    
}
