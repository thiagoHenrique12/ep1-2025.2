package hospital.repository;

import hospital.entidades.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    private final List<Paciente> listaPacientes = new ArrayList<>();

    public void salvarPaciente(Paciente paciente){
        listaPacientes.add(paciente);
    }
    public List<Paciente> listarTodos() {
        return new ArrayList<>(listaPacientes); // devolve uma cópia
    }
}
