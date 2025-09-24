package hospital.services;

import java.util.Scanner;

public class PessoaService {
    private static Scanner sc = new Scanner(System.in);

    public static int validarIdade(){
        while(true){
            try {
                System.out.print("Digite a idade: ");
               int idade= Integer.parseInt(sc.nextLine());
               if (idade < 0){
                   System.out.println("Idade inválida, tente novamente");
               }
               else{
                   return idade;
               }
            }
            catch (NumberFormatException e){
                System.out.println("Idade inválida, tente novamente ");
            }
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

