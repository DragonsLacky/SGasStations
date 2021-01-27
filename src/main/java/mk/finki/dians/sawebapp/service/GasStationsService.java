package mk.finki.dians.sawebapp.service;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.ISearchBuilderSimplified;
import mk.finki.dians.sawebapp.service.implementation.SearchBuilder.MethodBuilder;
import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Service used to access the database and get either all the data of filter it.
 */
@Service
public interface GasStationsService {
        
        /**
         * Calls the specified method from the builder, with all the conditions set up in the builder.
         * @return List of gas stations
         * @throws NoSuchMethodException when the method doesn't exist
         * @throws IllegalAccessException when the caller does not have access to the specified method
         * @throws InvocationTargetException wraps the exceptions thrown by invocation of function
         */
        List<GasStation> listByBuilderConditions() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
        /**
         * Accesses the repository object that has access to the database and returns all the available elements and there are no specific search conditions.
         * @return List of the requested objects, contained within the database
         */
        List<GasStation> list();
        
        /**
         * The function called by the search builder, with all its parameters specified, it can also be called by the user with the parameters specified.
         * @param term the keyword that must be contained by any of the given object attributes.
         * @param userLocation the current users location.
         * @param Distance the maximum search distance.
         * @param bioDiesel a boolean parameter that describes the fuel type
         * @param lpg a boolean parameter that describes the fuel type
         * @param diesel a boolean parameter that describes the fuel type
         * @param octane100 a boolean parameter that describes the fuel type
         * @param octane98 a boolean parameter that describes the fuel type
         * @param octane95 a boolean parameter that describes the fuel type
         * @param ignore a boolean parameter that describes the fuel type
         * @return the list of filtered documents by the conditions specified in the method builder ordered by the distance
         */
        List<GasStation> listByTermDistanceTypeAndOrderByDistance(String term, Location userLocation, Double Distance, Boolean bioDiesel, Boolean lpg, Boolean diesel, Boolean octane100, Boolean octane98, Boolean octane95, Boolean ignore);
        
        /**
         *This function gets the current instance of the searchbuilder object.
         * @return SearchConditionBuilder the builder that is used to call the function to filter ducuments.
         */
        ISearchBuilderSimplified getSearchBuilder();
}
