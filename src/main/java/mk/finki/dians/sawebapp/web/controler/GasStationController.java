package mk.finki.dians.sawebapp.web.controler;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.model.Location;
import mk.finki.dians.sawebapp.service.GasStationsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

/**
 * Main controler in charge of viewing and searching through the gas Stations from the database
 */
@Controller
@RequestMapping("")
public class GasStationController {
    private final GasStationsService gasStationsService;
    
    public GasStationController(GasStationsService gasStationsService) {
        this.gasStationsService = gasStationsService;
    }
    
    /**
     * Just returns the view available, so that teh javascript functions can run for the first time and get the current user location.
     * @return String the name of the view
     */
    @GetMapping
    public String getLocation(){
        return "GasStations";
    }
    
    /**
     * Filters the data and delivers it to the view via the model.
     * @param location application user.
     * @param search the search term entered by the user
     * @param distance the max distance entered by the user
     * @param biodiesel selected fuel type
     * @param lpg selected fuel type
     * @param diesel selected fuel type
     * @param octane95 selected fuel type
     * @param octane98 selected fuel type
     * @param octane100 selected fuel type
     * @param any selected to ignore the fuel type
     * @param model The object that passes the data to the view
     * @return String name of the view
     */
    @GetMapping("show")
    public String getLocation(@RequestParam String location,@RequestParam(required = false) String search,@RequestParam(required = false)Double distance,@RequestParam(required = false) Boolean biodiesel,@RequestParam(required = false) Boolean lpg,@RequestParam(required = false) Boolean diesel, @RequestParam(required = false) Boolean octane95, @RequestParam(required = false) Boolean octane98, @RequestParam(required = false) Boolean octane100, @RequestParam(required = false) Boolean any  , Model model) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String[] l = location.split(",");
        Location loc = new Location("Point", new Double[]{Double.parseDouble(l[0]),Double.parseDouble(l[1])});
        
        gasStationsService.getSearchBuilder()
                .buildUserLocation(loc)
                .buildTerm(search!=null ? search.toLowerCase() : "")
                .buildBioDiesel(biodiesel)
                .buildLpg(lpg)
                .buildDiesel(diesel)
                .buildOctane95(octane95)
                .buildOctane98(octane98)
                .buildOctane100(octane100)
                .buildOIgnore(biodiesel != null || diesel != null || lpg != null || octane95 != null || octane98 != null || octane100 != null);
        
        if(distance != null){
            gasStationsService.getSearchBuilder().buildDistance(distance);
        }
        List<GasStation> gasStations;
        //try {
            gasStations = gasStationsService.listByBuilderConditions();
        //} catch (Exception e) {
            //gasStations = gasStationsService.list();
        //}
        model.addAttribute("searchTerm", search);
        model.addAttribute("gasStations", gasStations);
        model.addAttribute("userLocation", loc);
        return "GasStations";
    }
}
