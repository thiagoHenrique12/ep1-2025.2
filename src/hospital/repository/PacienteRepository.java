package hospital.repository;

import hospital.entidades.Paciente;
import hospital.entidades.PlanoDeSaude;

import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    private static final PacienteRepository instance = new PacienteRepository();
    private final List<Paciente> listaPacientes = new ArrayList<>();

    private PacienteRepository(){

    }
    public static PacienteRepository getInstance(){
        return instance;
    }
    public void salvarPaciente(Paciente paciente){
        listaPacientes.add(paciente);
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(listaPacientes); // devolve uma cópia
    }

    public Paciente buscarPorId(String id){
        return listaPacientes.stream().filter((Paciente p)->p.getId().equals(id)).findFirst().orElse(null);
    }

}
