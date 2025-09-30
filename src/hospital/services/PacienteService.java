package hospital.services;

import hospital.entidades.Paciente;
import hospital.repository.PacienteRepository;
import hospital.repository.PlanoSaudeRepository;

import java.util.List;
import java.util.UUID;

public class PacienteService {
    private final PlanoSaudeRepository planoRepository = new PlanoSaudeRepository();
    private final PacienteRepository repository = new PacienteRepository();


    //posteriormente adicionar o id do plano como parâmetro
    public Paciente adicionarPaciente(String nome, String cpf, int idade){
        String idPaciente = "P-"+ UUID.randomUUID().toString().substring(0, 8);

        Paciente paciente = new Paciente(idPaciente,nome,cpf, idade); ///// idPlano aqui
        repository.salvarPaciente(paciente);
        return paciente;
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

        //
        String cpfSemFormato = "\\d{11}"; // 11 dígitos inteiros

        String cpfFormatado = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

        // Se não corresponder a nenhum dos dois formatos, retorna falso
        if (!cpf.matches(cpfSemFormato) && !cpf.matches(cpfFormatado)) {
            return null;
        }

        //caso esteja formatado, remove a pontuação para padronizar
        String cpfNumerico = cpf.replaceAll("\\D", "");
        return cpfNumerico;
    }

    public List<Paciente> listarPacientes() {
        return repository.listarTodos();
    }
}
