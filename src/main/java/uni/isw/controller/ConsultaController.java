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

import uni.isw.model.Consulta;
import uni.isw.service.ConsultaService;

@RestController
@RequestMapping(path="api/v1/consulta")
public class ConsultaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsultaService consultaService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consulta>> getConsultas() {
        logger.info("> listar");

        List<Consulta> listaConsultas;
        try {
            listaConsultas = consultaService.getConsultas();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> listar");

        return new ResponseEntity<>(listaConsultas, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> getConsulta(@RequestBody Optional<Consulta> consulta) {
            logger.info(">buscar" +  consulta.toString());
            try {
                    consulta = consultaService.getConsulta(consulta.get().getConsulta_ID());                    
                    
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.info(">buscar" +  consulta.toString());
            return new ResponseEntity<>(consulta.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> agregar(@RequestBody Consulta consulta) {
        logger.info("> agregar: " + consulta.toString());
        try {
            consultaService.saveOrUpdate(consulta);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> agregar: " + consulta.toString());
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    } 

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> actualizar(@RequestBody Consulta consulta) {
        logger.info("> actualizar: " + consulta.toString());
        try {
            consultaService.saveOrUpdate(consulta);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> actualizar: " + consulta.toString());
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> delete(@RequestParam Long id) {
        logger.info("> eliminar id: " + id);
        Optional<Consulta> consulta;
        try {
            consulta = consultaService.getConsulta(id);
            if (consulta.isPresent()) {
                consultaService.delete(id);
                logger.info("> eliminado: " + consulta.toString());
                return new ResponseEntity<>(consulta.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}
