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
@Table(name = "agente_de_reservas")
public class AgenteDeReservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Agente_de_Reservas_ID")
    private Long Agente_de_Reservas_ID;

    @JsonProperty("Nombre")
    private String Nombre;

    @JsonProperty("ApePaterno")
    private String ApePaterno;

    @JsonProperty("ApeMaterno")
    private String ApeMaterno;
}
