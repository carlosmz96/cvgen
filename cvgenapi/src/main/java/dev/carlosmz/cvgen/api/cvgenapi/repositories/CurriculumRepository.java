package dev.carlosmz.cvgen.api.cvgenapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

}
