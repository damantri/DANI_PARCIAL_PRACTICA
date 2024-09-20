package co.edu.uniandes.dse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import co.edu.uniandes.dse.parcialprueba.services.AsociacionService;


@ExtendWith(MockitoExtension.class)
public class AsociacionServiceTest {
    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private EspecialidadRepository especialidadRepository;

    @InjectMocks
    private AsociacionService asociacionService;

    private MedicoEntity medico;
    private EspecialidadEntity especialidad;

    @BeforeEach
    public void setUp() {
        medico = new MedicoEntity();
        medico.setId(1L);
        medico.setNombre("Juan");

        especialidad = new EspecialidadEntity();
        especialidad.setId(1L);
        especialidad.setNombre("Cardiología");
    }

    // 1. Test donde se agrega la especialidad correctamente al médico
    @Test
    public void testAddEspecialidadCorrectamente() throws IllegalOperationException {
        Mockito.when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        Mockito.when(especialidadRepository.findById(1L)).thenReturn(Optional.of(especialidad));
        Mockito.when(medicoRepository.save(Mockito.any(MedicoEntity.class))).thenReturn(medico);

        MedicoEntity result = asociacionService.addEspecialidad(1L, 1L);

        assertEquals(1, result.getEspecialidad().size());
        assertEquals("Cardiología", result.getEspecialidad().get(0).getNombre());
    }

    // 2. Test donde se lanza una excepción porque el médico no existe
    @Test
    public void testAddEspecialidadMedicoNoExiste() {
        Mockito.when(medicoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalOperationException.class, () -> {
            asociacionService.addEspecialidad(1L, 1L);
        });
    }

    // 3. Test donde se lanza una excepción porque la especialidad no existe
    @Test
    public void testAddEspecialidadEspecialidadNoExiste() {
        Mockito.when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        Mockito.when(especialidadRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalOperationException.class, () -> {
            asociacionService.addEspecialidad(1L, 1L);
        });
    }
}
