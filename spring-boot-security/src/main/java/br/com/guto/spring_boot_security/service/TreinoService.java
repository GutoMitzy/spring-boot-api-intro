package br.com.guto.spring_boot_security.service;

import br.com.guto.spring_boot_security.database.model.AlunoModel;
import br.com.guto.spring_boot_security.database.model.ExercicioModel;
import br.com.guto.spring_boot_security.database.model.TreinoModel;
import br.com.guto.spring_boot_security.database.repository.IAlunosRepository;
import br.com.guto.spring_boot_security.database.repository.IExerciciosRepository;
import br.com.guto.spring_boot_security.database.repository.ITreinosRepository;
import br.com.guto.spring_boot_security.dto.TreinoDto;
import br.com.guto.spring_boot_security.exception.BadRequestException;
import br.com.guto.spring_boot_security.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {
    private final IAlunosRepository alunosRepository;
    private final ITreinosRepository treinosRepository;
    private final IExerciciosRepository exerciciosRepository;

    public void criarTreino(TreinoDto treinoDto) throws NotFoundException, BadRequestException {
        Set<ExercicioModel> exercicios = new HashSet<>();

        AlunoModel aluno = alunosRepository.findById(treinoDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));

        TreinoModel treino = treinosRepository.findByNomeAndAlunoId(treinoDto.getNome(), treinoDto.getAlunoId())
                .orElse(null);

        if(treino != null){
            throw new BadRequestException("Já existe um treino com esse nome para esse aluno!");
        }

        for(Integer exercicioId : treinoDto.getExerciciosIds()) {
            ExercicioModel exercicio = exerciciosRepository.findById(exercicioId)
                    .orElseThrow(() -> new NotFoundException(String.format("Exercício %s não encontrado!", exercicioId)));

            exercicios.add(exercicio);
        }

        treino = TreinoModel.builder()
                .nome(treinoDto.getNome())
                .aluno(aluno)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }
}
