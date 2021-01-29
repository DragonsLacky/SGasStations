package mk.finki.dians.sawebapp.repository;

import mk.finki.dians.sawebapp.model.GasStation;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GasStationRepository extends MongoRepository<GasStation, String> {
    /**
     * The main function that directly accesses the database and gives all the documents available.
     * @return List<GasStation> the list of all objects available in database.
     */
    List<GasStation> findAll();
    
    /**
     * Searches the database for object that contain the name, brand or english name at the same time, and filters accordingly.
     * @param name the name of the object that is searched.
     * @param brand the name of the brand of the searched object.
     * @param enName the english name of the object that is search for.
     * @return List<GasStation> filtered list of the database satisfying the condition.
     */
    List<GasStation> findAllByNameContainsIgnoreCaseOrBrandContainsIgnoreCaseOrEnNameContainsIgnoreCase(String name, String brand, String enName);
    
    @Query("{\"location\":{$near: {$geometry: { type: \"Point\", coordinates: ?0},$maxDistance: ?1, $minDistance: 0}}}")
    List<GasStation> findByLocationCoordinatesWithin(Double[] coordinates, Double maxDistance);
}
