package hospital.ui;


import hospital.entidades.Medico;
import hospital.services.MedicoService;

import java.util.List;
import java.util.Scanner;

import static hospital.utils.InputUtils.*;


public class MenuMedicos {
    private final MedicoService medicoService = new MedicoService();

    public void exibirMenu(Scanner sc) {
        int op=-1;
        while (op != 0) {
            System.out.println("\n======= MENU MÉDICOS ========");
            System.out.println("1. CADASTRAR MÉDICO");
            System.out.println("2. LISTAR MÉDICOS");
            System.out.println("0. VOLTAR");
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
                    System.out.println("=== LISTA DE MÉDICOS ===");
                    listarMedicos(medicoService.listarTodos());
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
            System.out.print("Digite o CRM ( 5 dígitos numéricos): ");
            crm = sc.nextLine();
            if (medicoService.validarCrm(crm)) {
                System.out.println("CRM inválido. Deve conter 5 dígitos numéricos.");
            }
        } while (medicoService.validarCrm(crm));

        String especialidade= abaEspecialidades(sc);
        System.out.println();
        double preco;
        do {
            System.out.print("Digite o preço da consulta: R$");
            preco = validarDouble(sc);
            if(preco < 0) System.out.println("Preço inválido, tente novamente");
        }
        while (preco < 0);

        String horarioInicio;
        System.out.println("\nOs horários devem estar no formato: XX:XX");
        do {
            System.out.print("Digite o horário do início do expediente: ");
            horarioInicio = sc.nextLine();
            horarioInicio=medicoService.validarHorario(horarioInicio);
        }
        while (horarioInicio == null);


        String horarioFinal;
        do {
            System.out.print("Digite o horário de término do expediente: ");
            horarioFinal =sc.nextLine();
            horarioFinal = medicoService.validarHorario(horarioFinal);

            if (horarioFinal!= null){
                String[] partesI = horarioInicio.split(":");
                String[] partesF = horarioFinal.split(":");
                //conferindo se as horas do horario final vem depois do horário inicial:
                if (Integer.parseInt(partesI[0]) > Integer.parseInt(partesF[0])){
                    System.out.println("Horário inválido, expediente começando às "+horarioInicio);
                    horarioFinal = null;
                }
                if (Integer.parseInt(partesI[0]) == Integer.parseInt(partesF[0]) && Integer.parseInt(partesI[1]) >= Integer.parseInt(partesF[0])){
                    System.out.println("Horário inválido, expediente começando às "+horarioInicio);
                    horarioFinal = null;
                }
            }
        }
        while (horarioFinal ==null);
        System.out.println();
        medicoService.cadastrarMedico(nome,crm, especialidade, preco, horarioInicio, horarioFinal);
        System.out.println();

        return abaPosCadastro(sc);
    }



    public String abaEspecialidades(Scanner sc){
        String especialidade;
        System.out.println();
        System.out.println("=== DEFINA UMA ESPECIALIDADE ===");
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
                    === MENU PÓS CADASTRO ===\
                    
                    1. CADASTRAR OUTRO MÉDICO\
                    
                    2. VOLTAR AO MENU DE MÉDICOS \
                    
                    0. VOLTAR AO MENU PRINCIPAL""");
                resposta = entradaValida(sc);
                if (resposta == 1 || resposta == 2 || resposta == 0) {
                    valido = false;
                }
            }
            return resposta;
        }

        public void listarMedicos(List<Medico> medicos){
            if (medicos.isEmpty()) System.out.println("Nenhum médico foi cadastrado");

            int i=1;
            for(Medico m : medicos){
                System.out.println(i+".");
                System.out.println("Nome: "+m.getNome());
                System.out.println("CRM: "+m.getCrm());
                System.out.println("Especialidade: "+m.getEspecialidade());
                System.out.println();
                i++;
            }

        }
    }


