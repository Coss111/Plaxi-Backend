package plaxi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.CursoDto;
import plaxi.backend.dto.InscripcionDto;
import plaxi.backend.dto.InscripcionResponseDto;
import plaxi.backend.service.CursoService;
import plaxi.backend.service.InscripcionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private CursoService cursoService;

    // Método para manejar las solicitudes GET a la ruta "/api/inscripciones" y obtener todas las inscripciones.
    @GetMapping
    public ResponseEntity<List<InscripcionResponseDto>> getAllInscripciones() {
        // Llama al servicio para obtener todas las inscripciones y retorna una respuesta HTTP con el listado.
        return ResponseEntity.ok(inscripcionService.getAllInscripciones());
    }

    // Método para manejar las solicitudes GET a la ruta "/api/inscripciones/{id}" y obtener una inscripción por su ID.
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionResponseDto> getInscripcionById(@PathVariable Long id) {
        // Intenta obtener la inscripción por su ID desde el servicio.
        Optional<InscripcionResponseDto> inscripcionOpt = inscripcionService.getInscripcionById(id);
        // Si la inscripción existe, retorna una respuesta OK con los datos; de lo contrario, retorna una respuesta NOT_FOUND.
        return inscripcionOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para manejar las solicitudes POST a la ruta "/api/inscripciones" y crear una nueva inscripción.
    @PostMapping
    public ResponseEntity<InscripcionResponseDto> createInscripcion(@RequestBody InscripcionDto inscripcionDto) {
        // Llama al servicio para crear una nueva inscripción con los datos proporcionados.
        InscripcionResponseDto newInscripcion = inscripcionService.createInscripcion(inscripcionDto);
        // Retorna una respuesta OK con los datos de la nueva inscripción creada.
        return ResponseEntity.ok(newInscripcion);
    }

    // Método para manejar las solicitudes PUT a la ruta "/api/inscripciones/{id}" y actualizar una inscripción existente.
    @PutMapping("/{id}")
    public ResponseEntity<InscripcionResponseDto> updateInscripcion(@PathVariable Long id, @RequestBody InscripcionDto inscripcionDto) {
        // Llama al servicio para actualizar la inscripción con el ID proporcionado.
        InscripcionResponseDto updatedInscripcion = inscripcionService.updateInscripcion(id, inscripcionDto);
        // Si la inscripción fue actualizada con éxito, retorna una respuesta OK con los nuevos datos; de lo contrario, retorna NOT_FOUND.
        if (updatedInscripcion != null) {
            return ResponseEntity.ok(updatedInscripcion);
        }
        return ResponseEntity.notFound().build();
    }

    // Método para manejar las solicitudes DELETE a la ruta "/api/inscripciones/{id}" y eliminar una inscripción por su ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        // Llama al servicio para eliminar la inscripción con el ID proporcionado.
        boolean isDeleted = inscripcionService.deleteInscripcion(id);
        // Si la inscripción fue eliminada correctamente, retorna una respuesta OK (sin cuerpo); si no, retorna NOT_FOUND.
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Método para manejar las solicitudes GET a la ruta "/api/inscripciones/usuario/{usuarioId}" y obtener las inscripciones de un usuario por su ID.
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<InscripcionResponseDto>> getInscripcionesByUsuarioId(@PathVariable Long usuarioId) {
        // Llama al servicio para obtener las inscripciones asociadas al usuario con el ID proporcionado.
        List<InscripcionResponseDto> inscripciones = inscripcionService.getInscripcionesByUsuarioId(usuarioId);
        // Retorna una respuesta OK con las inscripciones encontradas.
        return ResponseEntity.ok(inscripciones);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<InscripcionResponseDto>> getInscripcionesByCursoId(@PathVariable Long cursoId) {
        List<InscripcionResponseDto> inscripciones = inscripcionService.getInscripcionesByCursoId(cursoId);
        return ResponseEntity.ok(inscripciones);
    }

    @GetMapping("/curso/{cursoId}/count")
    public ResponseEntity<Long> countInscritosByCurso(@PathVariable Long cursoId) {
        try {
            long count = inscripcionService.countInscritosByCurso(cursoId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/populares")
    public ResponseEntity<List<CursoDto>> getCursosPopulares(@RequestParam(defaultValue = "10") int limit) {
        try {
            List<CursoDto> cursosPopulares = inscripcionService.getCursosPopulares(limit);
            return ResponseEntity.ok(cursosPopulares);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
