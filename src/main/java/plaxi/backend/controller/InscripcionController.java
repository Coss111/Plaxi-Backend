package plaxi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para inyectar dependencias automáticamente.
import org.springframework.http.ResponseEntity; // Importa ResponseEntity para manejar las respuestas HTTP.
import org.springframework.web.bind.annotation.*; // Importa las anotaciones necesarias para manejar las solicitudes HTTP en un controlador REST.
import plaxi.backend.dto.InscripcionDto; // Importa el DTO que representa los datos necesarios para la inscripción.
import plaxi.backend.dto.InscripcionResponseDto; // Importa el DTO que representa la respuesta de inscripción.
import plaxi.backend.service.InscripcionService; // Importa el servicio encargado de la lógica de negocio de inscripciones.

import java.util.List; // Importa la clase List, que se utilizará para manejar colecciones de inscripciones.
import java.util.Optional; // Importa la clase Optional, que se utiliza para manejar valores que podrían ser nulos.

@RestController // Indica que esta clase es un controlador REST.
@RequestMapping("/api/inscripciones") // Define la ruta base para todas las solicitudes de este controlador (en este caso, "/api/inscripciones").
public class InscripcionController {

    @Autowired // Inyecta el servicio InscripcionService para su uso en este controlador.
    private InscripcionService inscripcionService;

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

}
