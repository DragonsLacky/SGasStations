package mk.finki.dians.sawebapp.service;

import jdk.dynalink.beans.StaticClass;
import mk.finki.dians.sawebapp.model.Location;
import org.apache.catalina.User;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

/**
 * The class implements builder pattern and is used to build a build up parameters for calling a function.
 * In this case it builds the search parameters one by one and calls the function.
 * only one instance of this class is kept.
 */
public class SearchConditionBuilder {
    private static SearchConditionBuilder builder;
    private static Boolean created = false;
    private String term;
    private Location UserLocation;
    private Double Distance;
    private Boolean bioDiesel;
    private Boolean lpg;
    private Boolean diesel;
    private Boolean octane100;
    private Boolean octane95;
    private Boolean octane98;
    private Class<GasStationsService> c;
    private String method;
    private Boolean any;
    
    /**
     * private constructor
     */
    private SearchConditionBuilder(){}
    
    /**
     * The function returns the currently instantiated SearchConditionBuilder, if none exist it will be created by the function call.
     * @return SearchConditionBuilder the builder that is instantiated.
     */
    public static SearchConditionBuilder SearchBuilder(){
        if(builder == null){
            builder = new SearchConditionBuilder();
            builder.c = GasStationsService.class;
            builder.any = true;
            created = true;
        }
        return builder;
    }
    
    /**
     * Sets the method to be used by the Builder when calling a function.
     * @param method the method name.
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addSearchMethod(String method){
        this.method = method;
        return this;
    }
    
    /**
     * Sets the the term to search for.
     * @param term the term specified
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addTerm(String term){
        this.term = term;
        return this;
    }
    
    /**
     * Sets the current user location, to filter the data by.
     * @param UserLocation location of the user
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addUserLocation(Location UserLocation){
        this.UserLocation = UserLocation;
        return this;
    }
    
    /**
     * Sets the maximum distance to search from a geoLocation to the current user location for in the data.
     * @param distance maximum distance specified.
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addDistance(Double distance){
        this.Distance = distance;
        return this;
    }
    
    /**
     * Sets the bioDiesel parameter( specific type of fuel to search for).
     * @param bioDiesel the bioDiesel specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addBioDiesel(Boolean  bioDiesel){
        this.bioDiesel = bioDiesel;
        return this;
    }
    
    /**
     * Sets the lpg parameter( specific type of fuel to search for).
     * @param lpg the lpg specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addLpg(Boolean lpg){
        this.lpg = lpg;
        return this;
    }
    
    /**
     * Sets the diesel parameter( specific type of fuel to search for).
     * @param diesel the diesel specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addDiesel(Boolean diesel){
        this.diesel = diesel;
        return this;
    }
    
    /**
     * Sets the octane100 parameter( specific type of fuel to search for).
     * @param octane100 the octane100 specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addOctane100(Boolean octane100){
        this.octane100 = octane100;
        return this;
    }
    
    /**
     * Sets the octane95 parameter( specific type of fuel to search for).
     * @param octane95 the octane95 specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addOctane95(Boolean octane95){
        this.octane95 = octane95;
        return this;
    }
    
    /**
     * Sets the octane98 parameter( specific type of fuel to search for).
     * @param octane98 the octane98 specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder addOctane98(Boolean octane98){
        this.octane98 = octane98;
        return this;
    }
    
    /**
     * Tells the called function to ignore the fuel type or not.
     * @param any ignore type specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    public SearchConditionBuilder setAny(Boolean any){
        this.any = any;
        return this;
    }
    
    /**
     * Calls the specified function with the entered parameters.
     * @param obj the object from which to call the specified method
     * @return List<GasStations> the filtered list by the called method (can be null if the method is static)
     * @throws NoSuchMethodException is thrown if the method does not exist
     * @throws InvocationTargetException Exception thrown by the called method
     * @throws IllegalAccessException is thrown if the current object can not access the method
     */
    public Object call(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return c.getMethod(method,String.class,Location.class,Double.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class,Boolean.class)
                .invoke(obj,term, UserLocation, Distance, bioDiesel, lpg, diesel, octane100, octane98, octane95, any);
    }
    
}
