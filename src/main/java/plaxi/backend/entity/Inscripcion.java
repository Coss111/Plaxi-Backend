package plaxi.backend.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Table(name = "Inscripcion") // Define el nombre de la tabla correspondiente en la base de datos.
public class Inscripcion implements Serializable {

    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que el valor de la clave primaria será auto-generado.
    @Column(name = "id_inscripcion") // Especifica el nombre de la columna en la base de datos correspondiente a este campo.
    private Long idInscripcion; // Identificador único para cada inscripción.

    @Temporal(TemporalType.DATE) // Indica que este campo almacena solo la fecha (sin hora) en la base de datos.
    @Column(name = "fecha_inscripcion", nullable = false, updatable = false) // Define la columna "fecha_inscripcion", que no puede ser nula ni actualizable.
    private Date fechaInscripcion; // Fecha en la que se realizó la inscripción.

    @Column(name = "estado_inscripcion", nullable = false) // Define la columna "estado_inscripcion" y establece que no puede ser nula.
    private Boolean estadoInscripcion; // Estado de la inscripción (activa/inactiva).

    @ManyToOne // Define una relación de muchos-a-uno con la entidad Usuario.
    @JoinColumn(name = "Usuario_id_usuario", referencedColumnName = "id_usuario", nullable = false) // Especifica la clave foránea y la columna referenciada en Usuario.
    private Usuario usuario; // Usuario que realiza la inscripción.

    @ManyToOne // Define una relación de muchos-a-uno con la entidad Curso.
    @JoinColumn(name = "Curso_id_curso", referencedColumnName = "id_curso", nullable = false) // Especifica la clave foránea y la columna referenciada en Curso.
    private Curso curso; // Curso en el que se inscribe el usuario.

    // Constructor por defecto
    public Inscripcion() {
        this.fechaInscripcion = new Date(); // Fecha actual al momento de creación de la inscripción.
        this.estadoInscripcion = true;      // Estado por defecto de la inscripción como activa (true).
    }

    // Constructor con parámetros
    public Inscripcion(Long idInscripcion, Usuario usuario, Curso curso) {
        this.idInscripcion = idInscripcion;
        this.fechaInscripcion = new Date(); // Fecha actual al momento de creación de la inscripción.
        this.estadoInscripcion = true;      // Estado por defecto de la inscripción como activa (true).
        this.usuario = usuario;
        this.curso = curso;
    }

    // Métodos getter y setter para idInscripcion.
    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    // Métodos getter y setter para fechaInscripcion.
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    // Métodos getter y setter para estadoInscripcion.
    public Boolean getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(Boolean estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    // Métodos getter y setter para usuario.
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Métodos getter y setter para curso.
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
