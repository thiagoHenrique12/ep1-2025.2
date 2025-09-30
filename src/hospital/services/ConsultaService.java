package hospital.services;

import hospital.entidades.Consulta;
import hospital.repository.ConsultaRepository;
import hospital.repository.PacienteRepository;

import java.util.List;
import java.util.UUID;

public class ConsultaService {
    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    private final PacienteRepository pacienteRepository = new PacienteRepository();




    public Consulta agendarConsulta(
            String pacienteId,
            String medicoId,
            String dataHora,
            String local,
            double custoBaseMedico) {


        if (checarConflito(dataHora, medicoId, local)) {
            System.err.println("ERRO: O horário, médico ou local está indisponível.");
            return null;
        }

        double custoFinal = custoBaseMedico; // Polimorfismo futuro, necessario melhorar a parte de médicos
        String id = "C-" + UUID.randomUUID().toString().substring(0, 8);

        Consulta novaConsulta = new Consulta(
                id, pacienteId, medicoId, dataHora, local, "AGENDADA", custoFinal
        );

        consultaRepository.salvarConsulta(novaConsulta);
        System.out.println("Consulta agendada com sucesso! ID: " + id);
        return novaConsulta;
    }

    private boolean checarConflito(String novaDataHora, String novoMedicoId, String novoLocal) {
        List<Consulta> consultasAtivas = consultaRepository.listarTodasAtivas();

        for (Consulta existente : consultasAtivas) {

            // validando o Conflito entre MÉDICO E HORÁRIO
            if (existente.getMedicoId().equals(novoMedicoId) &&
                    existente.getDataHora().equals(novaDataHora)) {

                System.err.println("Conflito: Médico " + novoMedicoId + " já está agendado nesse horário.");
                return true;
            }

            // validando o conflito de LOCAL E HORÁRIO
            if (existente.getLocal().equals(novoLocal) &&
                    existente.getDataHora().equals(novaDataHora)) {

                System.err.println("Conflito: Local " + novoLocal + " já está reservado para esse horário.");
                return true; // ambos os true acima indicam um retorno de que existe conflito
            }
        }
        return false; //esse falso está indicando que nenhum conflito foi encontrado
    }
}


