package mk.finki.dians.sawebapp.service;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Service used to access the database and get either all the data of filter it.
 */
@Service
public interface GasStationsService {
        List<GasStation> find() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
        /**
         * Accesses the repository object that has access to the database and returns all the available elements.
         * @return List returns the List of the requested object
         */
        List<GasStation> findAll();
        /**
         * Sets the parameters of the searchBuilder and calls the searchBuilder function which accesses the repository object that has access to the database and returns all the available elements while filtering by the distance and current user location.
         * Sends a custom created query to the database to filter and acquire the required data
         * @param location custom class created consisting of longitude and latitude for the current location of the user.
         * @param distance distance in meters to filter by
         * @return List returns the List of the objects within distance from the current location.
         */
        List<GasStation> findByDistance(Location location, double distance) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
        
        /**
         * Sets the parameters of the searchBuilder and calls the searchBuilder function which accesses the repository object that has access to the database and returns all the available elements while filtering by the distance and current user location.
         * Sends a custom created query to the database to filter and acquire the required data ordered by the distance in ascending.
         * @param location custom class created consisting of longitude and latitude for the current location of the user.
         * @param distance distance in meters to filter by.
         * @return List returns the List of the objects within distance from the current location ordered by distance in ascending.
         */
        List<GasStation> findAndOrderByDistance(Location location, double distance);
        
        /**
         * Set as the default search function in the searchBuilder, it filters the list by the given parameters.
         * @param term the keyword that must be contained by any of the given object attributes.
         * @param userLocation the current users location.
         * @param Distance the maximum search distance.
         * @param bioDiesel a boolean parameter that describes the fuel type
         * @param lpg a boolean parameter that describes the fuel type
         * @param diesel a boolean parameter that describes the fuel type
         * @param octane100 a boolean parameter that describes the fuel type
         * @param octane98 a boolean parameter that describes the fuel type
         * @param octane95 a boolean parameter that describes the fuel type
         * @param any a boolean parameter that describes the fuel type
         * @return List<GasStation> the list of filtered documents
         */
        List<GasStation> findByTermDistanceTypeAndOrderByDistance(String term, Location userLocation, Double Distance, Boolean bioDiesel, Boolean lpg, Boolean diesel, Boolean octane100, Boolean octane98, Boolean octane95, Boolean any);
        
        /**
         *This function gets the current instance of the searchbuilder object.
         * @return SearchConditionBuilder the builder that is used to call the function to filter ducuments.
         */
        SearchConditionBuilder getSearchBuilder();
}
