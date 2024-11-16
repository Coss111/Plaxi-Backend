package plaxi.backend.entity;

import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA que será mapeada a una tabla de base de datos.
@Table(name = "s3_object") // Define el nombre de la tabla en la base de datos que representará esta entidad.
public class S3Object {

    @Id // Define que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave primaria se generará automáticamente.
    @Column(name = "s3_object_id") // Especifica el nombre de la columna en la base de datos.
    private Long s3ObjectId; // Identificador único del objeto S3.

    @Column(name = "content_type") // Especifica el nombre de la columna en la base de datos.
    private String contentType = "application/octet-stream"; // Tipo de contenido del archivo (por defecto es "application/octet-stream").

    @Column(name = "bucket") // Especifica el nombre de la columna en la base de datos.
    private String bucket; // Nombre del bucket S3 donde se almacena el objeto.

    @Column(name = "filename") // Especifica el nombre de la columna en la base de datos.
    private String filename; // Nombre del archivo en el bucket S3.

    @Column(name = "url", length = 1024) // Especifica el nombre de la columna y limita su longitud.
    private String url; // URL para acceder al objeto S3.

    @Column(name = "status") // Especifica el nombre de la columna en la base de datos.
    private Boolean status = true; // Estado del objeto (por defecto es true, lo que indica que está activo).

    // Constructor por defecto
    public S3Object() {
    }

    // Constructor sin s3ObjectId
    public S3Object(String contentType, String bucket, String filename, String url, Boolean status) {
        this.contentType = contentType;
        this.bucket = bucket;
        this.filename = filename;
        this.url = url;
        this.status = status;
    }

    // Constructor completo
    public S3Object(Long s3ObjectId, String contentType, String bucket, String filename, String url, Boolean status) {
        this.s3ObjectId = s3ObjectId;
        this.contentType = contentType;
        this.bucket = bucket;
        this.filename = filename;
        this.url = url;
        this.status = status;
    }

    // Getters y Setters
    public Long getS3ObjectId() {
        return s3ObjectId;
    }

    public void setS3ObjectId(Long s3ObjectId) {
        this.s3ObjectId = s3ObjectId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
