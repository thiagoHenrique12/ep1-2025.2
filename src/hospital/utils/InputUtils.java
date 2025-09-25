package hospital.utils;

import java.util.Scanner;

public class InputUtils {
    private static Scanner sc = new Scanner(System.in);

        public static int validarInteiro() {

                try {
                    return Integer.parseInt(sc.nextLine());

                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido ");
                    return -1;
                }
            }
        }

