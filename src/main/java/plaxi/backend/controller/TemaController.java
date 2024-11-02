package plaxi.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import plaxi.backend.dto.PaginadoDto;
import plaxi.backend.dto.TemaDto;
import plaxi.backend.service.TemaService;

@RestController
@RequestMapping("/api/tema")
public class TemaController {

    private static final Logger logger = LoggerFactory.getLogger(TemaController.class);

    @Autowired
    private TemaService temaService;

    // Obtener todos los temas
    @GetMapping("/all")
    public ResponseEntity<Page<TemaDto>> getAllTemas(@RequestBody PaginadoDto paginadoDto) {
        logger.info("Solicitud para obtener todos los temas");
        try {
            Page<TemaDto> temas = temaService.getAllTemas(paginadoDto);
            logger.info("Temas obtenidos exitosamente");
            return ResponseEntity.ok(temas);
        } catch (Exception e) {
            logger.error("Error al obtener los temas: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener tema por ID
    @GetMapping("/{idTema}")
    public ResponseEntity<TemaDto> getTemaById(@PathVariable Long idTema) {
        logger.info("Solicitud para obtener el tema con ID: {}", idTema);
        try {
            TemaDto tema = temaService.getTema(idTema);
            logger.info("Tema obtenido exitosamente con ID: {}", idTema);
            return ResponseEntity.ok(tema);
        } catch (Exception e) {
            logger.error("Error al obtener el tema con ID: {} - {}", idTema, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obtener temas por lección
    @GetMapping("/leccion/{leccionId}")
    public ResponseEntity<Page<TemaDto>> getTemasByLeccion(@RequestBody PaginadoDto paginadoDto, @PathVariable Long leccionId) {
        logger.info("Solicitud para obtener temas de la lección con ID: {}", leccionId);
        try {
            Page<TemaDto> temas = temaService.getTemasByLeccion(leccionId, paginadoDto);
            logger.info("Temas de la lección con ID: {} obtenidos exitosamente", leccionId);
            return ResponseEntity.ok(temas);
        } catch (Exception e) {
            logger.error("Error al obtener los temas de la lección con ID: {} - {}", leccionId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Crear un tema
    @PostMapping("/create")
    public ResponseEntity<String> createTema(
            @RequestParam("titulo") String titulo,
            @RequestParam("orden") int orden,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("leccionId") Long leccionId,
            @RequestParam("file") MultipartFile file) {

        logger.info("Solicitud para crear un tema");
        TemaDto temaDto = new TemaDto();
        temaDto.setTitulo(titulo);
        temaDto.setOrden(orden);
        temaDto.setDescripcion(descripcion);
        temaDto.setLeccionId(leccionId);

        try {
            temaService.createTema(temaDto, file);
            logger.info("Tema creado exitosamente");
            return ResponseEntity.ok("Tema creado exitosamente");
        } catch (Exception e) {
            logger.error("Error al crear el tema: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el tema");
        }
    }

    // Actualizar un tema
    @PutMapping("/update")
    public ResponseEntity<String> updateTema(
            @RequestParam("idTema") Long idTema,
            @RequestParam("titulo") String titulo,
            @RequestParam("orden") int orden,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("leccionId") Long leccionId,
            @RequestParam("file") MultipartFile file) {

        logger.info("Solicitud para actualizar un tema");
        TemaDto temaDto = new TemaDto();
        temaDto.setIdTema(idTema);
        temaDto.setTitulo(titulo);
        temaDto.setOrden(orden);
        temaDto.setDescripcion(descripcion);
        temaDto.setLeccionId(leccionId);

        try {
            temaService.updateTema(temaDto, file);
            logger.info("Tema actualizado exitosamente");
            return ResponseEntity.ok("Tema actualizado exitosamente");
        } catch (Exception e) {
            logger.error("Error al actualizar el tema: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el tema");
        }
    }

    // Borrar un tema
    @DeleteMapping("/delete/{idTema}")
    public ResponseEntity<String> deleteTema(@PathVariable Long idTema) {
        logger.info("Solicitud para borrar un tema con ID: {}", idTema);
        try {
            temaService.deleteTema(idTema);
            logger.info("Tema borrado exitosamente con ID: {}", idTema);
            return ResponseEntity.ok("Tema borrado exitosamente");
        } catch (Exception e) {
            logger.error("Error al borrar el tema con ID: {} - {}", idTema, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al borrar el tema");
        }
    }
}
