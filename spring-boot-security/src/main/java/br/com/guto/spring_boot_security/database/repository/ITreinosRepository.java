package br.com.guto.spring_boot_security.database.repository;

import br.com.guto.spring_boot_security.database.model.TreinoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITreinosRepository extends JpaRepository<TreinoModel, Integer> {
    Optional<TreinoModel> findByNomeAndAlunoId(String nome, Integer alunoId);
}
