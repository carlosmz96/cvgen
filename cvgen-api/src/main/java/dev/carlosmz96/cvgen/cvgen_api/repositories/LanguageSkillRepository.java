package dev.carlosmz96.cvgen.cvgen_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlosmz96.cvgen.cvgen_api.models.entities.LanguageSkill;

public interface LanguageSkillRepository extends JpaRepository<LanguageSkill, Long> {
    
}
