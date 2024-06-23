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

import uni.isw.model.ServicioAlCliente;
import uni.isw.service.ServicioAlClienteService;

@RestController
@RequestMapping(path="api/v1/servicioAlCliente")
public class ServicioAlClienteController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServicioAlClienteService servicioAlClienteService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServicioAlCliente>> getServiciosAlCliente() {
        logger.info("> listar");

        List<ServicioAlCliente> listaServicios;
        try {
            listaServicios = servicioAlClienteService.getServiciosAlCliente();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> listar");

        return new ResponseEntity<>(listaServicios, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicioAlCliente> getServicioAlCliente(@RequestBody Optional<ServicioAlCliente> servicio) {
            logger.info(">buscar" +  servicio.toString());
            try {
                    servicio = servicioAlClienteService.getServicioAlCliente(servicio.get().getServicio_al_Cliente_ID());                    
                    
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.info(">buscar" +  servicio.toString());
            return new ResponseEntity<>(servicio.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicioAlCliente> agregar(@RequestBody ServicioAlCliente servicio) {
        logger.info("> agregar: " + servicio.toString());
        try {
            servicioAlClienteService.saveOrUpdate(servicio);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> agregar: " + servicio.toString());
        return new ResponseEntity<>(servicio, HttpStatus.CREATED);
    } 

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicioAlCliente> actualizar(@RequestBody ServicioAlCliente servicio) {
        logger.info("> actualizar: " + servicio.toString());
        try {
            servicioAlClienteService.saveOrUpdate(servicio);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> actualizar: " + servicio.toString());
        return new ResponseEntity<>(servicio, HttpStatus.OK);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicioAlCliente> delete(@RequestParam Long id) {
        logger.info("> eliminar id: " + id);
        Optional<ServicioAlCliente> servicio;
        try {
            servicio = servicioAlClienteService.getServicioAlCliente(id);
            if (servicio.isPresent()) {
                servicioAlClienteService.delete(id);
                logger.info("> eliminado: " + servicio.toString());
                return new ResponseEntity<>(servicio.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}
