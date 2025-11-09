package dev.carlosmz96.cvgen.cvgen_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Curriculum;
import dev.carlosmz96.cvgen.cvgen_api.models.mappers.CurriculumMapper;
import dev.carlosmz96.cvgen.cvgen_api.repositories.CurriculumRepository;
import dev.carlosmz96.cvgen.cvgen_api.security.models.entities.User;
import dev.carlosmz96.cvgen.cvgen_api.security.repositories.UserRepository;
import dev.carlosmz96.cvgen.cvgen_api.services.CurriculumService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository repository;
    private final CurriculumMapper mapper;
    private final PdfServiceImpl pdfService;
    private final CvTemplateServiceImpl templateService;
    private final UserRepository userRepository;

    @Override
    public List<CurriculumDTO> listByUserId(Long idUser) {
        List<Curriculum> curriculumList = repository.findByUserId(idUser);
        return curriculumList.stream().map(mapper::curriculumToCurriculumDTO).toList();
    }

    @Override
    public CurriculumDTO getById(Long id) {
        Optional<Curriculum> optCurriculum = repository.findById(id);
        if (optCurriculum.isPresent()) {
            return mapper.curriculumToCurriculumDTO(optCurriculum.get());
        } else {
            throw new EntityNotFoundException("El currículum con id " + id + " no existe.");
        }
    }

    @Override
    public CurriculumDTO create(CurriculumDTO dto) {
        Curriculum curriculum = mapper.curriculumDtoToCurriculum(dto);
        
        if (curriculum.getUser() != null && curriculum.getUser().getEmail() != null) {
            Optional<User> optUser = userRepository.findByEmail(curriculum.getUser().getEmail());
            if (optUser.isPresent()) {
                curriculum.setUser(optUser.get());
            }
        }

        curriculum = repository.save(curriculum);
        return mapper.curriculumToCurriculumDTO(curriculum);
    }

    @Override
    public CurriculumDTO update(CurriculumDTO dto, Long id) {
        boolean existe = repository.existsById(id);
        if (existe) {
            Curriculum curriculum = mapper.curriculumDtoToCurriculum(dto);
            curriculum = repository.save(curriculum);
            return mapper.curriculumToCurriculumDTO(curriculum);
        } else {
            throw new EntityNotFoundException("El currículum con id " + id + " no existe.");
        }
    }

    @Override
    public void delete(Long id) {
        boolean existe = repository.existsById(id);
        if (existe) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("El currículum con id " + id + " no existe.");
        }
    }

    @Override
    public byte[] generatePdf(CurriculumDTO dto) {
        String html = templateService.renderCurriculumHtml(dto);
        return pdfService.htmlToPdf(html);
    }

}
