package hospital.services;

import hospital.entidades.Medico;
import hospital.repository.MedicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicoService {
    private final List<Medico> listaMedicos = new ArrayList<>();
    private final MedicoRepository medicoRepository= MedicoRepository.getInstance();

    public void cadastrarMedico(String nome, String crm, String especialidade){
        String id = "M-" + UUID.randomUUID().toString().substring(0, 8);
        Medico medico = new Medico(nome, crm, especialidade, id);
        medicoRepository.salvarMedico(medico);
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

    public List<Medico> listarMedicos(){
        return listaMedicos;
    }
}
