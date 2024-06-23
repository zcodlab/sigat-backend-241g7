package uni.isw.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.Reserva;
import uni.isw.repository.ReservaRepository;

@Service
public class ReservaService {
    @Autowired
    ReservaRepository reservaRepository;

    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> getReserva(Long id) {
        return reservaRepository.findById(id);
    }

    public void saveOrUpdate(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    public void delete(Long id) {
        reservaRepository.deleteById(id);
    }

    // Método para cancelar una reserva
    public void cancelarReserva(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            Reserva r = reserva.get();
            r.setEstado("Cancelada");
            reservaRepository.save(r);
        }
    }
}
