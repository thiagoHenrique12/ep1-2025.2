package hospital.services;

import hospital.entidades.Medico;
import hospital.entidades.Consulta;
import hospital.entidades.Paciente;
import hospital.repository.ConsultaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaService {
    private final ConsultaRepository consultaRepository = ConsultaRepository.getInstance();
    private final PacienteService pacienteService = new PacienteService();

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
        // buscando o paciente
        Paciente paciente = pacienteService.buscarPorId(pacienteId);
        if (paciente ==null){
            System.out.println("Nenhum paciente foi encontrado para o id fornecido");
            return null;
        }
        double custoFinal = paciente.calcularCustoConsulta(custoBaseMedico);
        String id = "Con-" + UUID.randomUUID().toString().substring(0, 8);

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


    public List<String> gerarGradeHorarios(Medico medico, String data, int intervaloMinutos) {
        List<String> todosSlots = new ArrayList<>();

        int inicioMinutos = converterParaMinutos(medico.getHorarioInicioTrabalho());

        int fimMinutos = converterParaMinutos(medico.getHorarioFimTrabalho());

        int minutosAtuais = inicioMinutos;

        // Loop para criar slots de consulta a cada 30 minutos
        while (minutosAtuais < fimMinutos) {

            int h = minutosAtuais / 60;
            int m = minutosAtuais % 60;
            String horario = String.format("%02d:%02d", h, m);

            // Adiciona formatado: "AAAA-MM-DD HH:mm"
            todosSlots.add(data + " " + horario);

            minutosAtuais += intervaloMinutos; // Avança para o próximo slot
        }

        return todosSlots;
    }

    // método para converter HH:mm para minutos totais
    private int converterParaMinutos(String horarioHHMM) {
        try {
            String[] partes = horarioHHMM.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            return horas * 60 + minutos;
        } catch (Exception e) {
            return 0;
        }
    }

}


