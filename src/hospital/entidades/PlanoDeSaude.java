package hospital.entidades;

public class PlanoDeSaude {
    private String nome;
    private String planoId;
    private double descontoBase;

    public PlanoDeSaude(String nome, String PlanoId, double descontoBase){
        this.nome=nome;
        this.planoId = PlanoId;
        this.descontoBase=descontoBase;
    }

    public double getDescontoBase(){
        return this.descontoBase;
    }

    public String getNome(){
        return nome;
    }
    public String getPlanoId(){       //necessario implementação de um planoId válido
        return this.planoId;
    }

    @Override
    public String toString() { //futuramente será útil na persistência
        return planoId + ";" + nome + ";" + String.format("%.2f", descontoBase);
    }
}
