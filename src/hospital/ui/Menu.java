package hospital.ui;

import hospital.entidades.Medicos;
import hospital.entidades.Pacientes;
import hospital.services.MedicoService;
import hospital.services.PacienteService;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private PacienteService pacienteService = new PacienteService();
    private MedicoService medicoService = new MedicoService();
    private Pacientes paciente = new Pacientes();
    private Medicos medico =new Medicos();

    public void iniciar() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n========= MENU HOSPITALAR =========");
            System.out.println("1. PACIENTES");
            System.out.println("2. MÉDICOS");
            System.out.println("3. CONSULTAS");
            System.out.println("0. SAIR");
            try {
                System.out.print("Selecione uma opção: ");
                op = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                op = -1;
            }

            switch (op){
                case 1:
                    menuPacientes();
                    break;
                case 2:
                    menuMedicos();
                    break;
                case 3:

                    break;
                case 0:
                    System.out.println("Saindo ...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente");
                    break;
            }
        }
    }

    public void menuPacientes() {
        int op;

        System.out.println("\n======= MENU PACIENTES ========");
        System.out.println("1. CADASTRAR PACIENTE");
        System.out.println("2. LISTAR PACIENTE");
        System.out.println("0. SAIR");
        System.out.print("Selecione uma opção: ");
        op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
               cadastroPacientes();
               break;
            case 2:
                System.out.println("AREA PARA LISTAR...");
                for (Pacientes p : pacienteService.listarPacientes()){
                    System.out.println(p);
                }
                break;
            case 0:
                System.out.println("saindo...");
                break;
            default:
                System.out.println("opção inválida");
        }
    }
    private void cadastroPacientes(){
        int r = 0;
        do {
            System.out.println("======= CADASTRO DE PACIENTES ========");
            System.out.print("Digite o nome do paciente:");
            String nome = sc.nextLine();
            int idade =0;
            do{ // esse looping é responsável para solicitar uma idade válida
                r=0;
                try {
                    System.out.print("Digite a idade: ");
                     idade = Integer.parseInt(sc.nextLine());
                }
                catch (NumberFormatException e){
                    System.out.println("erro: valor para idade inválido, tente novamente");
                    r = -1;
                }
            }
            while (r ==-1);
            System.out.print("Digite o cpf: ");
            String cpf = sc.nextLine(); //será necessário implementar uma forma de verificar se o cpf está no formato adequado
            pacienteService.adicionarPaciente(nome, cpf, idade);
            System.out.println();
            System.out.println("""
                    1. CADASTRAR OUTRO PACIENTE\
                    
                    2. VOLTAR AO MENU PRINCIPAL \
                    
                    3. VOLTAR AO MENU DE PACIENTES""");
            r = sc.nextInt();
            sc.nextLine();
        }
        while (r == 1); //metodo provisório de verificar se o usario deseja continuar cadastrando pacientes
    }

    private void menuMedicos() {
        System.out.println("\n======= MENU MÉDICOS =======");
        System.out.println("1. CADASTRAR MÉDICO");
        System.out.println("2. LISTAR MÉDICOS");
        System.out.print("Selecione uma opção:");
        int op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:
                int r = 0;
                do {
                    System.out.println("AREA DE CADASTRO...");
                    System.out.print("Digite o nome do médico:");
                    String nome = sc.nextLine();
                    System.out.print("Digite a especialidade: ");
                    String especialidade = sc.nextLine();
                    System.out.print("Digite o crm: ");
                    String crm = sc.nextLine(); //será necessário implementar uma forma de verificar se o crm está no formato adequado
                    medicoService.cadastrarMedico(nome, crm, especialidade);
                    System.out.println("1. CADASTRAR OUTRO MÉDICO\n0. SAIR ");
                    System.out.print("Selecione uma opção: ");
                    r = sc.nextInt();
                    sc.nextLine();
                }
                while (r == 1);
                break;
            case 2:
                for( Medicos p : medicoService.listarMedicos() ){
                    System.out.println(p);
                }
        }
    }
}