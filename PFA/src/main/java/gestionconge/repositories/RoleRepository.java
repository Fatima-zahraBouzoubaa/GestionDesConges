package gestionconge.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import gestionconge.dao.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	List<Role> findAll();
}
