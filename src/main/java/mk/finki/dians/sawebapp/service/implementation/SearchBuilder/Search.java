package mk.finki.dians.sawebapp.service.implementation.SearchBuilder;

import mk.finki.dians.sawebapp.model.Location;

public class Search implements SearchConditions {
    
    private static Search instance;
    private String term;
    private Location userLocation;
    private Double distance;
    private Boolean bioDiesel;
    private Boolean lpg;
    private Boolean diesel;
    private Boolean octane100;
    private Boolean octane95;
    private Boolean octane98;
    private Boolean ignore;
    
    private Search() {
    
    }
    
    public static Search getInstance() {
        if(Search.instance == null) {
            Search.instance = new Search();
        }
        return Search.instance;
    }
    
    @Override
    public SearchConditions setTerm(String term) {
        this.term = term;
        return this;
    }
    
    @Override
    public SearchConditions setUserLocation(Location UserLocation) {
        this.userLocation = UserLocation;
        return this;
    }
    
    @Override
    public SearchConditions setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
    
    @Override
    public SearchConditions setBioDiesel(Boolean bioDiesel) {
        this.bioDiesel = bioDiesel;
        return this;
    }
    
    @Override
    public SearchConditions setLpg(Boolean lpg) {
        this.lpg = lpg;
        return this;
    }
    
    @Override
    public SearchConditions setDiesel(Boolean diesel) {
        this.diesel = diesel;
        return this;
    }
    
    @Override
    public SearchConditions setOctane100(Boolean octane100) {
        this.octane100 = octane100;
        return this;
    }
    
    @Override
    public SearchConditions setOctane95(Boolean octane95) {
        this.octane95 = octane95;
        return this;
    }
    
    @Override
    public SearchConditions setOctane98(Boolean octane98) {
        this.octane98 = octane98;
        return this;
    }
    
    @Override
    public SearchConditions setIgnore(Boolean ignore) {
        this.ignore = ignore;
        return this;
    }
    
    public String getTerm() {
        return term;
    }
    
    public Location getUserLocation() {
        return userLocation;
    }
    
    public Double getDistance() {
        return distance;
    }
    
    public Boolean getBioDiesel() {
        return bioDiesel;
    }
    
    public Boolean getLpg() {
        return lpg;
    }
    
    public Boolean getDiesel() {
        return diesel;
    }
    
    public Boolean getOctane100() {
        return octane100;
    }
    
    public Boolean getOctane95() {
        return octane95;
    }
    
    public Boolean getOctane98() {
        return octane98;
    }
    
    public Boolean getIgnore() {
        return ignore;
    }
}
