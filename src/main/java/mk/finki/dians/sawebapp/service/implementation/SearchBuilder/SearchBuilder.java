package mk.finki.dians.sawebapp.service.implementation.SearchBuilder;

import mk.finki.dians.sawebapp.model.Location;

import java.lang.reflect.InvocationTargetException;

/**
 * The class implements builder pattern and is used to build a build up parameters for calling a function.
 * In this case it builds the search parameters one by one and calls the function.
 * only one instance of this class is kept.
 */
public class SearchBuilder extends MethodBuilder {
    private final Search search;
    /**
     * private constructor
     */
    public SearchBuilder(String method, Class classname) {
        setSearchMethod(method);
        this.className = classname;
        search = Search.getInstance();
        search.setTerm("").setDistance(100.0);
    }
    
    /**
     * Calls the specified function with the entered parameters.
     * @param obj the object from which to call the specified method
     * @return List<GasStations> the filtered list by the called method (can be null if the method is static)
     * @throws NoSuchMethodException is thrown if the method does not exist
     * @throws InvocationTargetException Exception thrown by the called method
     * @throws IllegalAccessException is thrown if the current object can not access the method
     */
    @Override
    public Object call(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return this.className.getMethod(this.method, String.class, Location.class, Double.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class)
                .invoke(obj, search.getTerm(), search.getUserLocation(), search.getDistance(), search.getBioDiesel(), search.getLpg(), search.getDiesel(), search.getOctane100(), search.getOctane98(), search.getOctane95(), search.getIgnore());
    }
    
    @Override
    public MethodBuilder setParameter(String parameter, Object value, Class c) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        search.getClass().getMethod("set" + Character.toUpperCase(parameter.charAt(0)) + parameter.substring(1), c)
        .invoke(search,value);
        return this;
    }
    
}
