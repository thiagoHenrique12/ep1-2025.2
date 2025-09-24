package hospital.ui;

import hospital.entidades.Medicos;
import hospital.entidades.Pacientes;
import hospital.services.MedicoService;
import hospital.services.PacienteService;
import hospital.services.PessoaService;

import java.util.Scanner;

import static hospital.services.PessoaService.validarCpf;
import static hospital.services.PessoaService.validarIdade;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private PacienteService pacienteService = new PacienteService();
    private MedicoService medicoService = new MedicoService();
    private final MenuPacientes menuPacientes = new MenuPacientes();


    public void iniciar() {

        int op = -1;
        while (op != 0) {
            System.out.println("\n========= MENU HOSPITALAR =========");
            System.out.println("1. PACIENTES");
            System.out.println("2. MÉDICOS");
            System.out.println("3. CONSULTAS");
            System.out.println("0. SAIR");
            try {
                System.out.print("Selecione uma opção: ");
                op = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                op = -1;
            }
            switch (op){
                case 1:
                    menuPacientes.exibirMenu();
                    break;
                case 2:
                    //menuMedicos();
                    break;
                case 3:

                    break;
                case 0:
                    System.out.println("Saindo ...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente");
                    break;
            }
        }
    }

//
//    public void menuPacientes() {
//        int op = -1;
//        while(op != 0){
//
//            System.out.println("\n======= MENU PACIENTES ========");
//            System.out.println("1. CADASTRAR PACIENTE");
//            System.out.println("2. LISTAR PACIENTE");
//            System.out.println("0. SAIR");
//            System.out.print("Selecione uma opção: ");
//            op = sc.nextInt();
//            sc.nextLine();
//
//            switch (op) {
//                case 1:
//                 op = cadastroPacientes();
//                 if ( op == 3){
//                      op = 0; /* apenas uma conversão, pois optei por usar o valor 3 para representar a opção de voltar
//                                ao menu principal*/
//                 }
//                   break;
//                case 2:
//                    System.out.println("AREA PARA LISTAR...");
//                    for (Pacientes p : pacienteService.listarPacientes()){
//                        System.out.println(p);
//                    }
//                    break;
//                case 0:
//                    System.out.println("saindo...");
//                    break;
//                default:
//                    System.out.println("opção inválida");
//            }
//        }
//    }

    //private int cadastroPacientes() {
//        int resposta = 0;
//        do {
//            System.out.println("======= CADASTRO DE PACIENTES ========");
//            System.out.print("Digite o nome do paciente:");
//            String nome = sc.nextLine();
//            int idade = PessoaService.validarIdade(); //validar idade esta incoerente, necessario separar a validação do input
//
//            String cpf;
//            do{
//                System.out.print("Digite o cpf: ");
//                cpf = sc.nextLine();
//                if(validarCpf(cpf) == null){
//                    System.out.println("cpf inválido, tente novamente");
//                }
//            }
//            while(validarCpf(cpf)==null);
//
//
//            pacienteService.adicionarPaciente(nome, PessoaService.validarCpf(cpf), idade);
//            System.out.println();
//
//            resposta =pCadastroPaciente(); /* aqui o valor retornado pelo método precisou ser
//                                              armazenado em r pois logo abaixo esse
//                                              esse valor será retornado para menuPacientes*/
//        }
//        while (resposta == 1);
//        return resposta;
//    }

        // método auxiliar pós cadastro do pacientes
//    private int pCadastroPaciente(){
//        int resposta= 0;
//        boolean valido = true;
//        while(valido) {
//            System.out.println("""
//                    1. CADASTRAR OUTRO PACIENTE\
//
//                    2. VOLTAR AO MENU DE PACIENTES \
//
//                    3. VOLTAR AO MENU PRINCIPAL""");
//            System.out.print("Selecione uma opção: ");
//            resposta = Integer.parseInt(sc.nextLine());
//            if (resposta != 1 && resposta != 2 && resposta != 3) {
//                System.out.println("Digite uma opção válida.");
//            }
//            else {
//                valido = false;
//            }
//        }
//        return resposta;
//    }
    }
//    private void menuMedicos() {
//        System.out.println("\n======= MENU MÉDICOS =======");
//        System.out.println("1. CADASTRAR MÉDICO");
//        System.out.println("2. LISTAR MÉDICOS");
//        System.out.print("Selecione uma opção:");
//        int op = sc.nextInt();
//        sc.nextLine();
//        switch (op) {
//            case 1:
//                int r = 0;
//                do {
//                    System.out.println("AREA DE CADASTRO...");
//                    System.out.print("Digite o nome do médico:");
//                    String nome = sc.nextLine();
//                    System.out.print("Digite a especialidade: ");
//                    String especialidade = sc.nextLine();
//                    System.out.print("Digite o crm: ");
//                    String crm = sc.nextLine(); //será necessário implementar uma forma de verificar se o crm está no formato adequado
//                    medicoService.cadastrarMedico(nome, crm, especialidade);
//                    System.out.println("1. CADASTRAR OUTRO MÉDICO\n0. SAIR ");
//                    System.out.print("Selecione uma opção: ");
//                    r = sc.nextInt();
//                    sc.nextLine();
//                }
//                while (r == 1);
//                break;
//            case 2:
//                for( Medicos p : medicoService.listarMedicos() ){
//                    System.out.println(p);
//                }
//        }
//    }
