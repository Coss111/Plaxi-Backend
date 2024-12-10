package plaxi.backend.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import plaxi.backend.dto.S3ObjectDto;
import plaxi.backend.entity.S3Object;
import plaxi.backend.repository.S3ObjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    @Value("${minio.bucket-name}")
    private String bucketName;

    // Método para subir un archivo a MinIO y guardarlo en la base de datos
    public S3ObjectDto uploadFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        // Subir archivo a MinIO
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(contentType)
                        .build()
        );

        // Generar la URL del archivo
        String fileUrl = generateFileUrl(bucketName, fileName);

        // Crear el objeto S3Object para guardar en la base de datos sin s3ObjectId
        S3Object s3Object = new S3Object(contentType, bucketName, fileName, fileUrl, true);
        s3ObjectRepository.save(s3Object);

        // Crear y retornar el DTO con los detalles del archivo
        S3ObjectDto s3ObjectDto = new S3ObjectDto(s3Object.getS3ObjectId(), contentType, bucketName, fileName, fileUrl, true);
        return s3ObjectDto;
    }

    // Método para generar el enlace del archivo subido
    private String generateFileUrl(String bucket, String filename) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucket)
                            .object(filename)
                            .method(Method.GET)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error generating file URL", e);
        }
    }

    // Método para listar todos los archivos subidos
    public List<S3ObjectDto> listUploadedFiles() {
        List<S3Object> s3Objects = s3ObjectRepository.findAll();

        return s3Objects.stream()
                .map(s3Object -> {
                    String fileUrl = generateFileUrl(s3Object.getBucket(), s3Object.getFilename());
                    return new S3ObjectDto(
                            s3Object.getS3ObjectId(),
                            s3Object.getContentType(),
                            s3Object.getBucket(),
                            s3Object.getFilename(),
                            fileUrl,
                            s3Object.getStatus()
                    );
                })
                .collect(Collectors.toList());
    }

}
