package dev.carlosmz.cvgen.api.cvgenapi.services;

import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;

public interface CurriculumService {

    CurriculumDTO getCurriculum(Long id);

    CurriculumDTO createCurriculum(CurriculumDTO dto);

    CurriculumDTO updateCurriculum(CurriculumDTO dto);

    void deleteCurriculum(Long id);

}
