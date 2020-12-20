package mk.finki.dians.sawebapp.service;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;

import java.util.List;

public interface GasStationsService {
        /***
         * Accesses the repository object that has access to the database and returns all the available elements.
         * @return List returns the List of the requested object
         */
        List<GasStation> findAll();
        
        /**
         * Accesses the repository object and filters the object that contain the given term.
         * @param term the search condition, (The name, brand name or english name of the object).
         * @return List<GasStation> the resulting list from filtered by items that contain search term.
         */
        List<GasStation> findAllBySearchTerm(String term);
        /***
         * Accesses the the repository object that has access to the database and returns all the available elements while filtering by the distance and current user location.
         * Sends a custom created query to the database to filter and acquire the required data
         * @param loc custom class created consisting of longitude and latitude for the current location of the user.
         * @param distance distance in meters to filter by
         * @return List returns the List of the requested object
         */
        List<GasStation> findByDistance(Location loc, double distance);
}
