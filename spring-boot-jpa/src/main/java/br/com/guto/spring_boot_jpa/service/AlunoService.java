package br.com.guto.spring_boot_jpa.service;

import br.com.guto.spring_boot_jpa.database.model.AlunoModel;
import br.com.guto.spring_boot_jpa.database.model.AvaliacaoFisicaModel;
import br.com.guto.spring_boot_jpa.database.model.TreinoModel;
import br.com.guto.spring_boot_jpa.database.repository.IAlunosRepository;
import br.com.guto.spring_boot_jpa.database.repository.IAvaliacaoFisicaRepository;
import br.com.guto.spring_boot_jpa.database.repository.ITreinosRepository;
import br.com.guto.spring_boot_jpa.dto.AlunoDto;
import br.com.guto.spring_boot_jpa.exception.BadRequestException;
import br.com.guto.spring_boot_jpa.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final IAlunosRepository alunosRepository;
    private final ITreinosRepository treinosRepository;
    private final IAvaliacaoFisicaRepository avaliacaoFisicaRepository;

    public void criarAluno(AlunoDto alunoDto) throws BadRequestException {
        AlunoModel aluno = alunosRepository.findByEmail(alunoDto.getEmail())
                    .orElse(null);

        if(aluno != null){
            throw new BadRequestException("Não foi possível cadastrar o email!");
        }

        alunosRepository.save(AlunoModel.builder()
                        .nome(alunoDto.getNome())
                        .email(alunoDto.getEmail())
                        .cpf(alunoDto.getCpf())
                .build());
    }

    public AvaliacaoFisicaModel getAlunoAvaliacaoFisica(Integer idAluno) throws NotFoundException {
        AlunoModel aluno = alunosRepository.findByFetch(idAluno)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));

        AvaliacaoFisicaModel avaliacao = aluno.getAvaliacaoFisica();
        if(avaliacao == null){
            throw new NotFoundException("Avaliação Física não encontrada para esse aluno!");
        }

        return avaliacao;
    }

    @Transactional(rollbackFor =  Exception.class)
    public void deletarAluno(Integer alunoId) throws NotFoundException {
        AlunoModel aluno = alunosRepository.findByFetch(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));

        List<Integer> treinosAlunoId = aluno.getTreinos().stream()
                .map(TreinoModel::getId)
                .toList();

        treinosRepository.deleteAllById(treinosAlunoId);
        alunosRepository.deleteById(alunoId);
        avaliacaoFisicaRepository.deleteById(aluno.getAvaliacaoFisica().getId());
    }
}
