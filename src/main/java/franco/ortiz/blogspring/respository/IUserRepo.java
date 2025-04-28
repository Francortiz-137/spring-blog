package franco.ortiz.blogspring.respository;

import franco.ortiz.blogspring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

}
