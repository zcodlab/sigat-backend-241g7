package uni.isw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.isw.model.ServicioAlCliente;
import uni.isw.repository.ServicioAlClienteRepository;

@Service
public class ServicioAlClienteService {

    @Autowired
    ServicioAlClienteRepository servicioAlClienteRepository;

    public List<ServicioAlCliente> getServiciosAlCliente() {
        return servicioAlClienteRepository.findAll();
    }

    public Optional<ServicioAlCliente> getServicioAlCliente(Long id) {
        return servicioAlClienteRepository.findById(id);
    }

    public ServicioAlCliente saveOrUpdate(ServicioAlCliente servicioAlCliente) {
        return servicioAlClienteRepository.save(servicioAlCliente);
    }

    public void delete(Long id) {
        servicioAlClienteRepository.deleteById(id);
    }
}
