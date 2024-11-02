package plaxi.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import plaxi.backend.dto.LeccionDto;
import plaxi.backend.dto.PaginadoDto;
import plaxi.backend.service.LeccionService;

import java.util.List;

@RestController
@RequestMapping("/api/leccion")
public class LeccionController {

    private static final Logger logger = LoggerFactory.getLogger(LeccionController.class);

    @Autowired
    private LeccionService leccionService;

    // Obtener todas las lecciones
    @GetMapping("/all")
    public ResponseEntity<Page<LeccionDto>> getAllLecciones(@RequestBody PaginadoDto paginadoDto) {
        logger.info("Solicitud para obtener todas las lecciones");
        try {
            Page<LeccionDto> lecciones = leccionService.getAllLecciones(paginadoDto);
            logger.info("Lecciones obtenidas exitosamente");
            return ResponseEntity.ok(lecciones);
        } catch (Exception e) {
            logger.error("Error al obtener las lecciones: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener leccion por ID
    @GetMapping("/{idLeccion}")
    public ResponseEntity<LeccionDto> getLeccionById(@PathVariable Long idLeccion) {
        logger.info("Solicitud para obtener la leccion con ID: {}", idLeccion);
        try {
            LeccionDto leccion = leccionService.getLeccion(idLeccion);
            logger.info("Leccion obtenida exitosamente con ID: {}", idLeccion);
            return ResponseEntity.ok(leccion);
        } catch (Exception e) {
            logger.error("Error al obtener la leccion con ID: {} - {}", idLeccion, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obtener lecciones por curso
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<Page<LeccionDto>> getLeccionesByCurso(@PathVariable Long cursoId, @RequestBody PaginadoDto paginadoDto) {
        logger.info("Solicitud para obtener lecciones del curso con ID: {}", cursoId);
        try {
            Page<LeccionDto> lecciones = leccionService.getLeccionesByCurso(cursoId, paginadoDto);
            logger.info("Lecciones obtenidas exitosamente para el curso con ID: {}", cursoId);
            return ResponseEntity.ok(lecciones);
        } catch (Exception e) {
            logger.error("Error al obtener las lecciones del curso con ID: {} - {}", cursoId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Crear leccion
    @PostMapping("/create")
    public ResponseEntity<String> createLeccion(@RequestBody LeccionDto leccionDto) {
        logger.info("Solicitud para crear una nueva leccion");
        try {
            leccionService.createLeccion(leccionDto);
            logger.info("Leccion creada exitosamente.");
            return ResponseEntity.ok("Leccion creada exitosamente.");

        } catch (Exception e) {
            logger.error("Error al crear la leccion: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar leccion
    @PutMapping("/update")
    public ResponseEntity<String> updateLeccion(@RequestBody LeccionDto leccionDto) {
        logger.info("Solicitud para actualizar la leccion con ID: {}", leccionDto.getIdLeccion());
        try {
            leccionService.updateLeccion(leccionDto);
            logger.info("Leccion actualizada exitosamente.");
            return ResponseEntity.ok("Leccion actualizada exitosamente.");
        } catch (Exception e) {
            logger.error("Error al actualizar la leccion con ID: {} - {}", leccionDto.getIdLeccion(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar leccion
    @DeleteMapping("/delete/{idLeccion}")
    public ResponseEntity<String> deleteLeccion(@PathVariable Long idLeccion) {
        logger.info("Solicitud para eliminar la leccion con ID: {}", idLeccion);
        try {
            leccionService.deleteLeccion(idLeccion);
            logger.info("Leccion eliminada exitosamente.");
            return ResponseEntity.ok("Leccion eliminada exitosamente.");
        } catch (Exception e) {
            logger.error("Error al eliminar la leccion con ID: {} - {}", idLeccion, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
