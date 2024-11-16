package plaxi.backend.entity;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Table(name = "rol", catalog = "PlaxiDB", schema = "public") // Define el nombre de la tabla, catálogo y esquema en la base de datos.
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador de la versión para serialización.

    @Id // Define que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave primaria se generará automáticamente.
    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "id_rol") // Especifica el nombre de la columna en la base de datos.
    private Long idRol; // Identificador único del rol.

    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "nombre") // Especifica el nombre de la columna en la base de datos.
    private String nombre; // Nombre del rol (por ejemplo, Admin, Usuario).

    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "status") // Especifica el nombre de la columna en la base de datos.
    private boolean status; // Estado del rol (activo o inactivo).

    // Relación OneToMany con la entidad Usuario, donde "rolId" es la clave foránea en Usuario.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId", fetch = FetchType.LAZY) 
    private Collection<Usuario> usuarioCollection; // Colección de usuarios asociados a este rol.

    // Constructor por defecto
    public Rol() {}

    // Constructor con parámetros
    public Rol(Long idRol, String nombre, boolean status, Collection<Usuario> usuarioCollection) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.status = status;
        this.usuarioCollection = usuarioCollection;
    }

    // Métodos getter y setter para idRol.
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    // Métodos getter y setter para nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para status.
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Métodos getter y setter para usuarioCollection.
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }
}
