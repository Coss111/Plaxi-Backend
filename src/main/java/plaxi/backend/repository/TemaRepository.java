package plaxi.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import plaxi.backend.dto.TemaDto;
import plaxi.backend.entity.Tema;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long>{

    @Query("SELECT t FROM Tema t WHERE t.leccion.idLeccion = :leccionId")
    List<Tema> findAllByLeccionIdLeccion(Long leccionId);

    @Query("SELECT " +
            "new plaxi.backend.dto.TemaDto(t.idTema, " +
            "t.titulo, " +
            "t.orden, " +
            "t.descripcion, " +
            "t.recursoMultimedia.url, " +
            "t.estado, " +
            "t.leccion.idLeccion) FROM Tema t " +
            "WHERE t.leccion.idLeccion = :leccionId " +
            "AND t.estado = true ")
    Page<TemaDto> findAllByLeccionIdLeccion(Long leccionId, Pageable pageable);

    @Query("SELECT " +
            "new plaxi.backend.dto.TemaDto(t.idTema, " +
            "t.titulo, " +
            "t.orden, " +
            "t.descripcion, " +
            "t.recursoMultimedia.url, " +
            "t.estado, " +
            "t.leccion.idLeccion) FROM Tema t " +
            "WHERE t.estado = true ")
    Page<TemaDto> findAllPaginado(Pageable pageable);

}
