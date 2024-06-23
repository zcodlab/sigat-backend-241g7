package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.isw.model.ServicioAlCliente;

@Repository
public interface ServicioAlClienteRepository extends JpaRepository<ServicioAlCliente, Long> {
}
