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

import uni.isw.model.Cliente;
import uni.isw.service.ClienteService;

@RestController
@RequestMapping(path="api/v1/cliente")
public class ClienteController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getClientes() {
        logger.info("> listar");

        List<Cliente> listaClientes;
        try {
            listaClientes = clienteService.getClientes();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> listar");

        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getCliente(@RequestBody Optional<Cliente> cliente) {
            logger.info(">buscar" +  cliente.toString());
            try {
                    cliente = clienteService.getCliente(cliente.get().getCliente_ID());                    
                    
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.info(">buscar" +  cliente.toString());
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> agregar(@RequestBody Cliente cliente) {
        logger.info("> agregar: " + cliente.toString());
        try {
            clienteService.saveOrUpdate(cliente);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> agregar: " + cliente.toString());
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente) {
        logger.info("> actualizar: " + cliente.toString());
        try {
            clienteService.saveOrUpdate(cliente);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> actualizar: " + cliente.toString());
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        logger.info("> eliminar id: " + id);
        Optional<Cliente> cliente;
        try {
            cliente = clienteService.getCliente(id);
            if (cliente.isPresent()) {
                clienteService.delete(id);
                logger.info("> eliminado: " + cliente.toString());
                return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
