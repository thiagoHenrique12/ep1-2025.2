package hospital.services;

import hospital.entidades.Medico;
import hospital.entidades.Paciente;
import hospital.repository.MedicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicoService {
    private final List<Medico> listaMedicos = new ArrayList<>();
    private final MedicoRepository medicoRepository= MedicoRepository.getInstance();

    public void cadastrarMedico(String nome, String crm, String especialidade, double valorDaConsulta,
            String horarioInicioTrabalho, String horarioFimTrabalho){

        String id = "M-" + UUID.randomUUID().toString().substring(0, 8);
        Medico medico = new Medico(nome, crm, especialidade, id, valorDaConsulta,horarioInicioTrabalho,horarioFimTrabalho);
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
            case 6 -> "Cirurgião";
            default -> {
            System.out.println("Opção indisponível, tente novamente");
            yield null;
            }
        };
        return especialidade;
    }

    public boolean validarCrm(String crm) {
        if (crm == null) {
            return true; // esse true serve para ficar no looping while enquanto o crm for null
        }
        return !crm.matches("\\d{5}");
    }

    public String validarHorario(String horario){
        if (horario == null || !horario.matches("\\d{2}:\\d{2}")){
            System.out.println("Formato de horário inválido");
            return null;
        }
        try {// separando as horas dos minutos
            String[] partes = horario.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);

            if ( horas <0 || horas > 23 || minutos < 0 || minutos>59){
                System.out.println("Valores inválidos, digite um horário entre 00:00 e 23:59");
                return null;
            }
            return horario;
        }
        catch (NumberFormatException e){
            System.out.println("Erro, digite novamente"); // camada extra para o programa não parar
            return null;
        }
    }

    public List<Medico> listarMedicos(){
        return medicoRepository.listarTodos();
    }

    public Medico buscarPorId(String id){
        return medicoRepository.buscarPorId(id);
    }


}
