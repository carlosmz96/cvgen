package dev.carlosmz96.cvgen.cvgen_api.services;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;

public interface CvTemplateService {

    public String renderCurriculumHtml(CurriculumDTO dto);

}
