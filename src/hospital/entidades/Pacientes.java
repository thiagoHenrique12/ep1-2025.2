package hospital.entidades;

public class Pacientes {
    private String nome;
    private String cpf;
    private int idade;
    private String id;


    public Pacientes(String id, String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
        this.idade= idade;
    }
}
