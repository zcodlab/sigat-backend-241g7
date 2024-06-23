package uni.isw.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.isw.model.Reserva;
import uni.isw.service.ReservaService;

@RestController
@RequestMapping(path="api/v1/reserva")
public class ReservaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReservaService reservaService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reserva>> getReservas() {
        logger.info("> listar");

        List<Reserva> listaReservas;
        try {
            listaReservas = reservaService.getReservas();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> listar");

        return new ResponseEntity<>(listaReservas, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserva> getReserva(@RequestBody Optional<Reserva> reserva) {
            logger.info(">buscar" +  reserva.toString());
            try {
                    reserva = reservaService.getReserva(reserva.get().getAgente_de_Reservas_ID());                    
                    
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.info(">buscar" +  reserva.toString());
            return new ResponseEntity<>(reserva.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserva> agregar(@RequestBody Reserva reserva) {
        logger.info("> agregar: " + reserva.toString());
        try {
            reservaService.saveOrUpdate(reserva);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> agregar: " + reserva.toString());
        return new ResponseEntity<>(reserva, HttpStatus.CREATED);
    } 

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserva> actualizar(@RequestBody Reserva reserva) {
        logger.info("> actualizar: " + reserva.toString());
        try {
            reservaService.saveOrUpdate(reserva);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> actualizar: " + reserva.toString());
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserva> delete(@RequestParam Long id) {
        logger.info("> eliminar id: " + id);
        Optional<Reserva> reserva;
        try {
            reserva = reservaService.getReserva(id);
            if (reserva.isPresent()) {
                reservaService.delete(id);
                logger.info("> eliminado: " + reserva.toString());
                return new ResponseEntity<>(reserva.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}
