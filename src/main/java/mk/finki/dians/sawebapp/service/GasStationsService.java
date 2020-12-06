package mk.finki.dians.sawebapp.service;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.location;

import java.util.List;

public interface GasStationsService {
        List<GasStation> findAll();
        List<GasStation> findByDistance(location loc, double distance);
        void setDistance(location loc);
}
