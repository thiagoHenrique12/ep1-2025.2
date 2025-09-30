package hospital.ui;

import hospital.entidades.Paciente;
import hospital.services.PacienteService;

import java.util.Scanner;

import static hospital.services.PacienteService.validarCpf;
import static hospital.utils.InputUtils.validarInteiro;

public class MenuPacientes {

    PacienteService pacienteService = new PacienteService();

    public void exibirMenu(Scanner sc) {
        int op = -1;
        while (op != 0) {

            System.out.println("\n======= MENU PACIENTES ========");
            System.out.println("1. CADASTRAR PACIENTE");
            System.out.println("2. LISTAR PACIENTE");
            System.out.println("0. SAIR");
            System.out.print("Selecione uma opção: ");
            op = validarInteiro(sc);

            switch (op) {
                case 1:
                     //looping para realizar cadastro de novos pacientes
                    do {
                        op = menuCadastrarPaciente(sc);
                    }
                    while (op == 1);
                    break;
                case 2:
                    System.out.println("AREA PARA LISTAR...");
                    for (Paciente p : pacienteService.listarPacientes()) { //fazer um metodo para isso
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

    private int menuCadastrarPaciente(Scanner sc) {
        int resposta = 0;
            System.out.println("======= CADASTRO DE PACIENTES ========");
            System.out.print("Digite o nome do paciente:");
            String nome = sc.nextLine();
            int idadeValidada;
            do {
                System.out.print("Digite a idade: ");
                String idade = sc.nextLine();
                idadeValidada = PacienteService.validarIdade(idade); //validar idade esta incoerente, necessario separar a validação do input
            }
            while(idadeValidada < 0);
            String cpf;
            do {
                System.out.println("O CPF deve estar no formato de 11 dígitos seguidos ou no formato xxx.xxx.xxx-xx.");
                System.out.print("Digite o cpf: ");
                cpf = sc.nextLine();
                cpf = validarCpf(cpf);
                if (cpf == null) {
                    System.out.println("cpf inválido, tente novamente");
                }
            }
            while (cpf == null);

            pacienteService.adicionarPaciente(nome, cpf, idadeValidada);// aquiiiii
            System.out.println();
            return menuPosCadastro(sc);

        }


    private int menuPosCadastro(Scanner sc) {
        int resposta = 0;
        boolean valido = true;
        while (valido) {
            System.out.println("""
                    1. CADASTRAR OUTRO PACIENTE\
                    
                    2. VOLTAR AO MENU DE PACIENTES \
                    
                    0. VOLTAR AO MENU PRINCIPAL""");
            System.out.print("Selecione uma opção: ");
            resposta = Integer.parseInt(sc.nextLine());
            if (resposta != 1 && resposta != 2 && resposta != 0) {
                System.out.println("Digite uma opção válida.");
            } else {
                valido = false;
            }
        }
        return resposta;
    }
}



