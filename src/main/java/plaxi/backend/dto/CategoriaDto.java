package plaxi.backend.dto;

public class CategoriaDto {

    private Long idCategoria;
    private String nombre;
    private String decripcion;

    public CategoriaDto() {}

    public CategoriaDto(Long idCategoria, String nombre, String decripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.decripcion = decripcion;
    }

    // Getters y Setters
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }
}
