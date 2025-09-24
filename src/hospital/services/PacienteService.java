package hospital.services;

import hospital.entidades.Pacientes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacienteService {

    private List<Pacientes> listaPacientes = new ArrayList<>();

    public void adicionarPaciente(String nome, String cpf, int idade){
        String id = UUID.randomUUID().toString();           /* buscar forma de realizar diferenciação entre os ids de medico
                                                            e paciente P------ e M -------*/
        Pacientes paciente = new Pacientes(id,nome,cpf, idade);
        listaPacientes.add(paciente);
        System.out.println("Paciente cadastrado com sucesso");

    }
    public List<Pacientes> listarPacientes(){
        return listaPacientes;
    }
}
