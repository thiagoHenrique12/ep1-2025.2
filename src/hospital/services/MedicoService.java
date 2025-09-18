package hospital.services;

import hospital.entidades.Medicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MedicoService {
    private List<Medicos> listaMedicos = new ArrayList<>();

    public void cadastrarMedico(String nome, String crm, String especialidade){
        String id = UUID.randomUUID().toString();
        Medicos medico = new Medicos(nome, crm, especialidade, id);
        listaMedicos.add(medico);
        System.out.println("Médico cadastrado com sucesso!");
    }
    public List<Medicos> listarMedicos(){
        return listaMedicos;
    }
}
