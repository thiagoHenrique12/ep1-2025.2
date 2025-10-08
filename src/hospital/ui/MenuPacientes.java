package hospital.ui;

import hospital.entidades.Paciente;
import hospital.entidades.PacienteEspecial;
import hospital.entidades.PlanoDeSaude;
import hospital.services.PacienteService;
import hospital.services.PlanoSaudeService;


import java.util.List;
import java.util.Scanner;

import static hospital.services.PacienteService.validarCpf;
import static hospital.utils.InputUtils.entradaValida;
import static hospital.utils.InputUtils.validarInteiro;

public class MenuPacientes {

    PacienteService pacienteService = new PacienteService();
    PlanoSaudeService planoService = new PlanoSaudeService();
    public void exibirMenu(Scanner sc) {
        int op = -1;
        while (op != 0) {

            System.out.println("\n======= MENU PACIENTES ========");
            System.out.println("1. CADASTRAR PACIENTE");
            System.out.println("2. LISTAR PACIENTE");
            System.out.println("0. SAIR");
            int entradaValida;
            do {
                System.out.print("Selecione uma opção: ");
                entradaValida = validarInteiro(sc);

                if (entradaValida == -1) {
                    System.out.println("Entrada inválida.");
                }
            } while (entradaValida == -1);
            op = entradaValida;
            switch (op) {
                case 1:
                     //looping para realizar cadastro de novos pacientes
                    do {
                        op = menuCadastrarPaciente(sc);
                    }
                    while (op == 1);
                    break;
                case 2:
                    System.out.println("=== LISTA DE PACIENTES ===");
                    listarPacientes(pacienteService.listarPacientes());

                    break;
                case 0:
                    System.out.println("saindo...");
                    break;
                default:
                    System.out.println("opção inválida");
            }
        }
    }

    private int menuCadastrarPaciente(Scanner sc) {
        System.out.println("======= CADASTRO DE PACIENTES ========");
        System.out.print("Digite o nome do paciente:");
        String nome = sc.nextLine();
        int idadeValidada;
        do {
            System.out.print("Digite a idade: ");
            String idade = sc.nextLine();
            idadeValidada = PacienteService.validarIdade(idade);
        }
        while(idadeValidada < 0);
        String cpf;
        do {
            System.out.println("O CPF deve estar no formato de 11 dígitos seguidos ou no formato xxx.xxx.xxx-xx.");
            System.out.print("Digite o cpf: ");
            cpf = sc.nextLine();
            cpf = validarCpf(cpf);
            if (cpf == null) {
                System.out.println("cpf inválido, tente novamente");
            }
        }
        while (cpf == null);

        PlanoDeSaude plano= verificarPlano(sc);
        pacienteService.adicionarPaciente(nome, cpf, idadeValidada,plano );

        return menuPosCadastro(sc);

    }

    private int menuPosCadastro(Scanner sc) {
        int resposta = 0;
        boolean valido = true;
        while (valido) {
            System.out.println("""
                    === MENU PÓS CADASTRO === \
                    
                    1. CADASTRAR OUTRO PACIENTE \
                    
                    2. VOLTAR AO MENU DE PACIENTES \
                    
                    0. VOLTAR AO MENU PRINCIPAL""");

            resposta =entradaValida(sc);
            if (resposta != 1 && resposta != 2 && resposta != 0) {
                System.out.println("Digite uma opção válida.\n");
            } else {
                valido = false;
            }
        }
        return resposta;
    }

    public PlanoDeSaude verificarPlano(Scanner sc) {
        System.out.println("O paciente possui plano de saúde?");
        System.out.println("1. SIM \n2. NÃO");
        int entrada;
        do {
            entrada = entradaValida(sc);
            if (entrada !=1 && entrada!=2){
                System.out.println("Escolha entre 1 e 2");
            }
        } while (entrada != 1 && entrada != 2);

        if (entrada == 1) {       //listando os planos
            List<PlanoDeSaude> planosDisponiveis = planoService.listarTodos();
            System.out.println("\n===== SELEÇÃO DE PLANO =====");
            for (int i = 0; i < planosDisponiveis.size(); i++) {
                PlanoDeSaude p = planosDisponiveis.get(i);
                System.out.printf("  %d. %s (Desconto: %.0f%%)\n", i + 1, p.getNome(), p.getDescontoBase() * 100);
            }
            int op;
            do {
                op = entradaValida(sc);
                if (op < 1 || op > planosDisponiveis.size()) {
                    System.out.println("Opção indisponível, digite um número de 1 até " + planosDisponiveis.size());
                    op = -1;
                }
            } while (op == -1);
            return planosDisponiveis.get(op-1);
        } else {
            return null;
        }
    }

    public void listarPacientes(List<Paciente> pacientes){
        if (pacientes.isEmpty()){
            System.out.println("Nenhum paciente foi cadastrado");
            return;
        }
        int i=1;
        for (Paciente p : pacientes){
            System.out.println(i+".");
            System.out.println("Nome: "+p.getNome());
            System.out.println("Idade: "+p.getIdade());
            System.out.println("CPF: "+p.getCpf());
            if(p instanceof PacienteEspecial pEsp){
                System.out.println("Plano de saúde: "+pEsp.getPlano().getNome());
            }
            else System.out.println("Não possui plano");
            i++;
            System.out.println();
        }

    }
}



