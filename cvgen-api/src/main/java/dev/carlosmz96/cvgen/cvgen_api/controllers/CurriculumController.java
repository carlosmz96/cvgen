package dev.carlosmz96.cvgen.cvgen_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
import dev.carlosmz96.cvgen.cvgen_api.services.CurriculumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/curriculums")
@RequiredArgsConstructor
public class CurriculumController {

    private final CurriculumService service;

    @GetMapping("/listarPorUsuario/{userId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long userId) {
        List<CurriculumDTO> curriculums = service.listByUserId(userId);
        return ResponseEntity.ok(curriculums);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCurriculum(@RequestBody CurriculumDTO dto) {
        CurriculumDTO createdDto = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCurriculum(@RequestBody CurriculumDTO dto, @PathVariable Long id) {
        CurriculumDTO modifiedDto = service.update(dto, id);
        return ResponseEntity.ok(modifiedDto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCurriculum(@PathVariable Long id) {
        service.delete(id);
    }

}
