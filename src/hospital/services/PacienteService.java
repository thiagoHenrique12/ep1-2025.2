package hospital.services;

import hospital.entidades.Pacientes;
import hospital.repository.PacienteRepository;


import java.util.List;
import java.util.UUID;

public class PacienteService {

    private final PacienteRepository repository = new PacienteRepository();


    public Pacientes adicionarPaciente(String nome, String cpf, int idade){
        String id = UUID.randomUUID().toString();           /* buscar forma de realizar diferenciação entre os ids de medico
                                                            e paciente P------ e M -------*/
        Pacientes paciente = new Pacientes(id,nome,cpf, idade);
        repository.salvarPaciente(paciente);
        return paciente;
    }
    public List<Pacientes> listarPacientes() {
        return repository.listarTodos();
    }
}
