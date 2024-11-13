package plaxi.backend.entity;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
@Table(name = "persona", catalog = "PlaxiDB", schema = "public") // Define el nombre de la tabla, catálogo y esquema en la base de datos.
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador de la versión para serialización.

    @Id // Define que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave primaria se generará automáticamente.
    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "id_persona") // Especifica el nombre de la columna en la base de datos.
    private Long idPersona; // Identificador único de la persona.

    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "nombre") // Especifica el nombre de la columna en la base de datos.
    private String nombre; // Nombre de la persona.

    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "primer_apellido") // Especifica el nombre de la columna en la base de datos.
    private String primerApellido; // Primer apellido de la persona.

    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "segundo_apellido") // Especifica el nombre de la columna en la base de datos.
    private String segundoApellido; // Segundo apellido de la persona.

    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "telefono") // Especifica el nombre de la columna en la base de datos.
    private String telefono; // Teléfono de la persona.

    @Basic(optional = false) // Indica que este campo es obligatorio.
    @Column(name = "ci") // Especifica el nombre de la columna en la base de datos.
    private String ci; // Cédula de identidad (u otro identificador) de la persona.

    // Relación OneToMany con la entidad Usuario, donde "personaId" es la clave foránea en Usuario.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId", fetch = FetchType.LAZY) 
    @JsonBackReference // Previene la serialización recursiva infinita en la relación bidireccional.
    private Collection<Usuario> usuarioCollection; // Colección de usuarios asociados a esta persona.

    // Constructor por defecto
    public Persona() {}

    // Constructor con parámetros
    public Persona(Long idPersona, String nombre, String primerApellido, String segundoApellido, String telefono, String ci, Collection<Usuario> usuarioCollection) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.ci = ci;
        this.usuarioCollection = usuarioCollection;
    }

    // Métodos getter y setter para idPersona.
    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    // Métodos getter y setter para nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para primerApellido.
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    // Métodos getter y setter para segundoApellido.
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    // Métodos getter y setter para telefono.
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Métodos getter y setter para ci.
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    // Métodos getter y setter para usuarioCollection.
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }
}
