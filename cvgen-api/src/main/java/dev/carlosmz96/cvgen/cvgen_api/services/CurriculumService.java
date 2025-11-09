package dev.carlosmz96.cvgen.cvgen_api.services;

import java.util.List;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;

public interface CurriculumService {

    public List<CurriculumDTO> listByUserId(Long userId);
    public CurriculumDTO getById(Long id);
    public CurriculumDTO create(CurriculumDTO dto);
    public CurriculumDTO update(CurriculumDTO dto, Long id);
    public void delete(Long id);

    public byte[] generatePdf(CurriculumDTO dto);
    
}
