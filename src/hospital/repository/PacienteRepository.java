package hospital.repository;

import hospital.entidades.Paciente;
import hospital.entidades.PacienteEspecial;
import hospital.entidades.PlanoDeSaude;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    private static final PacienteRepository instance = new PacienteRepository();
    private final List<Paciente> listaPacientes = new ArrayList<>();
    private static final String arquivoPacientes = "pacientes.csv";


    private PacienteRepository(){
        carregarArquivo();// sempre que o repositório for instanciado o arquivo será carregado
    }
    public static PacienteRepository getInstance(){
        return instance;
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(listaPacientes); // devolve uma cópia
    }

    public Paciente buscarPorId(String id){
        return listaPacientes.stream().filter((Paciente p)->p.getId().equals(id)).findFirst().orElse(null);
    }


    public void salvarArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPacientes))) {

            for (Paciente paciente : listaPacientes) {
                writer.write(paciente.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(" Falha ao salvar pacientes: " + e.getMessage());
        }
    }

    public void salvarPaciente(Paciente paciente){
        listaPacientes.add(paciente);
        salvarArquivo();
    }

    public void carregarArquivo( ) {
        PlanoSaudeRepository planoSaudeRepository = PlanoSaudeRepository.getInstance();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoPacientes))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                String id = campos[0];
                String nome = campos[1];
                String cpf = campos[2];
                int idade = Integer.parseInt(campos[3]);

                if (campos.length > 4) {
                    String planoId = campos[4];
                    PlanoDeSaude plano = planoSaudeRepository.buscarPorId(planoId);
                    if (plano != null) {
                        PacienteEspecial pEsp = new PacienteEspecial(id, nome, cpf, idade, plano);
                        listaPacientes.add(pEsp);
                    } else {
                        // se o plano estiver vazio o sistema vai tratar como se fosse um paciente normal
                        // para evitar perder os dados
                        listaPacientes.add(new Paciente(id, nome, cpf, idade));
                    }
                }
                else {
                    Paciente p = new Paciente(id, nome, cpf, idade);
                    listaPacientes.add(p);
                }
            }
        }
             catch (FileNotFoundException e) {
                System.out.println("Arquivo de pacientes não encontrado");
        }
            catch(IOException | NumberFormatException e) {
            System.out.println("Erro no salvamento de dados dos pacientes: \n" + e.getMessage());
        }
    }
}









