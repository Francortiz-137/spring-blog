package franco.ortiz.blogspring.respository;

import franco.ortiz.blogspring.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<PostEntity, Long> {
}
