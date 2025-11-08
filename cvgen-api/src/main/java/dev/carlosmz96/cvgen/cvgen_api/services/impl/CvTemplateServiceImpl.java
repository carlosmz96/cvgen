package dev.carlosmz96.cvgen.cvgen_api.services.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
import dev.carlosmz96.cvgen.cvgen_api.services.CvTemplateService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CvTemplateServiceImpl implements CvTemplateService {

    private final TemplateEngine templateEngine;

    @Override
    public String renderCurriculumHtml(CurriculumDTO dto) {
        Context ctx = new Context();
        ctx.setVariables(Map.of(
                "cv", dto));
        // "cv/template" = src/main/resources/templates/cv/template.html
        return templateEngine.process("cv/template", ctx);
    }

}
