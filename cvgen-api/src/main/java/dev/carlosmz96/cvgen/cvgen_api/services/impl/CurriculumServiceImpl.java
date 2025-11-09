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
import jakarta.transaction.Transactional;
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
        Optional<Curriculum> optCurriculum = repository.findByIdWithUser(id);
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
    @Transactional
    public CurriculumDTO update(CurriculumDTO dto, Long id) {
        Curriculum curriculumBd = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El currículum con id " + id + " no existe."));
        Curriculum curriculum = mapper.curriculumDtoToCurriculum(dto);

        // Campos que NO deben tocarse en el update
        curriculum.setId(curriculumBd.getId());
        curriculum.setUser(curriculumBd.getUser());
        curriculum.setCreatedAt(curriculumBd.getCreatedAt());
        curriculum.setUpdatedAt(curriculumBd.getUpdatedAt());

        // Resto de relaciones
        if (curriculum.getExperiences() != null)
            curriculum.getExperiences().forEach(e -> e.setCurriculum(curriculum));
        if (curriculum.getEducations() != null)
            curriculum.getEducations().forEach(e -> e.setCurriculum(curriculum));
        if (curriculum.getSkills() != null)
            curriculum.getSkills().forEach(s -> s.setCurriculum(curriculum));
        if (curriculum.getCertifications() != null)
            curriculum.getCertifications().forEach(c -> c.setCurriculum(curriculum));
        if (curriculum.getLanguageSkills() != null)
            curriculum.getLanguageSkills().forEach(l -> l.setCurriculum(curriculum));

        Curriculum curriculumSaved = repository.save(curriculum);
        return mapper.curriculumToCurriculumDTO(curriculumSaved);
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
