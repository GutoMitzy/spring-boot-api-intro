package br.com.guto.spring_boot_jpa.service;

import br.com.guto.spring_boot_jpa.database.model.ExercicioModel;
import br.com.guto.spring_boot_jpa.database.repository.IExerciciosRepository;
import br.com.guto.spring_boot_jpa.dto.ExercicioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
public class ExercicioService {
    private final IExerciciosRepository exerciciosRepository;

    public List<ExercicioModel> findAll() {
        return exerciciosRepository.findAll();
    }

    public void createExercicio(ExercicioDto exercicio){
        exerciciosRepository.save(ExercicioModel.builder()
                        .nome(exercicio.getNome())
                        .grupoMuscular(exercicio.getGrupoMuscular())
                .build());
    }

    public List<ExercicioModel> getExerciciosByGrupoMuscular(String grupoMuscular){
        return exerciciosRepository.findAllByGrupoMuscular(grupoMuscular);
    }
}
