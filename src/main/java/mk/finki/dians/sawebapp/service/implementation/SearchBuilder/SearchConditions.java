package mk.finki.dians.sawebapp.service.implementation.SearchBuilder;

import mk.finki.dians.sawebapp.model.Location;

public interface SearchConditions {
    
    /**
     * Sets the the term to search for.
     * @param term the term specified
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setTerm(String term);
    
    /**
     * Sets the current user location, to filter the data by.
     * @param UserLocation location of the user
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setUserLocation(Location UserLocation);
    
    /**
     * Sets the maximum distance to search from a geoLocation to the current user location for in the data.
     * @param distance maximum distance specified.
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setDistance(Double distance);
    
    /**
     * Sets the bioDiesel parameter( specific type of fuel to search for).
     * @param bioDiesel the bioDiesel specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setBioDiesel(Boolean  bioDiesel);
    
    /**
     * Sets the lpg parameter( specific type of fuel to search for).
     * @param lpg the lpg specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setLpg(Boolean lpg);
    
    /**
     * Sets the diesel parameter( specific type of fuel to search for).
     * @param diesel the diesel specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setDiesel(Boolean diesel);
    
    /**
     * Sets the octane100 parameter( specific type of fuel to search for).
     * @param octane100 the octane100 specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setOctane100(Boolean octane100);
    
    /**
     * Sets the octane95 parameter( specific type of fuel to search for).
     * @param octane95 the octane95 specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setOctane95(Boolean octane95);
    
    /**
     * Sets the octane98 parameter( specific type of fuel to search for).
     * @param octane98 the octane98 specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setOctane98(Boolean octane98);
    
    /**
     * Tells the called function to ignore the fuel type or not.
     * @param ignore ignore type of fuel specifier
     * @return SearchConditionBuilder the object that called this function.
     */
    SearchConditions setIgnore(Boolean ignore);
}
