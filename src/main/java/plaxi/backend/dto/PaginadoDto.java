package plaxi.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginadoDto {
    private int page;
    private int size;
    private String sortBy;
    private String sortDir;
}
