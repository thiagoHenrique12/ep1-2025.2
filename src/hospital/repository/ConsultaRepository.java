package hospital.repository;

import hospital.entidades.Consulta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaRepository {
    //aplicação de singleton
    private static final ConsultaRepository instance = new ConsultaRepository();
    private final List<Consulta> listaConsultas= new ArrayList<>();
    private static final String arquivosConsultas = "src/hospital/data/consultas.csv";


    private ConsultaRepository(){
        carregarDoArquivo();
    }

    public static ConsultaRepository getInstance(){
        return instance;
    }

    public void salvarConsulta(Consulta consulta) {
        listaConsultas.add(consulta);
        salvarParaArquivo();
    }

    public List<Consulta> listarTodas(){
        return new ArrayList<>(listaConsultas);
    }

    public List<Consulta> listarTodasAtivas() {
        return listaConsultas.stream()
                .filter(c -> c.getStatus().equals("AGENDADA")) // Filtra apenas as que estão ATIVAS
                .collect(Collectors.toList());
    }


    public void salvarParaArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivosConsultas))) {

            for (Consulta consulta : listaConsultas) {
                writer.write(consulta.toString());
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo de consultas não encontrado");
        }
        catch (IOException e){
            System.err.println("Erro: Falha ao carregar dados das consultas: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivosConsultas))) {
            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] campos = linha.split(";");

                String id = campos[0];
                String pacienteId = campos[1];
                String medicoId = campos[2];
                String dataHora = campos[3];
                String local = campos[4];
                String status = campos[5];
                double custoFinal = Double.parseDouble(campos[6]);

                Consulta consulta = new Consulta(id, pacienteId, medicoId, dataHora, local, status, custoFinal);
                listaConsultas.add(consulta);

            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo de consultas não encontrado. Iniciando vazio.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro: Falha ao carregar dados das consultas: " + e.getMessage());
        }
    }


}


