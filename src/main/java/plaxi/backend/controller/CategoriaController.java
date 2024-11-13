package plaxi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.CategoriaDto;
import plaxi.backend.entity.Categoria;
import plaxi.backend.service.CategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    // Inyección de dependencia para el servicio de categorías
    @Autowired
    private CategoriaService categoriaService;

    /**
     * Endpoint para crear una nueva categoría
     * @param categoriaDto Datos de la categoría a crear
     * @return Respuesta con la categoría creada
     */
    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody CategoriaDto categoriaDto) {
        // Llamada al servicio para crear la categoría
        CategoriaDto createdCategoria = categoriaService.createCategoria(categoriaDto);
        // Respuesta exitosa con la categoría creada
        return ResponseEntity.ok(createdCategoria);
    }

    /**
     * Endpoint para actualizar una categoría existente
     * @param id Identificador de la categoría a actualizar
     * @param categoriaDto Datos de la categoría actualizada
     * @return Respuesta con la categoría actualizada o Not Found si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(
            @PathVariable("id") Long id,
            @RequestBody CategoriaDto categoriaDto) {
        // Llamada al servicio para actualizar la categoría
        CategoriaDto updatedCategoria = categoriaService.updateCategoria(id, categoriaDto);
        
        // Si no se encuentra la categoría, retorna un estado 404
        if (updatedCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        // Respuesta exitosa con la categoría actualizada
        return ResponseEntity.ok(updatedCategoria);
    }

    /**
     * Endpoint para obtener todas las categorías
     * @return Respuesta con la lista de todas las categorías
     */
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        // Llamada al servicio para obtener todas las categorías
        List<Categoria> categorias = categoriaService.getAllCategorias();
        // Respuesta exitosa con la lista de categorías
        return ResponseEntity.ok(categorias);
    }

    /**
     * Endpoint para obtener una categoría por su ID
     * @param id Identificador de la categoría a obtener
     * @return Respuesta con la categoría encontrada o Not Found si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") Long id) {
        // Llamada al servicio para obtener la categoría por ID
        Optional<Categoria> categoria = categoriaService.getCategoriaById(id);
        // Si la categoría existe, retorna la categoría; si no, retorna un estado 404
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para eliminar una categoría por su ID
     * @param id Identificador de la categoría a eliminar
     * @return Respuesta con estado 204 si se eliminó correctamente, o Not Found si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable("id") Long id) {
        // Llamada al servicio para eliminar la categoría
        boolean deleted = categoriaService.deleteCategoria(id);
        // Si la categoría fue eliminada, retorna estado 204 (sin contenido)
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        // Si no se encontró la categoría, retorna un estado 404
        return ResponseEntity.notFound().build();
    }
}
