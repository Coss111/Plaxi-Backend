package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera automáticamente los métodos getter, setter, toString, equals y hashCode.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor // Genera un constructor sin parámetros (por defecto).
@Entity // Indica que esta clase es una entidad JPA.
@Table(name = "Tema", catalog = "PlaxiDB", schema = "public") // Define el nombre de la tabla y el esquema en la base de datos.
public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // Define el campo 'idTema' como la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave primaria se generará automáticamente.
    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "id_tema") // Especifica el nombre de la columna en la base de datos.
    private Long idTema; // Identificador único del tema.

    @Column(name = "titulo", nullable = false) // Especifica el nombre de la columna y que no puede ser nula.
    private String titulo; // Título del tema.

    @Column(name = "orden", nullable = false) // Especifica el nombre de la columna y que no puede ser nula.
    private int orden; // Orden o posición del tema.

    @Column(name = "descripcion", nullable = false) // Especifica el nombre de la columna y que no puede ser nula.
    private String descripcion; // Descripción del tema.

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Relación Many-to-One con la entidad S3Object.
    @JoinColumn(name = "recurso_multimedia", referencedColumnName = "s3_object_id", nullable = false) // Mapea la columna que hace referencia al recurso multimedia.
    private S3Object recursoMultimedia; // Recurso multimedia asociado al tema.

    @Column(name = "estado", nullable = false) // Especifica el nombre de la columna y que no puede ser nula.
    private boolean estado; // Estado del tema (activo/inactivo).

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Relación Many-to-One con la entidad Leccion.
    @JoinColumn(name = "leccion_id", referencedColumnName = "id_leccion", nullable = false) // Mapea la columna que hace referencia a la lección.
    private Leccion leccion; // Lección asociada a este tema.
}
