package hospital.utils;

import java.util.Scanner;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

        public static int validarInteiro() {
            while (true) {
                try {
                    return Integer.parseInt(sc.nextLine());

                } catch (NumberFormatException e) {
                    System.out.print("Valor inválido, digite novamente: ");
                }
            }
        }
    }

