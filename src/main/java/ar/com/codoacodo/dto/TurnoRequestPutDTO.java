package ar.com.codoacodo.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurnoRequestPutDTO {

	@NotNull
    private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaAtencion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaFinalizacion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaCancelacion;
}
