package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.isw.model.AgenteDeReservas;

@Repository
public interface AgenteDeReservasRepository extends JpaRepository<AgenteDeReservas, Long> {
}
