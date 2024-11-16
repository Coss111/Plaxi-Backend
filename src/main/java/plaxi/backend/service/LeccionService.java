package plaxi.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import plaxi.backend.dto.LeccionDto;
import plaxi.backend.dto.PaginadoDto;
import plaxi.backend.entity.Leccion;
import plaxi.backend.repository.CursoRepository;
import plaxi.backend.repository.LeccionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeccionService {

    @Autowired
    private LeccionRepository leccionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Obtener todas las lecciones ***
    public Page<LeccionDto> getAllLecciones(PaginadoDto paginadoDto) {
        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);
        return leccionRepository.findAllPaginado(page);
    }

    // Obtener una leccion por su ID
    public LeccionDto getLeccion(Long idLeccion) throws Exception {
        return leccionRepository.findById(idLeccion)
                .map(leccion -> new LeccionDto(
                        leccion.getIdLeccion(),
                        leccion.getTitulo(),
                        leccion.getOrden(),
                        leccion.getDuracionEstimada(),
                        leccion.getContenido(),
                        leccion.isEstado(),
                        leccion.getCurso() != null ? leccion.getCurso().getIdCurso() : null  // ID del curso
                )).orElseThrow(() -> new Exception("Leccion no encontrada"));
    }

    // Obtener lecciones por curso ***
    public Page<LeccionDto> getLeccionesByCurso(Long cursoId, PaginadoDto paginadoDto) {
        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);
        return leccionRepository.findAllByCursoIdCurso(cursoId, page);
    }

    // Crear una leccion
    public void createLeccion(LeccionDto leccionDto) {
        Leccion leccion = new Leccion();
        leccion.setTitulo(leccionDto.getTitulo());
        leccion.setOrden(leccionDto.getOrden());
        leccion.setDuracionEstimada(leccionDto.getDuracionEstimada());
        leccion.setContenido(leccionDto.getContenido());
        leccion.setCurso(cursoRepository.findById(leccionDto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));
        leccion.setEstado(leccionDto.isEstado());
        leccionRepository.save(leccion);
    }

    // Actualizar una leccion
    public void updateLeccion(LeccionDto leccionDto) {
        Leccion leccion = leccionRepository.findById(leccionDto.getIdLeccion())
                .orElseThrow(() -> new RuntimeException("Leccion no encontrada"));
        leccion.setTitulo(leccionDto.getTitulo());
        leccion.setOrden(leccionDto.getOrden());
        leccion.setDuracionEstimada(leccionDto.getDuracionEstimada());
        leccion.setContenido(leccionDto.getContenido());
        leccion.setCurso(cursoRepository.findById(leccionDto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));
        leccion.setEstado(leccionDto.isEstado());
        leccionRepository.save(leccion);
    }

    // Eliminar una leccion
    public void deleteLeccion(Long idLeccion) {
        // borrado logico
        Leccion leccion = leccionRepository.findById(idLeccion)
                .orElseThrow(() -> new RuntimeException("Leccion no encontrada"));
        leccion.setEstado(false);
        leccionRepository.save(leccion);
    }
}
