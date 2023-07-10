package ar.com.codoacodo.controllers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.domain.Turno;
import ar.com.codoacodo.domain.User;
import ar.com.codoacodo.dto.TurnoDTO;
import ar.com.codoacodo.dto.TurnoRequestDTO;
import ar.com.codoacodo.dto.TurnoRequestPutDTO;
import ar.com.codoacodo.dto.TurnoResponseDTO;
import ar.com.codoacodo.services.TurnoService;
import ar.com.codoacodo.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/turno")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TurnoController {

    private final TurnoService turnoService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<Turno>> findAll() {
    	
        List<Turno> turnos = turnoService.buscarTodos();
        return ResponseEntity.ok(turnos);
    }

	
    @PostMapping()
    public ResponseEntity<TurnoResponseDTO> crearTurno(
    		@RequestBody TurnoRequestDTO request) {
    	
        // Obtener el objeto Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el nombre de usuario del token de autenticación
        String username = authentication.getName();	
    
		User user = this.userService.buscarUserPorUsername(username);

	    User userTurno = User.builder()
	    		.id(user.getId())
	    		.build();
 
//		TurnoUsuario userTurno = TurnoUsuario.builder()
//				.usuarioId(user.getId())
//	    		.build();
	    
	    Set<User> userSet = new HashSet<>();
	    userSet.add(userTurno);
		Turno newTurno = Turno.builder()
        		.tipoDocumento(request.getTipoDocumento())
        		.numeroDocumento(request.getNumeroDocumento())
        		.usuarios(userSet)
        		.build();
        
        
        this.turnoService.crearTurno(newTurno);

        TurnoResponseDTO response = TurnoResponseDTO.builder()
        		.tipoDocumento(newTurno.getTipoDocumento())
        		.numeroDocumento(newTurno.getNumeroDocumento())
        		.build();
        
        return ResponseEntity.ok(response);
    }

  @PutMapping("/{id}")
  public ResponseEntity<TurnoRequestPutDTO> actualizarTurno(
		  @PathVariable(name="id", required = true) Long id,
		  @RequestBody TurnoRequestPutDTO request
		  ) {
	  
      Turno turno = this.turnoService.buscarTurno(id);
      if (!turno.getId().equals(request.getId())){
    	  return ResponseEntity.badRequest().build();
      }
      
      LocalDateTime fechaAtencion = request.getFechaAtencion();
      if (fechaAtencion != null) {
          turno.setFechaAtencion(fechaAtencion);
      }

      LocalDateTime fechaFinalizacion = request.getFechaFinalizacion();
      if (fechaFinalizacion != null) {
          turno.setFechaFinalizacion(fechaFinalizacion);
      }
 
      LocalDateTime fechaCancelacion = request.getFechaCancelacion();
      if (fechaCancelacion != null) {
          turno.setFechaCancelacion(fechaCancelacion);
      }    
      
      this.turnoService.actualizarTurno(turno);
      
      return ResponseEntity.ok().build();
      
  }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable("id") Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok().build();
    }

}
