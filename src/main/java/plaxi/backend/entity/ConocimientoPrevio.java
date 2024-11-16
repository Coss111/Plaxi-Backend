package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Table(name = "ConocimientoPrevio", catalog = "PlaxiDB", schema = "public") // Define el nombre de la tabla, catálogo y esquema.
public class ConocimientoPrevio implements Serializable {

    private static final long serialVersionUID = 1L; // Define un identificador único para la serialización de la clase.

    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que el valor de la clave primaria será auto-generado por la base de datos.
    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "id_conocimiento") // Especifica el nombre de la columna en la base de datos correspondiente a este campo.
    private Long idConocimiento; // Identificador único para cada registro de ConocimientoPrevio.

    @Column(name = "descripcion", nullable = false) // Define la columna "descripcion" en la base de datos y establece que no puede ser nulo.
    private String descripcion; // Descripción del conocimiento previo.

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Define una relación de muchos-a-uno con la entidad Curso.
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso", nullable = false) // Especifica la clave foránea y la columna referenciada en la entidad Curso.
    private Curso curso; // Referencia al curso relacionado con el conocimiento previo.

    // Constructor sin argumentos, requerido por JPA.
    public ConocimientoPrevio() {
    }

    // Constructor que inicializa los campos "descripcion" y "curso".
    public ConocimientoPrevio(String descripcion, Curso curso) {
        this.descripcion = descripcion;
        this.curso = curso;
    }

    // Métodos getter y setter para el campo idConocimiento.
    public Long getIdConocimiento() {
        return idConocimiento;
    }

    public void setIdConocimiento(Long idConocimiento) {
        this.idConocimiento = idConocimiento;
    }

    // Métodos getter y setter para el campo descripcion.
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos getter y setter para el campo curso.
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
