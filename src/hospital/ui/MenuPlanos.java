package hospital.ui;

import hospital.entidades.PlanoDeSaude;
import hospital.services.PlanoSaudeService;

import java.util.Scanner;

import static hospital.utils.InputUtils.lerPorcentagem;
import static hospital.utils.InputUtils.validarInteiro;

public class MenuPlanos {
    private final PlanoSaudeService planoService = new PlanoSaudeService();

    public void exibirMenu(Scanner sc ){
        int op = -1;
        while (op !=0) {
            System.out.println("\n====== MENU DE PLANOS ======");
            System.out.println("1. CADASTRAR UM PLANO");
            System.out.println("2. LISTAR PLANOS EXISTENTES");
            System.out.println("0. SAIR");
            System.out.print("Selecione uma opção: ");
            int entradaValida;
            do {
                System.out.print("Selecione uma opção: ");
                entradaValida = validarInteiro(sc);

                if (entradaValida == -1) {
                    System.out.println("Entrada inválida. Digite apenas números.");
                }
            } while (entradaValida == -1);

            op = entradaValida;
            switch (op) {
                case 1:
                    menuCadastrarPlano(sc);
                    break;
                case 2:
                    System.out.println("AREA PARA LISTAR...");
                    for (PlanoDeSaude p : planoService.listarTodos()) { //fazer um metodo para isso e aplicar o tostring
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
    }

    public void menuCadastrarPlano(Scanner sc){
        System.out.println("======= CADASTRO DE PLANOS ========");
        System.out.print("Digite o nome do plano:");
        String nome = sc.nextLine();
        double desconto;
        do { // forma de verificar se o valor para porcentagem é válido
             desconto = lerPorcentagem(sc);
        }while (desconto == -1);
        desconto= desconto / 100; //convertendo a porcentagem
        planoService.cadastrarPlano(nome, desconto);
    }
}
