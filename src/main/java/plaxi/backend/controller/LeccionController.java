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

// Esta es la clase del controlador para las operaciones relacionadas con las lecciones
@RestController
@RequestMapping("/api/leccion")
public class LeccionController {

    // Logger para el control de logs de la aplicación
    private static final Logger logger = LoggerFactory.getLogger(LeccionController.class);

    // Inyección de dependencia para el servicio de lecciones
    @Autowired
    private LeccionService leccionService;

    // Obtener todas las lecciones con paginación
    @GetMapping("/all")
    public ResponseEntity<Page<LeccionDto>> getAllLecciones(@RequestBody PaginadoDto paginadoDto) {
        logger.info("Solicitud para obtener todas las lecciones"); // Log de entrada para la solicitud
        try {
            // Llama al servicio para obtener las lecciones
            Page<LeccionDto> lecciones = leccionService.getAllLecciones(paginadoDto);
            logger.info("Lecciones obtenidas exitosamente"); // Log de éxito
            return ResponseEntity.ok(lecciones); // Devuelve las lecciones en la respuesta con status 200
        } catch (Exception e) {
            // En caso de error, se logea y se devuelve un error 500
            logger.error("Error al obtener las lecciones: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener una lección específica por su ID
    @GetMapping("/{idLeccion}")
    public ResponseEntity<LeccionDto> getLeccionById(@PathVariable Long idLeccion) {
        logger.info("Solicitud para obtener la leccion con ID: {}", idLeccion); // Log de entrada para la solicitud
        try {
            // Llama al servicio para obtener la lección
            LeccionDto leccion = leccionService.getLeccion(idLeccion);
            logger.info("Leccion obtenida exitosamente con ID: {}", idLeccion); // Log de éxito
            return ResponseEntity.ok(leccion); // Devuelve la lección encontrada con status 200
        } catch (Exception e) {
            // En caso de error, se logea y se devuelve un error 404
            logger.error("Error al obtener la leccion con ID: {} - {}", idLeccion, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obtener lecciones por curso con paginación y ordenamiento
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<Page<LeccionDto>> getLeccionesByCurso(
            @PathVariable Long cursoId,
            @RequestParam int page, // Página solicitada
            @RequestParam int size, // Tamaño de página
            @RequestParam String sortBy, // Campo por el que ordenar
            @RequestParam String sortDir) { // Dirección de orden (ascendente o descendente)
        logger.info("Solicitud para obtener lecciones del curso con ID: {}", cursoId); // Log de entrada

        // Crear un objeto DTO para la paginación y ordenamiento
        PaginadoDto paginadoDto = new PaginadoDto(page, size, sortBy, sortDir);

        try {
            // Llama al servicio para obtener las lecciones del curso con paginación
            Page<LeccionDto> lecciones = leccionService.getLeccionesByCurso(cursoId, paginadoDto);
            logger.info("Lecciones obtenidas exitosamente para el curso con ID: {}", cursoId); // Log de éxito
            return ResponseEntity.ok(lecciones); // Devuelve las lecciones encontradas con status 200
        } catch (Exception e) {
            // En caso de error, se logea y se devuelve un error 500
            logger.error("Error al obtener las lecciones del curso con ID: {} - {}", cursoId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Crear una nueva lección
    @PostMapping("/create")
    public ResponseEntity<String> createLeccion(@RequestBody LeccionDto leccionDto) {
        logger.info("Solicitud para crear una nueva leccion"); // Log de entrada para la solicitud
        try {
            // Llama al servicio para crear la lección
            leccionService.createLeccion(leccionDto);
            logger.info("Leccion creada exitosamente."); // Log de éxito
            return ResponseEntity.ok("Leccion creada exitosamente."); // Devuelve un mensaje de éxito con status 200
        } catch (Exception e) {
            // En caso de error, se logea y se devuelve un error 500
            logger.error("Error al crear la leccion: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una lección existente
    @PutMapping("/update")
    public ResponseEntity<String> updateLeccion(@RequestBody LeccionDto leccionDto) {
        logger.info("Solicitud para actualizar la leccion con ID: {}", leccionDto.getIdLeccion()); // Log de entrada
        try {
            // Llama al servicio para actualizar la lección
            leccionService.updateLeccion(leccionDto);
            logger.info("Leccion actualizada exitosamente."); // Log de éxito
            return ResponseEntity.ok("Leccion actualizada exitosamente."); // Devuelve un mensaje de éxito con status 200
        } catch (Exception e) {
            // En caso de error, se logea y se devuelve un error 500
            logger.error("Error al actualizar la leccion con ID: {} - {}", leccionDto.getIdLeccion(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una lección por ID
    @DeleteMapping("/delete/{idLeccion}")
    public ResponseEntity<String> deleteLeccion(@PathVariable Long idLeccion) {
        logger.info("Solicitud para eliminar la leccion con ID: {}", idLeccion); // Log de entrada para la solicitud
        try {
            // Llama al servicio para eliminar la lección
            leccionService.deleteLeccion(idLeccion);
            logger.info("Leccion eliminada exitosamente."); // Log de éxito
            return ResponseEntity.ok("Leccion eliminada exitosamente."); // Devuelve un mensaje de éxito con status 200
        } catch (Exception e) {
            // En caso de error, se logea y se devuelve un error 500
            logger.error("Error al eliminar la leccion con ID: {} - {}", idLeccion, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
