package hospital.repository;

import hospital.entidades.Quarto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuartoRepository {

    // Padrão Singleton
    private static final QuartoRepository instance = new QuartoRepository();
    private final List<Quarto> listaQuartos = new ArrayList<>();
    private static final String arquivosQuartos = "src/hospital/data/quartos.csv";

    private QuartoRepository() {
        carregarDoArquivo();
        if (listaQuartos.isEmpty()) {
            carregarQuartosIniciais();
        }
    }

    public static QuartoRepository getInstance() {
        return instance;
    }

    //quartos disponíveis
    private void carregarQuartosIniciais() {
            listaQuartos.add(new Quarto("Q01", "101", "Privativo", 350.00));
            listaQuartos.add(new Quarto("Q02", "102", "Semi-privativo", 200.00));
            listaQuartos.add(new Quarto("Q03", "UTI", "UTI", 800.00));
            salvarParaArquivo();
    }


    public void salvarParaArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivosQuartos))) {
            for (Quarto quarto : listaQuartos) {
                writer.write(quarto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: Falha ao salvar quartos: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivosQuartos))) {
            String linha;
            while ((linha = reader.readLine()) != null) {

                String[] campos = linha.split(";");

                String id = campos[0];
                String codigo = campos[1];
                String tipo = campos[2];
                double custoDiario = Double.parseDouble(campos[3]);
                boolean ocupado = Boolean.parseBoolean(campos[4]);

                Quarto quarto = new Quarto(id, codigo, tipo, custoDiario);
                quarto.setDisponivel(ocupado); // Atualiza o estado lido do arquivo
                listaQuartos.add(quarto);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: arquivo de quartos não foi encontrado");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro: Falha ao carregar dados de quartos: " + e.getMessage());
        }
    }

    public List<Quarto> listarTodos() {
        return new ArrayList<>(listaQuartos);
    }

    public Quarto buscarPorId(String id) {
        return listaQuartos.stream().filter(q -> q.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void atualizar(Quarto quarto) {
        // Remove a versão antiga e adiciona a nova (atualizada) para garantir o estado
        listaQuartos.removeIf(q -> q.getId().equals(quarto.getId()));
        listaQuartos.add(quarto);
        salvarParaArquivo();
    }
}