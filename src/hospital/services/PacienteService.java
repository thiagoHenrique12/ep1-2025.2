package hospital.services;

import hospital.entidades.Pacientes;
import hospital.entidades.Pacientes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacienteService {

    private List<Pacientes> listaPacientes = new ArrayList<>();

    public void cadastrarPaciente(String nome, String cpf, int idade){
        String id = UUID.randomUUID().toString();
        Pacientes paciente = new Pacientes(id,nome,cpf, idade);
        listaPacientes.add(paciente);
        System.out.println("Paciente cadastrado com sucesso");
    }
    public List<Pacientes> listarPacientes(){
        return listaPacientes;
    }
}
