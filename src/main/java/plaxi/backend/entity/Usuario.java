package plaxi.backend.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario", catalog = "PlaxiDB", schema = "public")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Basic(optional = false)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @Column(name = "gmail")
    private String gmail;

    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    @JoinColumn(name = "persona_id", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Persona personaId;

    @JoinColumn(name = "rol_id", referencedColumnName = "id_rol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rolId;

    // Relación con la imagen de perfil, puede ser nula
    @JoinColumn(name = "imagen_perfil_id", referencedColumnName = "s3_object_id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private S3Object imagenPerfil;

    public Usuario() {
    }

    // Constructor sin imagen de perfil
    public Usuario(Long idUsuario, String username, String password, String gmail, boolean status, Persona personaId, Rol rolId) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.status = status;
        this.personaId = personaId;
        this.rolId = rolId;
    }

    // Constructor con imagen de perfil
    public Usuario(Long idUsuario, String username, String password, String gmail, boolean status, Persona personaId, Rol rolId, S3Object imagenPerfil) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.status = status;
        this.personaId = personaId;
        this.rolId = rolId;
        this.imagenPerfil = imagenPerfil;
    }

    // Getters y Setters

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public S3Object getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(S3Object imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }
}
