package uni.isw.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Reserva_ID")
    private Long Reserva_ID;

    @ManyToOne
    @JoinColumn(name = "Cliente_ID")
    @JsonProperty("Cliente")
    private Cliente cliente;

    @JsonProperty("Agente_de_Reservas_ID")
    private Long Agente_de_Reservas_ID;

    @JsonProperty("Producto_ID")
    private Long Producto_ID;

    @JsonProperty("Fecha_Reserva")
    private Date Fecha_Reserva;

    @JsonProperty("Fecha_Inicio")
    private Date Fecha_Inicio;

    @JsonProperty("Fecha_Fin")
    private Date Fecha_Fin;

    @JsonProperty("Estado")
    private String Estado;
}
