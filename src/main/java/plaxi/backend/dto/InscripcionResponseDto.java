package plaxi.backend.dto;

public class InscripcionResponseDto {

    private Long idInscripcion;
    private String fechaInscripcion;
    private Boolean estadoInscripcion;
    private Long usuarioId;
    private String usuarioNombre;
    private String usuarioEmail;
    private Long cursoId;
    private String cursoNombre;
    private Long usuarioCreadorId;
    private String cursoDescripcion;
    private String cursoPortadaUrl;

    // Constructor por defecto
    public InscripcionResponseDto() {}

    public InscripcionResponseDto(Long idInscripcion, String fechaInscripcion, Boolean estadoInscripcion, Long usuarioId, String usuarioNombre, String usuarioEmail, Long cursoId, String cursoNombre, Long usuarioCreadorId, String cursoDescripcion, String cursoPortadaUrl) {
        this.idInscripcion = idInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.estadoInscripcion = estadoInscripcion;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioEmail = usuarioEmail;
        this.cursoId = cursoId;
        this.cursoNombre = cursoNombre;
        this.usuarioCreadorId = usuarioCreadorId;
        this.cursoDescripcion = cursoDescripcion;
        this.cursoPortadaUrl = cursoPortadaUrl;
    }

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Boolean getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(Boolean estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public Long getUsuarioCreadorId() {
        return usuarioCreadorId;
    }

    public void setUsuarioCreadorId(Long usuarioCreadorId) {
        this.usuarioCreadorId = usuarioCreadorId;
    }

    public String getCursoDescripcion() {
        return cursoDescripcion;
    }

    public void setCursoDescripcion(String cursoDescripcion) {
        this.cursoDescripcion = cursoDescripcion;
    }

    public String getCursoPortadaUrl() {
        return cursoPortadaUrl;
    }

    public void setCursoPortadaUrl(String cursoPortadaUrl) {
        this.cursoPortadaUrl = cursoPortadaUrl;
    }
}
