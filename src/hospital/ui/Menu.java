package hospital.ui;

import java.util.Scanner;

public class Menu {
    // Scanner final usado como parâmetro
    private final Scanner sc = new Scanner(System.in);
    private final MenuPacientes menuPacientes = new MenuPacientes();
    private final MenuMedicos menuMedicos = new MenuMedicos();
    private final MenuPlanos menuPlanos = new MenuPlanos();
    private final MenuConsultas menuConsultas=new MenuConsultas();
    private final MenuInternacoes menuInternacoes = new MenuInternacoes();
    public void iniciar() {

        int op = -1;
        while (op != 0) {
            System.out.println("\n========= MENU HOSPITALAR =========");
            System.out.println("1. PACIENTES");
            System.out.println("2. MÉDICOS");
            System.out.println("3. CONSULTAS");
            System.out.println("4. PLANOS");
            System.out.println("5. INTERNAÇÕES");
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
                    menuPacientes.exibirMenu(sc);
                    break;
                case 2:
                    menuMedicos.exibirMenu(sc);
                    break;
                case 3:
                    menuConsultas.exibirMenu(sc);
                    break;
                case 4:
                    menuPlanos.exibirMenu(sc);
                    break;
                case 5:
                    menuInternacoes.exibirMenu(sc);
                    break;
                case 0:
                    System.out.println("Finalizando o sistema ...");
                    sc.close();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente");
                    break;
            }
        }
    }
}
