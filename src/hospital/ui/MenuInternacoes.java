package hospital.ui;

import hospital.entidades.*;
import hospital.services.InternacaoService;
import hospital.services.MedicoService;
import hospital.services.PacienteService;
import hospital.services.QuartoService;
import hospital.utils.InputUtils;

import java.util.List;
import java.util.Scanner;

import static hospital.utils.InputUtils.entradaValida;
import static hospital.utils.InputUtils.validarData;

public class MenuInternacoes {
    private final InternacaoService internacaoService = new InternacaoService();
    private final PacienteService pacienteService = new PacienteService();
    private  final MedicoService medicoService = new MedicoService();
    private final QuartoService quartoService = new QuartoService();

    public void exibirMenu(Scanner sc) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n===== MENU INTERNAÇÕES =====");
            System.out.println("1. INTERNAR NOVO PACIENTE (Check-in)");
            System.out.println("2. DAR ALTA HOSPITALAR (Check-out)");
            System.out.println("3. LISTAR INTERNAÇÕES ATIVAS");
            System.out.println("0. VOLTAR");
            System.out.print("Selecione uma opção: ");
            op = InputUtils.entradaValida(sc);

            switch (op) {
                case 1:
                    menuInternarPaciente(sc);
                    break;
                case 2:
                    menuDarAlta(sc);
                    break;
                case 3:
                    listarInternacoesAtivas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuInternarPaciente(Scanner sc) {
        System.out.println("\n===== CHECK-IN DE PACIENTE =====");

        Paciente paciente = selecionarPaciente(sc);
        if (paciente == null) return;

        Medico medico = selecionarMedico(sc);
        if (medico == null) return;

        Quarto quarto = selecionarQuarto(sc);
        if (quarto == null) {
            System.out.println("Não foi possível internar. Nenhum quarto disponível encontrado.");
            return;
        }

        String dataEntrada;
        do {
            System.out.print("\nDigite a data de entrada (AAAA-MM-DD): ");
            dataEntrada = sc.nextLine();
            dataEntrada = validarData(dataEntrada);
        }
        while (dataEntrada == null);

        internacaoService.internarPaciente(paciente.getId(), medico.getId(), quarto.getId(), dataEntrada);
    }


    private void menuDarAlta(Scanner sc) {
        System.out.println("\n===== CONCEDER ALTA HOSPITALAR =====");

        List<Internacao> ativas = internacaoService.listarInternacoesAtivas();

        if (ativas.isEmpty()) {
            System.out.println("Nenhuma internação ativa no momento para dar alta.");
            return;
        }

        listarInternacoesAtivas();

        int op = -1;
        do {
            System.out.print("\nSelecione o número da internação para dar alta: ");
             op = InputUtils.entradaValida(sc);
            if (op < 1 || op > ativas.size()) {
                System.out.println("Seleção inválida.");
            }
        }
        while (op < 1 || op > ativas.size());

        Internacao internacao = ativas.get(op - 1);

        int dias;
        do {
            System.out.print("Digite a quantidade de dias internados: ");
            dias = InputUtils.entradaValida(sc);
            if (dias <= 0) System.out.println("O número de dias deve ser maior que zero.");
        } while (dias <= 0);


        internacaoService.darAlta(internacao.getId(), dias);
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
            System.out.print("Selecione uma opção: ");
            int op = entradaValida(sc);

            if (op > 0 && op <= lista.size()) {
                return lista.get(op - 1);
            }
            System.out.println("Nenhum paciente encontrado para esse valor. Finalizando internação...");
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
            System.out.print("Selecione uma opção: ");
            int op = entradaValida(sc);

            if (op > 0 && op <= lista.size()) {
                return lista.get(op - 1);
            }
            System.out.println("Médico não encontrado para esse valor. Finalizando...");
        }
        return null;
    }

    private Quarto selecionarQuarto(Scanner sc) {

        List<Quarto> disponiveis = quartoService.listarDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("Todos os quartos estão ocupados no momento.");
            return null;
        }

        System.out.println("\n===== QUARTOS DISPONÍVEIS PARA INTERNAÇÃO =====");

        for (int i = 0; i < disponiveis.size(); i++) {
            Quarto q = disponiveis.get(i);
            System.out.printf("  %d. Quarto %s (Tipo: %s) - Custo: R$ %.2f/dia\n",
                    i + 1, q.getCodigo(), q.getTipo(), q.getCustoDiaria());
        }

        System.out.print("Digite o número do quarto desejado para internação: ");
        int op = InputUtils.entradaValida(sc);

        if (op > 0 && op <= disponiveis.size()) {
            Quarto quartoSelecionado = disponiveis.get(op - 1);
            System.out.printf("\nQuarto %s (%s) selecionado.\n", quartoSelecionado.getCodigo(), quartoSelecionado.getTipo());
            return quartoSelecionado;
        }

        System.out.println("Seleção inválida. Retornando ao menu.");
        return null;
    }


    public void listarInternacoesAtivas(){

        System.out.println("\n===== INTERNAÇÕES ATIVAS NO HOSPITAL =====");
        List<Internacao> ativas = internacaoService.listarInternacoesAtivas();

        if (ativas.isEmpty()) {
            System.out.println("Nenhuma internação ativa registrada no momento.");
            return;
        }

        int c = 1;
        for (Internacao i : ativas) {

            Paciente p = pacienteService.buscarPorId(i.getPacienteId());
            Quarto q = quartoService.buscarPorId(i.getQuartoId());
            Medico m = medicoService.buscarPorId(i.getMedicoId());

            System.out.println();
            System.out.println(c+".");
            System.out.println("Quarto: "+q.getCodigo());
            System.out.println("Nome do paciente: "+p.getNome());
            System.out.println("Nome do médico: "+m.getNome());
            System.out.println("Tipo de quarto: "+ q.getTipo());
            System.out.printf("Valor da diária: R$ %.2f",q.getCustoDiaria());
            c++;
            System.out.println();
        }
    }


}


