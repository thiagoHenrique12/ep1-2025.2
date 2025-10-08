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

    public void internarPaciente(String pacienteId, String medicoId,
                                       String quartoId, String dataEntrada) {

        Quarto quarto = quartoService.buscarPorId(quartoId);

        if (quarto == null || quarto.isOcupado()) {
            System.err.println("ERRO: Quarto não encontrado ou já está ocupado.");
            return ;
        }
        quartoService.ocuparQuarto(quarto);

        String id = "I-" + UUID.randomUUID().toString().substring(0, 8);
        Internacao novaInternacao = new Internacao(id, pacienteId, medicoId, quartoId, dataEntrada);

        internacaoRepository.salvar(novaInternacao);

        System.out.println("Paciente internado com sucesso! Quarto: " + quarto.getCodigo());

    }

    public void darAlta(String internacaoId, int diasInternados ) {

        Internacao internacao = internacaoRepository.buscarPorId(internacaoId);
        if (internacao == null ) {
            System.err.println("ERRO: Internação inválida ou já encerrada.");
            return ;
        }

        Quarto quarto = quartoService.buscarPorId(internacao.getQuartoId());
        Paciente paciente = pacienteService.buscarPorId(internacao.getPacienteId());

        if (quarto == null || paciente == null) {
            System.err.println("ERRO Crítico: Recurso associado não encontrado.");
            return ;
        }

        internacao.setAtiva(false);
        double custoBaseTotal = (double) diasInternados * quarto.getCustoDiaria();

        double custoFinal = paciente.calcularDescontoInternacao(custoBaseTotal);

        quartoService.liberarQuarto(quarto);

        internacao.setCustoTotal(custoFinal);
        internacaoRepository.atualizar(internacao);

        System.out.printf("\n Alta concedida com sucesso.\n Custo Base: R$ %.2f\n Custo Final: R$ %.2f\n", custoBaseTotal, custoFinal);

    }

    public List<Internacao> listarInternacoesAtivas() {
        return internacaoRepository.listarTodas().stream().filter(Internacao::isAtiva).collect(Collectors.toList());
    }
}



