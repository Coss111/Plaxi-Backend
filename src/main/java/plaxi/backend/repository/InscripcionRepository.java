package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import plaxi.backend.entity.Inscripcion;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByUsuario_IdUsuario(Long usuarioId);

    List<Inscripcion> findByCurso_IdCurso(Long cursoId);

    long countByCurso_IdCursoAndEstadoInscripcionTrue(Long cursoId);

    @Query("SELECT i.curso.idCurso AS cursoId, COUNT(i) AS totalInscripciones " +
            "FROM Inscripcion i WHERE i.estadoInscripcion = true " +
            "GROUP BY i.curso.idCurso " +
            "ORDER BY totalInscripciones DESC")
    List<Object[]> findCursosPopulares();

    @Query("SELECT DISTINCT i.curso.categoria.idCategoria " +
            "FROM Inscripcion i " +
            "WHERE i.usuario.idUsuario = :usuarioId AND i.estadoInscripcion = true")
    List<Long> findDistinctCategoriasByUsuarioId(Long usuarioId);
}