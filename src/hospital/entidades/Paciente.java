package hospital.entidades;

public class Paciente {
    private String nome;
    private String cpf;
    private int idade;
    private String id;

    public Paciente(){

    }
    public Paciente(String id, String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
        this.idade= idade;
    }


    public String toString() {
        return nome +";"+cpf+";"+idade+";"+id;

    }
}
