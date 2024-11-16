package plaxi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import plaxi.backend.dto.PaginadoDto;
import plaxi.backend.dto.S3ObjectDto;
import plaxi.backend.dto.TemaDto;
import plaxi.backend.entity.Leccion;
import plaxi.backend.entity.S3Object;
import plaxi.backend.entity.Tema;
import plaxi.backend.repository.LeccionRepository;
import plaxi.backend.repository.S3ObjectRepository;
import plaxi.backend.repository.TemaRepository;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private LeccionRepository leccionRepository;

    @Autowired
    private MinioService recursoMultimediaService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    // Obtener todos los temas
    public Page<TemaDto> getAllTemas(PaginadoDto paginadoDto) {
        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);
        return temaRepository.findAllPaginado(page);
    }

    // Obtener un tema por su ID
    public TemaDto getTema(Long idTema) throws Exception {
        Tema tema = temaRepository.findById(idTema)
                .orElseThrow(() -> new Exception("Tema no encontrado"));

        return new TemaDto(
                tema.getIdTema(),
                tema.getTitulo(),
                tema.getOrden(),
                tema.getDescripcion(),
                tema.getRecursoMultimedia().getUrl(),
                tema.isEstado(),
                tema.getLeccion().getIdLeccion()
        );
    }

    // Obtener temas por lección
    public Page<TemaDto> getTemasByLeccion(Long leccionId, PaginadoDto paginadoDto) {
        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);
        return temaRepository.findAllByLeccionIdLeccion(leccionId, page);
    }

    // Crear un tema
    public void createTema(TemaDto temaDto, MultipartFile file) throws Exception {
        // Validar archivo y lección
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("El archivo multimedia es obligatorio.");
        }

        Leccion leccion = leccionRepository.findById(temaDto.getLeccionId())
                .orElseThrow(() -> {
                    return new RuntimeException("Lección no encontrada con ID: " + temaDto.getLeccionId());
                });

        // Subir archivo a MinIO y guardar detalles en la base de datos
        S3ObjectDto s3ObjectDto = recursoMultimediaService.uploadFile(file);
        S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId())
                .orElseThrow(() -> new RuntimeException("S3 Object no encontrado"));

        // Crear y guardar el tema
        Tema tema = new Tema();
        tema.setTitulo(temaDto.getTitulo());
        tema.setOrden(temaDto.getOrden());
        tema.setDescripcion(temaDto.getDescripcion());
        tema.setRecursoMultimedia(s3Object);
        tema.setLeccion(leccion); // Asigna la lección obtenida
        tema.setEstado(true);  // Asignar valor predeterminado true

        temaRepository.save(tema);
    }

    // Actualizar un tema
    public void updateTema(TemaDto temaDto, MultipartFile file) throws Exception {
        // Find the existing tema
        Tema tema = temaRepository.findById(temaDto.getIdTema())
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));
    
        // Obtener la lección asociada
        Leccion leccion = leccionRepository.findById(temaDto.getLeccionId())
                .orElseThrow(() -> new RuntimeException("Lección no encontrada"));
    
        // Update tema fields
        tema.setTitulo(temaDto.getTitulo());
        tema.setOrden(temaDto.getOrden());
        tema.setDescripcion(temaDto.getDescripcion());
        tema.setLeccion(leccion);
        tema.setEstado(temaDto.isEstado()); // Update estado
    
        // Handle the file if a new one is provided
        if (file != null && !file.isEmpty()) {
            // Upload new file and update recursoMultimedia
            S3ObjectDto s3ObjectDto = recursoMultimediaService.uploadFile(file);
            S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId())
                    .orElseThrow(() -> new RuntimeException("S3 Object no encontrado"));
            tema.setRecursoMultimedia(s3Object);
        }
        // If no new file is provided, keep the existing recursoMultimedia
    
        // Save the updated tema
        temaRepository.save(tema);
    }
    

    // Eliminar un tema
    public void deleteTema(Long idTema) {
        Tema tema = temaRepository.findById(idTema)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));
        tema.setEstado(false);  // Borrado lógico
        temaRepository.save(tema);
    }
}
