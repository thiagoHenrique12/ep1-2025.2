package hospital.ui;

import hospital.services.PacienteService;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private PacienteService pacienteService = new PacienteService();

    public void iniciar() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n========= MENU HOSPITALAR =========");
            System.out.println("1. PACIENTES");
            System.out.println("2. MÉDICOS");
            System.out.println("3. CONSULTAS");
            System.out.println("0. SAIR");
            System.out.print("Selecione uma opção: ");
            op = sc.nextInt(); /*essa parte deve MELHORAR para garantir a analise correta mesmo que usuario nao
                               forneça um int na entrada, de forma que o looping nao pare*/

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
                    System.out.println("opção inválida");
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
                System.out.println("AREA DE CADASTRO...");
                System.out.print("Digite o nome do paciente:");
                String nome = sc.nextLine();
                System.out.print("Digite a idade: " );
                int idade = sc.nextInt();
                sc.nextLine();
                System.out.print("Digite o cpf: ");
                String cpf = sc.nextLine();
                pacienteService.cadastrarPaciente(nome, cpf, idade);

                break;
            case 2:
                System.out.println("AREA PARA LISTAR...");
                break;
            case 0:
                System.out.println("saindo...");
                break;
            default:
                System.out.println("opção inválida");
                /* após o usuário fornecer oq ele deseja, basta chamar um metodo situado em services.PacienteService*/

        }
    }

    public void menuMedicos(){
        System.out.println("\n======= MENU MÉDICOS =======");
        System.out.println("1. CADASTRAR MÉDICO");
    }

}