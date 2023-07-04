package ar.com.codoacodo.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turnos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();
	
	@Column(name="tipo_doc")
    private int tipoDocumento;
	
	@Column(name="nro_doc")
    private String numeroDocumento;
	
	@Column(name="fecha_atencion")
    private LocalDateTime fechaAtencion;
	
	@Column(name="fecha_finalizacion")
    private LocalDateTime fechaFinalizacion;
	
	@Column(name="fecha_cancelacion")
    private LocalDateTime fechaCancelacion;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	name = "turno_usuario",
			joinColumns = @JoinColumn(name="turno_id"),
			inverseJoinColumns = @JoinColumn(name="usuario_id")
	)	
 	private List<User> usuarios;
	
}