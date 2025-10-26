package dev.carlosmz96.cvgen.cvgen_api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Curriculum;
import dev.carlosmz96.cvgen.cvgen_api.models.mappers.CurriculumMapper;
import dev.carlosmz96.cvgen.cvgen_api.repositories.CurriculumRepository;
import dev.carlosmz96.cvgen.cvgen_api.services.CurriculumService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository repository;
    private final CurriculumMapper mapper;

    @Override
    public List<CurriculumDTO> listByUserId(Long idUser) {
        List<Curriculum> curriculumList = repository.findByUserId(idUser);
        return curriculumList.stream().map(mapper::curriculumToCurriculumDTO).toList();
    }

    @Override
    public CurriculumDTO create(CurriculumDTO dto) {
        Curriculum curriculum = mapper.curriculumDtoToCurriculum(dto);
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

}
