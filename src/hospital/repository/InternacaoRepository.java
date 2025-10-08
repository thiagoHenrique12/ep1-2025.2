package hospital.repository;

import hospital.entidades.Internacao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InternacaoRepository {

    // Padrão Singleton
    private static final InternacaoRepository instance = new InternacaoRepository();
    private final List<Internacao> listaInternacoes = new ArrayList<>();
    private static final String arquivosInternacoes = "src/hospital/data/internacoes.csv";

    private InternacaoRepository() {
        carregarDoArquivo();
    }

    public static InternacaoRepository getInstance() {
        return instance;
    }


    public void salvarParaArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivosInternacoes))) {
            for (Internacao internacao : listaInternacoes) {
                writer.write(internacao.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro: Falha ao salvar internações: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivosInternacoes))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] campos = linha.split(";");
                String id = campos[0];
                String pacienteId = campos[1];
                String medicoId = campos[2];
                String quartoId = campos[3];
                String dataEntrada = campos[4];
                String dataSaida = campos[5].equals("null") ? null : campos[5]; // Lida com o campo "null"
                double custoTotal = Double.parseDouble(campos[6]);
                Internacao internacao = new Internacao(id, pacienteId, medicoId, quartoId, dataEntrada);

                // Sobrescreve os campos dataSaida e custoTotal se a internação já tiver tido alta
                internacao.setDataSaida(dataSaida);
                internacao.setCustoTotal(custoTotal);

                listaInternacoes.add(internacao);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo de internações não encontrado.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Falha ao carregar dados de internações: " + e.getMessage());
        }
    }

    public void salvar(Internacao internacao) {
        listaInternacoes.add(internacao);
        salvarParaArquivo();
    }

    public Internacao buscarPorId(String id) {
        return listaInternacoes.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public void atualizar(Internacao internacao) {
        listaInternacoes.removeIf(i -> i.getId().equals(internacao.getId()));
        listaInternacoes.add(internacao);
        salvarParaArquivo();
    }

    public List<Internacao> listarTodas() {
        return new ArrayList<>(listaInternacoes);
    }
}