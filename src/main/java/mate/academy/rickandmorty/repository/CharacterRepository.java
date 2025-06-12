package repository;

import model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    List<CharacterEntity> findByNameContainingIgnoreCase(String namePart);
}
