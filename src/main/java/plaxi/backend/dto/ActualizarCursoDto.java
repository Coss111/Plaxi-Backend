package plaxi.backend.dto;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ActualizarCursoDto {

    private Long idCurso;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private Boolean estado;
    private Long categoriaId;
    private Long usuarioCreadorId;  // Agregar el campo usuarioCreadorId

    @JsonIgnore
    private MultipartFile portada;

    // Constructor vacío
    public ActualizarCursoDto() {}

    // Constructor con todos los parámetros
    public ActualizarCursoDto(Long idCurso, String nombre, String descripcion, MultipartFile portada, String dificultad, Boolean estado, Long categoriaId, Long usuarioCreadorId) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.dificultad = dificultad;
        this.estado = estado;
        this.categoriaId = categoriaId;
        this.usuarioCreadorId = usuarioCreadorId;  // Inicializar el campo usuarioCreadorId
    }

    // Getters y Settersasdvasd

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MultipartFile getPortada() {
        return portada;
    }

    public void setPortada(MultipartFile portada) {
        this.portada = portada;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getUsuarioCreadorId() {  // Getter para usuarioCreadorId
        return usuarioCreadorId;
    }

    public void setUsuarioCreadorId(Long usuarioCreadorId) {  // Setter para usuarioCreadorId
        this.usuarioCreadorId = usuarioCreadorId;
    }
}
