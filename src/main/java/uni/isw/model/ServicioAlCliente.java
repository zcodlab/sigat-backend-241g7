package uni.isw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "servicio_al_cliente")
public class ServicioAlCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Servicio_al_Cliente_ID")
    private Long Servicio_al_Cliente_ID;

    @JsonProperty("Nombre")
    private String Nombre;

    @JsonProperty("Descripcion")
    private String Descripcion;
}
