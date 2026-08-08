package br.com.guto.spring_boot_jpa.controller;

import br.com.guto.spring_boot_jpa.database.model.AvaliacaoFisicaModel;
import br.com.guto.spring_boot_jpa.dto.AvaliacaoFisicaDto;
import br.com.guto.spring_boot_jpa.dto.AvaliacaoFisicaProjection;
import br.com.guto.spring_boot_jpa.exception.BadRequestException;
import br.com.guto.spring_boot_jpa.exception.NotFoundException;
import br.com.guto.spring_boot_jpa.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoFisicaController {
    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFoundException, BadRequestException {
        avaliacaoFisicaService.criarAvaliacaoFisica(avaliacaoFisicaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacaoFisicaProjection> getAllAvaliacoesFisicas() {
        return avaliacaoFisicaService.getAllAvaliacoesFisicas();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AvaliacaoFisicaProjection> getAllAvaliacoesFisicasPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return avaliacaoFisicaService.getAllAvaliacoesFisicasPageable(page, size);
    }
}
