package hospital.services;

import java.util.Scanner;

public class PessoaService {
    private static Scanner sc = new Scanner(System.in);

    public static int validarIdade(String idade){
            try {
                int idadeValidada= Integer.parseInt(idade);

               if ( idadeValidada< 0){
                   System.out.println("Idade inválida, tente novamente");
                   return -1;
               }
               else{
                   return idadeValidada;
               }
            }
            catch (NumberFormatException e){
                System.out.println("Idade inválida, tente novamente ");
                return -1;
            }
        }



    public static String validarCpf(String cpf){
        if (cpf == null) return null;

        //
        String cpfSemFormato = "\\d{11}"; // 11 dígitos inteiros

        String cpfFormatado = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

        // Se não corresponder a nenhum dos dois formatos, retorna falso
        if (!cpf.matches(cpfSemFormato) && !cpf.matches(cpfFormatado)) {
            return null;
        }

        //caso esteja formatado, remove a pontuação para padronizar
        String cpfNumerico = cpf.replaceAll("\\D", "");
        return cpfNumerico;
    }
}

