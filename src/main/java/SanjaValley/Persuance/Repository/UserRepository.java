package SanjaValley.Persuance.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SanjaValley.Persuance.Entity.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    // Optional<UserModel> findByAdministrador(String administrador);
    Optional<UserModel> findByUsername(String administrador);
    // Boolean existsByAdministrador(String administrador);
}
