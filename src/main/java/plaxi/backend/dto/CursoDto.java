package plaxi.backend.dto;

public class CursoDto {

    private Long idCurso;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private String portada;
    private Boolean estado;
    private Long categoriaId;  // ID de la categoría
    private Long usuarioCreadorId;  // ID del usuario creador
    private Long totalInscripciones;

    public CursoDto() {}

    // Constructor actualizado
    public CursoDto(Long idCurso, String nombre, String descripcion, String dificultad, String portada, Boolean estado,
                    Long categoriaId, Long usuarioCreadorId, Long totalInscripciones) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.portada = portada;
        this.estado = estado;
        this.categoriaId = categoriaId;
        this.usuarioCreadorId = usuarioCreadorId;
        this.totalInscripciones = totalInscripciones;
    }


    public CursoDto(Long idCurso, String nombre, String descripcion, String dificultad, String portada, Boolean estado, Long categoriaId, Long usuarioCreadorId) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.portada = portada;
        this.estado = estado;
        this.categoriaId = categoriaId;
        this.usuarioCreadorId = usuarioCreadorId;
    }

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

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
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

    public Long getUsuarioCreadorId() {
        return usuarioCreadorId;
    }

    public void setUsuarioCreadorId(Long usuarioCreadorId) {
        this.usuarioCreadorId = usuarioCreadorId;
    }

    public Long getTotalInscripciones() {
        return totalInscripciones;
    }

    public void setTotalInscripciones(Long totalInscripciones) {
        this.totalInscripciones = totalInscripciones;
    }
}
