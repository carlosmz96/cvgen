package dev.carlosmz.cvgen.api.cvgenapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;

public interface CurriculumService {

    Page<CurriculumDTO> findAll(Pageable pageable);

    CurriculumDTO getCurriculum(Long id);

    CurriculumDTO createCurriculum(CurriculumDTO dto);

    CurriculumDTO updateCurriculum(CurriculumDTO dto);

    void deleteCurriculum(Long id);

}
