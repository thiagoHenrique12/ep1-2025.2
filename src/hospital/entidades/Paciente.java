package hospital.entidades;

public class Paciente {
    private final String nome;
    private final String cpf;
    private final int idade;
    private final String id;

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

    public int getIdade() {
        return idade;
    }

    public double calcularCustoConsulta(double custoBase){
        double descontoIdade = 0;
        if (this.idade>= 60){
            descontoIdade = 0.15; // valor fixo de desconto para idade: 15%
        }
        return custoBase* (1 - descontoIdade);
    }

    public double calcularDescontoInternacao(double custoBase) {
        double descontoIdade = 0;
        if (this.idade >= 60) {
            descontoIdade = 0.15; // desconto fixo
        }
        return custoBase * (1.0 - descontoIdade);
    }

    public String toString() {
        return getId() + ";" + getNome() + ";" + getCpf() + ";" + getIdade();
    }
}
