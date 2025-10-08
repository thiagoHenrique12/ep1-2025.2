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
    private final MedicoService medicoService = new MedicoService();

    public void agendarConsulta(
            String pacienteId,
            String medicoId,
            String dataHora,
            String local,
            double custoBaseMedico) {

        if (checarConflito(dataHora, local)) {
            return ;
        }
        Paciente paciente = pacienteService.buscarPorId(pacienteId);

        double custoFinal = paciente.calcularCustoConsulta(custoBaseMedico);
        String id = "Con-" + UUID.randomUUID().toString().substring(0, 8);

        Consulta novaConsulta = new Consulta(id, pacienteId, medicoId, dataHora, local, "AGENDADA", custoFinal);

        consultaRepository.salvarConsulta(novaConsulta);
        Medico medico = medicoService.buscarPorId(medicoId);
        System.out.println();
        System.out.println("Consulta agendada com o médico: "+ medico.getNome()+ " na data: "+dataHora+", "+local);
    }

    private boolean checarConflito(String novaDataHora,  String novoLocal) {
        List<Consulta> consultasAtivas = consultaRepository.listarTodasAtivas();

        for (Consulta existente : consultasAtivas) {

            // validando o conflito de LOCAL E HORÁRIO
            if (existente.getLocal().equals(novoLocal) &&
                    existente.getDataHora().equals(novaDataHora)) {

                System.out.println("Conflito: O Local " + novoLocal + " já está reservado para esse horário.");
                System.out.println("Essa consulta não foi agendada!");
                return true;
            }
        }
        return false;
    }

    public List<String> gerarGradeHorarios(Medico medico, String data) {
        List<String> todosSlots = new ArrayList<>();
        int intervaloMinutos = 30; // por padrão cada consulta vai durar 30 minutos

        int inicioMinutos = converterParaMinutos(medico.getHorarioInicioTrabalho());
        int fimMinutos = converterParaMinutos(medico.getHorarioFimTrabalho());
        int minutosAtuais = inicioMinutos; // minutosAtuais vai servir como um ponto de partida

        // Loop para criar slots de consulta a cada 30 minutos
        while (minutosAtuais < fimMinutos) {

            int h = minutosAtuais / 60;
            int m = minutosAtuais % 60;
            String horario = String.format("%02d:%02d", h, m);

            // formata para: "AAAA-MM-DD HH: mm"
            todosSlots.add(data + " " + horario);

            minutosAtuais += intervaloMinutos; // Avança para o próximo slot
        }
        return todosSlots;
    }

    private int converterParaMinutos(String horario) {
        try {
            String[] partes = horario.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            return horas * 60 + minutos;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> listarHorariosLivres(Medico medico, String data) {
        List<String> todosSlots = gerarGradeHorarios(medico, data);

        List<Consulta> consultasAtivas = consultaRepository.listarTodasAtivas();

        List<String> horariosOcupados = consultasAtivas.stream().filter(c -> c.getMedicoId().equals(medico.getId()) &&
                c.getDataHora().startsWith(data)).map(Consulta::getDataHora).toList();
        todosSlots.removeAll(horariosOcupados);
        return todosSlots;
    }


    //para adicionar novas salas no sistema basta atualizar essa lista
    public static final List<String> salas = List.of(
            "Consultório 101",
            "Consultório 102",
            "Consultório 103",
            "Consultório 205",
            "Sala de Exames A",
            "Sala de Exames B",
            "Sala 301",
            "Sala 302",
            "Bloco Cirúrgico 501",
            "Consultório 502 ",
            "Sala de internação 132",
            "Sala de internação 133");


    public List<String> listarSalasDisponiveis() {
        return salas;
    }

    //método usado para intermediar o contato entre UI e repositório

    public List<Consulta> listarTodas(){
        return consultaRepository.listarTodas();
    }
}
