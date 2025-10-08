package hospital.ui;

import hospital.entidades.Consulta;
import hospital.entidades.Medico;
import hospital.entidades.Paciente;
import hospital.services.ConsultaService;
import hospital.services.MedicoService;
import hospital.services.PacienteService;

import java.util.List;
import java.util.Scanner;

import static hospital.utils.InputUtils.entradaValida;

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
            op = entradaValida(sc);

            switch (op) {
                case 1:
                    menuAgendarConsulta(sc);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("=== CONSULTAS ATIVAS ===");
                    listarConsultas(consultaService.listarTodas());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }


    private void menuAgendarConsulta(Scanner sc) {
        System.out.println("\n     AGENDAMENTO DE CONSULTA ");
        Paciente paciente = selecionarPaciente(sc); // implementar looping para casos gerais de entradas erradas
        if(paciente == null) {
            System.out.println("Não foi possível concluir esse agendamento");
            return;
        }
        Medico medico = selecionarMedico(sc);
        if(medico == null){
            System.out.println("Não foi possível concluir esse agendamento");
            return;
        }
        String data;
        do {
            System.out.print("\nDigite a data da consulta (AAAA-MM-DD): ");
            data = sc.nextLine();
            data = consultaService.validarData(data);
        }
        while (data ==null);

        String local = selecionarLocal(sc);

        String horarioEscolhido = selecionarHorario(sc, medico, data);

        double custoBase = medico.getValorDaConsulta();

        consultaService.agendarConsulta(paciente.getId(), medico.getId(),
                horarioEscolhido, local, custoBase);
    }



    public Paciente selecionarPaciente(Scanner sc) {
        System.out.println("\n===== SELEÇÃO DE PACIENTE =====");
        List<Paciente> lista = pacienteService.listarPacientes();

        if (lista.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado no sistema...");
        }
        else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.printf("%d. %s (CPF: %s)\n", i + 1, lista.get(i).getNome(), lista.get(i).getCpf());
            }

            int op = entradaValida(sc);

            if (op > 0 && op <= lista.size()) {
                return lista.get(op - 1);
            }
            System.out.println("Paciente não encontrado.");
        }
        return null;
    }

    private Medico selecionarMedico(Scanner sc) {
        System.out.println("\n===== SELEÇÃO DE MÉDICO =====");
        List<Medico> lista = medicoService.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum médico cadastrado no sistema...");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.printf("%d. %s (CRM: %s)\n", i + 1, lista.get(i).getNome(), lista.get(i).getCrm());
            }
            int op = entradaValida(sc);

            if (op > 0 && op <= lista.size()) {
                return lista.get(op - 1);
            }
            System.out.println("Médico não encontrado...");
        }
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
            op = entradaValida(sc);
        } while (op < 1 || op > horariosLivresComData.size());

        // vai retornar o slot COMPLETO (com data e hora) para o agendarConsulta
        return horariosLivresComData.get(op - 1);
    }

    public String selecionarLocal(Scanner sc){
        List<String> salas = consultaService.listarSalasDisponiveis();
        System.out.println("\n===== SELEÇÃO DE LOCAL =====");

        for( int i= 0; i< salas.size(); i++){
            System.out.printf(" %d. %s\n",i+1, salas.get(i));
        }
        int op;
        do {
            op = entradaValida(sc);
            if (op < 1 || op > salas.size()){
                System.out.println("Digite um valor entre 1 e "+salas.size());
            }
        }
        while (op < 1 || op > salas.size());
        String salaEscolhida = salas.get((op-1));
        System.out.println("Local escolhido: "+salaEscolhida);
        return salaEscolhida;
    }

    public void listarConsultas(List<Consulta> consultas){
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consultada agendada");
            return;
        }
        int i =1;
        for(Consulta c : consultas){
            System.out.println(i+".");
            Paciente p = pacienteService.buscarPorId(c.getPacienteId());
            Medico m = medicoService.buscarPorId(c.getMedicoId());
            System.out.println("Nome do paciente: "+p.getNome());
            System.out.println("Nome do médico: "+m.getNome());
            System.out.println("Data e hora: "+c.getDataHora());
            System.out.println("Local: "+c.getLocal());
            System.out.printf("Valor final da consulta: R$ %.2f",c.getCustoFinal());
            i++;
            System.out.println();
        }
    }
}
