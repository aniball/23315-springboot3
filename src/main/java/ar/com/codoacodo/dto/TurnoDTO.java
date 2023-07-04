package ar.com.codoacodo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {
    private Long id;
    private LocalDateTime fecha;
    private int tipoDocumento;
    private String numeroDocumento;
    private LocalDateTime fechaAtencion;
    private LocalDateTime fechaFinalizacion;
    private LocalDateTime fechaCancelacion;
}
