package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;


@Service 
@Slf4j

public class AsociacionService {
    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoEntity addEspecialidad(Long medicoId, Long especialidadId) throws IllegalOperationException{
        Optional<MedicoEntity> medicoOpt = medicoRepository.findById(medicoId);
        if(!medicoOpt.isPresent()){
            throw new IllegalOperationException("El medico no existe");
        }

        Optional<EspecialidadEntity> especialidadOpt= especialidadRepository.findById(especialidadId); 
        if(!especialidadOpt.isPresent()){
            throw new IllegalOperationException("La especialidad no existe");
        }

        MedicoEntity medico = medicoOpt.get();
        EspecialidadEntity especialidad = especialidadOpt.get();

        medico.getEspecialidad().add(especialidad);

        return medicoRepository.save(medico);
    }
    


}