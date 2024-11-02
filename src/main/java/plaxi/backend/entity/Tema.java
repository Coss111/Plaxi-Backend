package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tema", catalog = "PlaxiDB", schema = "public")
public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tema")
    private Long idTema;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "orden", nullable = false)
    private int orden;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recurso_multimedia", referencedColumnName = "s3_object_id", nullable = false)
    private S3Object recursoMultimedia;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leccion_id", referencedColumnName = "id_leccion", nullable = false)
    private Leccion leccion;

}
