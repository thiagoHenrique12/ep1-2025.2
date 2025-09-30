package hospital.entidades;

public class PlanoDeSaude {
    private String nome;
    private String PlanoId;
    private double descontoBase;

    PlanoDeSaude(String nome, String PlanoId, double descontoBase){
        this.nome=nome;
        this.PlanoId = PlanoId;
        this.descontoBase=Math.max(0,Math.min(1.0,descontoBase));
        //para evitar problemas atribuí uma de forma do valor do desconto estar entre 0 e 100 %
    }
    public double getDescontoBase(){
        return this.descontoBase;
    }
    public String getPlanoId(){       //necessario implementação de um PlanoId válido
        return this.PlanoId;
    }
    @Override
    public String toString() { //futuramente será útil na persistência
        return PlanoId + ";" + nome + ";" + String.format("%.2f", descontoBase);
    }
}
