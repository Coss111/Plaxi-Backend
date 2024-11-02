package plaxi.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import plaxi.backend.dto.LeccionDto;
import plaxi.backend.dto.PaginadoDto;
import plaxi.backend.entity.Leccion;
import plaxi.backend.entity.Tema;

import java.util.List;

public interface LeccionRepository extends JpaRepository<Leccion, Long>{


    @Query("SELECT " +
            "new plaxi.backend.dto.LeccionDto(l.idLeccion, " +
            "l.titulo, " +
            "l.orden, " +
            "l.duracionEstimada, " +
            "l.contenido," +
            "l.estado, " +
            "l.curso.idCurso) " +
            "FROM Leccion l " +
            "WHERE l.estado = true ")
    Page<LeccionDto> findAllPaginado(Pageable pageable);

    @Query("SELECT " +
            "new plaxi.backend.dto.LeccionDto(l.idLeccion, " +
            "l.titulo, " +
            "l.orden, " +
            "l.duracionEstimada, " +
            "l.contenido," +
            "l.estado, " +
            "l.curso.idCurso) " +
            "FROM Leccion l " +
            "WHERE l.curso.idCurso = :cursoId " +
            "AND l.estado = true ")
    Page<LeccionDto> findAllByCursoIdCurso(Long cursoId, Pageable pageable);

    @Query("SELECT t.leccion FROM Tema t WHERE t.idTema = :idTema")
    List<Leccion> findAllByTemaIdTema(Long idTema);

    
}
