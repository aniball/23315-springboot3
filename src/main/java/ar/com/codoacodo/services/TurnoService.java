package ar.com.codoacodo.services;

import ar.com.codoacodo.domain.Turno;
import ar.com.codoacodo.dto.TurnoDTO;

import java.util.List;

public interface TurnoService {
    public Turno buscarTurno(Long id);
    void actualizarTurno(Turno turno);
    void eliminarTurno(Long id);
    void registrarAtendido(Long id);
	void crearTurno(Turno turno);
	public List<Turno> buscarTodos();
}
