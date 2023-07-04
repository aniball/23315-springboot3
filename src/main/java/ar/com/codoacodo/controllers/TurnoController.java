package ar.com.codoacodo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.domain.Turno;
import ar.com.codoacodo.domain.TurnoUsuario;
import ar.com.codoacodo.domain.User;
import ar.com.codoacodo.dto.TurnoRequestDTO;
import ar.com.codoacodo.dto.TurnoResponseDTO;
import ar.com.codoacodo.services.TurnoService;
import ar.com.codoacodo.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/turno")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TurnoController {

    private final TurnoService turnoService;
    private final UserService userService;

//    @GetMapping("/{id}")
//    public ResponseEntity<TurnoDTO> obtenerTurno(@PathVariable("id") Long id) {
//        Turno turno = turnoService.buscarTurno(id);
//        TurnoDTO turnoDTO = convertirTurnoADTO(turno);
//        return ResponseEntity.ok(turnoDTO);
//    }

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
	    
        Turno newTurno = Turno.builder()
        		.tipoDocumento(request.getTipoDocumento())
        		.numeroDocumento(request.getNumeroDocumento())
        		.usuarios(Collections.singletonList(userTurno))
        		.build();
        
        
        this.turnoService.crearTurno(newTurno);

        TurnoResponseDTO response = TurnoResponseDTO.builder()
        		.tipoDocumento(newTurno.getTipoDocumento())
        		.numeroDocumento(newTurno.getNumeroDocumento())
        		.build();
        
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<TurnoDTO> actualizarTurno(@PathVariable("id") Long id, @RequestBody TurnoDTO turnoDTO) {
//        Turno turno = convertirDTOATurno(turnoDTO);
//        turno.setId(id);
//        Turno turnoActualizado = turnoService.actualizarTurno(turno);
//        TurnoDTO turnoActualizadoDTO = convertirTurnoADTO(turnoActualizado);
//        return ResponseEntity.ok(turnoActualizadoDTO);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable("id") Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok().build();
    }

}
