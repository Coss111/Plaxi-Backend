package plaxi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.InscripcionDto;
import plaxi.backend.dto.InscripcionResponseDto;
import plaxi.backend.service.InscripcionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<InscripcionResponseDto>> getAllInscripciones() {
        return ResponseEntity.ok(inscripcionService.getAllInscripciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionResponseDto> getInscripcionById(@PathVariable Long id) {
        Optional<InscripcionResponseDto> inscripcionOpt = inscripcionService.getInscripcionById(id);
        return inscripcionOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InscripcionResponseDto> createInscripcion(@RequestBody InscripcionDto inscripcionDto) {
        InscripcionResponseDto newInscripcion = inscripcionService.createInscripcion(inscripcionDto);
        return ResponseEntity.ok(newInscripcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionResponseDto> updateInscripcion(@PathVariable Long id, @RequestBody InscripcionDto inscripcionDto) {
        InscripcionResponseDto updatedInscripcion = inscripcionService.updateInscripcion(id, inscripcionDto);
        if (updatedInscripcion != null) {
            return ResponseEntity.ok(updatedInscripcion);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        boolean isDeleted = inscripcionService.deleteInscripcion(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<InscripcionResponseDto>> getInscripcionesByUsuarioId(@PathVariable Long usuarioId) {
        List<InscripcionResponseDto> inscripciones = inscripcionService.getInscripcionesByUsuarioId(usuarioId);
        return ResponseEntity.ok(inscripciones);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<InscripcionResponseDto>> getInscripcionesByCursoId(@PathVariable Long cursoId) {
        List<InscripcionResponseDto> inscripciones = inscripcionService.getInscripcionesByCursoId(cursoId);
        return ResponseEntity.ok(inscripciones);
    }

}
