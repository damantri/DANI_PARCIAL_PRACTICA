package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoServices {
    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoEntity createMedico(MedicoEntity medico) throws IllegalOperationException{
        if (!medico.getRegistro().startsWith("RM")){
            throw new IllegalOperationException("El registro debe incluir 'RM'.");

        };
        log.info("Guardando el medico con el nombre{}, apellido{}, registro{} y especialidad{}",
        medico.getNombre(), medico.getApellido(), medico.getRegistro(), medico.getEspecialidad());
        return medicoRepository.save(medico);
    } 
}

