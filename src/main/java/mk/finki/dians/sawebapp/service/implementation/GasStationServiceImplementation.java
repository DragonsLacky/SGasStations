package mk.finki.dians.sawebapp.service.implementation;

import mk.finki.dians.sawebapp.model.GasStation;
import mk.finki.dians.sawebapp.repository.GasStationRepository;
import mk.finki.dians.sawebapp.service.GasStationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GasStationServiceImplementation implements GasStationsService {
    
    private final GasStationRepository gasStationRepository;
    
    public GasStationServiceImplementation(GasStationRepository gasStationRepository) {
        this.gasStationRepository = gasStationRepository;
    }
    
    
    @Override
    public List<GasStation> findAll() {
        return gasStationRepository.findAll();
    }
}
