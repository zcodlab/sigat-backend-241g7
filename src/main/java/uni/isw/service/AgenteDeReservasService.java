package uni.isw.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.AgenteDeReservas;
import uni.isw.repository.AgenteDeReservasRepository;

@Service
public class AgenteDeReservasService {
    @Autowired
    AgenteDeReservasRepository agenteDeReservasRepository;

    public List<AgenteDeReservas> getAgentesDeReservas() {
        return agenteDeReservasRepository.findAll();
    }

    public Optional<AgenteDeReservas> getAgenteDeReservas(Long id) {
        return agenteDeReservasRepository.findById(id);
    }

    public void saveOrUpdate(AgenteDeReservas agenteDeReservas) {
        agenteDeReservasRepository.save(agenteDeReservas);
    }

    public void delete(Long id) {
        agenteDeReservasRepository.deleteById(id);
    }
}
