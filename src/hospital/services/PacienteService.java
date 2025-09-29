package hospital.services;

import hospital.entidades.Paciente;
import hospital.repository.PacienteRepository;


import java.util.List;
import java.util.UUID;

public class PacienteService {

    private final PacienteRepository repository = new PacienteRepository();


    public Paciente adicionarPaciente(String nome, String cpf, int idade){
        String id = "P-"+ UUID.randomUUID().toString().substring(0, 8);
        Paciente paciente = new Paciente(id,nome,cpf, idade);
        repository.salvarPaciente(paciente);
        return paciente;
    }
    public List<Paciente> listarPacientes() {
        return repository.listarTodos();
    }
}
