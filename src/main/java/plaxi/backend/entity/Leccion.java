package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Table(name = "Leccion", catalog = "PlaxiDB", schema = "public") // Define el nombre de la tabla, catálogo y esquema en la base de datos.
public class Leccion implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador de la versión para serialización.

    @Id // Define que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave primaria se generará automáticamente.
    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "id_leccion") // Especifica el nombre de la columna en la base de datos.
    private Long idLeccion; // Identificador único para cada lección.

    @Column(name = "titulo", nullable = false) // Define la columna "titulo" como no nula.
    private String titulo; // Título de la lección.

    @Column(name = "orden", nullable = false) // Define la columna "orden" como no nula.
    private int orden; // Orden en el que se presenta la lección en el curso.

    @Column(name = "duracion_estimada", nullable = false) // Define la columna "duracion_estimada" como no nula.
    private int duracionEstimada; // Duración estimada de la lección en minutos.

    @Column(name = "contenido", nullable = false) // Define la columna "contenido" como no nula.
    private String contenido; // Contenido principal de la lección.

    @Column(name = "estado", nullable = false) // Define la columna "estado" como no nula.
    private boolean estado; // Estado de la lección (activa/inactiva).

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Define una relación de muchos-a-uno con la entidad Curso.
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso", nullable = false) // Define la clave foránea "curso_id_curso" y la columna referenciada en Curso.
    private Curso curso; // Curso al que pertenece la lección.

    // Constructor por defecto
    public Leccion() {}

    // Constructor con todos los parámetros
    public Leccion(Long idLeccion, String titulo, int orden, int duracionEstimada, String contenido, boolean estado, Curso curso) {
        this.idLeccion = idLeccion;
        this.titulo = titulo;
        this.orden = orden;
        this.duracionEstimada = duracionEstimada;
        this.contenido = contenido;
        this.estado = estado;
        this.curso = curso;
    }

    // Métodos getter y setter para idLeccion.
    public Long getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(Long idLeccion) {
        this.idLeccion = idLeccion;
    }

    // Métodos getter y setter para titulo.
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Métodos getter y setter para orden.
    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    // Métodos getter y setter para duracionEstimada.
    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    // Métodos getter y setter para contenido.
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    // Métodos getter y setter para estado.
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Métodos getter y setter para curso.
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
