package plaxi.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeccionDto {
    private Long idLeccion;
    private String titulo;
    private int orden;
    private int duracionEstimada;
    private String contenido;
    private boolean estado;
    private Long cursoId;  // Solo el ID del curso al que pertenece la lección
}
