package uni.isw.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.Consulta;
import uni.isw.repository.ConsultaRepository;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;

    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> getConsulta(Long id) {
        return consultaRepository.findById(id);
    }

    public void saveOrUpdate(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    public void delete(Long id) {
        consultaRepository.deleteById(id);
    }
}
