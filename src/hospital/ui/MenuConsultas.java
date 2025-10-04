package hospital.ui;

import hospital.entidades.Medico;
import hospital.entidades.Paciente;
import hospital.services.ConsultaService;
import hospital.services.MedicoService;
import hospital.services.PacienteService;
import hospital.utils.InputUtils;

import java.util.List;
import java.util.Scanner;

public class MenuConsultas{
    private final ConsultaService consultaService = new ConsultaService();
    private final PacienteService pacienteService = new PacienteService();
    private final MedicoService medicoService = new MedicoService();

    public void exibirMenu(Scanner sc) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n===== MENU DE CONSULTAS =====");
            System.out.println("1. AGENDAR NOVA CONSULTA");
            System.out.println("2. LISTAR CONSULTAS FUTURAS");
            System.out.println("0. VOLTAR");
            System.out.print("Selecione uma opção: ");
            op = InputUtils.entradaValida(sc);

            switch (op) {
                case 1:
                    menuAgendarConsulta(sc);
                    break;
                case 2:
                    // implementar lógica para listar consultas
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }


    private void menuAgendarConsulta(Scanner sc) {
        System.out.println("\n===== AGENDAMENTO DE CONSULTA =====");
        Paciente paciente = selecionarPaciente(sc); // implementar looping para casos gerais de entradas erradas

        Medico medico = selecionarMedico(sc);


        System.out.print("Digite a data da consulta (AAAA-MM-DD): "); // fazer um metodo de validação de data
        String data = sc.nextLine();

        System.out.print("Digite o local/consultório: "); // implementar uma lista de salas futuramente disponíveis
        String local = sc.nextLine();


        String horarioEscolhido = selecionarHorario(sc, medico, data);

        // OBS: Você precisa garantir que o PacienteService e MedicoService tenham buscarPorId
        // e que Medico tenha o getter para valorDaConsulta.

        assert medico != null;
        double custoBase = medico.getValorDaConsulta();

        assert paciente != null;
        consultaService.agendarConsulta(paciente.getId(), medico.getId(),
                data + " " + horarioEscolhido, local, custoBase);

        System.out.println("Consulta agendada com sucesso para " + paciente.getNome() + ".");
    }



    private Paciente selecionarPaciente(Scanner sc) {
        System.out.println("===== SELEÇÃO DE PACIENTE =====");
        List<Paciente> lista = pacienteService.listarPacientes();

        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%d. %s (CPF: %s)\n", i + 1, lista.get(i).getNome(), lista.get(i).getCpf());
        }

        System.out.print("Escolha o número do paciente: ");  //fazer caso para quando não houver paciente
        int op = InputUtils.entradaValida(sc);

        if (op > 0 && op <= lista.size()) {
            return lista.get(op - 1);
        }
        System.out.println("Paciente não encontrado.");
        return null;
    }

    private Medico selecionarMedico(Scanner sc) {
        System.out.println("===== SELEÇÃO DE MÉDICO =====");
        List<Medico> lista = medicoService.listarMedicos();

        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%d. %s (CRM: %s)\n", i + 1, lista.get(i).getNome(), lista.get(i).getCrm());
        }
        System.out.print("Escolha o número do médico: ");
        int op = InputUtils.entradaValida(sc);

        if (op > 0 && op <= lista.size()) {
            return lista.get(op - 1);
        }
        System.out.println("Médico não encontrado.");
        return null;
    }


    private String selecionarHorario(Scanner sc, Medico medico, String data) {
        List<String> horariosLivresComData = consultaService.listarHorariosLivres(medico, data);

        if (horariosLivresComData.isEmpty()) {
            System.out.println("Nenhum horário livre encontrado para " + data + ".");
            return null;
        }

        System.out.println("\n===== HORÁRIOS LIVRES em " + data + " =====");

        for (int i = 0; i < horariosLivresComData.size(); i++) {
            String slotCompleto = horariosLivresComData.get(i);
            String horario = slotCompleto.substring(slotCompleto.lastIndexOf(" ") + 1);
            System.out.printf("  %d. %s\n", i + 1, horario);
        }

        int op;
        do {
            System.out.println("Digite o número do horário desejado ");
            op = InputUtils.entradaValida(sc);
        } while (op < 1 || op > horariosLivresComData.size());

        // vai retornar o slot COMPLETO (com data e hora) para o agendarConsulta
        return horariosLivresComData.get(op - 1);
    }
}
