package hospital.utils;

import java.util.Scanner;

public class InputUtils {
    public static int entradaValida (Scanner sc){
        int entradaValida;

            System.out.print("Selecione uma opção: ");
            entradaValida = validarInteiro(sc);

        return entradaValida;

    }

    public static String validarData(String data) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if (!data.matches(regex)) {
            System.out.println("Formato inválido de data, siga exatamente o modelo e use apenas dígitos");
            return null;
        }
        String[] dataSeparada = data.split("-");
        int mes = Integer.parseInt(dataSeparada[1]);
        int dia = Integer.parseInt(dataSeparada[2]);
        if (dia > 30){
            System.out.println("Data inválida, digite um dia entre 01 e 30");
            return null;
        }
        if (mes > 12){
            System.out.println("Data inválida, digite um mês entre 01 e 12");
            return null;
        }
        return data;
    }

    public static int validarInteiro(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static double validarDouble(Scanner sc){
        try {
            return Double.parseDouble(sc.nextLine());
        }
        catch (NumberFormatException e){
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


