package hospital.services;

import hospital.entidades.Internacao;
import hospital.entidades.Paciente;
import hospital.entidades.Quarto;
import hospital.repository.InternacaoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InternacaoService {
    private final InternacaoRepository internacaoRepository = InternacaoRepository.getInstance();
    private final PacienteService pacienteService = new PacienteService();
    private final QuartoService quartoService = new QuartoService();

    public Internacao internarPaciente(String pacienteId, String medicoId,
                                       String quartoId, String dataEntrada) {

        Quarto quarto = quartoService.buscarPorId(quartoId);

        if (quarto == null || quarto.isOcupado()) {
            System.err.println("ERRO: Quarto não encontrado ou já está ocupado.");
            return null;
        }
        quartoService.ocuparQuarto(quarto);

        String id = "I-" + UUID.randomUUID().toString().substring(0, 8);
        Internacao novaInternacao = new Internacao(id, pacienteId, medicoId, quartoId, dataEntrada);

        internacaoRepository.salvar(novaInternacao);

        System.out.println("Paciente internado com sucesso! Quarto: " + quarto.getCodigo());
        return novaInternacao;
    }

    public Internacao darAlta(String internacaoId, int diasInternados, String dataSaida) {

        Internacao internacao = internacaoRepository.buscarPorId(internacaoId);
        if (internacao == null || internacao.getDataSaida() != null || diasInternados <= 0) {
            System.err.println("ERRO: Internação inválida ou já encerrada.");
            return null;
        }

        Quarto quarto = quartoService.buscarPorId(internacao.getQuartoId());
        Paciente paciente = pacienteService.buscarPorId(internacao.getPacienteId());

        if (quarto == null || paciente == null) {
            System.err.println("ERRO Crítico: Recurso associado não encontrado.");
            return null;
        }

        double custoBaseTotal = (double) diasInternados * quarto.getCustoDiaria();

        double custoFinal = paciente.calcularDescontoInternacao(custoBaseTotal);

        quartoService.liberarQuarto(quarto);

        internacao.setDataSaida(dataSaida);
        internacao.setCustoTotal(custoFinal);
        internacaoRepository.atualizar(internacao);

        System.out.printf(" Alta realizada.\n Custo Base: R$ %.2f\n Custo Final: R$ %.2f\n", custoBaseTotal, custoFinal);
        return internacao;
    }

    public List<Internacao> listarInternacoesAtivas() {
        return internacaoRepository.listarTodas().stream()
                .filter(i -> i.getDataSaida() == null)
                .collect(Collectors.toList());
    }
}



