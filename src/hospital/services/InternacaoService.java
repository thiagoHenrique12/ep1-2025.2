package hospital.services;

import hospital.entidades.Internacao;
import hospital.entidades.Quarto;
import hospital.repository.InternacaoRepository;
import hospital.repository.QuartoRepository;

import java.util.UUID;

public class InternacaoService {
    private final InternacaoRepository internacaoRepository = InternacaoRepository.getInstance();
    private final QuartoRepository quartoRepository = QuartoRepository.getInstance();

    public Internacao internarPaciente(String pacienteId, String medicoId,
                                       String quartoId, String dataEntrada) {

        Quarto quarto = quartoRepository.buscarPorId(quartoId);

        if (quarto == null) {
            System.err.println("ERRO: Quarto não encontrado.");
            return null;
        }

        if (quarto.isOcupado()) {
            System.err.println("ERRO: O quarto " + quarto.getCodigo() + " já está ocupado.");
            return null;
        }

        String id = "I-" + UUID.randomUUID().toString().substring(0, 8);
        Internacao novaInternacao = new Internacao(id, pacienteId, medicoId, quartoId, dataEntrada);

        quarto.setOcupado(true);
        quartoRepository.atualizar(quarto); // o Repository vai salvar a mudança no CSV

        internacaoRepository.salvar(novaInternacao);
        System.out.println("Paciente internado no quarto " + quarto.getCodigo() + ". ID: " + id);
        return novaInternacao;
    }

}
