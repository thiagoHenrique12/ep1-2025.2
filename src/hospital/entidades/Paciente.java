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

    public String getNome(){
        return nome;
    }

    public String getId(){
        return id;
    }

    public String getCpf (){
        return cpf;
    }

    public double calcularCustoConsulta(double custoBase){
        double descontoIdade = 0;
        if (this.idade>= 60){
            descontoIdade = 0.15;
            System.out.println("paciente possui desconto por idade");
        }
        return custoBase* (1 - descontoIdade);
    }


    public String toString() {
        return nome +";"+cpf+";"+idade+";"+id;

    }
}
