package mk.finki.dians.sawebapp.service.implementation.SearchBuilder;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SearchBuilderSimplified implements ISearchBuilderSimplified {
    SearchBuilder builder;
    
    public SearchBuilderSimplified(SearchBuilder builder) {
        this.builder = builder;
    }
    
    @Override
    public SearchBuilderSimplified buildTerm(String term) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("term", term, term.getClass());
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildUserLocation(Location location) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("userLocation", location, location.getClass());
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildDistance(Double distance) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("distance", distance, distance.getClass());
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildBioDiesel(Boolean bioDiesel) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("bioDiesel",bioDiesel, Boolean.class);
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildLpg(Boolean Lpg) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("lpg",Lpg, Boolean.class);
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildDiesel(Boolean diesel) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("diesel",diesel, Boolean.class);
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildOctane95(Boolean octane95) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("octane95",octane95, Boolean.class);
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildOctane98(Boolean octane98) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("octane98",octane98, Boolean.class);
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildOctane100(Boolean octane100) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("octane100",octane100, Boolean.class);
        return this;
    }
    
    @Override
    public SearchBuilderSimplified buildOIgnore(Boolean ignore) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        builder.setParameter("ignore",ignore, Boolean.class);
        return this;
    }
    
    @Override
    public List<GasStation> call(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (List<GasStation>) builder.call(obj);
    }
}
