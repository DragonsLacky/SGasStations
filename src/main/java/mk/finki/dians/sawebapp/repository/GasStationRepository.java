package mk.finki.dians.sawebapp.repository;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GasStationRepository extends MongoRepository<GasStation, String> {
    //public List<GasStation> findByName(String name);
    //public List<GasStation> findByBioDiessel(Boolean B);
    List<GasStation> findAll();
    List<GasStation> findByLocation(location location);
}
