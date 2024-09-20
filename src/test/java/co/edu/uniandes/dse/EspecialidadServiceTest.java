package co.edu.uniandes.dse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.services.EspecialidadServices;

@ExtendWith(MockitoExtension.class)
public class EspecialidadServiceTest {

    @Mock
    private EspecialidadRepository especialidadRepository;

    @InjectMocks
    private EspecialidadServices especialidadServices;

    @Test
    public void testCreateEspecialidadConDescripcionValida() throws IllegalOperationException {
        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setDescripcion("Descripcion válida");
        especialidad.setNombre("Cardiología");

        Mockito.when(especialidadRepository.save(Mockito.any(EspecialidadEntity.class))).thenReturn(especialidad);

        EspecialidadEntity result = especialidadServices.createEspecialidad(especialidad);
        assertEquals("Descripcion válida", result.getDescripcion());
        assertEquals("Cardiología", result.getNombre());
    }

    @Test
    public void testCreateEspecialidadConDescripcionInvalida() {
        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setDescripcion("Corta");
        especialidad.setNombre("Pediatría");

        assertThrows(IllegalOperationException.class, () -> {
            especialidadServices.createEspecialidad(especialidad);
        });
    }
}
