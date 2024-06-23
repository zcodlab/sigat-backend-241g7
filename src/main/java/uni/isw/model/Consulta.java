package uni.isw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Consulta_ID")
    private Long Consulta_ID;

    @ManyToOne
    @JoinColumn(name = "Cliente_ID")
    @JsonProperty("Cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Servicio_al_Cliente_ID")
    @JsonProperty("ServicioAlCliente")
    private ServicioAlCliente servicioAlCliente;

    @JsonProperty("Descripcion")
    private String Descripcion;

    @JsonProperty("Fecha_Consulta")
    private Date Fecha_Consulta;
}
