package hospital.utils;

import java.util.Scanner;

public class InputUtils {
//    private static Scanner sc = new Scanner(System.in);

    public static int validarInteiro(Scanner sc) {
    //esse método serve para garantir que usuário irá fornecer apenas números
        try {
            return Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static double lerPorcentagem(Scanner sc) {
        double valor;
        System.out.print("Digite a porcentagem de desconto (0 a 100%): ");

        try {
            valor = Double.parseDouble(sc.nextLine());
            if (valor < 0 || valor > 100) {
                System.out.println("Valor inválido! O desconto deve estar entre 0 e 100.");
                valor = -1.0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite apenas números.");
            valor = -1.0; //
        }
        return valor;
    }
}


