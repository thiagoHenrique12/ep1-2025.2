package hospital.repository;

import hospital.entidades.PlanoDeSaude;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoSaudeRepository {

    private final List<PlanoDeSaude> listaPlanos = new ArrayList<>();
    private static final PlanoSaudeRepository instance = new PlanoSaudeRepository();
    private static final String arquivoPlanos = "planos.csv";

    private PlanoSaudeRepository(){
        carregarArquivo();
        if (listaPlanos.isEmpty()) {
            carregarPlanosIniciais();
            salvarArquivo();
        }
    }

    public static PlanoSaudeRepository getInstance(){
        return instance;
    }

    private void carregarPlanosIniciais() {

        //esses planos são os planos iniciais do sistema
        listaPlanos.add(new PlanoDeSaude("Plano Básico", "Id-Plano Básico", 0.20));
        listaPlanos.add(new PlanoDeSaude("Plano Premium", "Id-Plano Premium", 0.40));
    }

    public void salvarPlano(PlanoDeSaude plano){
        listaPlanos.add(plano);
        salvarArquivo();
    }

    public List<PlanoDeSaude> listarTodos() {
        return new ArrayList<>(listaPlanos); // vai retorna uma cópia
    }

    public PlanoDeSaude buscarPorId(String idPlano){
        /*obs: mudei para uma notação mais longa, mas que faz mais sentido para acompanhar o código */
        return listaPlanos.stream().filter((PlanoDeSaude p)->p.getPlanoId().equals(idPlano)).findFirst().orElse(null);
        //CONCEITO IMPORTANTE, pesquisar mais sobre
        /*esse filtro está buscando o primeiro Plano que possuir um id igual ao parametro,
        se nao achar vai retornar o container Optional<> null  */
    }

    public void salvarArquivo(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPlanos))){

            for (PlanoDeSaude plano : listaPlanos){
                writer.write(plano.toString());
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Falha ao salvar plano: "+e.getMessage());
        }
    }

    public void carregarArquivo(){
        try(BufferedReader reader = new BufferedReader(new FileReader(arquivoPlanos))){
            String linha;

            while( (linha= reader.readLine()) != null){
                String[] campos = linha.split(";");
                String id = campos[0];
                String nome = campos[1];
                double descontoBase = Double.parseDouble(campos[2]);

                PlanoDeSaude plano = new PlanoDeSaude(nome, id, descontoBase);
                listaPlanos.add(plano);
            }
            System.out.println("Dados dos planos carregados com sucesso!");
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo de planos não foi encontrado");
        }
        catch (IOException | NumberFormatException e ){
            System.out.println("Falha ao carregar arquivo com planos: "+e.getMessage());
        }
    }
}
