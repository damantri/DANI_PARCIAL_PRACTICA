package co.edu.uniandes.dse.parcialprueba.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Data

public class EspecialidadEntity extends BaseEntity {
    @PodamExclude
    @ManyToMany
    private MedicoEntity medico;

    private String nombre;

    private String descripcion;

}
