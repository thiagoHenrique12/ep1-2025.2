package hospital.ui;

import hospital.entidades.Pacientes;
import hospital.services.PacienteService;
import hospital.services.PessoaService;

import java.util.Scanner;

import static hospital.services.PessoaService.validarCpf;
import static hospital.utils.InputUtils.validarInteiro;

public class MenuPacientes {
    Scanner sc = new Scanner(System.in);
    PacienteService pacienteService = new PacienteService();

    public void exibirMenu() {
        int op = -1;
        while (op != 0) {

            System.out.println("\n======= MENU PACIENTES ========");
            System.out.println("1. CADASTRAR PACIENTE");
            System.out.println("2. LISTAR PACIENTE");
            System.out.println("0. SAIR");
            System.out.print("Selecione uma opção: ");
            op = validarInteiro();

            switch (op) {
                case 1:
                     //looping para realizar cadastro de novos pacientes
                    do {
                        op = menuCadastrarPaciente();
                    }
                    while (op == 1);
                    break;
                case 2:
                    System.out.println("AREA PARA LISTAR...");
                    for (Pacientes p : pacienteService.listarPacientes()) { //fazer um metodo para isso
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

    private int menuCadastrarPaciente() {
        int resposta = 0;
            System.out.println("======= CADASTRO DE PACIENTES ========");
            System.out.print("Digite o nome do paciente:");
            String nome = sc.nextLine();
            int idade = PessoaService.validarIdade(); //validar idade esta incoerente, necessario separar a validação do input

            String cpf;
            do {
                System.out.print("Digite o cpf: ");
                cpf = sc.nextLine();
                if (validarCpf(cpf) == null) {
                    System.out.println("cpf inválido, tente novamente");
                }
            }
            while (validarCpf(cpf) == null);

            pacienteService.adicionarPaciente(nome, PessoaService.validarCpf(cpf), idade);
            System.out.println();
            return menuPosCadastro();

        }


    private int menuPosCadastro() {
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



