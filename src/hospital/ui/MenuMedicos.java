package hospital.ui;


import hospital.services.ConsultaService;
import hospital.services.MedicoService;

import java.util.Scanner;

import static hospital.utils.InputUtils.validarInteiro;


public class MenuMedicos {
    private final MedicoService medicoService = new MedicoService();

    public void exibirMenu(Scanner sc) {
        int op=-1;
        while (op != 0) {
            System.out.println("\n======= MENU MÉDICOS ========");
            System.out.println("1. CADASTRAR MÉDICO");
            System.out.println("2. LISTAR MÉDICOS");
            System.out.println("3teste");
            System.out.print("Selecione uma opção: ");
            op = validarInteiro(sc);
            switch (op) {
                case 1:
                    do {
                        op = menuCadastrarMedico(sc);
                    }
                    while(op ==1);
                    break;
                case 2:
                    break;
                case 3:

                case 0:
                    System.out.println("saindo...");
                    break;
                default:
                    System.out.println("opção inválida");
            }
        }
    }
    public int menuCadastrarMedico(Scanner sc){
        System.out.println("=== CADASTRANDO MÉDICO ===");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        String crm;
        do {
            System.out.print("Digite o CRM (apenas números): ");
            crm = sc.nextLine();
            if (validarCrm(crm)) {
                System.out.println("CRM inválido. Deve conter 5 dígitos numéricos.");
            }
        } while (validarCrm(crm));

        String especialidade= abaEspecialidades(sc);
        System.out.println();
        System.out.println("Digite o horário do início do expediente: ");
        String horarioInicio =sc.nextLine();
        validarHorario(horarioInicio);  // necessário ainda implementar lógica para valores validos de horario
        return abaPosCadastro(sc);
    }


    public boolean validarCrm(String crm) {
        if (crm == null) {
            return true; // esse true serve para ficar no looping while enquanto o crm for null
        }
        return !crm.matches("\\d{5}");
    }

    public boolean validarHorario(String horario){
        if (horario == null){
            return true;
        }
        return !horario.matches("\\d{2}:\\d{2}");
    }

    public String abaEspecialidades(Scanner sc){
        String especialidade;
        System.out.println();
        System.out.println("DEFINA UMA ESPECIALIDADE");
        System.out.println("""
                1. CARDIOLOGISTA
                2. UROLOGISTA
                3. ORTOPEDISTA
                4. CLÍNICO GERAL
                5. DERMATOLOGISTA
                6. CIRURGIÃO""");
        do {
            System.out.print("Digite uma opção:");
            int op = validarInteiro(sc);
            especialidade = medicoService.validarEspecialidade(op);
        }
        while (especialidade== null);
        return especialidade;
        }


    public int abaPosCadastro(Scanner sc){

            int resposta = 0;
            boolean valido = true;
            while (valido) {
                System.out.println("""
                    1. CADASTRAR OUTRO MÉDICO\
                    
                    2. VOLTAR AO MENU DE MÉDICOS \
                    
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


