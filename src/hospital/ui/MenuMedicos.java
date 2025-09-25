package hospital.ui;


import hospital.services.ConsultaService;
import hospital.services.MedicoService;

import java.util.Scanner;

import static hospital.utils.InputUtils.validarInteiro;


public class MenuMedicos {
    private final MedicoService medicoService = new MedicoService();

    public void exibirMenu(Scanner sc){
        int op;
        System.out.println("\n======= MENU MÉDICOS ========");
        System.out.println("1. CADASTRAR MÉDICO");
        System.out.println("2. LISTAR MÉDICOS");
        System.out.println("3teste");
        System.out.print("Selecione uma opção: ");
        op = validarInteiro(sc);
        switch (op){
            case 1:
                menuCadastrarMedico(sc);
                break;
            case 2:
                break;
            case 3:
                // aqui chamaremos o teste improvisado
                ConsultaService scTeste = new ConsultaService();
                scTeste.iniciarTestes();
                break;
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
                System.out.println("CRM inválido. Deve conter apenas números.");
            }
        } while (validarCrm(crm));

        abaEspecialidades(sc);
        return 0;
    }

    public boolean validarCrm(String crm) {
        if (crm == null) return true;
        // Exemplo: 5 a 10 dígitos, apenas números. Ajuste conforme o requisito.
        return !crm.matches("\\d{5,10}");
    }

    public void abaEspecialidades(Scanner sc){
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
        }
    }

