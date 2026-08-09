package br.com.guto.spring_boot_security.database.repository;

import br.com.guto.spring_boot_security.database.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesRepository extends JpaRepository<RoleModel, Integer> {
    Optional<RoleModel> findByNome(String nome);
}
