package hospital.ui;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private final MenuPacientes menuPacientes = new MenuPacientes();


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
                    menuPacientes.exibirMenu();
                    break;
                case 2:
                    //menuMedicos();
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
}
//    private void menuMedicos() {
//        System.out.println("\n======= MENU MÉDICOS =======");
//        System.out.println("1. CADASTRAR MÉDICO");
//        System.out.println("2. LISTAR MÉDICOS");
//        System.out.print("Selecione uma opção:");
//        int op = sc.nextInt();
//        sc.nextLine();
//        switch (op) {
//            case 1:
//                int r = 0;
//                do {
//                    System.out.println("AREA DE CADASTRO...");
//                    System.out.print("Digite o nome do médico:");
//                    String nome = sc.nextLine();
//                    System.out.print("Digite a especialidade: ");
//                    String especialidade = sc.nextLine();
//                    System.out.print("Digite o crm: ");
//                    String crm = sc.nextLine(); //será necessário implementar uma forma de verificar se o crm está no formato adequado
//                    medicoService.cadastrarMedico(nome, crm, especialidade);
//                    System.out.println("1. CADASTRAR OUTRO MÉDICO\n0. SAIR ");
//                    System.out.print("Selecione uma opção: ");
//                    r = sc.nextInt();
//                    sc.nextLine();
//                }
//                while (r == 1);
//                break;
//            case 2:
//                for( Medicos p : medicoService.listarMedicos() ){
//                    System.out.println(p);
//                }
//        }
//    }
