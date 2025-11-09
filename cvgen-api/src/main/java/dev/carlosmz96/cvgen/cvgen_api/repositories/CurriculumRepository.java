package dev.carlosmz96.cvgen.cvgen_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.carlosmz96.cvgen.cvgen_api.models.entities.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    @Query("select c from Curriculum c join fetch c.user where c.id = :id")
    Optional<Curriculum> findByIdWithUser(@Param("id") Long id);

    List<Curriculum> findByUserId(Long userId);

}
