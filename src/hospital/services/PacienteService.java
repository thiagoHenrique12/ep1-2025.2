package hospital.services;

import hospital.entidades.Pacientes;
import hospital.entidades.Pacientes;

import java.util.UUID;

public class PacienteService {

    public void cadastrarPaciente(String nome, String cpf, int idade){
        String id = UUID.randomUUID().toString();
        Pacientes paciente = new Pacientes(id,nome,cpf, idade);
        System.out.println("Paciente cadastrado com sucesso");



    }
    //* aqui serão listados os métodos para gerir todos os serviços relacionados a pacientes

}
