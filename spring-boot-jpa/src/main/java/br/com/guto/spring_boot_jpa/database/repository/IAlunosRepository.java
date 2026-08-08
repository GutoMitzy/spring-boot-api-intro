package br.com.guto.spring_boot_jpa.database.repository;

import br.com.guto.spring_boot_jpa.database.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAlunosRepository extends JpaRepository<AlunoModel, Integer> {

    Optional<AlunoModel> findByEmail(String email);

    @Query(value = "SELECT a FROM AlunoModel a JOIN FETCH a.avaliacaoFisica WHERE a.id = :alunoId")
    Optional<AlunoModel> findByFetch(Integer alunoId);
}
