package dev.carlosmz.cvgen.api.cvgenapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.PageDTO;
import dev.carlosmz.cvgen.api.cvgenapi.services.CurriculumService;
import dev.carlosmz.cvgen.api.cvgenapi.services.PdfService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/curriculums", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public Page<CurriculumDTO> list(Pageable pageable) {
        return curriculumService.findAll(pageable);
    }

    @GetMapping("/user/{userId}")    
    public PageDTO<CurriculumDTO> listByUserId(Pageable pageable, @PathVariable Long userId) {
        return PageDTO.from(curriculumService.findAllByUserId(pageable, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurriculumDTO> getCurriculum(@PathVariable Long id) {
        CurriculumDTO dto = curriculumService.getCurriculum(id);
        if (dto != null) {
            return ResponseEntity.ok(curriculumService.getCurriculum(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurriculumDTO> createCurriculum(@Valid @RequestBody CurriculumDTO dto) {
        log.info("[IN] DTO -> fullName='{}' | email='{}' | city='{}'",
      dto.getFullName(), dto.getEmail(), dto.getLocationCity());
        return ResponseEntity.status(HttpStatus.CREATED).body(curriculumService.createCurriculum(dto));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurriculumDTO> updateCurriculum(@Valid @RequestBody CurriculumDTO dto,
            @PathVariable Long id) {
        if (dto.getId() != null && !dto.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        CurriculumDTO dtoDatabase = curriculumService.getCurriculum(id);
        if (dtoDatabase != null) {
            dto.setId(id);
            return ResponseEntity.ok().body(curriculumService.updateCurriculum(dto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurriculum(@PathVariable Long id) {
        CurriculumDTO dto = curriculumService.getCurriculum(id);
        if (dto != null) {
            curriculumService.deleteCurriculum(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id,
            @RequestParam(defaultValue = "classic") String template) throws Exception {
        CurriculumDTO curriculumDTO = curriculumService.getCurriculum(id);
        byte[] pdfBytes = pdfService.generateCvPdf(curriculumDTO, template);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
