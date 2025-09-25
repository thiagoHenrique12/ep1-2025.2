package hospital.services;

import hospital.entidades.Consultas;
import hospital.repository.ConsultaRepository;
import hospital.repository.PacienteRepository;

import java.util.List;
import java.util.UUID;

public class ConsultaService {
    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    private final PacienteRepository pacienteRepository = new PacienteRepository();



    // teste improvisado para validar as aplicações
    public void iniciarTestes() {
        System.out.println("testando");

        // valores ficticios apenas para teste
        String p1 = "P-id_paciente1";
        String m1 = "M-id_médico1";
        String m2 = "M-id_médico2";
        String s1 = "sala. 101";
        String s2 = "sala. 13";
        double custo = 150.00;

        String horarioConflito = "2025-11-01 15:00";

        System.out.println("agendando consulta1....");
        Consultas base = agendarConsulta(p1, m1, horarioConflito, s1, custo);

        if (base != null) {
            System.out.println("agendada com sucesso. Total Ativas: " + consultaRepository.listarTodasAtivas().size());
        }


        // testando o conflito de horario para médico ocupado
        System.out.println(" Teste: Médico Ocupado ");
        Consultas conflitoMedico = agendarConsulta(
                "Paciente-2", m1, horarioConflito, s2, custo // Mesmo Médico (m1), Mesmo Horário
        );
        if (conflitoMedico == null) {
            System.out.println("conflito de Médico/Horário detectado! (deu certo)");// mensagem esperada para confirmar que o programa esta certo
        } else {
            System.out.println(" o agendamento deveria ter falahdo.");
        }


        System.out.println("testando se o local vai estar ocupado");
        Consultas conflitoLocal = agendarConsulta(
                "P-3", m2, horarioConflito, s1, custo // Mesmo Local (s1), outro Médico (m2)
        );
        if (conflitoLocal == null) {
            System.out.println(" Conflito de Local/Horário detectado (deu certo"); //essa mensagem é a esperada para caso o programa esteja correto
        } else {
            System.out.println(" Agendamento deveria falhar.");
        }

        System.out.println("testandi horário Livre");
        Consultas livre = agendarConsulta(
                "P-4", m2, "2025-11-01 16:00", s2, custo // Tudo diferente dessa vez
        );
        if (livre != null) {
            System.out.println("agendamento Livre efetuado com sucesso.");
        }

        System.out.println("Total de consultas ativas final: " + consultaRepository.listarTodasAtivas().size());
        System.out.println("--- TESTE CONCLUÍDO ---");
    }






    public Consultas agendarConsulta(
            String pacienteId,
            String medicoId,
            String dataHora,
            String local,
            double custoBaseMedico) {


        if (checarConflito(dataHora, medicoId, local)) {
            System.err.println("ERRO: O horário, médico ou local está indisponível.");
            return null;
        }

        // 2. Lógica de Criação:
        double custoFinal = custoBaseMedico; // Polimorfismo futuro, necessario melhorar a parte de médicos
        String id = "C-" + UUID.randomUUID().toString().substring(0, 8);

        Consultas novaConsulta = new Consultas(
                id, pacienteId, medicoId, dataHora, local, "AGENDADA", custoFinal
        );

        consultaRepository.salvarConsulta(novaConsulta);
        System.out.println("Consulta agendada com sucesso! ID: " + id);
        return novaConsulta;
    }

    private boolean checarConflito(String novaDataHora, String novoMedicoId, String novoLocal) {
        List<Consultas> consultasAtivas = consultaRepository.listarTodasAtivas();

        for (Consultas existente : consultasAtivas) {

            // validando o Conflito entre MÉDICO E HORÁRIO
            if (existente.getMedicoId().equals(novoMedicoId) &&
                    existente.getDataHora().equals(novaDataHora)) {

                System.err.println("Conflito: Médico " + novoMedicoId + " já está agendado.");
                return true;
            }

            // validando o conflito de LOCAL E HORÁRIO
            if (existente.getLocal().equals(novoLocal) &&
                    existente.getDataHora().equals(novaDataHora)) {

                System.err.println("Conflito: Local " + novoLocal + " já está reservado para esse horário.");
                return true;
            }
        }
        return false; //esse falso está indicando que nenhum conflito foi encontrado
    }
}


