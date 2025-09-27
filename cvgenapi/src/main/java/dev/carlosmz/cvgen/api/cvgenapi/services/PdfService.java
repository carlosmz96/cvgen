package dev.carlosmz.cvgen.api.cvgenapi.services;

import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;

public interface PdfService {
    
    byte[] generateCvPdf(CurriculumDTO curriculum, String templateName) throws Exception;

}
