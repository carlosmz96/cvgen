package dev.carlosmz.cvgen.api.cvgenapi.services.impl;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Curriculum;
import dev.carlosmz.cvgen.api.cvgenapi.models.mappers.CurriculumMapper;
import dev.carlosmz.cvgen.api.cvgenapi.repositories.CurriculumRepository;
import dev.carlosmz.cvgen.api.cvgenapi.services.CurriculumService;

@Service
public class CurriculumServiceImpl implements CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Override
    public Page<CurriculumDTO> findAll(Pageable pageable) {
        Page<Curriculum> pageCurriculum = curriculumRepository.findAll(pageable);
        return pageCurriculum.map(curriculumMapper::toDto);
    }

    @Override
    public Page<CurriculumDTO> findAllByUserId(Pageable pageable, Long userId) {
        Page<Curriculum> pageCurriculum = curriculumRepository.findByUserId(pageable, userId);
        return pageCurriculum.map(curriculumMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CurriculumDTO getCurriculum(Long id) {
        return curriculumMapper.toDto(curriculumRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Currículum con id=" + id + " no encontrado")));
    }

    @Override
    @Transactional
    public CurriculumDTO createCurriculum(CurriculumDTO dto) {
        return curriculumMapper.toDto(curriculumRepository.save(curriculumMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public CurriculumDTO updateCurriculum(CurriculumDTO dto) {
        Optional<Curriculum> optCurriculum = curriculumRepository.findById(dto.getId());
        if (optCurriculum.isPresent()) {
            Curriculum curriculum = curriculumMapper.toEntity(dto);
            curriculum.setUpdatedAt(Instant.now());
            return curriculumMapper.toDto(curriculumRepository.save(curriculum));
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public void deleteCurriculum(Long id) {
        if (curriculumRepository.existsById(id)) {
            curriculumRepository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

}
