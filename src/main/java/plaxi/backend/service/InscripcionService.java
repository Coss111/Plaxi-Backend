package plaxi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plaxi.backend.dto.CursoDto;
import plaxi.backend.dto.InscripcionDto;

import plaxi.backend.dto.InscripcionResponseDto;
import plaxi.backend.entity.Curso;
import plaxi.backend.entity.Inscripcion;
import plaxi.backend.entity.Usuario;
import plaxi.backend.repository.CursoRepository;
import plaxi.backend.repository.InscripcionRepository;
import plaxi.backend.repository.UsuarioRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<InscripcionResponseDto> getAllInscripciones() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        List<InscripcionResponseDto> responseList = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            responseList.add(convertToDto(inscripcion));
        }
        return responseList;
    }

    public Optional<InscripcionResponseDto> getInscripcionById(Long id) {
        Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(id);
        return inscripcionOpt.map(this::convertToDto);
    }

    public InscripcionResponseDto createInscripcion(InscripcionDto inscripcionDto) {
        Inscripcion inscripcion = new Inscripcion();

        // Buscar el usuario y curso correspondientes
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(inscripcionDto.getUsuarioId());
        Optional<Curso> cursoOpt = cursoRepository.findById(inscripcionDto.getCursoId());

        if (usuarioOpt.isPresent() && cursoOpt.isPresent()) {
            inscripcion.setUsuario(usuarioOpt.get());
            inscripcion.setCurso(cursoOpt.get());
        } else {
            throw new IllegalArgumentException("Usuario o Curso no encontrado");
        }

        // Guardar la inscripción con fecha y estado por defecto
        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);
        return convertToDto(savedInscripcion);
    }

    public InscripcionResponseDto updateInscripcion(Long id, InscripcionDto inscripcionDto) {
        Optional<Inscripcion> optionalInscripcion = inscripcionRepository.findById(id);

        if (optionalInscripcion.isPresent()) {
            Inscripcion inscripcion = optionalInscripcion.get();
            // Actualizar solo las relaciones, no la fecha ni el estado
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(inscripcionDto.getUsuarioId());
            Optional<Curso> cursoOpt = cursoRepository.findById(inscripcionDto.getCursoId());

            if (usuarioOpt.isPresent() && cursoOpt.isPresent()) {
                inscripcion.setUsuario(usuarioOpt.get());
                inscripcion.setCurso(cursoOpt.get());
                Inscripcion updatedInscripcion = inscripcionRepository.save(inscripcion);
                return convertToDto(updatedInscripcion);
            } else {
                throw new IllegalArgumentException("Usuario o Curso no encontrado");
            }
        }
        return null;
    }

    public boolean deleteInscripcion(Long id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private InscripcionResponseDto convertToDto(Inscripcion inscripcion) {
        Long usuarioCreadorId = inscripcion.getCurso().getUsuarioCreador() != null ? inscripcion.getCurso().getUsuarioCreador().getIdUsuario() : null;
        String cursoDescripcion = inscripcion.getCurso().getDescripcion();
        String cursoPortadaUrl = inscripcion.getCurso().getPortada() != null ? inscripcion.getCurso().getPortada().getUrl() : null;

        return new InscripcionResponseDto(
                inscripcion.getIdInscripcion(),
                dateFormat.format(inscripcion.getFechaInscripcion()),
                inscripcion.getEstadoInscripcion(),
                inscripcion.getUsuario().getIdUsuario(),
                inscripcion.getUsuario().getUsername(),
                inscripcion.getUsuario().getGmail(),
                inscripcion.getCurso().getIdCurso(),
                inscripcion.getCurso().getNombre(),
                usuarioCreadorId,
                cursoDescripcion,
                cursoPortadaUrl
        );
    }

    public List<InscripcionResponseDto> getInscripcionesByUsuarioId(Long usuarioId) {
        // Obtener todas las inscripciones del repositorio
        List<Inscripcion> inscripciones = inscripcionRepository.findByUsuario_IdUsuario(usuarioId);
        List<InscripcionResponseDto> responseList = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            responseList.add(convertToDto(inscripcion));
        }
        return responseList;
    }

    public List<InscripcionResponseDto> getInscripcionesByCursoId(Long cursoId) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByCurso_IdCurso(cursoId);
        List<InscripcionResponseDto> responseList = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            responseList.add(convertToDto(inscripcion));
        }
        return responseList;
    }

    public long countInscritosByCurso(Long cursoId) {
        return inscripcionRepository.countByCurso_IdCursoAndEstadoInscripcionTrue(cursoId);
    }

    // Método para obtener los cursos más populares
    public List<CursoDto> getCursosPopulares(int limit) {
        List<Object[]> resultados = inscripcionRepository.findCursosPopulares();
        List<CursoDto> cursosPopulares = new ArrayList<>();

        for (int i = 0; i < Math.min(limit, resultados.size()); i++) {
            Object[] fila = resultados.get(i);
            Long cursoId = (Long) fila[0];
            Long totalInscripciones = (Long) fila[1];

            // Obtener detalles del curso por su ID
            Curso curso = cursoRepository.findById(cursoId).orElse(null);
            if (curso != null) {
                cursosPopulares.add(new CursoDto(
                        curso.getIdCurso(),
                        curso.getNombre(),
                        curso.getDescripcion(),
                        curso.getDificultad(),
                        curso.getPortada() != null ? curso.getPortada().getUrl() : null,
                        curso.getEstado(),
                        curso.getCategoria() != null ? curso.getCategoria().getIdCategoria() : null,
                        curso.getUsuarioCreador() != null ? curso.getUsuarioCreador().getIdUsuario() : null,
                        totalInscripciones // Añadimos el número de inscripciones
                ));
            }
        }

        return cursosPopulares;
    }


}
