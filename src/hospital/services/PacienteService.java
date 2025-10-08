package hospital.services;

import hospital.entidades.Paciente;
import hospital.entidades.PacienteEspecial;
import hospital.entidades.PlanoDeSaude;
import hospital.repository.PacienteRepository;

import java.util.List;
import java.util.UUID;

public class PacienteService {
    private final PacienteRepository pacienteRepository = PacienteRepository.getInstance();

    public void adicionarPaciente(String nome, String cpf, int idade,PlanoDeSaude plano){
        String idPaciente = "idP-"+ UUID.randomUUID().toString().substring(0, 8);

        Paciente paciente;
        //lógica para instanciar um paciente especial ou um paciente comum
        if (plano !=null){
             paciente = new PacienteEspecial(idPaciente,nome,cpf,idade,plano);
            System.out.println("Paciente "+nome+", cadastrado com o plano: " +plano.getNome());
            System.out.println();
        }
        else {
             paciente = new Paciente(idPaciente, nome, cpf, idade);
            System.out.println("Paciente "+nome+" cadastrado sem plano");
            System.out.println();
        }
        pacienteRepository.salvarPaciente(paciente);
    }

    public static int validarIdade(String idade){
        try {
            int idadeValidada= Integer.parseInt(idade);

            if ( idadeValidada< 0){
                System.out.println("Idade inválida, tente novamente");
                return -1;
            }
            else{
                return idadeValidada;
            }
        }
        catch (NumberFormatException e){
            System.out.println("Idade inválida, tente novamente ");
            return -1;
        }
    }

    public static String validarCpf(String cpf){
        if (cpf == null) return null;

        String cpfSemFormato = "\\d{11}"; // 11 dígitos inteiros
        String cpfFormatado = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";// 11 dígitos formatados
        if (!cpf.matches(cpfSemFormato) && !cpf.matches(cpfFormatado)) {
            return null;
        }

        return cpf.replaceAll("\\D", "");
    }

    // métodos usados para intermediar o contato entre UI e o repositório
    public Paciente buscarPorId(String id){
        return pacienteRepository.buscarPorId(id);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.listarTodos();
    }
}
