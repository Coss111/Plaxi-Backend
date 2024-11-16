package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import plaxi.backend.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNombre(String nombre);

    Optional<Curso> findByIdCurso(Long idCurso);

    // Cambia este método para usar 'estado' en lugar de 'status'
    List<Curso> findAllByEstadoTrue();

    List<Curso> findByUsuarioCreador_IdUsuarioAndEstadoTrue(Long usuarioId);

    List<Curso> findAllByEstadoTrueOrderByFechaCreacionDesc();

    @Query("SELECT c FROM Curso c WHERE c.categoria.idCategoria IN :categorias " +
            "AND c.idCurso NOT IN (SELECT i.curso.idCurso FROM Inscripcion i WHERE i.usuario.idUsuario = :usuarioId AND i.estadoInscripcion = true) " +
            "AND c.estado = true")
    List<Curso> findRecommendedCursosByCategoriasAndUsuarioId(List<Long> categorias, Long usuarioId);

}
