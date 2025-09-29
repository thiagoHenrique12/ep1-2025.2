package hospital.repository;

import hospital.entidades.Pacientes;

import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    private final List<Pacientes> listaPacientes = new ArrayList<>();

    public void salvarPaciente(Pacientes paciente){
        listaPacientes.add(paciente);
    }
    public List<Pacientes> listarTodos() {
        return new ArrayList<>(listaPacientes); // devolve uma cópia
    }
}
