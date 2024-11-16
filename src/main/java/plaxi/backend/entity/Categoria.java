package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA y se mapeará a una tabla en la base de datos.
@Table(name = "Categoria", catalog = "PlaxiDB", schema = "public") // Especifica el nombre de la tabla, el catálogo y el esquema de la base de datos.
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L; // Define un identificador único para la clase, útil en la serialización.

    @Id // Anotación para indicar que el siguiente campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define la estrategia de generación de valores para la clave primaria (auto-incremental en la base de datos).
    @Basic(optional = false) // Indica que el campo es obligatorio.
    @Column(name = "id_categoria") // Especifica el nombre de la columna en la tabla que se mapea a este campo.
    private Long idCategoria; // Identificador único para cada categoría.

    @Column(name = "nombre", nullable = false) // Define la columna "nombre" en la base de datos y establece que no puede ser nulo.
    private String nombre; // Nombre de la categoría.

    @Column(name = "decripcion", nullable = false) // Define la columna "decripcion" en la base de datos y establece que no puede ser nulo.
    private String decripcion; // Descripción de la categoría.

    // Constructor sin argumentos, requerido por JPA.
    public Categoria() {
    }

    // Constructor que inicializa los campos "nombre" y "decripcion".
    public Categoria(String nombre, String decripcion) {
        this.nombre = nombre;
        this.decripcion = decripcion;
    }

    // Métodos getter y setter para el campo idCategoria.
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Métodos getter y setter para el campo nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para el campo decripcion.
    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }
}
