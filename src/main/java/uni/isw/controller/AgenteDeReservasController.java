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

import uni.isw.model.AgenteDeReservas;
import uni.isw.service.AgenteDeReservasService;

@RestController
@RequestMapping(path="api/v1/agenteDeReservas")
public class AgenteDeReservasController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private AgenteDeReservasService agenteDeReservasService;
      
    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgenteDeReservas>> getAgentesDeReservas() {
        logger.info("> listar");

        List<AgenteDeReservas> listaAgentes;
        try {
            listaAgentes = agenteDeReservasService.getAgentesDeReservas();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> listar");            
        
        return new ResponseEntity<>(listaAgentes, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgenteDeReservas> getAgenteDeReservas(@RequestBody Optional<AgenteDeReservas> agente) {
            logger.info(">buscar" +  agente.toString());
            try {
                    agente = agenteDeReservasService.getAgenteDeReservas(agente.get().getAgente_de_Reservas_ID());                    
                    
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.info(">buscar" +  agente.toString());
            return new ResponseEntity<>(agente.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgenteDeReservas> agregar(@RequestBody AgenteDeReservas agente) {
        logger.info("> agregar: " + agente.toString());
        try {
            agenteDeReservasService.saveOrUpdate(agente);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> agregar: " + agente.toString());
        return new ResponseEntity<>(agente, HttpStatus.CREATED);
    } 
    
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgenteDeReservas> actualizar(@RequestBody AgenteDeReservas agente) {
        logger.info("> actualizar: " + agente.toString());
        try {
            agenteDeReservasService.saveOrUpdate(agente);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> actualizar: " + agente.toString());
        return new ResponseEntity<>(agente, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgenteDeReservas> delete(@RequestParam Long id) {
        logger.info("> eliminar id: " + id);
        Optional<AgenteDeReservas> agente;
        try {
            agente = agenteDeReservasService.getAgenteDeReservas(id);
            if (agente.isPresent()) {
                agenteDeReservasService.delete(id);
                logger.info("> eliminado: " + agente.toString());
                return new ResponseEntity<>(agente.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}
