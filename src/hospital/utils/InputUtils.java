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
        }

