package br.com.guto.spring_boot_security.controller;

import br.com.guto.spring_boot_security.dto.TreinoDto;
import br.com.guto.spring_boot_security.exception.BadRequestException;
import br.com.guto.spring_boot_security.exception.NotFoundException;
import br.com.guto.spring_boot_security.service.TreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinoController {
    private final TreinoService treinoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTreino(@Valid @RequestBody TreinoDto treinoDto) throws NotFoundException, BadRequestException {
        treinoService.criarTreino(treinoDto);
    }
}
