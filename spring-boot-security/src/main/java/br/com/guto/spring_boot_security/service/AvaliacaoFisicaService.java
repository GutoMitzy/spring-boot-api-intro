package br.com.guto.spring_boot_security.service;

import br.com.guto.spring_boot_security.database.model.AlunoModel;
import br.com.guto.spring_boot_security.database.model.AvaliacaoFisicaModel;
import br.com.guto.spring_boot_security.database.repository.IAlunosRepository;
import br.com.guto.spring_boot_security.database.repository.IAvaliacaoFisicaRepository;
import br.com.guto.spring_boot_security.dto.AvaliacaoFisicaDto;
import br.com.guto.spring_boot_security.dto.AvaliacaoFisicaProjection;
import br.com.guto.spring_boot_security.exception.BadRequestException;
import br.com.guto.spring_boot_security.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {
    private final IAlunosRepository alunosRepository;
    private final IAvaliacaoFisicaRepository avaliacaoFisicaRepository;

    public void criarAvaliacaoFisica(AvaliacaoFisicaDto avaliacaoFisicaDto) throws  NotFoundException, BadRequestException {
        AlunoModel aluno = alunosRepository.findById(avaliacaoFisicaDto.getAlunoId())
                            .orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));

        AvaliacaoFisicaModel avaliacaoFisica = aluno.getAvaliacaoFisica();
        if(avaliacaoFisica != null){
            throw new BadRequestException("Avaliação Física já cadastrada para esse aluno!");
        }

        avaliacaoFisica = AvaliacaoFisicaModel.builder()
                .peso(avaliacaoFisicaDto.getPeso())
                .altura(avaliacaoFisicaDto.getAltura())
                .porcentagemGorduraCorporal(avaliacaoFisicaDto.getPorcentagemGorduraCorporal())
                .build();

        aluno.setAvaliacaoFisica(avaliacaoFisica);
        alunosRepository.save(aluno);
    }

    public List<AvaliacaoFisicaProjection> getAllAvaliacoesFisicas() {
        return avaliacaoFisicaRepository.getAllAvaliacoes();
    }

    public Page<AvaliacaoFisicaProjection> getAllAvaliacoesFisicasPageable(Integer page, Integer size) {
        return avaliacaoFisicaRepository.getAllAvaliacoesPage(PageRequest.of(page, size));
    }
}
