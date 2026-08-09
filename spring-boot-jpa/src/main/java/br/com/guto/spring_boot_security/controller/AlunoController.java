package br.com.guto.spring_boot_security.controller;

import br.com.guto.spring_boot_security.database.model.AvaliacaoFisicaModel;
import br.com.guto.spring_boot_security.dto.AlunoDto;
import br.com.guto.spring_boot_security.exception.BadRequestException;
import br.com.guto.spring_boot_security.exception.NotFoundException;
import br.com.guto.spring_boot_security.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody AlunoDto alunoDto) throws BadRequestException {
        alunoService.criarAluno(alunoDto);
    }

    @GetMapping("/{idAluno}/avaliacao")
    @ResponseStatus(HttpStatus.OK)
    public AvaliacaoFisicaModel getAlunoAvaliacaoFisica(@PathVariable Integer idAluno) throws NotFoundException {
        return alunoService.getAlunoAvaliacaoFisica(idAluno);
    }

    @DeleteMapping("/{idAluno}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable Integer idAluno) throws NotFoundException {
        alunoService.deletarAluno(idAluno);
    }
}
