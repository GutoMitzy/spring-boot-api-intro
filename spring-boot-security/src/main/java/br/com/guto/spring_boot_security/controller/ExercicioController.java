package br.com.guto.spring_boot_security.controller;

import br.com.guto.spring_boot_security.database.model.ExercicioModel;
import br.com.guto.spring_boot_security.dto.ExercicioDto;
import br.com.guto.spring_boot_security.service.ExercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exercicios")
@RequiredArgsConstructor
@Validated
public class ExercicioController {
    private final ExercicioService exercicioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExercicioModel> getExercicios(){
        return exercicioService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createExercicio(@Valid @RequestBody ExercicioDto exercicio){
         exercicioService.createExercicio(exercicio);
    }

    @GetMapping("/grupos/{grupoMuscular}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExercicioModel> getExerciciosByGrupoMuscular(@PathVariable String grupoMuscular){
        return exercicioService.getExerciciosByGrupoMuscular(grupoMuscular);
    }
}
