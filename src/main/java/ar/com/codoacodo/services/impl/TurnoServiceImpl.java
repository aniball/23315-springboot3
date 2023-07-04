package ar.com.codoacodo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.codoacodo.domain.Turno;
import ar.com.codoacodo.dto.TurnoDTO;
import ar.com.codoacodo.repository.TurnoRepository;
import ar.com.codoacodo.repository.UserRepository;
import ar.com.codoacodo.services.TurnoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TurnoServiceImpl implements TurnoService {

	private final TurnoRepository repository;
	
	@Override
	public void crearTurno(Turno turno) {
		this.repository.save(turno);
	}

	@Override
	public TurnoDTO obtenerTurno(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turno> buscarTodos() {
		return this.repository.findAll();
	}

	@Override
	public void actualizarTurno(TurnoDTO turnoDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarTurno(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarAtendido(Long id) {
		// TODO Auto-generated method stub

	}


}
