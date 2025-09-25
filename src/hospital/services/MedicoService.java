package hospital.services;

import hospital.entidades.Medicos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicoService {
    private List<Medicos> listaMedicos = new ArrayList<>();

    public void cadastrarMedico(String nome, String crm, String especialidade){
        String id = "M-" + UUID.randomUUID().toString().substring(0, 8);
        Medicos medico = new Medicos(nome, crm, especialidade, id);
        listaMedicos.add(medico);
        System.out.println("Médico cadastrado com sucesso!");
    }
    public String validarEspecialidade(int op){
        String especialidade;
        especialidade = switch (op) {
            case 1 -> "Cardiologista";
            case 2 -> "Urologista";
            case 3 -> "Ortopedista";
            case 4 -> "Clínico Geral";
            case 5 -> "Dermatologista";
            default -> {
            System.out.println("Opção indisponível, tente novamente");
            yield null;
            }
        };
        return especialidade;
    }



    public List<Medicos> listarMedicos(){
        return listaMedicos;
    }
}
