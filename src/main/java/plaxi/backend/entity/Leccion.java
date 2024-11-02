package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name = "Leccion", catalog = "PlaxiDB", schema = "public")
public class Leccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_leccion")
    private Long idLeccion;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "orden", nullable = false)
    private int orden;

    @Column(name = "duracion_estimada", nullable = false)
    private int duracionEstimada;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso", nullable = false)
    private Curso curso;

    public Leccion() {
    }

    public Leccion(Long idLeccion, String titulo, int orden, int duracionEstimada, String contenido, boolean estado, Curso curso) {
        this.idLeccion = idLeccion;
        this.titulo = titulo;
        this.orden = orden;
        this.duracionEstimada = duracionEstimada;
        this.contenido = contenido;
        this.estado = estado;
        this.curso = curso;
    }

    public Long getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(Long idLeccion) {
        this.idLeccion = idLeccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
