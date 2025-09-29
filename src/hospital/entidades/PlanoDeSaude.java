package hospital.entidades;

public class PlanoDeSaude {
    private String nome;
    private String id;
    private double descontoBase;

    PlanoDeSaude(String nome, String id, double descontoBase){
        this.nome=nome;
        this.id = id;
        this.descontoBase=Math.max(0,Math.min(1.0,descontoBase));
        //para evitar problemas atribuí uma de forma do valor do desconto estar entre 0 e 100 %
    }
    public double getDescontoBase(){
        return this.descontoBase;
    }
    public String getId(){       //necessario implementação de um método para gerar um id válido
        return this.id;
    }
    @Override
    public String toString() { //futuramente será útil na persistência
        return id + ";" + nome + ";" + String.format("%.2f", descontoBase);
    }
}
