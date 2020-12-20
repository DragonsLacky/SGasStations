package mk.finki.dians.sawebapp.service;

import jdk.dynalink.beans.StaticClass;
import mk.finki.dians.sawebapp.model.Location;
import org.apache.catalina.User;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

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
    
    private SearchConditionBuilder(){}
    
    public static SearchConditionBuilder SearchBuilder(){
        if(builder == null){
            builder = new SearchConditionBuilder();
            builder.c = GasStationsService.class;
            builder.any = true;
            created = true;
        }
        return builder;
    }
    
    public SearchConditionBuilder addSearchMethod(String method){
        this.method = method;
        return this;
    }
    
    public SearchConditionBuilder addTerm(String term){
        this.term = term;
        return this;
    }
    
    public SearchConditionBuilder addUserLocation(Location UserLocation){
        this.UserLocation = UserLocation;
        return this;
    }
    
    public SearchConditionBuilder addDistance(Double distance){
        this.Distance = distance;
        return this;
    }
    
    public SearchConditionBuilder addBioDiesel(Boolean  bioDiesel){
        this.any = false;
        this.bioDiesel = bioDiesel;
        return this;
    }
    public SearchConditionBuilder addLpg(Boolean lpg){
        this.any = false;
        this.lpg = lpg;
        return this;
    }
    public SearchConditionBuilder addDiesel(Boolean diesel){
        this.any = false;
        this.diesel = diesel;
        return this;
    }
    public SearchConditionBuilder addOctane100(Boolean octane100){
        this.any = false;
        this.octane100 = octane100;
        return this;
    }
    public SearchConditionBuilder addOctane95(Boolean octane95){
        this.any = false;
        this.octane95 = octane95;
        return this;
    }
    public SearchConditionBuilder addOctane98(Boolean octane98){
        this.any = false;
        this.octane98 = octane98;
        return this;
    }
    
    public SearchConditionBuilder setAny(){
        this.any = true;
        return this;
    }
    
    public Object call(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return c.getMethod(method,String.class,Location.class,Double.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class,Boolean.class)
                .invoke(obj,term, UserLocation, Distance, bioDiesel, lpg, diesel, octane100, octane98, octane95, any);
    }
    
}
