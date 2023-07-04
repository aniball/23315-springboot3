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
public class TurnoRequestPutDTO {

    private Long id;
    private LocalDateTime fechaAtencion;
    private LocalDateTime fechaFinalizacion;
    private LocalDateTime fechaCancelacion;
}
