package dev.carlosmz96.cvgen.cvgen_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlosmz96.cvgen.cvgen_api.models.entities.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    List<Curriculum> findByUserId(Long userId);
    
}
