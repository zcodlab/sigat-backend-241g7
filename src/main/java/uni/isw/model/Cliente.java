package uni.isw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pk_cliente_id")
    @JsonProperty("Cliente_ID")
    private Long Cliente_ID;

    @JsonProperty("Cliente_Nombre")
    private String Cliente_Nombre;

    @JsonProperty("Cliente_ApePaterno")
    private String Cliente_ApePaterno;

    @JsonProperty("Cliente_ApeMaterno")
    private String Cliente_ApeMaterno;
    
    /*
    @JsonProperty("Cliente_Telefono")
    private String Cliente_Telefono;*/

    @JsonProperty("Cliente_Correo")
    private String Cliente_Correo;
}
