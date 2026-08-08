package br.com.guto.spring_boot_jpa.database.repository;

import br.com.guto.spring_boot_jpa.database.model.ExercicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExerciciosRepository extends JpaRepository<ExercicioModel, Integer> {

    List<ExercicioModel> findAllByGrupoMuscular(String grupoMuscular);

    @Query(value = """
            SELECT e 
            from ExerciciosModel e 
            WHERE UPPER(e.grupoMuscular) = UPPER(:grupoMuscular)
""", nativeQuery = true)
    List<ExercicioModel> findAllByGrupoMuscularJpql(@Param("grupoMuscular") String grupoMuscular);
}
