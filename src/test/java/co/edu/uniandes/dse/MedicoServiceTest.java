package co.edu.uniandes.dse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import co.edu.uniandes.dse.parcialprueba.services.MedicoServices;


@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoServices medicoServices;

    @Test
    public void testCreateMedicoConRegistroValido() throws IllegalOperationException {
        MedicoEntity medico = new MedicoEntity();
        medico.setRegistro("RM1234");
        medico.setNombre("Juan");

        Mockito.when(medicoRepository.save(Mockito.any(MedicoEntity.class))).thenReturn(medico);

        MedicoEntity result = medicoServices.createMedico(medico);
        assertEquals("RM1234", result.getRegistro());
    }

    @Test
    public void testCreateMedicoConRegistroInvalido() {
        MedicoEntity medico = new MedicoEntity();
        medico.setRegistro("1234");

        assertThrows(IllegalOperationException.class, () -> {
            medicoServices.createMedico(medico);
        });
    }
}
