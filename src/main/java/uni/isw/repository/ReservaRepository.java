package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.isw.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}

