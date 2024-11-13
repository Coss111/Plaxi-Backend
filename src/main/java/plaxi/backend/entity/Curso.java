package plaxi.backend.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Table(name = "Curso") // Define el nombre de la tabla correspondiente en la base de datos.
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador único para la serialización de la clase.

    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que el valor de la clave primaria será auto-generado.
    @Column(name = "id_curso") // Especifica el nombre de la columna en la base de datos correspondiente a este campo.
    private Long idCurso; // Identificador único para cada curso.

    @Column(name = "nombre", nullable = false) // Define la columna "nombre" en la base de datos y establece que no puede ser nulo.
    private String nombre; // Nombre del curso.

    @Column(name = "descripcion", nullable = false) // Define la columna "descripcion" en la base de datos y establece que no puede ser nulo.
    private String descripcion; // Descripción del curso.

    @ManyToOne(fetch = FetchType.LAZY) // Define una relación de muchos-a-uno con la entidad S3Object.
    @JoinColumn(name = "portada", referencedColumnName = "s3_object_id", nullable = true) // Especifica la clave foránea y columna referenciada en S3Object.
    private S3Object portada; // Referencia a la portada del curso almacenada en S3.

    @Column(name = "dificultad", nullable = false) // Define la columna "dificultad" y establece que no puede ser nula.
    private String dificultad; // Nivel de dificultad del curso.

    @Column(name = "estado", nullable = false) // Define la columna "estado" y establece que no puede ser nula.
    private Boolean estado; // Estado del curso (activo/inactivo).

    // Relación con la categoría
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Define una relación de muchos-a-uno con la entidad Categoria.
    @JoinColumn(name = "categoria_id_categoria", referencedColumnName = "id_categoria", nullable = false) // Especifica la clave foránea y columna referenciada en Categoria.
    private Categoria categoria; // Referencia a la categoría del curso.

    // Relación con el usuario que creó el curso
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Define una relación de muchos-a-uno con la entidad Usuario.
    @JoinColumn(name = "usuario_creador_id", referencedColumnName = "id_usuario", nullable = false) // Especifica la clave foránea y columna referenciada en Usuario.
    private Usuario usuarioCreador; // Usuario que creó el curso.

    // Constructor vacío, requerido por JPA.
    public Curso() {}

    // Constructor que inicializa todos los campos de la clase.
    public Curso(String nombre, String descripcion, S3Object portada, String dificultad, Boolean estado, Categoria categoria, Usuario usuarioCreador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.dificultad = dificultad;
        this.estado = estado;
        this.categoria = categoria;
        this.usuarioCreador = usuarioCreador;
    }

    // Métodos getter y setter para idCurso.
    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    // Métodos getter y setter para nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para descripcion.
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos getter y setter para portada.
    public S3Object getPortada() {
        return portada;
    }

    public void setPortada(S3Object portada) {
        this.portada = portada;
    }

    // Métodos getter y setter para dificultad.
    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    // Métodos getter y setter para estado.
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    // Métodos getter y setter para categoria.
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Métodos getter y setter para usuarioCreador.
    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }
}
