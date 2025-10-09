package hospital.repository;

import hospital.entidades.Medico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {
    //padrão singleton
    private static final MedicoRepository instance = new MedicoRepository();
    private final List<Medico> listaMedicos = new ArrayList<>();
    private static final String arquivosMedicos = "ep1-2025.2-main/src/hospital/data/medicos.csv";

    private MedicoRepository(){
        carregarArquivo();
    }

    public static MedicoRepository getInstance(){
        return instance;

    }
    public void salvarMedico(Medico medico){
        listaMedicos.add(medico);
        salvarParaArquivo();
    }

    public List<Medico> listarTodos() {
        return new ArrayList<>(listaMedicos);
    }

    public Medico buscarPorId(String id){
        return listaMedicos.stream().filter((Medico m)->m.getId().equals(id)).findFirst().orElse(null);
    }

    public void salvarParaArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivosMedicos))) {
            for (Medico medico : listaMedicos) {
                writer.write(medico.toString());
                writer.newLine();
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Erro: arquivo de médicos não foi encontrado");
        }
        catch (IOException | NumberFormatException e) {
            System.err.println("Falha ao salvar médicos: " + e.getMessage());
        }
    }

    private void carregarArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivosMedicos))) {
            String linha;

            while ((linha = reader.readLine()) != null) {

                Medico medico = getMedico(linha);
                listaMedicos.add(medico);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Iniciando arquivo de médicos vazio");
        } catch (IOException | NumberFormatException e) {
            System.out.println();
            System.err.println("Erro: Falha ao carregar dados de médicos: " + e.getMessage());
        }
    }

    private static Medico getMedico(String linha) {
        String[] campos = linha.split(";");
        String nome = campos[0];
        String crm = campos[1];
        String especialidade = campos[2];
        String id = campos[3];
        double valorDaConsulta = Double.parseDouble(campos[4]);

        String horarioInicioTrabalho = campos[5];
        String horarioFimTrabalho = campos[6];

        return new Medico(nome, crm, especialidade, id, valorDaConsulta, horarioInicioTrabalho, horarioFimTrabalho);
    }
}
