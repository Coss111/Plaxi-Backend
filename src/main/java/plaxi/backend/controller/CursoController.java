package plaxi.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.ActualizarCursoDto;
import plaxi.backend.dto.CursoDto;
import plaxi.backend.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    // Logger para registrar eventos importantes del controlador
    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);

    // Inyección de dependencia del servicio de cursos
    @Autowired
    private CursoService cursoService;

    /**
     * Endpoint para obtener todos los cursos
     * @return Respuesta con la lista de todos los cursos
     */
    @GetMapping("/all")
    public ResponseEntity<List<CursoDto>> getAllCursos() {
        logger.info("Solicitud para obtener todos los cursos");
        try {
            // Llamada al servicio para obtener todos los cursos
            List<CursoDto> cursos = cursoService.getAllCursos();
            logger.info("Cursos obtenidos exitosamente, total: {}", cursos.size());
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            // En caso de error, se registra el error y se retorna un estado 500
            logger.error("Error al obtener los cursos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Endpoint para obtener un curso por su ID
     * @param idCurso ID del curso a obtener
     * @return Respuesta con el curso encontrado o Not Found si no existe
     */
    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoDto> getCursoById(@PathVariable Long idCurso) {
        logger.info("Solicitud para obtener el curso con ID: {}", idCurso);
        try {
            // Llamada al servicio para obtener el curso por su ID
            CursoDto curso = cursoService.getCurso(idCurso);
            logger.info("Curso obtenido exitosamente con ID: {}", idCurso);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            // Si el curso no es encontrado, se registra el error y se retorna un estado 404
            logger.error("Error al obtener el curso con ID: {} - {}", idCurso, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Endpoint para obtener todos los cursos creados por un usuario específico
     * @param usuarioId ID del usuario creador
     * @return Respuesta con los cursos encontrados para ese usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CursoDto>> getCursosByUsuario(@PathVariable Long usuarioId) {
        logger.info("Solicitud para obtener cursos del usuario con ID: {}", usuarioId);
        try {
            // Llamada al servicio para obtener cursos por usuario
            List<CursoDto> cursos = cursoService.getCursosByUsuario(usuarioId);
            logger.info("Cursos obtenidos exitosamente para el usuario con ID: {}", usuarioId);
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            // Si ocurre un error, se retorna un estado 404
            logger.error("Error al obtener cursos del usuario con ID: {} - {}", usuarioId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Endpoint para crear un nuevo curso
     * @param cursoDto Datos del nuevo curso
     * @return Respuesta con el curso creado y estado 201 (CREATED)
     */
    @PostMapping("/create")
    public ResponseEntity<ActualizarCursoDto> createCurso(@ModelAttribute ActualizarCursoDto cursoDto) {
        logger.info("Solicitud para crear un nuevo curso: {}", cursoDto.getNombre());
        try {
            // Llamada al servicio para crear el curso
            ActualizarCursoDto nuevoCurso = cursoService.createCurso(cursoDto);
            logger.info("Curso creado exitosamente con ID: {}", nuevoCurso.getIdCurso());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
        } catch (Exception e) {
            // Si ocurre un error, se retorna un estado 500 (INTERNAL SERVER ERROR)
            logger.error("Error al crear el curso: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Endpoint para actualizar un curso por su ID
     * @param idCurso ID del curso a actualizar
     * @param cursoDto Datos actualizados del curso
     * @return Respuesta con el curso actualizado o Not Found si no existe
     */
    @PutMapping("/{idCurso}")
    public ResponseEntity<ActualizarCursoDto> updateCurso(@PathVariable Long idCurso, @RequestBody ActualizarCursoDto cursoDto) {
        logger.info("Solicitud para actualizar el curso con ID: {}", idCurso);
        try {
            // Llamada al servicio para actualizar el curso
            ActualizarCursoDto cursoActualizado = cursoService.updateCurso(idCurso, cursoDto);
            logger.info("Curso actualizado exitosamente con ID: {}", idCurso);
            return ResponseEntity.ok(cursoActualizado);
        } catch (Exception e) {
            // Si ocurre un error, se retorna un estado 404
            logger.error("Error al actualizar el curso con ID: {} - {}", idCurso, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Endpoint para borrar lógicamente un curso
     * @param idCurso ID del curso a eliminar
     * @return Respuesta con estado 204 (No Content) si se eliminó correctamente
     */
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long idCurso) {
        logger.info("Solicitud para borrar lógicamente el curso con ID: {}", idCurso);
        try {
            // Llamada al servicio para borrar lógicamente el curso
            cursoService.deleteCurso(idCurso);
            logger.info("Curso borrado lógicamente exitosamente con ID: {}", idCurso);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // Si ocurre un error, se retorna un estado 404
            logger.error("Error al borrar lógicamente el curso con ID: {} - {}", idCurso, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<List<CursoDto>> getRecentCursos(@RequestParam(defaultValue = "10") int limit) {
        logger.info("Solicitud para obtener los {} cursos más recientes", limit);
        try {
            // Llamada al servicio para obtener los cursos recientes
            List<CursoDto> cursos = cursoService.getRecentCursos(limit);
            logger.info("Cursos recientes obtenidos exitosamente, total: {}", cursos.size());
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            logger.error("Error al obtener los cursos recientes: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/recomendaciones/{usuarioId}")
    public ResponseEntity<List<CursoDto>> getRecomendaciones(@PathVariable Long usuarioId) {
        try {
            List<CursoDto> recomendaciones = cursoService.getRecomendacionesPorUsuario(usuarioId);
            return ResponseEntity.ok(recomendaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
