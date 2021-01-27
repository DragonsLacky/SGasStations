package mk.finki.dians.sawebapp.service.implementation.SearchBuilder;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ISearchBuilderSimplified {
    /**
     * provides a more specific function for setting the search term.
     * @param term the term to search by in the database
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildTerm(String term) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the location of the user.
     * @param location the location of the user
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildUserLocation(Location location) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the maximum distance to search by.
     * @param distance the radius around the user location
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildDistance(Double distance) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    
    /**
     * provides a more specific function for setting the type of fuel contained withing the gas station.
     * @param bioDiesel containing the type of fuel
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildBioDiesel(Boolean bioDiesel) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the type of fuel contained withing the gas station.
     * @param Lpg containing the type of fuel
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildLpg(Boolean Lpg) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the type of fuel contained withing the gas station.
     * @param diesel containing the type of fuel
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildDiesel(Boolean diesel) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the type of fuel contained withing the gas station.
     * @param octane95 containing the type of fuel
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildOctane95(Boolean octane95) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the type of fuel contained withing the gas station.
     * @param octane98 containing the type of fuel
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildOctane98(Boolean octane98) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the type of fuel contained withing the gas station.
     * @param octane100 containing the type of fuel
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildOctane100(Boolean octane100) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * provides a more specific function for setting the type of fuel contained withing the gas station.
     * @param ignore set to ignore the types of fuel specified.
     * @return the object that called the function, so you can easily chain function calls.
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    ISearchBuilderSimplified buildOIgnore(Boolean ignore) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    
    /**
     * calls the corresponding function in the builder
     * @param obj the caller of the function
     * @return a list of gas stations, the result from the called function
     * @throws IllegalAccessException when the object calling the specified method does not have permission to call that method
     * @throws NoSuchMethodException when the method doesn't exist
     * @throws InvocationTargetException wraps exceptions thrown by invocation
     */
    List<GasStation> call(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
