package co.edu.uniandes.dse.parcialprueba.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service 
@Slf4j

public class EspecialidadServices {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException{
        if (especialidad.getDescripcion().length()< 10 ){
            throw new IllegalOperationException("La descripcion debe tener minimo 10 caracteres");

        }
        log.info("Guardando el medico con el nombre{}, apellido{}, registro{} y especialidad{}",
        especialidad.getNombre(), especialidad.getDescripcion(), especialidad.getMedico());
        return especialidadRepository.save(especialidad);

    }
    

}
