package br.com.guto.spring_boot_security.database.repository;

import br.com.guto.spring_boot_security.database.model.AvaliacaoFisicaModel;
import br.com.guto.spring_boot_security.dto.AvaliacaoFisicaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisicaModel, Integer> {
    @NativeQuery(value = """
            SELECT  a.id                                idAluno,
                    a.nome                              nomeAluno,
                    af.id                               idAvaliacao,
                    af.peso                             peso,
                    af.altura                           altura,
                    af.porcentagem_gordura_corporal     porcentagemGorduraCorporal
            FROM `avaliacoes fisicas` af
            INNER JOIN alunos a
            ON a.id = af.id
        """)
    List<AvaliacaoFisicaProjection> getAllAvaliacoes();

    @NativeQuery(value = """
            SELECT  a.id                                idAluno,
                    a.nome                              nomeAluno,
                    af.id                               idAvaliacao,
                    af.peso                             peso,
                    af.altura                           altura,
                    af.porcentagem_gordura_corporal     porcentagemGorduraCorporal
            FROM `avaliacoes fisicas` af
            INNER JOIN alunos a
            ON a.id = af.id
        """, countQuery = """
            SELECT COUNT(af.id)
            FROM `avaliacoes fisicas` af
            INNER JOIN alunos a
            ON a.id = af.id
            """)
    Page<AvaliacaoFisicaProjection> getAllAvaliacoesPage(Pageable pageable);
}
